package dml.admin;

import app.beans.WfMF;
import dml.def.DMLHandler;
import utilities.AppUtility;
import utilities.AppConstants;
import workflow.loans.WfDML;
import dml.def.DMLTransWrapper;
import exceptionhandling.ExceptionHandler;

import java.util.logging.Level;

public class WorkFlowMFAdminDML implements DMLHandler
{
	public WorkFlowMFAdminDML()
	{
		
	}

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception 
	{
			if(obj instanceof WfMF)
		{
			dmlResultSet = new DMLTransWrapper();
			wfMfBean = (WfMF) obj;
			AppUtility.logger.log(Level.INFO, toString(oprType));
			wfMfBean.setExceptionMsgString(AppConstants.BLANK);
			switch(oprType)
			{
				case AppConstants.oprSave:
					save();
					break;
				case AppConstants.oprEdit:
					update();
					break;
			
			}
			dmlResultSet.setWfMFBean(wfMfBean);
		}
		return dmlResultSet;
	}

	public synchronized String toString(String oprType) 
	{
		String str = "WF mf having" + wfMfBean.getWorkFlowId() + " work floe ID with title "+ wfMfBean.getWorkFlowName()
					+ " going to perform " 	+ oprType + " operation. reference log id is " + wfMfBean.getLogID();
		return str;
	}
	
	private void save()
	{
		try
		{
			WfDML wfDML = new WfDML();
			wfDML.insertWorkFlow(wfMfBean);
		}
		catch(Exception ex)
		{
			wfMfBean.setExceptionMsgString(ExceptionHandler.getExceptionResultMSg (ex.getMessage()+"Caused By : "+ex.getCause(), wfMfBean.getWorkFlowName())); 
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+ex.getMessage()+"Caused By : "+ex.getCause());
		}
	}
	
	private void update()
	{
		
		try
		{
			WfDML wfDML = new WfDML();
			wfDML.updateWorkFlow(wfMfBean);
			
		}
		catch(Exception ex)
		{
			wfMfBean.setExceptionMsgString(ExceptionHandler.getExceptionResultMSg (ex.getMessage()+"Caused By : "+ex.getCause(), wfMfBean.getWorkFlowName())); 
            AppUtility.logger.log(Level.SEVERE, " transaction rollback due to "+ex.getMessage()+"Caused By : "+ex.getCause());
		}
	}

	
	private WfMF wfMfBean;
	private DMLTransWrapper dmlResultSet;
}

