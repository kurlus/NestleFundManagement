package app.beans;

import java.io.Serializable;

public class GeneralRequestBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841552368638713595L;
	public GeneralRequestBean()
	{
		// TODO Auto-generated constructor stub
	}
	

	public String getRequestType()
	{
		return requestType;
	}
	public void setRequestType(String requestType)
	{
		this.requestType = requestType;
	}
	public String getParam1()
	{
		return param1;
	}
	public void setParam1(String param1)
	{
		this.param1 = param1;
	}
	public String getParam2()
	{
		return param2;
	}
	public void setParam2(String param2)
	{
		this.param2 = param2;
	}
	public String getParam3()
	{
		return param3;
	}
	public void setParam3(String param3)
	{
		this.param3 = param3;
	}
	public String getResult1()
	{
		return result1;
	}
	public void setResult1(String result1)
	{
		this.result1 = result1;
	}
	public String getResult2()
	{
		return result2;
	}
	public void setResult2(String result2)
	{
		this.result2 = result2;
	}



	private String param1;
	private String param2;
	private String param3;
	private String result1;
	private String result2;
	private String requestType;

}
