package  app.beans.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class HGlVoucherTemplateMf implements Cloneable, Serializable {

	private static final long serialVersionUID = -5156441423019463701L;
	
	public HGlVoucherTemplateMf()
	{
		
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
	
	public int getTemplateID() 
	{
		return templateID;
	}
	public void setTemplateID(int templateID) 
	{
		this.templateID = templateID;
	}
	
	public String getTypeShort() 
	{
		return typeShort;
	}
	
	public void setTypeShort(String typeShort) 
	{
		this.typeShort = typeShort;
	}
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public String getTemplateName()
	{
		return templateName;
	}
	
	public void setTemplateName(String templateName) 
	{
		this.templateName = templateName;
	}
	
	public String getBookTypeShort() 
	{
		return bookTypeShort;
	}
	
	public void setBookTypeShort(String bookTypeShort) 
	{
		this.bookTypeShort = bookTypeShort;
	}
	
	public String getExceptionMsgString()
	{
		return exceptionMsgString;
	}
	
	public void setExceptionMsgString(String exceptionMsgString) 
	{
		this.exceptionMsgString = exceptionMsgString;
	}

	public Set<HGlVoucherTemplateDet> getGlVoucherTemplateDet() 
	{
		return glVoucherTemplateDet;
	}

	public void setGlVoucherTemplateDet(Set<HGlVoucherTemplateDet> glVoucherTemplateDet) 
	{
		this.glVoucherTemplateDet = glVoucherTemplateDet;
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	private int post;
	private long logID;
	private int templateID;
	private String typeShort;
	private String description;
	private String templateName;
	private String bookTypeShort;
	private String exceptionMsgString;
	private Set<HGlVoucherTemplateDet> glVoucherTemplateDet;
	

}
