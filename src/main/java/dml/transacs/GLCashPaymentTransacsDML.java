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
import app.beans.GlCashPaymentDet;
import app.beans.GlCashPaymentVoucher;
import app.beans.hibernate.HCashPaymentDet;
import app.beans.hibernate.HCashPaymentGlVoucher;

public class GLCashPaymentTransacsDML implements DMLHandler {

	public GLCashPaymentTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	{
		if (obj instanceof GlCashPaymentVoucher)
		{
			dmlResultSet = new DMLTransWrapper();
			glCashPaymentVoucher = (GlCashPaymentVoucher) obj;
			glCashPaymentVoucher.setExceptionMsgString(null);
			AppUtility.logger.log(Level.INFO, toString(oprType));
			processedGlCashPaymentVoucher(glCashPaymentVoucher, oprType);
			processedGlCashPaymentVoucher.setFormType("GCP");
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

			processedGlCashPaymentVoucher.setExceptionMsgString(glCashPaymentVoucher.getExceptionMsgString());
			dmlResultSet.setGlCashPaymentVoucher(this.cloneMaster(processedGlCashPaymentVoucher));
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "GL Cash Payment Det" + glCashPaymentVoucher.getGlVoucherNo() + " ID perform " + oprType;
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
			session.save(processedGlCashPaymentVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.update(processedGlCashPaymentVoucher);
			session.flush();
			tx.commit();
			if (processedGlCashPaymentVoucherForRemove != null && processedGlCashPaymentVoucherForRemove.getCashPaymentDet() != null
					&& processedGlCashPaymentVoucherForRemove.getCashPaymentDet().size() > 0)
			{
				deleteOrphan();
			}

		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.delete(processedGlCashPaymentVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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

			HCashPaymentGlVoucher parent = (HCashPaymentGlVoucher) session.get(HCashPaymentGlVoucher.class, new Long(processedGlCashPaymentVoucherForRemove.getGlVoucherNo()));

			for (HCashPaymentDet child : processedGlCashPaymentVoucherForRemove.getCashPaymentDet())
			{
				HCashPaymentDet obj = (HCashPaymentDet) session.get(HCashPaymentDet.class, new Integer(child.getGlLineNo()));
				parent.getCashPaymentDet().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glCashPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		}

	}

	private void processedGlCashPaymentVoucher(GlCashPaymentVoucher cashPayVoucher, String oprType)
	{
		HCashPaymentGlVoucher glVoucher = new HCashPaymentGlVoucher();
		HCashPaymentGlVoucher delete = new HCashPaymentGlVoucher();

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

		if (cashPayVoucher.getGlCashPaymentDet() != null && cashPayVoucher.getGlCashPaymentDet().size() > 0)
		{
			glVoucher.setCashPaymentDet(new HashSet<HCashPaymentDet>());
			delete.setCashPaymentDet(new HashSet<HCashPaymentDet>());
			for (GlCashPaymentDet cashPayment : cashPayVoucher.getGlCashPaymentDet())
			{
				HCashPaymentDet glCashPaymentDet = new HCashPaymentDet();
				glCashPaymentDet.setCreditAmount(cashPayment.getCreditAmount());
				glCashPaymentDet.setDebitAmount(cashPayment.getDebitAmount());
				glCashPaymentDet.setEmployeeID(cashPayment.getEmployeeID());
				glCashPaymentDet.setGlMfCode(cashPayment.getGlMFCode());
				glCashPaymentDet.setGlSlType(cashPayment.getGlSLType());
				glCashPaymentDet.setGlVoucher(glVoucher);
				glCashPaymentDet.setNarration(cashPayment.getNarration());
				glCashPaymentDet.setGlLineNo(cashPayment.getGlLineNo());

				if ((oprType != null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (cashPayment.getDMLType() != null && cashPayment.getDMLType().equals("I"))))
				{
					glCashPaymentDet.setGlLineNo(0);
				} else if ((oprType != null && !oprType.equals(AppConstants.oprSave)))
				{
					if (oprType.equals(AppConstants.oprDel)
							|| (oprType.equals(AppConstants.oprEdit) && (cashPayment.getDMLType() != null && !cashPayment.getDMLType().equals("I"))))
					{
						glCashPaymentDet.setGlLineNo(cashPayment.getGlLineNo());
					}

				}

				if (oprType.equals(AppConstants.oprEdit) && (cashPayment.getDMLType() != null && cashPayment.getDMLType().equals("D")))
				{
					delete.getCashPaymentDet().add(glCashPaymentDet);
					glVoucher.getCashPaymentDet().remove(glCashPaymentDet);

				} else
					glVoucher.getCashPaymentDet().add(glCashPaymentDet);

			}
		}

		this.processedGlCashPaymentVoucher = glVoucher;
		this.processedGlCashPaymentVoucherForRemove = delete;

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

	private GlCashPaymentVoucher cloneMaster(HCashPaymentGlVoucher voucher)
	{
		GlCashPaymentVoucher temp = new GlCashPaymentVoucher();
		temp.setBookTypeShort(voucher.getBookTypeShort());
		temp.setNarration(voucher.getNarration());
		temp.setFundShort(voucher.getFundShort());
		temp.setGlFormNo(voucher.getGlFormNo());
		temp.setGlVoucherDate(voucher.getGlVoucherDate());
		temp.setGlVoucherNo(voucher.getGlVoucherNo());
		temp.setExceptionMsgString(voucher.getExceptionMsgString());
		temp.setLogID(voucher.getLogID());
		temp.setPeriodID(voucher.getPeriodID());
		temp.setPost(voucher.getPost());
		temp.setGlCashPaymentDet(new HashSet<GlCashPaymentDet>());
		if (voucher.getCashPaymentDet() != null)
			for (HCashPaymentDet det : voucher.getCashPaymentDet())
			{
				temp.getGlCashPaymentDet().add(this.cloneDetail(det));
			}

		return temp;
	}

	private GlCashPaymentDet cloneDetail(HCashPaymentDet a)
	{
		GlCashPaymentDet t = new GlCashPaymentDet();

		t.setCreditAmount(a.getCreditAmount());
		t.setDebitAmount(a.getDebitAmount());
		t.setEmployeeID(a.getEmployeeID());
		t.setGlLineNo(a.getGlLineNo());
		t.setGlMFCode(a.getGlMfCode());
		t.setGlSLType(a.getGlSlType());
		t.setGlVoucherNo(a.getGlVoucher().getGlVoucherNo());
		t.setNarration(a.getNarration());
		// t.setTest(a.getTest());

		return t;
	}

	private Transaction tx;
	private Session session;
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;
	private GlCashPaymentVoucher glCashPaymentVoucher;
	private HCashPaymentGlVoucher processedGlCashPaymentVoucher;
	private HCashPaymentGlVoucher processedGlCashPaymentVoucherForRemove;

}
