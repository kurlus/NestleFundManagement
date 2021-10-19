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
import app.beans.GLBankPaymentDet;
import app.beans.GLBankPaymentVoucher;
import app.beans.hibernate.HBankPaymentDet;
import app.beans.hibernate.HBankPaymentGlVoucher;

public class GLBankPaymentTransacsDML implements DMLHandler {

	public GLBankPaymentTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	{
		if (obj instanceof GLBankPaymentVoucher)
		{
			dmlResultSet = new DMLTransWrapper();
			glBankPaymentVoucher = (GLBankPaymentVoucher) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			glBankPaymentVoucher.setExceptionMsgString(null);
			// processedGLBankPaymentVoucher(glBankPaymentVoucher, oprType);
			processedGlBankPaymentVoucher.setFormType("GBP");
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

			processedGlBankPaymentVoucher.setExceptionMsgString(glBankPaymentVoucher.getExceptionMsgString());
			dmlResultSet.setGlBankPaymentVoucher(this.cloneMaster(processedGlBankPaymentVoucher));
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "GL Bank Payment Det" + glBankPaymentVoucher.getGlVoucherNo() + " ID perform " + oprType;
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
			session.save(processedGlBankPaymentVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.update(processedGlBankPaymentVoucher);
			session.flush();
			tx.commit();
			if (processedGlBankPaymentVoucherForRemove != null && processedGlBankPaymentVoucherForRemove.getGlBankPaymentDet() != null
					&& processedGlBankPaymentVoucherForRemove.getGlBankPaymentDet().size() > 0)
			{
				deleteOrphan();
			}

		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.delete(processedGlBankPaymentVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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

			HBankPaymentGlVoucher parent = (HBankPaymentGlVoucher) session.get(HBankPaymentGlVoucher.class, new Long(processedGlBankPaymentVoucherForRemove.getGlVoucherNo()));

			for (HBankPaymentDet child : processedGlBankPaymentVoucherForRemove.getGlBankPaymentDet())
			{
				HBankPaymentDet obj = (HBankPaymentDet) session.get(HBankPaymentDet.class, new Integer(child.getGlLineNo()));
				parent.getGlBankPaymentDet().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankPaymentVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		}

	}

	private void processedGlBankPaymentVoucher(GLBankPaymentVoucher bankPayVoucher, String oprType)
	{
		HBankPaymentGlVoucher glVoucher = new HBankPaymentGlVoucher();
		HBankPaymentGlVoucher delete = new HBankPaymentGlVoucher();

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

		if (bankPayVoucher.getGlBankPaymentDet() != null && bankPayVoucher.getGlBankPaymentDet().size() > 0)
		{
			glVoucher.setGlBankPaymentDet(new HashSet<HBankPaymentDet>());
			delete.setGlBankPaymentDet(new HashSet<HBankPaymentDet>());
			for (GLBankPaymentDet bankPayment : bankPayVoucher.getGlBankPaymentDet())
			{
				HBankPaymentDet glBankPaymentDet = new HBankPaymentDet();
				glBankPaymentDet.setCreditAmount(bankPayment.getCreditAmount());
				glBankPaymentDet.setDebitAmount(bankPayment.getDebitAmount());
				glBankPaymentDet.setEmployeeID(bankPayment.getEmployeeID());
				glBankPaymentDet.setGlMFCode(bankPayment.getGlMFCode());
				glBankPaymentDet.setGlSLType(bankPayment.getGlSLType());
				glBankPaymentDet.setGlVoucher(glVoucher);
				glBankPaymentDet.setNarration(bankPayment.getNarration());
				glBankPaymentDet.setChequeNo(bankPayment.getChequeNo());
				glBankPaymentDet.setGlLineNo(bankPayment.getGlLineNo());

				if ((oprType != null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (bankPayment.getDMLType() != null && bankPayment.getDMLType().equals("I"))))
				{
					glBankPaymentDet.setGlLineNo(0);
				} else if ((oprType != null && !oprType.equals(AppConstants.oprSave)))
				{
					if (oprType.equals(AppConstants.oprDel)
							|| (oprType.equals(AppConstants.oprEdit) && (bankPayment.getDMLType() != null && !bankPayment.getDMLType().equals("I"))))
					{
						glBankPaymentDet.setGlLineNo(bankPayment.getGlLineNo());
					}

				}

				if (oprType.equals(AppConstants.oprEdit) && (bankPayment.getDMLType() != null && bankPayment.getDMLType().equals("D")))
				{
					delete.getGlBankPaymentDet().add(glBankPaymentDet);
					glVoucher.getGlBankPaymentDet().remove(glBankPaymentDet);

				} else
					glVoucher.getGlBankPaymentDet().add(glBankPaymentDet);

			}
		}

		this.processedGlBankPaymentVoucher = glVoucher;
		this.processedGlBankPaymentVoucherForRemove = delete;

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

	private GLBankPaymentVoucher cloneMaster(HBankPaymentGlVoucher voucher)
	{
		GLBankPaymentVoucher temp = new GLBankPaymentVoucher();
		temp.setBookTypeShort(voucher.getBookTypeShort());
		temp.setNarration(voucher.getNarration());
		temp.setFundShort(voucher.getFundShort());
		temp.setGlFormNo(voucher.getGlFormNo());
		temp.setGlVoucherDate(voucher.getGlVoucherDate());
		temp.setGlVoucherNo(voucher.getGlVoucherNo());
		temp.setFlagTreasury(voucher.getFlagTreasury());
		temp.setRemarksTreasury(voucher.getRemarksTreasury());
		temp.setLogID(voucher.getLogID());
		temp.setPeriodID(voucher.getPeriodID());
		temp.setExceptionMsgString(voucher.getExceptionMsgString());
		temp.setPost(voucher.getPost());
		temp.setGlBankPaymentDet(new HashSet<GLBankPaymentDet>());
		if (voucher.getGlBankPaymentDet() != null)
			for (HBankPaymentDet det : voucher.getGlBankPaymentDet())
			{
				temp.getGlBankPaymentDet().add(this.cloneDetail(det));
			}

		return temp;
	}

	private GLBankPaymentDet cloneDetail(HBankPaymentDet a)
	{
		GLBankPaymentDet t = new GLBankPaymentDet();

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
	private GLBankPaymentVoucher glBankPaymentVoucher;
	private HBankPaymentGlVoucher processedGlBankPaymentVoucher;
	private HBankPaymentGlVoucher processedGlBankPaymentVoucherForRemove;

}
