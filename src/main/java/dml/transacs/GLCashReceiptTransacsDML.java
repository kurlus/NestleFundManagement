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
import app.beans.GlCashReceiptDet;
import app.beans.GlCashReceiptVoucher;
import app.beans.hibernate.HCashReceiptDet;
import app.beans.hibernate.HCashReceiptGlVoucher;

public class GLCashReceiptTransacsDML implements DMLHandler {

	public GLCashReceiptTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	{
		if (obj instanceof GlCashReceiptVoucher)
		{
			dmlResultSet = new DMLTransWrapper();
			glCashReceiptVoucher = (GlCashReceiptVoucher) obj;
			glCashReceiptVoucher.setExceptionMsgString(null);
			AppUtility.logger.log(Level.INFO, toString(oprType));
			processedGlCashReceiptVoucher(glCashReceiptVoucher, oprType);
			processedGlCashReceiptVoucher.setFormType("GCR");
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

			processedGlCashReceiptVoucher.setExceptionMsgString(glCashReceiptVoucher.getExceptionMsgString());
			dmlResultSet.setGlCashReceiptVoucher(this.cloneMaster(processedGlCashReceiptVoucher));
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "GL Cash Receipt Det" + glCashReceiptVoucher.getGlVoucherNo() + " ID perform " + oprType;
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
			session.save(processedGlCashReceiptVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.update(processedGlCashReceiptVoucher);
			session.flush();
			tx.commit();
			if (processedGlCashReceiptVoucherForRemove != null && processedGlCashReceiptVoucherForRemove.getCashReceiptDet() != null
					&& processedGlCashReceiptVoucherForRemove.getCashReceiptDet().size() > 0)
			{
				deleteOrphan();
			}

		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.delete(processedGlCashReceiptVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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

			HCashReceiptGlVoucher parent = (HCashReceiptGlVoucher) session.get(HCashReceiptGlVoucher.class, new Long(processedGlCashReceiptVoucherForRemove.getGlVoucherNo()));

			for (HCashReceiptDet child : processedGlCashReceiptVoucherForRemove.getCashReceiptDet())
			{
				HCashReceiptDet obj = (HCashReceiptDet) session.get(HCashReceiptDet.class, new Integer(child.getGlLineNo()));
				parent.getCashReceiptDet().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		}

	}

	private void processedGlCashReceiptVoucher(GlCashReceiptVoucher cashPayVoucher, String oprType)
	{
		HCashReceiptGlVoucher glVoucher = new HCashReceiptGlVoucher();
		HCashReceiptGlVoucher delete = new HCashReceiptGlVoucher();

		glVoucher.setBookTypeShort(cashPayVoucher.getBookTypeShort());
		glVoucher.setNarration(cashPayVoucher.getNarration());
		glVoucher.setFlagTreasury(cashPayVoucher.getFlagTreasury());
		glVoucher.setRemarksTreasury(cashPayVoucher.getRemarksTreasury());
		glVoucher.setFundShort(cashPayVoucher.getFundShort());
		glVoucher.setGlVoucherDate(cashPayVoucher.getGlVoucherDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(cashPayVoucher.getGlVoucherDate());
		glVoucher.setYear(cal.get(Calendar.YEAR));
		glVoucher.setMonth(cal.get(Calendar.MONTH));

		glVoucher.setPeriodID(getPeriodIDByDate(cashPayVoucher.getGlVoucherDate()));

		glVoucher.setFinancialID(getFinancialIDByDate(cashPayVoucher.getGlVoucherDate()));

		glVoucher.setGlFormNo(getFormNumber(glVoucher.getBookTypeShort(), glVoucher.getPeriodID(), glVoucher.getFinancialID()));

		if (oprType.equals(AppConstants.oprSave))
			glVoucher.setGlVoucherNo(0);
		else
		{
			glVoucher.setGlVoucherNo(cashPayVoucher.getGlVoucherNo());
			delete.setGlVoucherNo(cashPayVoucher.getGlVoucherNo());
		}

		glVoucher.setLogID(cashPayVoucher.getLogID());
		glVoucher.setPost(cashPayVoucher.getPost());

		if (cashPayVoucher.getGlCashReceiptDet() != null && cashPayVoucher.getGlCashReceiptDet().size() > 0)
		{
			glVoucher.setCashReceiptDet(new HashSet<HCashReceiptDet>());
			delete.setCashReceiptDet(new HashSet<HCashReceiptDet>());
			for (GlCashReceiptDet cashReceipt : cashPayVoucher.getGlCashReceiptDet())
			{
				HCashReceiptDet glCashReceiptDet = new HCashReceiptDet();
				glCashReceiptDet.setCreditAmount(cashReceipt.getCreditAmount());
				glCashReceiptDet.setDebitAmount(cashReceipt.getDebitAmount());
				glCashReceiptDet.setEmployeeID(cashReceipt.getEmployeeID());
				glCashReceiptDet.setGlMfCode(cashReceipt.getGlMFCode());
				glCashReceiptDet.setGlSlType(cashReceipt.getGlSLType());
				glCashReceiptDet.setCashReceiptGlVoucher(glVoucher);
				glCashReceiptDet.setNarration(cashReceipt.getNarration());
				glCashReceiptDet.setGlLineNo(cashReceipt.getGlLineNo());

				if ((oprType != null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (cashReceipt.getDMLType() != null && cashReceipt.getDMLType().equals("I"))))
				{
					glCashReceiptDet.setGlLineNo(0);
				} else if ((oprType != null && !oprType.equals(AppConstants.oprSave)))
				{
					if (oprType.equals(AppConstants.oprDel)
							|| (oprType.equals(AppConstants.oprEdit) && (cashReceipt.getDMLType() != null && !cashReceipt.getDMLType().equals("I"))))
					{
						glCashReceiptDet.setGlLineNo(cashReceipt.getGlLineNo());
					}

				}

				if (oprType.equals(AppConstants.oprEdit) && (cashReceipt.getDMLType() != null && cashReceipt.getDMLType().equals("D")))
				{
					delete.getCashReceiptDet().add(glCashReceiptDet);
					glVoucher.getCashReceiptDet().remove(glCashReceiptDet);

				} else
					glVoucher.getCashReceiptDet().add(glCashReceiptDet);

			}
		}

		this.processedGlCashReceiptVoucher = glVoucher;
		this.processedGlCashReceiptVoucherForRemove = delete;

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

	private GlCashReceiptVoucher cloneMaster(HCashReceiptGlVoucher voucher)
	{
		GlCashReceiptVoucher temp = new GlCashReceiptVoucher();
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
		temp.setGlCashReceiptDet(new HashSet<GlCashReceiptDet>());
		if (voucher.getCashReceiptDet() != null)
			for (HCashReceiptDet det : voucher.getCashReceiptDet())
			{
				temp.getGlCashReceiptDet().add(this.cloneDetail(det));
			}

		return temp;
	}

	private GlCashReceiptDet cloneDetail(HCashReceiptDet a)
	{
		GlCashReceiptDet t = new GlCashReceiptDet();

		t.setCreditAmount(a.getCreditAmount());
		t.setDebitAmount(a.getDebitAmount());
		t.setEmployeeID(a.getEmployeeID());
		t.setGlLineNo(a.getGlLineNo());
		t.setGlMFCode(a.getGlMfCode());
		t.setGlSLType(a.getGlSlType());
		t.setGlVoucherNo(a.getCashReceiptGlVoucher().getGlVoucherNo());
		t.setNarration(a.getNarration());
		// t.setTest(a.getTest());

		return t;
	}

	private Transaction tx;
	private Session session;
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;
	private GlCashReceiptVoucher glCashReceiptVoucher;
	private HCashReceiptGlVoucher processedGlCashReceiptVoucher;
	private HCashReceiptGlVoucher processedGlCashReceiptVoucherForRemove;

}
