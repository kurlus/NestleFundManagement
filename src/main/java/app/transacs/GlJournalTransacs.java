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
import app.beans.GlJournalDet;
import app.beans.GlJournalVoucher;
import app.beans.hibernate.HJournalDet;
import app.beans.hibernate.HJournalGlVoucher;

public class GlJournalTransacs implements Definition 
{

	public GlJournalTransacs()
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
			glVoucherBeans = new GlJournalVoucher[v.size()];
			v.toArray(glVoucherBeans);
			defResultSet.setGlJournalVoucherBeans(glVoucherBeans);
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
				query = session.createQuery(DefQueries.findJournalGlVoucherQuery);
			}
			/*
			 * else if (loginEmployee.getFilterBy()!= null &&
			 * (loginEmployee.getFilterBy
			 * ().equals(AppConstants.filterForOtherScreenUsage))) { query =
			 * session.createQuery(DefQueries.findEmployeePostedQuery); }
			 */
			List<HJournalGlVoucher> resultlist = query.list();
			System.out.println("GL Voucher data found: " + resultlist.size());

			for (HJournalGlVoucher bean : resultlist)
			{
				GlJournalVoucher temp = new GlJournalVoucher();
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
				temp.setGlJournalDet(cloneDetail(bean.getGlJournalDet()));

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

	public Set<GlJournalDet> cloneDetail(Set<HJournalDet> temp)
	{
		List<GlJournalDet> list = new ArrayList<>();
		GlJournalDet journal = null;
		for (HJournalDet t : temp)
		{
			journal = new GlJournalDet();
			journal.setCreditAmount(t.getCreditAmount());
			journal.setDebitAmount(t.getDebitAmount());
			journal.setEmployeeID(t.getEmployeeID());
			journal.setGlLineNo(t.getGlLineNo());
			journal.setGlMFCode(t.getGlMFCode());
			journal.setGlSLType(t.getGlSLType());
			journal.setNarration(t.getNarration());
			journal.setChequeNo(t.getChequeNo());
			list.add(journal);
		}
		Set<app.beans.GlJournalDet> set = new HashSet<app.beans.GlJournalDet>();
		set.addAll(list);
		return set;
	}

	private GlJournalVoucher[] glVoucherBeans;
	private DefResultSet defResultSet;
	private LoginEmployee loginEmployee;
	private SessionFactory sessionFactory;
	private Vector<GlJournalVoucher> v = new Vector<GlJournalVoucher>();
	private Map<String, Object> parameters = new HashMap<String, Object>();

}
