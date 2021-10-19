package app.transacs;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utilities.AppConstants;
import utilities.AppUtility;
import database.DMLBase;
import database.DefQueries;
import app.DefResultSet;
import app.Definition;
import app.beans.GlVoucherTemplateDet;
import app.beans.GlVoucherTemplateMf;
import app.beans.LoginEmployee;
import app.beans.hibernate.HGlVoucherTemplateDet;
import app.beans.hibernate.HGlVoucherTemplateMf;

public class VoucherTemplateMFTransacs implements Definition
{

	public VoucherTemplateMFTransacs()
	{
		
	}

	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee)throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			glVoucherTemplateMFBeans = new GlVoucherTemplateMf[v.size()];
			v.toArray(glVoucherTemplateMFBeans);
			defResultSet.setGlVoucherTemplateMfBeans(glVoucherTemplateMFBeans);
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
			if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findGlVoucherTemplateMfQuery);
			}
			
			List<HGlVoucherTemplateMf> resultlist = query.list();
			System.out.println("GL Voucher data found: " + resultlist.size());

			for (HGlVoucherTemplateMf bean : resultlist)
			{
				GlVoucherTemplateMf temp = new GlVoucherTemplateMf();
				temp.setPost(bean.getPost());
				temp.setLogID(bean.getLogID());
				temp.setTypeShort(bean.getTypeShort());
				temp.setTemplateID(bean.getTemplateID());
				temp.setDescription(bean.getDescription());
				temp.setTemplateName(bean.getTemplateName());
				temp.setBookTypeShort(bean.getBookTypeShort());
				
//				Query query1 = session.createQuery("from HGlVoucherTemplateDet  where template_id='"+bean.getTemplateID()+"'");
//            	List<HGlVoucherTemplateDet> resultContactList = query1.list();
				
				temp.setGlVoucherTemplateDet(cloneDetail(bean.getGlVoucherTemplateDet()));

				v.add(temp);
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

	public Set<GlVoucherTemplateDet> cloneDetail(Set<HGlVoucherTemplateDet> temp)
	{
		Set<GlVoucherTemplateDet> list = new HashSet<GlVoucherTemplateDet>();
		
		for (HGlVoucherTemplateDet t : temp)
		{
			GlVoucherTemplateDet det = new GlVoucherTemplateDet();
			det.setDrCr(t.getDrCr());
			det.setGlLineNo(t.getGlLineNo());
			det.setAmount(t.getAmount());
			det.setTemplateID(t.getGlVoucherTemplateMf().getTemplateID());
			det.setGlMfCode(t.getGlMfCode());
			det.setGlSlType(t.getGlSlType());
			det.setNarration(t.getNarration());
			det.setEmployeeID(t.getEmployeeID());
			det.setDmlType("U");
			list.add(det);
		}
		
		return list;
	}
	
	
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private GlVoucherTemplateMf[] glVoucherTemplateMFBeans;
	private Map<String, Object> parameters = new HashMap<String, Object>();
	private Vector<GlVoucherTemplateMf> v = new Vector<GlVoucherTemplateMf>();
	
}
