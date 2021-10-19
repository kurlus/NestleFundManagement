package app.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class GlVoucherTemplateDet implements Serializable {

	private static final long serialVersionUID = -5156441423019463701L;
	
	public GlVoucherTemplateDet()
	{
		
	}
	
	public String getDrCr() 
	{
		return drCr;
	}
	
	public void setDrCr(String drCr) 
	{
		this.drCr = drCr;
	}
	
	public int getGlLineNo() 
	{
		return glLineNo;
	}
	public void setGlLineNo(int glLineNo) 
	{
		this.glLineNo = glLineNo;
	}
	
	public int getTemplateID() 
	{
		return templateID;
	}
	public void setTemplateID(int templateID)
	{
		this.templateID = templateID;
	}
	
	public String getGlMfCode() 
	{
		return glMfCode;
	}
	
	public void setGlMfCode(String glMfCode)
	{
		this.glMfCode = glMfCode;
	}
	
	public String getGlSlType() 
	{
		return glSlType;
	}
	
	public void setGlSlType(String glSlType)
	{
		this.glSlType = glSlType;
	}
	
	public String getNarration() 
	{
		return narration;
	}
	
	public void setNarration(String narration)
	{
		this.narration = narration;
	}
	
	public String getEmployeeID()
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) 
	{
		this.employeeID = employeeID;
	}
	
	public BigDecimal getAmount()
	{
		return amount;
	}
	
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}
	
	public String getExceptionMsgString() 
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString)
	{
		this.exceptionMsgString = exceptionMsgString;
	}
	
	public String getDmlType() 
	{
		return dmlType;
	}

	public void setDmlType(String dmlType) 
	{
		this.dmlType = dmlType;
	}

	


	private String drCr;
	private int glLineNo;
	private String dmlType;
	private int templateID;
	private String glMfCode;
	private String glSlType;
	private String narration;
	private String employeeID;
	private BigDecimal amount;
	private String exceptionMsgString;
	
	
	

}
