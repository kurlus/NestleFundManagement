package database;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Vector;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;

import app.beans.SubGroupSubMenu;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;
import app.beans.TransactionLog;
import app.beans.EmployeeRights;


public abstract class DMLBase implements BaseQueries, Serializable 
{
	private static final long serialVersionUID = -7945270738562739911L;


	public static synchronized String getPropertyValue(Properties properties, String property) throws Exception 
	{
		if (properties == null) 
			throw new Exception(" Property File not instantiated.................");

		if ((property == null) || (property != null && property.trim().equals(AppConstants.BLANK))) 
		{
			throw new Exception(" Argument property key is invalid. Seems to empty..........");
		}

		return properties.getProperty(property);
	}
	

	public static synchronized String getEmployeeRoleCode(String employeeID) throws Exception 
	{
			try 
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.employeeParamEmployeeID, employeeID);
				
				String roleid = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(employeeRoleCode, parameters, String.class);
				return roleid;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}
	
	public static synchronized List<Map<String, Object>> getEmployeeRoleGroups(String roleCode) throws Exception 
	{
			try 
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.roleDetailRoleID, roleCode);
				
				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(menuGroupsQuery, parameters);
				return resultList;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}
	public static synchronized List<Map<String, Object>> getEmployeeRoleMenus(String roleCode) throws Exception
	{
			try
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.roleDetailRoleID, roleCode);

				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(menusQuery, parameters);
				return resultList;
			}
			catch (Exception e)
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}

	public static synchronized List<Map<String, Object>> getEmployeeRoleSubMenus(String roleID, String groupID, String parentMenuID) throws Exception
	{
		try
		{
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put(AppConstants.roleDetailRoleID, roleID);
			parameters.put(AppConstants.appMenuGroupID, groupID);
			parameters.put(AppConstants.appMenuParentMenuID, parentMenuID);

			List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(subMenusQuery, parameters);
			return resultList;
		}
		catch (Exception e)
		{
			AppUtility.logger.log(Level.SEVERE, e.getMessage());
			throw e;
		}
	}
	public static synchronized List<Map<String, Object>> getEmployeeRoleGroupsMod(String roleCode, String groupID) throws Exception
	{
			try
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.roleDetailRoleID, roleCode);
				parameters.put(AppConstants.appMenuGroupID, groupID);

				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(menuGroupsModules, parameters);
				return resultList;
			}
			catch (Exception e)
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}

	public static synchronized List<Map<String, Object>> getEmployeeRoleGroupsMenuesByMod(String roleCode, String groupID, String moduleID) throws Exception
	{
			try
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.roleDetailRoleID, roleCode);
				parameters.put(AppConstants.appMenuGroupID, groupID);
				parameters.put(AppConstants.appMenuModuleID, moduleID);


				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(groupSubMenuesByModule, parameters);
				return resultList;
			}
			catch (Exception e)
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}

	public static synchronized List<Map<String, Object>> getEmployeeRoleGroupsMenuesByPMenu(String roleCode, String groupID, String moduleID) throws Exception 
	{
			try 
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.roleDetailRoleID, roleCode);
				parameters.put(AppConstants.appMenuGroupID, groupID);
				parameters.put(AppConstants.appMenuModuleID, moduleID);
				
				
				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(groupSubMenuesByPrMenu, parameters);
				return resultList;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}
	
	public static synchronized List<Map<String, Object>> getEmployeeMenuList(String roleID, String menuID) throws Exception 
	{
			try 
			{
				String query = null;
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.appMenuMenuID, menuID);
				
				String  menuRole = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(ADMQueries.menuLabelForRole, parameters, String.class);
				
				parameters.clear();
				parameters.put(AppConstants.roleDetailRoleID, roleID);
				
				if(menuID!=null && menuID.equals("104") && roleID.equals("ALL") )
					query = ADMQueries.AllRoleMenuesListQuery;
				else
					query = ADMQueries.RoleMenuesListQuery;
				
				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(query, parameters);
				return resultList;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}
	public static synchronized String getMenuParentDesc(String menuID) throws Exception 
	{
			try 
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.appMenuMenuID, menuID);
				
				String resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(DefQueries.getMenuParentDesc, parameters, String.class);
				return resultList;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}
	
	public static synchronized EmployeeRights populateEmployeeMenuRights(String roleID, String menuID) throws Exception
	{
		List<Map<String, Object>> resultList = getEmployeeMenuRights(roleID,  menuID);
		EmployeeRights rightsBean = loadEmployeeMenuRights(resultList);
		return rightsBean;
	}
	
	private static  List<Map<String, Object>> getEmployeeMenuRights(String roleID, String menuID) throws Exception 
	{
			try 
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.roleDetailRoleID, roleID);
				parameters.put(AppConstants.appMenuMenuID, menuID);
				
				List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(employeeMenuRightsQuery, parameters);
				return resultList;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}
	
	private static synchronized EmployeeRights loadEmployeeMenuRights(List<Map<String, Object>> menuRightsList)
	{
		boolean isAdd = false;
		boolean isDel = false;
		boolean isRun = false;
		boolean isView = false;
		boolean isEdit = false;
		boolean isSave = false;
		boolean isPost = false;
		
		String  oprID, menuID, roleID = null;
		
		for (Map<String, Object> entry : menuRightsList) 
		{	
			oprID = (String) entry.get(AppConstants.roleDetailOprID);
			roleID = (String) entry.get(AppConstants.roleDetailOprID);
			menuID = (String) entry.get(AppConstants.roleDetailOprID);
			
			switch(oprID)
			{
			case AppConstants.oprView:
				isView = true;
				break;
			case AppConstants.oprAdd:
				isAdd = true;
				break;
			case AppConstants.oprEdit:
				isEdit = true;
				break;
			case AppConstants.oprDel:
				isDel = true;
				break;
			case AppConstants.oprSave:
				isSave = true;
				break;
			case AppConstants.oprPost:
				isPost = true;
				break;
			case AppConstants.oprRun:
				isRun = true;
				break;
			default:
				AppUtility.logger.log(Level.INFO, "No rights are defined for the role =" + roleID + " and menuID =" + menuID);
			}
			
		}
		
		EmployeeRights empRights = new EmployeeRights(isDel, isAdd, isRun, isSave, isView, isEdit, isPost);
		
		
		return empRights;
	}
	
	public static synchronized void loadUserRights(LoginEmployee loginEmployee) throws Exception
	{
        	EmployeeRights employeeRightsBean = DMLBase.populateEmployeeMenuRights(loginEmployee.getRoleID(), loginEmployee.getMenuID());
        	{
        		loginEmployee.setEmployeeRightsBean(employeeRightsBean);
        	}
	}
	
	public static synchronized TransactionLog populateTransLog(LoginEmployee loginEmployee , String oprID, String remarks) throws Exception
	{
		TransactionLog transLog = new TransactionLog();
		
		transLog.setRemarks(remarks);
		transLog.setOperationID(oprID);
		transLog.setServerDate(new Date());
		transLog.setMenuID(Long.valueOf(loginEmployee.getMenuID()));
		if(loginEmployee.getTerminalID()!=null)
		{
			transLog.setTerminalID(loginEmployee.getTerminalID());
		}
		else
			transLog.setTerminalID(AppConstants.defaultTerminalID);
		
		transLog.setEmployeeID(loginEmployee.getEmployeeID());
		
		return transLog;
	}
	
	public static synchronized Vector<SubGroupSubMenu> getAllMenus() throws Exception 
	{
		try 
		{
			Map<String, String> parameters = new HashMap<String, String>();
			
			Vector<SubGroupSubMenu> v = new Vector<SubGroupSubMenu>();
			
			List<Map<String, Object>> resultList = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(BaseQueries.findAllScreensMenus, parameters);
			populateMenus(resultList,  v);
			
			return v;
		} 
		catch (Exception e) 
		{
			AppUtility.logger.log(Level.SEVERE, e.getMessage());
			throw e;
		}
	}
	
	private static void populateMenus(List<Map<String, Object>> resultList, Vector<SubGroupSubMenu> v)
	{
		for (Map<String, Object> entry : resultList)
		{
			SubGroupSubMenu subMenus = new SubGroupSubMenu();

			String menuID = String.valueOf(entry.get(AppConstants.appMenuMenuID));
			String menuLabel = String.valueOf(entry.get(AppConstants.appMenuMenuLabel));
			String menuDesc = String.valueOf(entry.get(AppConstants.appMenuMenuDesc));
			String menuUrl = String.valueOf(entry.get(AppConstants.appMenuMenuURL));
			String moduleID = String.valueOf(entry.get(AppConstants.appMenuModuleID));
			String groupDesc = String.valueOf(entry.get(AppConstants.appMenuGroupDesc));
			String parentMenuID = String.valueOf(entry.get(AppConstants.appMenuParentMenuID));

			subMenus.setSubGroupSubMenuID(menuID);
			// menu title carries the original value of Menu Description
			// menu Description carries Group Desc+Menu Desc
			
			subMenus.setSubGroupSubMenuUrl(menuUrl);
			subMenus.setSubGroupSubMenuLabel(menuLabel);
			subMenus.setSubGroupSubMenuModuleID(moduleID);
			subMenus.setSubGroupSubParentMenuID(parentMenuID);
			subMenus.setSubGroupSubMenuDescription(groupDesc + " - " + menuDesc);
			

			v.add(subMenus);

		}
		
	}

	
}
