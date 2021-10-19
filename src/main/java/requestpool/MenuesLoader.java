package requestpool;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.concurrent.Callable;

import database.DMLBase;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.Menus;
import app.beans.SubGroupMenu;
import app.beans.SubGroupSubMenu;
import app.beans.GroupMenus;


public class MenuesLoader implements Serializable, Callable<List<Menus>>
{
	private static final long serialVersionUID = -8048058356691859056L;
	
	
	public MenuesLoader(String employeeID) 
	{
		this.employeeID = employeeID;
	}

	@Override
	public List<Menus> call() throws Exception 
	{
		return loadUserMenuesData();
	}

	private List<Menus> loadUserMenuesData() throws Exception
	{
		try
		{
			String roleCode = DMLBase.getEmployeeRoleCode(employeeID);
			
			if(roleCode != null)
			{
				populateMenuBeans(roleCode, menuBeans);
			}
		}
		catch(Exception ex)
		{
			AppUtility.logger.log(Level.SEVERE, "no employee role id is defined.");
			throw ex;
		}
		return menuBeans;
	}
	
	private void populateMenuBeans(String roleCode, List<Menus> menuBeanList) throws Exception
	{
		List<Map<String, Object>> groupsMenus;
		List<Map<String, Object>> menus = DMLBase.getEmployeeRoleMenus(roleCode);
		
		for(Map<String, Object> entry : menus)
		{

			String menuID = String.valueOf(entry.get(AppConstants.appMenuMenuID));
			String menuDesc = String.valueOf(entry.get(AppConstants.appMenuMenuDesc));
			String menuLabel = String.valueOf(entry.get(AppConstants.appMenuMenuLabel));
			String menuGroupID = String.valueOf(entry.get(AppConstants.appMenuGroupID));
			String groupID = String.valueOf(entry.get(AppConstants.appMenuGroup));
			
			menuBean = getMenuBeanFactory();
			menuBean.setMenuID(menuID);
			menuBean.setMenuDesc(menuDesc);
			menuBean.setMenuLabel(menuLabel);
			menuBean.setMenuGroupID(menuGroupID);
			
			groupsMenus = DMLBase.getEmployeeRoleSubMenus(roleCode, groupID, menuBean.getMenuID());
			populateGroupMenus(groupsMenus, roleCode, groupID, menuBean);
			
			menuBeanList.add(menuBean);
		}
	}
	
	private void populateGroupMenus(List<Map<String, Object>> groupsMenus, String roleCode, String groupID, Menus menuBean) throws Exception
	{
		List<Map<String, Object>> groupsSubMenus;
		groupMenusList = getMenuBeanModsFactory();
		
		
		for(Map<String, Object> groupMenuEntry : groupsMenus)
		{
			groupMenu = getGroupMenuFactory();
			
			String groupMenuID = String.valueOf(groupMenuEntry.get(AppConstants.appMenuMenuID));
			String groupMenuURL = String.valueOf(groupMenuEntry.get(AppConstants.appMenuMenuURL));
			String groupMenuDesc = String.valueOf(groupMenuEntry.get(AppConstants.appMenuMenuDesc));
			String groupMenuLabel = String.valueOf(groupMenuEntry.get(AppConstants.appMenuMenuLabel));
			String groupMenuGroupID = String.valueOf(groupMenuEntry.get(AppConstants.appMenuGroupID));
			String groupMenuModuleID = String.valueOf(groupMenuEntry.get(AppConstants.appMenuModuleID));
			String groupMenuParentID = String.valueOf(groupMenuEntry.get(AppConstants.appMenuParentMenuID));
			
			if(groupMenuURL== AppConstants.nullValue || groupMenuURL.equals("null"))
			{
				
				groupMenu.setGroupMenuLevel("P");
				groupMenu.setGroupMenuID(groupMenuID);
				groupMenu.setGroupMenuURL(groupMenuURL);
				groupMenu.setGroupMenuDesc(groupMenuDesc);
				groupMenu.setGroupMenuLabel(groupMenuLabel);
				groupMenu.setGroupMenuGroupID(groupMenuGroupID);
				groupMenu.setGroupMenuModuleID(groupMenuModuleID);
				groupMenu.setGroupParentMenuID(groupMenuParentID);
				
				groupsSubMenus = DMLBase.getEmployeeRoleSubMenus(roleCode, groupID, groupMenu.getGroupMenuID());
				populateSubGroupMenus(roleCode, groupsSubMenus, groupMenu);
			}
			else
			{
				groupMenu.setGroupMenuLevel("D");
				groupMenu.setGroupMenuID(groupMenuID);
				groupMenu.setGroupMenuURL(groupMenuURL);
				groupMenu.setGroupMenuDesc(groupMenuDesc);
				groupMenu.setGroupMenuLabel(groupMenuLabel);
				groupMenu.setGroupMenuGroupID(groupMenuGroupID);
				groupMenu.setGroupMenuModuleID(groupMenuModuleID);
				groupMenu.setGroupParentMenuID(groupMenuParentID);
				
			}
			
			menuBean.getGroupMenusList().add(groupMenu);
		}
			
	}
	
	private void populateSubGroupMenus(String roleCode, List<Map<String, Object>> groupsSubMenus, GroupMenus groupMenus) throws Exception
	{
		List<Map<String, Object>> subGroupsSubMenus;
		subGroupMenuList = getSubGroupMenuListFactory();
		
		for(Map<String, Object> groupsSubMenusEntry : groupsSubMenus)
		{
			subGroupMenu = getSubGroupMenuFactory();
			
			String subGroupMenuID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuID));
			String subGroupMenuURL = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuURL));
			String subGroupMenuDesc = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuDesc));
			String subGroupMenuLabel = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuLabel));
			String subGroupMenuGroupID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuGroupID));
			String subGroupMenuModuleID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuModuleID));
			String subGroupMenuParentID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuParentMenuID));
			
			if(subGroupMenuURL == null || subGroupMenuURL.equals("null"))
			{
				subGroupMenu.setSubGroupMenuLevel("P");
				subGroupMenu.setSubGroupMenuID(subGroupMenuID);
				subGroupMenu.setSubGroupMenuURL(subGroupMenuURL);
				subGroupMenu.setSubGroupMenuDesc(subGroupMenuDesc);
				subGroupMenu.setSubGroupMenuLabel(subGroupMenuLabel);
				subGroupMenu.setSubGroupMenuGroupID(subGroupMenuGroupID);
				subGroupMenu.setSubGroupParentMenuID(subGroupMenuParentID);
				
				subGroupsSubMenus = DMLBase.getEmployeeRoleSubMenus(roleCode, subGroupMenuGroupID, subGroupMenuID);
				subGroupMenu.setSubGroupSubMenuList(populateSubGroupSubMenus(subGroupsSubMenus));
				
			}
			else
			{
				subGroupMenu.setSubGroupMenuLevel("D");
				subGroupMenu.setSubGroupMenuID(subGroupMenuID);
				subGroupMenu.setSubGroupMenuURL(subGroupMenuURL);
				subGroupMenu.setSubGroupMenuDesc(subGroupMenuDesc);
				subGroupMenu.setSubGroupMenuLabel(subGroupMenuLabel);
				subGroupMenu.setSubGroupMenuGroupID(subGroupMenuGroupID);
				subGroupMenu.setSubGroupParentMenuID(subGroupMenuParentID);
			}
			subGroupMenuList.add(subGroupMenu);
		}
		
		groupMenus.setSubGroupMenuList(subGroupMenuList);
		 
	}
	
	private List<SubGroupSubMenu> populateSubGroupSubMenus(List<Map<String, Object>> subGroupsSubMenus) throws Exception
	{
		
		subGroupSubMenuList = getSubGroupSubMenuListFactory();
		
		for(Map<String, Object> groupsSubMenusEntry : subGroupsSubMenus)
		{
			subGroupSubMenu = getSubGroupSubMenuFactory();
			
			String subGroupSubMenuID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuID));
			String subGroupSubMenuURL = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuURL));
			String subGroupSubMenuDesc = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuDesc));
			String subGroupSubMenuLabel = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuMenuLabel));
			String subGroupSubMenuGroupID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuGroupID));
			String subGroupSubMenuModuleID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuModuleID));
			String subGroupSubMenuParentID = String.valueOf(groupsSubMenusEntry.get(AppConstants.appMenuParentMenuID));
			
			if(subGroupSubMenuURL == null || subGroupSubMenuURL.equals("null"))
			{
				AppUtility.getLogger().log(Level.SEVERE, "no menu url is defined menu_id="+subGroupSubMenuURL);
			}
			else
			{
				subGroupSubMenu.setSubGroupSubMenuID(subGroupSubMenuID);
				subGroupSubMenu.setSubGroupSubMenuUrl(subGroupSubMenuURL);
				subGroupSubMenu.setSubGroupSubMenuLabel(subGroupSubMenuLabel);
				subGroupSubMenu.setSubGroupSubMenuGroupID(subGroupSubMenuGroupID);
				subGroupSubMenu.setSubGroupSubMenuDescription(subGroupSubMenuDesc);
				subGroupSubMenu.setSubGroupSubMenuModuleID(subGroupSubMenuModuleID);
				subGroupSubMenu.setSubGroupSubParentMenuID(subGroupSubMenuParentID);
			}
			subGroupSubMenuList.add(subGroupSubMenu);
		}
		 return subGroupSubMenuList;
	}
	
	
	
	private Menus getMenuBeanFactory()
	{
		return new Menus();
	}
	
	private SubGroupSubMenu getSubGroupSubMenuFactory()
	{
		return new SubGroupSubMenu();
	}
	
	private SubGroupMenu getSubGroupMenuFactory()
	{
		return new SubGroupMenu();
	}
	
	private GroupMenus getGroupMenuFactory()
	{
		return new GroupMenus();
	}
	
	private List<SubGroupSubMenu> getSubGroupSubMenuListFactory()
	{
		return new ArrayList<SubGroupSubMenu>();
	}
	
	private List<SubGroupMenu> getSubGroupMenuListFactory()
	{
		return new ArrayList<SubGroupMenu>();
	}
	
	private List<GroupMenus> getMenuBeanModsFactory()
	{
		return new ArrayList<GroupMenus>();
	}
	
	
	private Menus menuBean;
	private String employeeID;
	private GroupMenus groupMenu;
	private SubGroupMenu subGroupMenu;
	private SubGroupSubMenu subGroupSubMenu; 
	private List<GroupMenus> groupMenusList ;
	private List<SubGroupMenu> subGroupMenuList ;
	private List<SubGroupSubMenu> subGroupSubMenuList ;
	private List<Menus> menuBeans = new ArrayList<Menus>();

	
}
