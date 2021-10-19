package app.beans;

import java.util.Date;
import java.io.Serializable;


public class UserInfo implements Serializable
{
	private static final long serialVersionUID = -5016839561288492546L;


	public UserInfo()
	{
	}
	
	public boolean isClient() 
	{		
		return isClient;
	}
	
	public void setClient(boolean isClient) 
	{
		this.isClient = isClient;
	}

	public String getCnic() 
	{
		return cnic;
	}
	
	public void setCnic(String cnic) 
	{
		this.cnic = cnic;
	}
	
	public String getCell() 
	{
		return cell;
	}
	
	public void setCell(String cell) 
	{
		this.cell = cell;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getUserid() 
	{
		return userid;
	}
	
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	
	public Date getLastLogin() 
	{
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) 
	{
		this.lastLogin = lastLogin;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getClient_code() 
	{
		return client_code;
	}
	
	public void setClient_code(String client_code) 
	{
		this.client_code = client_code;
	}
	
	public String getLocalAddress() 
	{
		return localAddress;
	}
	
	public void setLocalAddress(String localAddress) 
	{
		this.localAddress = localAddress;
	}
	
	public String getRegisteredAddress() 
	{
		return registeredAddress;
	}
	
	public void setRegisteredAddress(String registeredAddress) 
	{
		this.registeredAddress = registeredAddress;
	}
	
    public String getTitle()
    {
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	boolean isClient;	

	private String cnic;
	private String title;
    private String cell;
    private String name;
    private String email;
    private String userid;
    private Date  lastLogin;
	private String password;
	private String client_code;
	private String localAddress;    
    private String registeredAddress;  
    
}
