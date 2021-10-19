package app.admin;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import app.DefResultSet;
import app.Definition;

import java.util.logging.Level;

import utilities.AppUtility;
import database.BaseQueries;
import database.QueryWrapper;
import utilities.AppConstants;
import database.DMLOperations;
import app.beans.LoginEmployee;

public class LoginEmployeeAdmin implements Definition 
{
	public LoginEmployeeAdmin()
	{

	}

	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception
	{
		v.clear();

		parameters.clear();
		defResultSet = new DefResultSet();

		try
		{
			Map<String, String> loginParams = new HashMap<String, String>();
			loginParams.put(AppConstants.employeeParamUserID, loginEmployee.getUserID());
			QueryWrapper qWrapper = new QueryWrapper(BaseQueries.loginEmployeeQuery, loginParams);
			List<Map<String, Object>> glList = DMLOperations.queryList(qWrapper);

			for (Map<String, Object> entry : glList)
			{
				String userID = (String) entry.get(AppConstants.employeeParamUserID);
				String roleID = String.valueOf(entry.get(AppConstants.roleDetailRoleID));
				String roleTypeID = (String) entry.get(AppConstants.roleTypeID);
				String employeeID = (String) entry.get(AppConstants.employeeParamEmployeeID);
				boolean obo = (boolean) entry.get(AppConstants.employeeParamOBO);

				LoginEmployee bean = getLoginEmployeeFactory();
				bean.setUserID(userID);
				bean.setRoleID(roleID);
				bean.setOnBehalfOf(obo);
				bean.setEmployeeID(employeeID);
				bean.setRoleTypeID(roleTypeID);
				v.add(bean);
			}

			if (v.size() > AppConstants.zero)
			{
				loginEmployeeBeans = new LoginEmployee[v.size()];
				v.toArray(loginEmployeeBeans);
				defResultSet.setLoginEmployeeBeans(loginEmployeeBeans);
			}
		} catch (Exception e)
		{
			AppUtility.logger.log(Level.SEVERE, "loading data failed =" + e.getMessage());
			throw e;
		}

		return defResultSet;
	}

	private void setListData(Object[] listOfObjs)
	{
		// TODO Auto-generated method stub

	}

	private LoginEmployee getLoginEmployeeFactory()
	{
		return new LoginEmployee();
	}

	private DefResultSet defResultSet;
	private LoginEmployee[] loginEmployeeBeans;
	private Vector<LoginEmployee> v = new Vector<LoginEmployee>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
