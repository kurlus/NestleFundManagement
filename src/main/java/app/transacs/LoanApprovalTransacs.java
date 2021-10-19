package app.transacs;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.util.logging.Level;

import database.DMLBase;
import database.DefQueries;
import app.Definition;
import app.DefResultSet;
import app.beans.LoanApply;
import utilities.AppUtility;
import app.beans.ProfileFiles;
import utilities.AppConstants;
import app.beans.LoginEmployee;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class LoanApprovalTransacs implements Definition 
{

	public LoanApprovalTransacs() 
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
			loanApprovalBeans = new LoanApply[v.size()];		
			v.toArray(loanApprovalBeans);		
			defResultSet.setLoanApply(loanApprovalBeans);
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
        Session session = null;
        HashSet<ProfileFiles> profiles = null;
        String roleTypeID = AppConstants.BLANK;
        String loanAppQuery = AppConstants.BLANK;
        
        try 
       {
	   	               if (loginEmployee.getRoleTypeID() == null) throw new NullPointerException(" login employee role type id is null....");
        	
        	if(sessionFactory == null)
        		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);

            session = sessionFactory.openSession();
            roleTypeID = loginEmployee.getRoleTypeID();
            
            if(loginEmployee.getRoleTypeID().equals(AppConstants.roleTypeAdmin))
            	roleTypeID = DMLBase.getMenuParentDesc(loginEmployee.getMenuID());
            
            if(roleTypeID.equals(AppConstants.roleTypeHR))
            {	
            	loanAppQuery = DefQueries.findLoanApplyQuery + " where post=1";
            }	
            else if(roleTypeID.equals(AppConstants.roleTypeTrustee))
            	loanAppQuery = DefQueries.findLoanApplyQuery + " where post=1 and flag_manager = 1 ";
            
            else if(roleTypeID.equals(AppConstants.roleTypeTreasury))
            	loanAppQuery = DefQueries.findLoanApplyQuery + " where post=1 and flag_manager = 1 and flag_t1 = 1 ";
			else if (roleTypeID.equals(AppConstants.roleTypeMember))
			{
				if (loginEmployee.isOnBehalfOf())
					loanAppQuery = DefQueries.findLoanApplyQuery;
				else
					loanAppQuery = DefQueries.findLoanApplyQuery + " where employee_id ='" + loginEmployee.getEmployeeID() + "'";

			} else
            	loanAppQuery = DefQueries.findLoanApplyQuery;
            
            query = session.createQuery(loanAppQuery);
            
            List<LoanApply> loanAppList = query.list();
            
            for(LoanApply loan: loanAppList) 
            {
            	profiles = new HashSet<ProfileFiles>();
            	Query query1 = session.createQuery("from ProfileFiles where apply_id='"+loan.getApplyID()+"'");
            	List<ProfileFiles> resultContactList = query1.list();
            	
            	for(ProfileFiles profileFiles : resultContactList)
            	{
            		ProfileFiles profile = getProFileFactory();
            		profile.setApplyID(profileFiles.getApplyID());
            		profile.setEmployeeID(profileFiles.getEmployeeID());
            		profile.setFileData(profileFiles.getFileData());
            		profile.setFileID(profileFiles.getFileID());
            		profile.setFileName(profileFiles.getFileName());
            		profile.setFileType(profileFiles.getFileType());
            		profile.setPost(profileFiles.getPost());
            		profile.setLogID(profileFiles.getLogID());
            		
            		profiles.add(profile);
            	}
            	loan.setProfileFiles(profiles);
    			v.add(loan);
            }
            
        } catch (RuntimeException e) 
        {
        	AppUtility.logger.log(Level.SEVERE, "loading data failed ="+e.getMessage());
            throw e;

        } finally 
        {
            session.close();
        }
    }

	private ProfileFiles getProFileFactory()
	{
		return new ProfileFiles();
	}
	
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private LoanApply[] loanApprovalBeans;
	private SessionFactory sessionFactory;
	private Vector<LoanApply> v = new Vector<LoanApply>();
	private Map<String, Object> parameters = new HashMap<String, Object>();	

}
