package app.beans;

import java.io.Serializable;


public class ProfileFiles implements Serializable 
{
	private static final long serialVersionUID = -1691229386211871408L;

	public ProfileFiles()
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

	
	

	public long getApplyID() 
	{
		return applyID;
	}

	public void setApplyID(long applyID) 
	{
		this.applyID = applyID;
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
	private long applyID;
	private int post = 0;
	private String fileType;
	private byte[] fileData;
	private String fileName;	
	private String employeeID;
	
}
