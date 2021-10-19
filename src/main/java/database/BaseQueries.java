package database;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public interface BaseQueries 
{	
	String DB_DATE_FORMAT   ="dd/mm/yyyy";
	String NOTIFICATION_DATE_FORMAT="dd-MM-yyyy";
	String NOTIFICATION_AMOUNT_FORTMAT="#,###,###,##0.00";
	String NOTIFICATION_UNIT_FORMAT   ="#,###,###,##0.0000";
	String NOTIFICATION_DATE_TIME_FORMAT="dd-MM-yyyy hh:mm a";

	String logSequenceQuery = "select log_seq.nextval from dual";
	String unitOnlineUserSessIDGenerator = "select unit_online_usersession.nextval from dual";
	String systemDateQuery = "select to_char(get_system_date,'MM/dd/yyyy') from dual";
	
//	String findAllScreensMenus = " select a.menu_id, a.menu_label, a.menu_description, a.group_id  , a.menu_URL, b.short_name , a.module_id , a.parent_menu_id "+
//			" from APPMENUS a,APPMODULES b where a.parent_menu_id is not null and ( a.module_id=b.module_id or a.module_id is null);";
	String findAllScreensMenus = " select a.menu_id, a.menu_label, a.menu_description, a.group_id , c.group_description  , a.menu_URL, a.module_id , a.parent_menu_id "+
			" from APPMENUS1 a, APPMENUGROUP c where A.menu_URL IS NOT NULL  and ( a.group_id=c.group_id );";

	String userOpLogInsertQuery = 
			"insert into user_op_log "
					+ " (log_id, user_id, trn_code, opr_code, server_date, terminal_id, remarks)"
					+ " values "
					+ " (:log_id, :user_id, :trn_code, :opr_code, :server_date, :terminal_id, :remarks)";

	String trnCodeQuery ="select tcode.trn_code,tCode.menu_Label,\n" +
			"           tCode.menu_url,tCode.priority_level from trn_code tCode,trn_group\n" +
			"           tGroup,ROLEDETAIL \n" +
			"           rDetail,users u where rDetail.role_id=u.role_code and \n" +
			"           tCode.trn_code=rDetail.trn_code and tcode.module_code='W' and \n" +
			"           tCode.trn_group_code=\n" +
			"           tGroup.trn_group_code and u.user_id=:user_id and not exists \n" +
			"           (select ue.trn_code from user_exception ue where ue.trn_code=\n" +
			"           rdetail.trn_code and ue.user_code=:user_id and block=1 and\n" +
			"           ue.opr_code='VIEW') and tGroup.trn_group_code=:trn_group_code\n" +
			"           union \n" +
			"           select tcode.trn_code,tCode.menu_Label,\n" +
			"           tCode.menu_url,tCode.priority_level from trn_code tCode,\n" +
			"           trn_group tGroup,\n" +
			"           user_exception ue where tCode.trn_group_code=tGroup.trn_group_code\n" +
			"           and tcode.module_code='W' and ue.trn_code=tcode.trn_code \n" +
			"           and ue.user_code=:user_id and block=0 and \n" +
			"           tGroup.trn_group_code=:trn_group_code order by priority_level";

	String trnGroupCodesQuery = "select tGroup.trn_group_code,tGroup.description from trn_group tGroup order by tGroup.priority_level";

	String trnCodesQuery = " select tcode.trn_code,tCode.menu_Label, tCode.menu_url,tCode.priority_level from trn_code tCode,trn_group\n" +
			"  tGroup,ROLEDETAIL rDetail,users u where rDetail.role_id=u.role_code and tCode.trn_code=rDetail.trn_code and tcode.module_code='W' and \n" +
			"  tCode.trn_group_code=tGroup.trn_group_code and u.user_id=:user_id and not exists (select ue.trn_code from user_exception ue where ue.trn_code=\n" +
			"  rdetail.trn_code and ue.user_code=:user_id and block=1 and ue.opr_code='VIEW') and tGroup.trn_group_code=:trn_group_code\n" +
			"  union \n" +
			"  select tcode.trn_code,tCode.menu_Label,tCode.menu_url,tCode.priority_level from trn_code tCode,trn_group tGroup,\n" +
			"  user_exception ue where tCode.trn_group_code=tGroup.trn_group_code and tcode.module_code='W' and ue.trn_code=tcode.trn_code \n" +
			"  and ue.user_code=:user_id and block=0 and tGroup.trn_group_code=:trn_group_code order by priority_level";

	String isSessionLapse = "select (sysdate - last_session_end_time)*24*60 as time from unit_online_user_session s where "
			+ "id = :id and user_id = :user_id and access_code=:access_code";
	
	String employeeRoleCode = " select role_id from employee where employee_id = :employee_id";
	//String employeeRoleCode = " select distinct role_id from ROLEDETAIL";
	String menuGroupsQuery = " select distinct a.group_id, group_description,short_name, p.post from APPMENUGROUP p, APPMENUS a "+
							 " where a.group_id= p.group_id  and a.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id)";
	String menusQuery = "Select * from APPMENUS1 where group_id in (select distinct a.group_id from APPMENUGROUP p, APPMENUS1 a "+
						" where a.group_id= p.group_id  and a.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id)) "+
						" and parent_menu_id is null";
	
	String subMenusQuery = "sELECT a.menu_id,a.menu_description,a.menu_label,a.menu_URL,a.module_id,a.parent_menu_id,a.group_id FROM APPMENUS1 a "+
						   " where a.parent_menu_id is not null and A.group_id=:group_id and a.parent_menu_id=:parent_menu_id  and a.menu_URL is null  and [dbo].[MENU_CHECK] (a.menu_id,:role_id)=1"+
						   " union "+
						   " sELECT a.menu_id,a.menu_description,a.menu_label,a.menu_URL,a.module_id,a.parent_menu_id,a.group_id FROM APPMENUS1 a "+
						   " where a.parent_menu_id is not null and A.group_id=:group_id and a.parent_menu_id=:parent_menu_id and a.menu_URL is not null and [dbo].[MENU_CHECK] (a.menu_id,:role_id)=1";
	
	String menuGroupsModules = " Select distinct a.module_id, m.module_description,'ByModule' module_filteration from APPMENUS a, APPMODULES m  "+
							   " where m.module_id = a.module_id and a.module_id is not null and a.group_id = :group_id "+
							   " and a.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id)"+
							   " union "+
							   " Select distinct CAST(am.menu_id AS VARCHAR), am.menu_description, 'ByMenu' from APPMENUS am  where am.parent_menu_id is null "+
							   " and am.module_id is null and am.group_id = :group_id  and am.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id)";
	
	String groupSubMenuesByModule = " With n(menu_id, menu_description, group_id, module_id, parent_menu_id, menu_url) AS "+
									 " (select a.menu_id, a.menu_description, a.group_id, a.module_id, a.parent_menu_id, a.menu_url from APPMENUS a "+
									 "  where a.parent_menu_id is null and a.module_id is not null and a.module_id = :module_id and a.group_id = :group_id"+
									 "  and a.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id)"+
									 " union all "+
									 " select s.menu_id, s.menu_description, s.group_id, s.module_id, s.parent_menu_id, s.menu_url from APPMENUS s , n"+
									 "  where  s.parent_menu_id = n.menu_id and s.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id))"+
									 " select menu_id, menu_description, group_id, module_id, parent_menu_id, menu_url from n " ;
	
	String groupSubMenuesByPrMenu = " With n(menu_id, menu_description, group_id, module_id, parent_menu_id, menu_url) AS "+
									 " (select a.menu_id, a.menu_description, a.group_id, a.module_id, a.parent_menu_id, a.menu_url from APPMENUS a "+
									 " where a.parent_menu_id is null and a.module_id is null and a.menu_id = :module_id and a.group_id = :group_id "+
									 " and a.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id) "+
									 " union all "+
									 " select s.menu_id, s.menu_description, s.group_id, s.module_id, s.parent_menu_id, s.menu_url from APPMENUS s , n "+
									 " where  s.parent_menu_id = n.menu_id and s.parent_menu_id is not null and s.menu_id in (select r.menu_id from ROLEDETAIL r where r.role_id = :role_id))"+ 
									 " select menu_id, menu_description, group_id, module_id, parent_menu_id, menu_url from n ";
	

	String loginEmployeeQuery = "select distinct e.employee_id , r.role_type_id, e.user_id, e.obo ,rd.role_id from employee e , roles r, roledetail rd  where e.role_id = r.role_id and e.role_id = rd.role_id  and e.obo=1";
	String employeeMenuRightsQuery ="select r.role_id, r.menu_id, r.operation_id, r.post from roledetail r where r.menu_id = :menu_id and r.role_id = :role_id";
	DateFormat dateFormatter = new SimpleDateFormat(NOTIFICATION_DATE_FORMAT);
	DateFormat dateTimeFormatter = new SimpleDateFormat(NOTIFICATION_DATE_TIME_FORMAT);
	DecimalFormat amountFormatter = new DecimalFormat(NOTIFICATION_AMOUNT_FORTMAT);
	DecimalFormat unitsFormatter = new DecimalFormat(NOTIFICATION_UNIT_FORMAT);

}
