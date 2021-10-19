package database;

public interface ADMQueries 
{

	String insertRoleDetailQuery = " insert into roledetail (menu_id, operation_id, post, role_id) values (?,?,?,?) ";
	
	String updateRoleDetailQuery = " update roledetail set  post=?  where menu_id=? and operation_id =? and role_id=?";
	
	String deleteRoleDetailQuery = " delete from roledetail where menu_id=? and role_id=? and operation_id =?";
	
	String menuLabelForRole = "select menu_label from appmenus1 m where menu_id = :menu_id";
	
	String AllRoleMenuesListQuery = "select distinct r.role_id, r.menu_id, a.menu_description from roledetail r, appmenus1 a where r.menu_id=a.menu_id";
	
	String RoleMenuesListQuery = "select distinct r.role_id, r.menu_id, a.menu_description from roledetail r, "+
								" appmenus1 a where r.menu_id=a.menu_id and a.parent_menu_id is not null and r.role_id=:role_id";
}
