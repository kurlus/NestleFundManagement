package app.beans;

import java.io.Serializable;

public class WfHistory implements Serializable 
{
	private static final long serialVersionUID = -9171913792511627661L;
	
	public WfHistory()
	{
	}

	public WfHistory(int wfSeqParam, long wfIDParam, long wfDetIDParam, long sourceIDParam, long historyIDParam, String wfNameParam,
			String wfEmployeeIDParam, String wfEventTypeParam, String wfEventActionParam, String wfFrmTransitionParam, String wfToTransitionParam)
	{
		this.wfSeqParam = wfSeqParam;
		this.wfIDParam = wfIDParam;
		this.wfDetIDParam = wfDetIDParam;
		this.sourceIDParam = sourceIDParam;
		this.historyIDParam = historyIDParam;
		this.wfNameParam = wfNameParam;
		this.wfEmployeeIDParam = wfEmployeeIDParam;
		this.wfEventTypeParam = wfEventTypeParam;
		this.wfEventActionParam = wfEventActionParam;
		this.wfFrmTransitionParam = wfFrmTransitionParam;
		this.wfToTransitionParam = wfToTransitionParam;
	}

	public int getWfSeqParam() 
	{
		return wfSeqParam;
	}
	
	public void setWfSeqParam(int wfSeqParam) 
	{
		this.wfSeqParam = wfSeqParam;
	}
	
	public long getWfIDParam() 
	{
		return wfIDParam;
	}
	
	public void setWfIDParam(long wfIDParam) 
	{
		this.wfIDParam = wfIDParam;
	}
	
	public long getWfDetIDParam() 
	{
		return wfDetIDParam;
	}
	
	public void setWfDetIDParam(long wfDetIDParam) 
	{
		this.wfDetIDParam = wfDetIDParam;
	}
	
	public long getSourceIDParam() 
	{
		return sourceIDParam;
	}
	
	public void setSourceIDParam(long sourceIDParam) 
	{
		this.sourceIDParam = sourceIDParam;
	}
	
	public long getHistoryIDParam() 
	{
		return historyIDParam;
	}
	
	public void setHistoryIDParam(long historyIDParam) 
	{
		this.historyIDParam = historyIDParam;
	}
	
	public String getWfNameParam() 
	{
		return wfNameParam;
	}
	
	public void setWfNameParam(String wfNameParam) 
	{
		this.wfNameParam = wfNameParam;
	}
	
	public String getWfEmployeeIDParam() 
	{
		return wfEmployeeIDParam;
	}
	
	public void setWfEmployeeIDParam(String wfEmployeeIDParam) 
	{
		this.wfEmployeeIDParam = wfEmployeeIDParam;
	}
	
	public String getWfEventTypeParam() 
	{
		return wfEventTypeParam;
	}
	
	public void setWfEventTypeParam(String wfEventTypeParam) 
	{
		this.wfEventTypeParam = wfEventTypeParam;
	}
	
	public String getWfEventActionParam() 
	{
		return wfEventActionParam;
	}
	
	public void setWfEventActionParam(String wfEventActionParam) 
	{
		this.wfEventActionParam = wfEventActionParam;
	}
	
	public String getWfFrmTransitionParam() 
	{
		return wfFrmTransitionParam;
	}
	
	public void setWfFrmTransitionParam(String wfFrmTransitionParam) 
	{
		this.wfFrmTransitionParam = wfFrmTransitionParam;
	}
	
	public String getWfToTransitionParam() 
	{
		return wfToTransitionParam;
	}
	
	public void setWfToTransitionParam(String wfToTransitionParam) 
	{
		this.wfToTransitionParam = wfToTransitionParam;
	}
	
	
	int wfSeqParam;
	long wfIDParam;	
	long wfDetIDParam;
	long sourceIDParam;
	long historyIDParam;

	String wfNameParam;
	String wfEmployeeIDParam;
	String wfEventTypeParam;
	String wfEventActionParam;
	String wfFrmTransitionParam;
	String wfToTransitionParam;
}
