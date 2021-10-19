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
import app.beans.GLBankReceiptDet;
import app.beans.GlBankReceiptVoucher;
import app.beans.hibernate.HBankReceiptDet;
import app.beans.hibernate.HBankReceiptGlVoucher;

public class GLBankReceiptTransacsDML implements DMLHandler {

	public GLBankReceiptTransacsDML()
	{
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	{
		if (obj instanceof GlBankReceiptVoucher)
		{
			dmlResultSet = new DMLTransWrapper();
			glBankReceiptVoucher = (GlBankReceiptVoucher) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			glBankReceiptVoucher.setExceptionMsgString(null);
			processedGlBankReceiptVoucher(glBankReceiptVoucher, oprType);
			processedGlBankReceiptVoucher.setFormType("GBR");
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

			processedGlBankReceiptVoucher.setExceptionMsgString(glBankReceiptVoucher.getExceptionMsgString());
			dmlResultSet.setGlBankReceiptVoucher(this.cloneMaster(processedGlBankReceiptVoucher));
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "GL Bank Receipt Det" + glBankReceiptVoucher.getGlVoucherNo() + " ID perform " + oprType;
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
			session.save(processedGlBankReceiptVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.update(processedGlBankReceiptVoucher);
			session.flush();
			tx.commit();
			if (processedGlBankReceiptVoucherForRemove != null && processedGlBankReceiptVoucherForRemove.getGlBankReceiptDet() != null
					&& processedGlBankReceiptVoucherForRemove.getGlBankReceiptDet().size() > 0)
			{
				deleteOrphan();
			}

		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.delete(processedGlBankReceiptVoucher);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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

			HBankReceiptGlVoucher parent = (HBankReceiptGlVoucher) session.get(HBankReceiptGlVoucher.class, new Long(processedGlBankReceiptVoucherForRemove.getGlVoucherNo()));

			for (HBankReceiptDet child : processedGlBankReceiptVoucherForRemove.getGlBankReceiptDet())
			{
				HBankReceiptDet obj = (HBankReceiptDet) session.get(HBankReceiptDet.class, new Integer(child.getGlLineNo()));
				parent.getGlBankReceiptDet().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glBankReceiptVoucher.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		}

	}

	private void processedGlBankReceiptVoucher(GlBankReceiptVoucher bankPayVoucher, String oprType)
	{
		HBankReceiptGlVoucher glVoucher = new HBankReceiptGlVoucher();
		HBankReceiptGlVoucher delete = new HBankReceiptGlVoucher();

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

		if (bankPayVoucher.getGlBankReceiptDet() != null && bankPayVoucher.getGlBankReceiptDet().size() > 0)
		{
			glVoucher.setGlBankReceiptDet(new HashSet<HBankReceiptDet>());
			delete.setGlBankReceiptDet(new HashSet<HBankReceiptDet>());
			for (GLBankReceiptDet bankReceipt : bankPayVoucher.getGlBankReceiptDet())
			{
				HBankReceiptDet glBankReceiptDet = new HBankReceiptDet();
				glBankReceiptDet.setCreditAmount(bankReceipt.getCreditAmount());
				glBankReceiptDet.setDebitAmount(bankReceipt.getDebitAmount());
				glBankReceiptDet.setEmployeeID(bankReceipt.getEmployeeID());
				glBankReceiptDet.setGlMFCode(bankReceipt.getGlMFCode());
				glBankReceiptDet.setGlSLType(bankReceipt.getGlSLType());
				glBankReceiptDet.setGlVoucher(glVoucher);
				glBankReceiptDet.setNarration(bankReceipt.getNarration());
				glBankReceiptDet.setChequeNo(bankReceipt.getChequeNo());
				glBankReceiptDet.setGlLineNo(bankReceipt.getGlLineNo());

				if ((oprType != null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (bankReceipt.getDMLType() != null && bankReceipt.getDMLType().equals("I"))))
				{
					glBankReceiptDet.setGlLineNo(0);
				} else if ((oprType != null && !oprType.equals(AppConstants.oprSave)))
				{
					if (oprType.equals(AppConstants.oprDel)
							|| (oprType.equals(AppConstants.oprEdit) && (bankReceipt.getDMLType() != null && !bankReceipt.getDMLType().equals("I"))))
					{
						glBankReceiptDet.setGlLineNo(bankReceipt.getGlLineNo());
					}

				}

				if (oprType.equals(AppConstants.oprEdit) && (bankReceipt.getDMLType() != null && bankReceipt.getDMLType().equals("D")))
				{
					delete.getGlBankReceiptDet().add(glBankReceiptDet);
					glVoucher.getGlBankReceiptDet().remove(glBankReceiptDet);

				} else
					glVoucher.getGlBankReceiptDet().add(glBankReceiptDet);

			}
		}

		this.processedGlBankReceiptVoucher = glVoucher;
		this.processedGlBankReceiptVoucherForRemove = delete;

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

	private GlBankReceiptVoucher cloneMaster(HBankReceiptGlVoucher voucher)
	{
		GlBankReceiptVoucher temp = new GlBankReceiptVoucher();
		temp.setBookTypeShort(voucher.getBookTypeShort());
		temp.setNarration(voucher.getNarration());
		temp.setFundShort(voucher.getFundShort());
		temp.setGlFormNo(voucher.getGlFormNo());
		temp.setGlVoucherDate(voucher.getGlVoucherDate());
		temp.setGlVoucherNo(voucher.getGlVoucherNo());
		temp.setFlagTreasury(voucher.getFlagTreasury());
		temp.setRemarksTreasury(voucher.getRemarksTreasury());
		temp.setExceptionMsgString(voucher.getExceptionMsgString());
		temp.setLogID(voucher.getLogID());
		temp.setPeriodID(voucher.getPeriodID());
		temp.setPost(voucher.getPost());
		temp.setGlBankReceiptDet(new HashSet<GLBankReceiptDet>());
		if (voucher.getGlBankReceiptDet() != null)
			for (HBankReceiptDet det : voucher.getGlBankReceiptDet())
			{
				temp.getGlBankReceiptDet().add(this.cloneDetail(det));
			}

		return temp;
	}

	private GLBankReceiptDet cloneDetail(HBankReceiptDet a)
	{
		GLBankReceiptDet t = new GLBankReceiptDet();

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
	private GlBankReceiptVoucher glBankReceiptVoucher;
	private HBankReceiptGlVoucher processedGlBankReceiptVoucher;
	private HBankReceiptGlVoucher processedGlBankReceiptVoucherForRemove;

}
