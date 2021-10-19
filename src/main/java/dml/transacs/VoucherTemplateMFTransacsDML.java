package dml.transacs;

import java.util.HashSet;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dml.def.DMLHandler;
import dml.def.DMLTransWrapper;
import utilities.AppUtility;
import utilities.AppConstants;
import app.beans.GlVoucherTemplateMf;
import app.beans.GlVoucherTemplateDet;
import app.beans.hibernate.HGlVoucherTemplateDet;
import app.beans.hibernate.HGlVoucherTemplateMf;

public class VoucherTemplateMFTransacsDML implements DMLHandler 
{
	public VoucherTemplateMFTransacsDML()
	{
		
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)throws Exception 
	{
		if (obj instanceof GlVoucherTemplateMf)
		{
			dmlResultSet = new DMLTransWrapper();
			glVoucherTemplateMf = (GlVoucherTemplateMf) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			processedGlVoucherTemplateMf(oprType);
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
			glVoucherTemplateMf.setTemplateID(processedGlVoucherTemplateMf.getTemplateID());
			dmlResultSet.setGlVoucherTemplateMfBean(glVoucherTemplateMf);
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType)
	{
		String str = "GL Voucher template MF" + glVoucherTemplateMf.getTemplateID() + " ID perform " + oprType;
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
			session.save(processedGlVoucherTemplateMf);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glVoucherTemplateMf.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			session.saveOrUpdate(processedGlVoucherTemplateMf);
			session.flush();
			tx.commit();
			
			if(processedGlVoucherTemplateMfForRemove.getGlVoucherTemplateDet().size()>0)
			{
				deleteOrphan();
			}
			glVoucherTemplateMf.setExceptionMsgString(AppConstants.BLANK);	
			
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glVoucherTemplateMf.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

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
			
			HGlVoucherTemplateMf parent = (HGlVoucherTemplateMf) session.get(HGlVoucherTemplateMf.class, new Integer(processedGlVoucherTemplateMfForRemove.getTemplateID()));
			
			for(HGlVoucherTemplateDet child: processedGlVoucherTemplateMfForRemove.getGlVoucherTemplateDet())
			{
				HGlVoucherTemplateDet obj = (HGlVoucherTemplateDet)session.get(HGlVoucherTemplateDet.class, new Integer(child.getGlLineNo()));
				parent.getGlVoucherTemplateDet().remove(obj);
			}
			session.saveOrUpdate(parent);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glVoucherTemplateMf.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		} 
		/*finally
		{
			session.close();
		}*/

	}

	private void delete()
	{
		try
		{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(processedGlVoucherTemplateMf);
			session.flush();
			tx.commit();
		} catch (RuntimeException e)
		{
			tx.rollback();
			AppUtility.logger.log(Level.SEVERE, " transaction rollback due to " + e.getMessage() + "Caused By : " + e.getCause());
			glVoucherTemplateMf.setExceptionMsgString(e.getMessage() + "Caused By : " + e.getCause());

		} finally
		{
			session.close();
		}

	}

	private void processedGlVoucherTemplateMf(String oprType) throws Exception
	{
		HGlVoucherTemplateMf hobj = new HGlVoucherTemplateMf();
		HGlVoucherTemplateMf delete = new HGlVoucherTemplateMf();
		
		hobj.setPost(glVoucherTemplateMf.getPost());
		hobj.setLogID(glVoucherTemplateMf.getLogID());
		hobj.setTypeShort(glVoucherTemplateMf.getTypeShort());
		
		hobj.setDescription(glVoucherTemplateMf.getDescription());
		hobj.setTemplateName(glVoucherTemplateMf.getTemplateName());
		hobj.setBookTypeShort(glVoucherTemplateMf.getBookTypeShort());
		
		if (oprType.equals(AppConstants.oprSave))
			hobj.setTemplateID(0);
		else 
		{
			hobj.setTemplateID(glVoucherTemplateMf.getTemplateID());
			
			if(oprType.equals(AppConstants.oprEdit))
				delete  = (HGlVoucherTemplateMf) hobj.clone();
		}
		
		
		if (glVoucherTemplateMf.getGlVoucherTemplateDet() != null && glVoucherTemplateMf.getGlVoucherTemplateDet().size() > 0)
		{
			hobj.setGlVoucherTemplateDet(new HashSet<HGlVoucherTemplateDet>());
			delete.setGlVoucherTemplateDet(new HashSet<HGlVoucherTemplateDet>());
			for (GlVoucherTemplateDet det : glVoucherTemplateMf.getGlVoucherTemplateDet())
			{
				HGlVoucherTemplateDet voucherTemplateDet = new HGlVoucherTemplateDet();
				
				voucherTemplateDet.setDrCr(det.getDrCr());
				voucherTemplateDet.setAmount(det.getAmount());
				voucherTemplateDet.setGlVoucherTemplateMf(hobj);
				voucherTemplateDet.setGlMfCode(det.getGlMfCode());
				voucherTemplateDet.setGlSlType(det.getGlSlType());
				voucherTemplateDet.setNarration(det.getNarration());
				voucherTemplateDet.setEmployeeID(det.getEmployeeID());
				
				if((oprType!=null && oprType.equals(AppConstants.oprSave))
						|| (oprType.equals(AppConstants.oprEdit) && (det.getDmlType()!= null && det.getDmlType().equals("I"))))
				{
					voucherTemplateDet.setGlLineNo(0);	
				}
				else if((oprType!=null && !oprType.equals(AppConstants.oprSave)))
				{
					if(oprType.equals(AppConstants.oprDel) || (oprType.equals(AppConstants.oprEdit) && (det.getDmlType()!= null && !det.getDmlType().equals("I"))))
					{
						voucherTemplateDet.setGlLineNo(det.getGlLineNo());
					}
						
				}
				
				if(oprType.equals(AppConstants.oprEdit) && (det.getDmlType()!= null && det.getDmlType().equals("D")))
				{
					delete.getGlVoucherTemplateDet().add(voucherTemplateDet);
				}
				else				
					hobj.getGlVoucherTemplateDet().add(voucherTemplateDet);
			}
		}

		this.processedGlVoucherTemplateMf = hobj;
		this.processedGlVoucherTemplateMfForRemove = delete;
	}

	private Transaction tx;
	private Session session;
	
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;
	private GlVoucherTemplateMf glVoucherTemplateMf;
	private HGlVoucherTemplateMf processedGlVoucherTemplateMf;
	private HGlVoucherTemplateMf processedGlVoucherTemplateMfForRemove;
}
