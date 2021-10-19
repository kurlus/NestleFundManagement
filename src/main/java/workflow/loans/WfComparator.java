package workflow.loans;

import java.io.Serializable;

public class WfComparator implements Comparable<WfComparator>, Serializable 
{
	private static final long serialVersionUID = -5319991518837063774L;
	
	public WfComparator()
	{
		initialize();
	}
	
	public WfComparator(Long wfID, Long wfDetID, String wfAction, String fromTransition, String toTransition)
	{
		initialize();
		
		this.wfID = wfID;
		this.wfDetID = wfDetID;
		this.wfAction =  wfAction;
		this.fromTransition = fromTransition;
		this.toTransition = toTransition;
	}

	public int compareTo(WfComparator obj) 
	{
		if (wfID == obj.getWfID() &&
				wfDetID == obj.getWfDetID() &&
				wfAction.trim().equalsIgnoreCase(obj.getWfAction().trim()) &&
				toTransition.trim().equalsIgnoreCase(obj.getToTransition().trim()) &&
				fromTransition.trim().equalsIgnoreCase(obj.getFromTransition().trim())
				)
			return zero;
		else
			return minusOne;	
	}

	public void initialize()
	{
		wfID = 0L; 
		wfDetID = 0L;
		wfAction = BLANK;
		toTransition = BLANK;
		fromTransition = BLANK;
	}

	public Long getWfID() 
	{
		return wfID;
	}

	public void setWfID(Long wfID) 
	{
		this.wfID = wfID;
	}

	public Long getWfDetID() 
	{
		return wfDetID;
	}

	public void setWfDetID(Long wfDetID) 
	{
		this.wfDetID = wfDetID;
	}

	public String getWfAction() 
	{
		return wfAction;
	}

	public void setWfAction(String wfAction) 
	{
		this.wfAction = wfAction;
	}

	public String getToTransition() 
	{
		return toTransition;
	}

	public void setToTransition(String toTransition) 
	{
		this.toTransition = toTransition;
	}

	public String getFromTransition() 
	{
		return fromTransition;
	}

	public void setFromTransition(String fromTransition) 
	{
		this.fromTransition = fromTransition;
	}


	private Long wfID;
	private Long wfDetID;
	private String wfAction;
	private String toTransition;
	private String fromTransition;

	private static final int zero = 0;
	private static final int minusOne = -1;
	private static final String BLANK = "";
}
