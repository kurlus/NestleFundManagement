package app.def;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;

import database.DMLBase;
import database.DefQueries;

import org.hibernate.Query;

import utilities.AppUtility;
import app.DefResultSet;
import app.Definition;
import app.beans.GlFormType;

import org.hibernate.Session;

import utilities.AppConstants;
import app.beans.LoginEmployee;

import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

public class GlFormTypeDef implements Definition {

	public GlFormTypeDef()
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
			glFormTypeBeans = new GlFormType[v.size()];
			v.toArray(glFormTypeBeans);
			defResultSet.setGlFormTypeBeans(glFormTypeBeans);
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
				query = session.createQuery(DefQueries.findGlFormType);
			} else if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterForOtherScreenUsage)))
			{
				query = session.createQuery(DefQueries.findGlFormTypePosted);
			}

			List<GlFormType> resultList = query.list();
			System.out.println("GlFormType found: " + resultList.size());
			for (GlFormType bean : resultList)
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

	private DefResultSet defResultSet;
	private GlFormType[] glFormTypeBeans;
	private SessionFactory sessionFactory;
	private Vector<GlFormType> v = new Vector<GlFormType>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
