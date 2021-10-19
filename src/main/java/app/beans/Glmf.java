package app.beans;

import java.io.Serializable;

public class Glmf implements Serializable 
{
	private static final long serialVersionUID = -7414824974556999134L;
	
	
	public long getLogID() 
	{
		return logID;
	}
	public void setLogID(long logID) 
	{
		this.logID = logID;
	}
	public int getPost() 
	{
		return post;
	}
	public void setPost(int post) 
	{
		this.post = post;
	}
	public int getGlmfLevel() 
	{
		return glmfLevel;
	}
	public void setGlmfLevel(int glmfLevel) 
	{
		this.glmfLevel = glmfLevel;
	}
	public int getDetailCode() 
	{
		return detailCode;
	}
	public void setDetailCode(int detailCode) 
	{
		this.detailCode = detailCode;
	}
	public String getGlmfCode() 
	{
		return glmfCode;
	}
	public void setGlmfCode(String glmfCode) 
	{
		this.glmfCode = glmfCode;
	}
	public String getGlmfDesc() 
	{
		return glmfDesc;
	}
	public void setGlmfDesc(String glmfDesc) 
	{
		this.glmfDesc = glmfDesc;
	}
	public int getGlActive() 
	{
		return glActive;
	}
	public void setGlActive(int glActive) 
	{
		this.glActive = glActive;
	}
	public String getGlmfParent() 
	{
		return glmfParent;
	}
	
	public void setGlmfParent(String glmfParent) 
	{
		this.glmfParent = glmfParent;
	}
	
	public String getGlGeneralDetail() 
	{
		return glGeneralDetail;
	}
	
	public void setGlGeneralDetail(String glGeneralDetail) 
	{
		this.glGeneralDetail = glGeneralDetail;
	}
	
	public String getExceptionMsgString() 
	{
		return this.exceptionMsgString;
	}

	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}	
	
	public String getFamilyID() 
	{
		return familyID;
	}
	public void setFamilyID(String familyID) 
	{
		this.familyID = familyID;
	}


	private long logID;
	private int post = 0;
	private String familyID;
	private int glmfLevel;
	private int detailCode;
	private String glmfCode;
	private String glmfDesc;
	private int glActive = 0;
	private String glmfParent;
	private String glGeneralDetail;
	private String exceptionMsgString;	
	

}
