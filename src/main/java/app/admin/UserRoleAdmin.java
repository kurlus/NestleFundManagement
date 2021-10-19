package app.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import app.DefResultSet;
import app.Definition;
import app.beans.EmployeeRights;
import app.beans.LoanApply;
import app.beans.LoginEmployee;
import app.beans.Roles;
import utilities.AppConstants;
import utilities.AppUtility;
import database.ADMQueries;
import database.DMLBase;
import database.DefQueries;
import database.JDBCTemplateFactory;

public class UserRoleAdmin implements Definition 
{
	public UserRoleAdmin()
	{
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


	@Override
	public synchronized DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);

		execute();

		if (v.size() > AppConstants.zero)
		{
			userRoleBeans = new Roles[v.size()];
			v.toArray(userRoleBeans);
			defResultSet.setRoles(userRoleBeans);
		}

		DMLBase.loadUserRights(this.getLoginEmployee());
		defResultSet.setLoginEmployee(getLoginEmployee());

		return defResultSet;
	}

	private synchronized void setListData(Object[] listOfObjs)
	{
	}

	private void execute() throws MappingException, IOException, Exception
	{
		Session session = sessionFactory.openSession();
		String menuID = this.getLoginEmployee().getMenuID();
		Query query = null;
		try
		{
			//if (menuID != null && (menuID.equals("102") || menuID.equals("104")))
			if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findRolesQuery);
			} 
			else if (loginEmployee.getFilterBy()!= null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findRolesQuery + " where post=1 ");
			} 
			
			else
			{
				parameters.put(AppConstants.appMenuMenuID, menuID);

				String menuRole = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(ADMQueries.menuLabelForRole, parameters, String.class);

				query = session.createQuery(DefQueries.findRolesByRoleIDQuery + " and post=1 ");
				query.setParameter(AppConstants.roleDetailRoleID, menuRole);
			}

			List<Roles> resultList = query.list();
			System.out.println("roles found: " + resultList.size());
			for (Roles bean : resultList)
			{
				v.add(bean);
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

	
	private Roles[] userRoleBeans;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<Roles> v = new Vector<Roles>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
