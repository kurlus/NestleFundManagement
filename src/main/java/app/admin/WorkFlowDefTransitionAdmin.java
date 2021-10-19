package app.admin;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import utilities.AppUtility;
import utilities.AppConstants;

import app.beans.WfMF;
import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import app.beans.WfDetail;
import database.DefQueries;
import app.beans.LoginEmployee;

import app.beans.hibernate.HWfDetail;
import app.beans.hibernate.HWfMF;

public class WorkFlowDefTransitionAdmin implements Definition
{
	public WorkFlowDefTransitionAdmin()
	{
		
	}

	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			wfMfBeans = new WfMF[v.size()];
			v.toArray(wfMfBeans);
			defResultSet.setWfMfBeans(wfMfBeans);
		}
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		return defResultSet;
	}

	public LoginEmployee getLoginEmployee()
	{
		return loginEmployee;
	}

	public void setLoginEmployee(LoginEmployee loginEmployee)
	{
		this.loginEmployee = loginEmployee;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private void setListData(Object[] listOfObjs)
	{
	}

	private void execute() throws MappingException, IOException, Exception
	{
		Query query = null;

		if (sessionFactory == null)
			sessionFactory = (SessionFactory) AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);

		Session session = sessionFactory.openSession();

		try
		{
			
			List<HWfMF> resultlist = session.createQuery(DefQueries.findWfMfQuery).list();
			
			System.out.println("Work flow MF data: " + resultlist.size());

			for (HWfMF bean : resultlist)
			{
				WfMF parent = new WfMF();
				
				parent.setActionID(bean.getActionID());
				parent.setEventType(bean.getEventTypeId());
				parent.setWorkFlowId(bean.getWorkFlowId());
				parent.setWorkFlowName(bean.getWorkFlowName());
				
				List<HWfDetail> resultDetList = session.createQuery(DefQueries.findWfDetQuery+bean.getWorkFlowId()).list();
				if(resultDetList!= null && resultDetList.size() > 0)
				{
					parent.setWfTransitions(new ArrayList<WfDetail>());
					for(HWfDetail det: resultDetList)
					{
						WfDetail child = new WfDetail();
						
						child.setWfDetailID(det.getWfDetailID());
						child.setWorkFlowId(det.getWorkFlowMF().getWorkFlowId());
						child.setFromTransition(det.getFromTransition());
						child.setToTransition(det.getToTransition());
						child.setWorkFlowSeq(det.getWorkFlowSeq());
						child.setIsMemberEmail(det.getIsMemberEmail());
						child.setMemberEmail(det.getIsMemberEmail()==1 ? true: false);
						parent.getWfTransitions().add(child);
					}
				}	

				v.add(parent);
			}

		} catch (RuntimeException e)
		{
			AppUtility.logger.log(Level.SEVERE, "loading data failed =" + e.getMessage());
			throw e;

		} finally
		{
			session.close();
		}
	}

	private WfMF[] wfMfBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<WfMF> v = new Vector<WfMF>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
	
}
