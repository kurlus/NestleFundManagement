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
import app.beans.GLBankReceiptDet;
import app.beans.GlBankReceiptVoucher;
import app.beans.hibernate.HBankReceiptDet;
import app.beans.hibernate.HBankReceiptGlVoucher;

public class GLBankReceiptTransacs implements Definition 
{

	public GLBankReceiptTransacs()
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
			glVoucherBeans = new GlBankReceiptVoucher[v.size()];
			v.toArray(glVoucherBeans);
			defResultSet.setGlBankReceiptVoucherBeans(glVoucherBeans);
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
				query = session.createQuery(DefQueries.findBankReceiptGlVoucherQuery);
			}
			/*
			 * else if (loginEmployee.getFilterBy()!= null &&
			 * (loginEmployee.getFilterBy
			 * ().equals(AppConstants.filterForOtherScreenUsage))) { query =
			 * session.createQuery(DefQueries.findEmployeePostedQuery); }
			 */
			List<HBankReceiptGlVoucher> resultlist = query.list();
			System.out.println("GL Voucher data found: " + resultlist.size());

			for (HBankReceiptGlVoucher bean : resultlist)
			{
				GlBankReceiptVoucher temp = new GlBankReceiptVoucher();
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
				temp.setGlBankReceiptDet(cloneDetail(bean.getGlBankReceiptDet()));

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

	public Set<GLBankReceiptDet> cloneDetail(Set<HBankReceiptDet> temp)
	{
		List<GLBankReceiptDet> list = new ArrayList<>();
		GLBankReceiptDet bank = null;
		for (HBankReceiptDet t : temp)
		{
			bank = new GLBankReceiptDet();
			bank.setCreditAmount(t.getCreditAmount());
			bank.setDebitAmount(t.getDebitAmount());
			bank.setEmployeeID(t.getEmployeeID());
			bank.setGlLineNo(t.getGlLineNo());
			bank.setGlMFCode(t.getGlMFCode());
			bank.setGlSLType(t.getGlSLType());
			bank.setNarration(t.getNarration());
			bank.setChequeNo(t.getChequeNo());
			list.add(bank);
		}
		Set<app.beans.GLBankReceiptDet> set = new HashSet<app.beans.GLBankReceiptDet>();
		set.addAll(list);
		return set;
	}

	private GlBankReceiptVoucher[] glVoucherBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<GlBankReceiptVoucher> v = new Vector<GlBankReceiptVoucher>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
