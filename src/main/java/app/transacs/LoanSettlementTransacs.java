package app.transacs;

import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;









import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.DefResultSet;
import app.Definition;
import app.beans.LoanSettlement;
import app.beans.LoanSettlementFiles;
import app.beans.LoginEmployee;
import app.beans.ProfileFiles;
import utilities.AppConstants;
import utilities.AppUtility;
import database.DMLBase;
import database.DefQueries;


public class LoanSettlementTransacs implements Definition 
{

	public LoanSettlementTransacs()
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
			loanSettlement = new LoanSettlement[v.size()];
			v.toArray(loanSettlement);
			defResultSet.setLoanSettlement(loanSettlement);
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

		Session session = null;
		Query query = null;
		try
		{
			if(sessionFactory == null)
				
        		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
            session = sessionFactory.openSession();
            if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findLoanSettlementQuery);
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findLoanSettlementPostedQuery);
			}
			
			List<LoanSettlement> resultlist = query.list();
			System.out.println("Loan Schedule Size=: " + resultlist.size());
			Set<LoanSettlementFiles> lsf=null;
			for (LoanSettlement bean : resultlist)
			{
				lsf = new HashSet<LoanSettlementFiles>();
            	Query query1 = session.createQuery("from LoanSettlementFiles where settlement_id='"+bean.getSettlementID()+"'");
            	List<LoanSettlementFiles> resultContactList = query1.list();
            	
            	for(LoanSettlementFiles profileFiles : resultContactList)
            	{
            		LoanSettlementFiles profile = new LoanSettlementFiles();
            		profile.setSettlementID(profileFiles.getSettlementID());
            		profile.setEmployeeID(profileFiles.getEmployeeID());
            		profile.setFileData(profileFiles.getFileData());
            		profile.setFileID(profileFiles.getFileID());
            		profile.setFileName(profileFiles.getFileName());
            		profile.setFileType(profileFiles.getFileType());
            		profile.setPost(profileFiles.getPost());
            		profile.setLogID(profileFiles.getLogID());
            		
            		lsf.add(profile);
            	}
            	bean.setLoanSettlementFiles(lsf);
    			v.add(bean);
			}
			
		} catch (RuntimeException e)
		{
			throw e;

		} finally
		{
			session.close();
		}
	}

	private LoanSettlement[] loanSettlement;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<LoanSettlement> v = new Vector<LoanSettlement>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}

