package dml.transacs;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import database.DMLOperations;
import database.JDBCTemplateFactory;
import dml.def.DMLHandler;
import utilities.AppUtility;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;
import app.beans.GlJournalDet;
import app.beans.GlJournalVoucher;
import app.beans.hibernate.HJournalDet;
import app.beans.hibernate.HJournalGlVoucher;

public class GlJournalTransacsDML implements DMLHandler {

	public GlJournalTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	{
		if (obj instanceof GlJournalVoucher)
		{
			dmlResultSet = new DMLTransWrapper();
			glJournalVoucher = (GlJournalVoucher) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			processedGlJournalVoucher(glJournalVoucher, oprType);
			processedGlJournalVoucher.setFormType("GJV");
			switch (oprType)
			{
			case AppConstants.oprSave:
				save();
				break;
			case AppConstants.oprEdit:
				update();
				break;
			case AppConstants.oprDel:
				delete();
				break;
			default:
				AppUtility.logger.log(Level.SEVERE, "Wrong operation type is requested " + oprType);
				break;
			}

			glJournalVoucher.setExceptionMsgString(null);
			processedGlJournalVoucher.setExceptionMsgString(glJournalVoucher.getExceptionMsgString());
			dmlResultSet.setGlJournalVoucher(this.cloneMaster(processedGlJournalVoucher));
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "GL Bank Payment Det" + glJournalVoucher.getGlVoucherNo() + " ID perform " + oprType;
		/* + " operation. reference log id is " + loanApprove.getLogID(); */
		return str;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private void save()
	{
		try
		{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(processedGlJournalVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glJournalVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		} finally
		{
			session.close();
		}

	}

	private void update()
	{
		try
		{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(processedGlJournalVoucher);
			session.flush();
			tx.commit();
			if (processedGlJournalVoucherForRemove != null && processedGlJournalVoucherForRemove.getGlJournalDet() != null
					&& processedGlJournalVoucherForRemove.getGlJournalDet().size() > 0)
			{
				deleteOrphan();
			}

		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glJournalVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		} finally
		{
			session.close();
		}

	}

	private void delete()
	{
		try
		{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(processedGlJournalVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glJournalVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		} finally
		{
			session.close();
		}

	}

	private void deleteOrphan()
	{
		try
		{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			HJournalGlVoucher parent = (HJournalGlVoucher) session.get(HJournalGlVoucher.class, new Long(processedGlJournalVoucherForRemove.getGlVoucherNo()));

			for (HJournalDet child : processedGlJournalVoucherForRemove.getGlJournalDet())
			{
				HJournalDet obj = (HJournalDet) session.get(HJournalDet.class, new Integer(child.getGlLineNo()));
				parent.getGlJournalDet().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glJournalVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		}

	}

	private void processedGlJournalVoucher(GlJournalVoucher bankPayVoucher, String oprType)
	{
		HJournalGlVoucher glVoucher = new HJournalGlVoucher();
		HJournalGlVoucher delete = new HJournalGlVoucher();

		glVoucher.setBookTypeShort(bankPayVoucher.getBookTypeShort());
		glVoucher.setNarration(bankPayVoucher.getNarration());
		glVoucher.setFlagTreasury(bankPayVoucher.getFlagTreasury());
		glVoucher.setRemarksTreasury(bankPayVoucher.getRemarksTreasury());
		glVoucher.setFundShort(bankPayVoucher.getFundShort());
		glVoucher.setGlVoucherDate(bankPayVoucher.getGlVoucherDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(bankPayVoucher.getGlVoucherDate());
		glVoucher.setYear(cal.get(Calendar.YEAR));
		glVoucher.setMonth(cal.get(Calendar.MONTH));

		glVoucher.setPeriodID(getPeriodIDByDate(bankPayVoucher.getGlVoucherDate()));

		glVoucher.setFinancialID(getFinancialIDByDate(bankPayVoucher.getGlVoucherDate()));

		glVoucher.setGlFormNo(getFormNumber(glVoucher.getBookTypeShort(), glVoucher.getPeriodID(), glVoucher.getFinancialID()));

		if (oprType.equals(AppConstants.oprSave))
			glVoucher.setGlVoucherNo(0);
		else
		{
			glVoucher.setGlVoucherNo(bankPayVoucher.getGlVoucherNo());
			delete.setGlVoucherNo(bankPayVoucher.getGlVoucherNo());
		}

		glVoucher.setLogID(bankPayVoucher.getLogID());
		glVoucher.setPost(bankPayVoucher.getPost());

		if (bankPayVoucher.getGlJournalDet() != null && bankPayVoucher.getGlJournalDet().size() > 0)
		{
			glVoucher.setGlJournalDet(new HashSet<HJournalDet>());
			delete.setGlJournalDet(new HashSet<HJournalDet>());
			for (GlJournalDet journal : bankPayVoucher.getGlJournalDet())
			{
				HJournalDet glJournalDet = new HJournalDet();
				glJournalDet.setCreditAmount(journal.getCreditAmount());
				glJournalDet.setDebitAmount(journal.getDebitAmount());
				glJournalDet.setEmployeeID(journal.getEmployeeID());
				glJournalDet.setGlMFCode(journal.getGlMFCode());
				glJournalDet.setGlSLType(journal.getGlSLType());
				glJournalDet.setGlVoucher(glVoucher);
				glJournalDet.setNarration(journal.getNarration());
				glJournalDet.setChequeNo(journal.getChequeNo());
				glJournalDet.setGlLineNo(journal.getGlLineNo());

				if ((oprType != null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (journal.getDMLType() != null && journal.getDMLType().equals("I"))))
				{
					glJournalDet.setGlLineNo(0);
				} else if ((oprType != null && !oprType.equals(AppConstants.oprSave)))
				{
					if (oprType.equals(AppConstants.oprDel) || (oprType.equals(AppConstants.oprEdit) && (journal.getDMLType() != null && !journal.getDMLType().equals("I"))))
					{
						glJournalDet.setGlLineNo(journal.getGlLineNo());
					}

				}

				if (oprType.equals(AppConstants.oprEdit) && (journal.getDMLType() != null && journal.getDMLType().equals("D")))
				{
					delete.getGlJournalDet().add(glJournalDet);
					glVoucher.getGlJournalDet().remove(glJournalDet);

				} else
					glVoucher.getGlJournalDet().add(glJournalDet);

			}
		}

		this.processedGlJournalVoucher = glVoucher;
		this.processedGlJournalVoucherForRemove = delete;

	}

	private long getPeriodIDByDate(Date date)
	{
		long result = 0;
		SimpleJdbcCall procedureCall = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AppConstants.paramDate, date);
		map.put(AppConstants.paramPeriodType, 'M');

		try
		{
			procedureCall = new SimpleJdbcCall(JDBCTemplateFactory.getDataSource()).withFunctionName(AppConstants.funcPeriodID);
			SqlParameterSource sourceParams = new MapSqlParameterSource(map);
			Map output = procedureCall.execute(sourceParams);
			result = (long) output.get("RETURN_VALUE");

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private int getFinancialIDByDate(Date date)
	{
		int result = 0;
		SimpleJdbcCall procedureCall = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AppConstants.paramDate, date);

		try
		{
			procedureCall = new SimpleJdbcCall(JDBCTemplateFactory.getDataSource()).withFunctionName(AppConstants.funcFinancialID);
			SqlParameterSource sourceParams = new MapSqlParameterSource(map);
			Map output = procedureCall.execute(sourceParams);
			result = (int) output.get("RETURN_VALUE");

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

		return result;
	}

	private int getFormNumber(String bookType, long periodID, int financialID)
	{
		int result = 0;
		SimpleJdbcCall procedureCall = null;
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(AppConstants.paramBookType, bookType);
		map.put(AppConstants.paramPeriodID, periodID);
		map.put(AppConstants.paramFinancialID, financialID);

		try
		{
			procedureCall = new SimpleJdbcCall(JDBCTemplateFactory.getDataSource()).withFunctionName(AppConstants.funcFormNumber);
			SqlParameterSource sourceParams = new MapSqlParameterSource(map);
			Map output = procedureCall.execute(sourceParams);
			result = (int) output.get("RETURN_VALUE");

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

		return result;
	}

	private GlJournalVoucher cloneMaster(HJournalGlVoucher voucher)
	{
		GlJournalVoucher temp = new GlJournalVoucher();
		temp.setBookTypeShort(voucher.getBookTypeShort());
		temp.setNarration(voucher.getNarration());
		temp.setFundShort(voucher.getFundShort());
		temp.setGlFormNo(voucher.getGlFormNo());
		temp.setGlVoucherDate(voucher.getGlVoucherDate());

		temp.setExceptionMsgString(voucher.getExceptionMsgString());
		temp.setGlVoucherNo(voucher.getGlVoucherNo());
		temp.setLogID(voucher.getLogID());
		temp.setPeriodID(voucher.getPeriodID());
		temp.setPost(voucher.getPost());
		temp.setGlJournalDet(new HashSet<GlJournalDet>());
		if (voucher.getGlJournalDet() != null)
			for (HJournalDet det : voucher.getGlJournalDet())
			{
				temp.getGlJournalDet().add(this.cloneDetail(det));
			}

		return temp;
	}

	private GlJournalDet cloneDetail(HJournalDet a)
	{
		GlJournalDet t = new GlJournalDet();

		t.setCreditAmount(a.getCreditAmount());
		t.setDebitAmount(a.getDebitAmount());
		t.setEmployeeID(a.getEmployeeID());
		t.setGlLineNo(a.getGlLineNo());
		t.setGlMFCode(a.getGlMFCode());
		t.setGlSLType(a.getGlSLType());
		t.setGlVoucherNo(a.getGlVoucher().getGlVoucherNo());
		t.setNarration(a.getNarration());
		// t.setTest(a.getTest());

		return t;
	}

	private Transaction tx;
	private Session session;
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;
	private GlJournalVoucher glJournalVoucher;
	private HJournalGlVoucher processedGlJournalVoucher;
	private HJournalGlVoucher processedGlJournalVoucherForRemove;

}
