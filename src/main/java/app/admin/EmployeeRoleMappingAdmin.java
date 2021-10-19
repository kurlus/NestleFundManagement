package app.admin;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import utilities.AppUtility;
import utilities.AppConstants;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import app.beans.SubGroupSubMenu;
import app.beans.RoleDetail;
import app.beans.LoginEmployee;
import app.beans.EmployeeRights;


public class EmployeeRoleMappingAdmin implements Definition 
{
	public EmployeeRoleMappingAdmin()
	{
	}
	
	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee)
			throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		
		execute();
		
		if (v.size() > AppConstants.zero)
		{
			roleDetailBeans = new RoleDetail[v.size()];		
			v.toArray(roleDetailBeans);		
			defResultSet.setRoleDetailBeans(roleDetailBeans);
		}
		
		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());
		
		return defResultSet;
	}
	
	public synchronized LoginEmployee getLoginEmployee() 
	{
		return loginEmployee;
	}

	public synchronized void setLoginEmployee(LoginEmployee loginEmployee) 
	{
		this.loginEmployee = loginEmployee;
	}	
	
	public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	private synchronized void setListData(Object[] listOfObjs) 
	{

	}
	
	private  void execute() throws MappingException, IOException, Exception 
    {
		if(sessionFactory == null)
    		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
    	
        Session session = sessionFactory.openSession();
        try 
        {
        	vs = DMLBase.getAllMenus();
        	if (vs.size() > AppConstants.zero)
			{
				subMenusBeans = new SubGroupSubMenu[vs.size()];		
				vs.toArray(subMenusBeans);		
				defResultSet.setSubMenusBeans(subMenusBeans);
			}
        	String roleID=null;
    		if(loginEmployee.getFilterBy() != null && !loginEmployee.getFilterBy().equals(AppConstants.filterByALL) && !loginEmployee.getFilterBy().equals(AppConstants.filterBySelected) && !loginEmployee
    				.getFilterBy().equals(AppConstants.filterForOtherScreenUsage))
    		{
    			roleID=loginEmployee.getFilterBy();
    		}
    		else 
    		{
    			roleID=loginEmployee.getRoleID();
    		}
    		
        	
        	List<Map<String, Object>> roleMenuesList = DMLBase.getEmployeeMenuList(roleID, this.getLoginEmployee().getMenuID());
            AppUtility.logger.log(Level.INFO, "Role detail found: " + roleMenuesList.size());
            
            for(Map<String, Object> bean: roleMenuesList) 
            {
            	EmployeeRights rights = DMLBase.populateEmployeeMenuRights(String.valueOf(bean.get(AppConstants.roleDetailRoleID)),
						  String.valueOf(bean.get(AppConstants.appMenuMenuID)));
            	RoleDetail roleDetail = new RoleDetail();
            	roleDetail.setRoleID(String.valueOf(bean.get(AppConstants.roleDetailRoleID)));
            	roleDetail.setMenuID(Long.valueOf(bean.get(AppConstants.appMenuMenuID).toString()));
            	roleDetail.setMenuDescription(String.valueOf(bean.get(AppConstants.appMenuMenuDesc)));
            	roleDetail.setEmployeeRightsBean(rights);
            	
    			v.add(roleDetail);
            }
            
        } 
        catch (RuntimeException e) 
        {
        	AppUtility.logger.log(Level.SEVERE, "loading data failed ="+e.getMessage());
            throw e;

        } finally 
        {
            session.close();
        }
    }
	
	private SubGroupSubMenu[] subMenusBeans ;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private RoleDetail[] roleDetailBeans;
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	
	private Vector<RoleDetail> v = new Vector<RoleDetail>();
	private Vector<SubGroupSubMenu> vs = new Vector<SubGroupSubMenu>();

}
