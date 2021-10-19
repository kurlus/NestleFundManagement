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
import app.beans.GlCashReceiptDet;
import app.beans.GlCashReceiptVoucher;
import app.beans.hibernate.HCashReceiptDet;
import app.beans.hibernate.HCashReceiptGlVoucher;

public class GLCashReceiptTransacs implements Definition 
{

	public GLCashReceiptTransacs()
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
			glVoucherBeans = new GlCashReceiptVoucher[v.size()];
			v.toArray(glVoucherBeans);
			defResultSet.setGlCashReceiptVoucherBeans(glVoucherBeans);
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
				query = session.createQuery(DefQueries.findCashReceiptGlVoucherQuery);
			}
			/*
			 * else if (loginEmployee.getFilterBy()!= null &&
			 * (loginEmployee.getFilterBy
			 * ().equals(AppConstants.filterForOtherScreenUsage))) { query =
			 * session.createQuery(DefQueries.findEmployeePostedQuery); }
			 */
			List<HCashReceiptGlVoucher> resultlist = query.list();
			System.out.println("GL Voucher data found: " + resultlist.size());

			for (HCashReceiptGlVoucher bean : resultlist)
			{
				GlCashReceiptVoucher temp = new GlCashReceiptVoucher();
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
				temp.setGlCashReceiptDet(cloneDetail(bean.getCashReceiptDet()));

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

	public Set<GlCashReceiptDet> cloneDetail(Set<HCashReceiptDet> temp)
	{
		List<GlCashReceiptDet> list = new ArrayList<>();
		GlCashReceiptDet cash = null;
		for (HCashReceiptDet t : temp)
		{
			cash = new GlCashReceiptDet();
			cash.setCreditAmount(t.getCreditAmount());
			cash.setDebitAmount(t.getDebitAmount());
			cash.setEmployeeID(t.getEmployeeID());
			cash.setGlLineNo(t.getGlLineNo());
			cash.setGlMFCode(t.getGlMfCode());
			cash.setGlSLType(t.getGlSlType());
			cash.setNarration(t.getNarration());
			list.add(cash);
		}
		Set<app.beans.GlCashReceiptDet> set = new HashSet<app.beans.GlCashReceiptDet>();
		set.addAll(list);
		return set;
	}

	private GlCashReceiptVoucher[] glVoucherBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<GlCashReceiptVoucher> v = new Vector<GlCashReceiptVoucher>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
