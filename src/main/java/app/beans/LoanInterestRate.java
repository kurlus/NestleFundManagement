package app.beans;
import java.util.Date;

public class LoanInterestRate 
{
	
	public int getPost() 
	{
		return post;
	}
	public void setPost(int post) 
	{
		this.post = post;
	}
	public double getInterestRate() 
	{
		return interestRate;
	}
	public void setInterestRate(double interestRate) 
	{
		this.interestRate = interestRate;
	}
	
	public Date getToDate() 
	{
		return toDate;
	}
	public void setToDate(Date toDate) 
	{
		this.toDate = toDate;
	}
	public Date getFromDate() 
	{
		return fromDate;
	}
	public void setFromDate(Date fromDate) 
	{
		this.fromDate = fromDate;
	}
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	public long getLogID() 
	{
		return logID;
	}
	public void setLogID(long logID)
	{
		this.logID = logID;
	}

	public long getInterestRateID() 
	{
		return interestRateID;
	}
	public void setInterestRateID(long interestRateID) 
	{
		this.interestRateID = interestRateID;
	}



	private int post;
	private long logID;
	private Date toDate;
	private Date fromDate;
	private double interestRate;
	private long interestRateID;
	private String exceptionMsgString;
}
