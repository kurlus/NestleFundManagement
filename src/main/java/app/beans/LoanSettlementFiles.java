package app.beans;

public class LoanSettlementFiles
{
	private static final long serialVersionUID = -1691229386211871408L;

	public LoanSettlementFiles()
	{
		
	}

	public long getFileID()
	{
		return fileID;
	}

	public void setFileID(long fileID)
	{
		this.fileID = fileID;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public byte[] getFileData()
	{
		return fileData;
	}

	public void setFileData(byte[] fileData)
	{
		this.fileData = fileData;
	}

	public int getPost() 
	{
		return post;
	}

	public void setPost(int post) 
	{
		this.post = post;
	}

	public long getLogID() 
	{
		return logID;
	}

	public void setLogID(long logID) 
	{
		this.logID = logID;
	}

	

	public long getSettlementID() 
	{
		return settlementID;
	}

	public void setSettlementID(long settlementID)
	{
		this.settlementID = settlementID;
	}

	public String getEmployeeID() 
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}

	public int hashCode()
	{
		return String.valueOf(getFileID()).trim().hashCode();
	}

	public boolean equals (Object obj)
	{
		try
		{
			if (obj instanceof ProfileFiles)
			{
				ProfileFiles argObject = (ProfileFiles) obj;
				if (argObject.getFileID() == this.getFileID())
					return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	private long logID;
	private long fileID;
	private int post = 0;
	private String fileType;
	private byte[] fileData;
	private String fileName;	
	private String employeeID;
	private long settlementID;
}
