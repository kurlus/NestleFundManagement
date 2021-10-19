package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;

import database.DMLBase;
import database.DefQueries;
import utilities.AppConstants;
import app.Definition;
import app.DefResultSet;
import app.beans.LoginEmployee;
import app.beans.RoleType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class RoleTypeDef implements Definition {
	public RoleTypeDef()
	{
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
			roleTypes = new RoleType[v.size()];
			v.toArray(roleTypes);
			defResultSet.setRoleTypes(roleTypes);
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

	private void execute() throws MappingException, IOException, Exception
	{
		Session session = sessionFactory.openSession();
		Query query = null;
		try
		{	
        	if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findRoleTypeQuery);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findRoleTypePostedQuery);
			}
			List<RoleType> resultlist = query.list();
			System.out.println("Role Types Size=: " + resultlist.size());
			for (RoleType bean : resultlist)
			{
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

	private RoleType[] roleTypes;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<RoleType> v = new Vector<RoleType>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
