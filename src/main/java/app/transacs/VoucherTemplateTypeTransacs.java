package app.transacs;

import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import database.DMLBase;
import app.DefResultSet;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.GlVoucherTemplateType;
import app.beans.LoginEmployee;

public class VoucherTemplateTypeTransacs implements Definition
{

	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee)throws Exception 
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			voucherTemplateTypeList = new GlVoucherTemplateType[v.size()];
			v.toArray(voucherTemplateTypeList);
			defResultSet.setGlVoucherTemplateTypeBeans(voucherTemplateTypeList);
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

		Session session = null;
		Query query = null;
		try
		{
			if(sessionFactory == null)
        		sessionFactory = (SessionFactory)AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);
        	
            session = sessionFactory.openSession();
			query = session.createQuery(DefQueries.findGlVoucherTemplateType);
			
			List<GlVoucherTemplateType> resultlist = query.list();
			System.out.println("Voucher template type  Size=: " + resultlist.size());
			  for(GlVoucherTemplateType bean: resultlist) 
	          {
	    			v.add(bean);
	          }
	            
		} 
		
		catch (RuntimeException e)
		{
			throw e;

		} 
		
		finally
		{
			session.close();
		}
	}

	private GlVoucherTemplateType[] voucherTemplateTypeList;
	private DefResultSet defResultSet;
	private SessionFactory sessionFactory;
	private Vector<GlVoucherTemplateType> v = new Vector<GlVoucherTemplateType>();
	private LoginEmployee loginEmployee = new LoginEmployee();
	private Map<String, Object> parameters = new HashMap<String, Object>();	

}
