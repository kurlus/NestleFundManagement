package app.def;

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
import app.beans.LoanAgeDef;
import app.beans.LoanDef;
import app.beans.LoginEmployee;
import app.beans.hibernate.HGlVoucherTemplateDet;
import app.beans.hibernate.HGlVoucherTemplateMf;
import app.beans.hibernate.HLoanAgeDef;
import app.beans.hibernate.HLoanDef;

public class LoanDefinitionDef implements Definition
{
	public LoanDefinitionDef()
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
			loanDefBeans = new LoanDef[v.size()];
			v.toArray(loanDefBeans);
			defResultSet.setLoanDefBeans(loanDefBeans);
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
				query = session.createQuery(DefQueries.findLoanDefQuery);
			}
			
			List<HLoanDef> resultlist = query.list();
			System.out.println("Loan Def data found: " + resultlist.size());

			for (HLoanDef bean : resultlist)
			{
				LoanDef temp = new LoanDef();
				
				temp.setLogID(bean.getLogID());
				temp.setRefundable(bean.getRefundable());
				temp.setLoanTypeId(bean.getLoanTypeId());
				temp.setConditions(bean.getConditions());
				temp.setDescription(bean.getDescription());
				temp.setFundBalance(bean.getFundBalance());
				temp.setLoanDefDate(bean.getLoanDefDate());
				temp.setSalaryMonths(bean.getSalaryMonths());
				temp.setAgeDependent(bean.getAgeDependent());
				temp.setLoanTypeName(bean.getLoanTypeName());
				
				
				
				Query query1 = session.createQuery("from HLoanAgeDef  where loan_type_id='"+bean.getLoanTypeId()+"'");
            	List<HLoanAgeDef> resultList = query1.list();
				
				temp.setLoanAgeDef(cloneDetail(resultList, bean.getLoanTypeId()));

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

	public Set<LoanAgeDef> cloneDetail(List<HLoanAgeDef> temp, long loanTypeID)
	{
		Set<LoanAgeDef> list = new HashSet<LoanAgeDef>();
		
		for (HLoanAgeDef t : temp)
		{
			LoanAgeDef det = new LoanAgeDef();
			
			det.setLoanTypeID(loanTypeID);
			det.setAge(t.getAge());
			det.setSequence(t.getSequenceID());
			det.setFundPercentage(t.getFundPercentage());
			det.setLogicalOperator(t.getLogicalOperator());
			list.add(det);
		}
		
		return list;
	}
	
	
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private LoanDef[] loanDefBeans;
	private Map<String, Object> parameters = new HashMap<String, Object>();
	private Vector<LoanDef> v = new Vector<LoanDef>();
}
