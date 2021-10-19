package app.transacs;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.MappingException;

import app.Definition;
import app.DefResultSet;
import database.DMLBase;
import database.DefQueries;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.LoginEmployee;
import app.beans.GlCashPaymentDet;
import app.beans.GlCashPaymentVoucher;
import app.beans.hibernate.HCashPaymentDet;
import app.beans.hibernate.HCashPaymentGlVoucher;

public class GLCashPaymentTransacs implements Definition 
{

	public GLCashPaymentTransacs()
	{

	}

	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception
	{
		v.clear();
		parameters.clear();
		defResultSet = new DefResultSet();
		this.setLoginEmployee(loginEmployee);
		execute();

		if (v.size() > AppConstants.zero)
		{
			glVoucherBeans = new GlCashPaymentVoucher[v.size()];
			v.toArray(glVoucherBeans);
			defResultSet.setGlCashPaymentVoucherBeans(glVoucherBeans);
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
		Query query = null;

		if (sessionFactory == null)
			sessionFactory = (SessionFactory) AppUtility.getWebApplicationContext().getBean(AppConstants.hibernateSessionFactory);

		Session session = sessionFactory.openSession();

		try
		{
			if (loginEmployee.getFilterBy() != null && (loginEmployee.getFilterBy().equals(AppConstants.filterByALL)))
			{
				query = session.createQuery(DefQueries.findCashPaymentGlVoucherQuery);
			}
			/*
			 * else if (loginEmployee.getFilterBy()!= null &&
			 * (loginEmployee.getFilterBy
			 * ().equals(AppConstants.filterForOtherScreenUsage))) { query =
			 * session.createQuery(DefQueries.findEmployeePostedQuery); }
			 */
			List<HCashPaymentGlVoucher> resultlist = query.list();
			System.out.println("GL Voucher data found: " + resultlist.size());

			for (HCashPaymentGlVoucher bean : resultlist)
			{
				GlCashPaymentVoucher temp = new GlCashPaymentVoucher();
				temp.setBookTypeShort(bean.getBookTypeShort());
				temp.setExceptionMsgString(bean.getExceptionMsgString());
				temp.setNarration(bean.getNarration());
				temp.setFlagTreasury(bean.getFlagTreasury());
				temp.setRemarksTreasury(bean.getRemarksTreasury());
				temp.setFundShort(bean.getFundShort());
				temp.setGlFormNo(bean.getGlFormNo());
				temp.setGlVoucherDate(bean.getGlVoucherDate());
				temp.setGlVoucherNo(bean.getGlVoucherNo());
				temp.setLogID(bean.getLogID());
				temp.setPeriodID(bean.getPeriodID());
				temp.setPost(bean.getPost());
				temp.setGlCashPaymentDet(cloneDetail(bean.getCashPaymentDet()));

				v.add(temp);
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

	public Set<GlCashPaymentDet> cloneDetail(Set<HCashPaymentDet> temp)
	{
		List<GlCashPaymentDet> list = new ArrayList<>();
		GlCashPaymentDet cash = null;
		for (HCashPaymentDet t : temp)
		{
			cash = new GlCashPaymentDet();
			cash.setCreditAmount(t.getCreditAmount());
			cash.setDebitAmount(t.getDebitAmount());
			cash.setEmployeeID(t.getEmployeeID());
			cash.setGlLineNo(t.getGlLineNo());
			cash.setGlMFCode(t.getGlMfCode());
			cash.setGlSLType(t.getGlSlType());
			cash.setNarration(t.getNarration());
			list.add(cash);
		}
		Set<app.beans.GlCashPaymentDet> set = new HashSet<app.beans.GlCashPaymentDet>();
		set.addAll(list);
		return set;
	}

	private GlCashPaymentVoucher[] glVoucherBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<GlCashPaymentVoucher> v = new Vector<GlCashPaymentVoucher>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
