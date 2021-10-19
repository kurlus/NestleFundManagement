package  app.beans.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;

public class HGlVoucherTemplateDet implements Serializable {

	private static final long serialVersionUID = -5156441423019463701L;
	
	public HGlVoucherTemplateDet()
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

	public HGlVoucherTemplateMf getGlVoucherTemplateMf() 
	{
		return glVoucherTemplateMf;
	}

	public void setGlVoucherTemplateMf(HGlVoucherTemplateMf glVoucherTemplateMf) 
	{
		this.glVoucherTemplateMf = glVoucherTemplateMf;
	}

	private String drCr;
	private int glLineNo;
	private String glMfCode;
	private String glSlType;
	private String narration;
	private String employeeID;
	private BigDecimal amount;
	private String exceptionMsgString;
	private HGlVoucherTemplateMf glVoucherTemplateMf;
	
	

}
