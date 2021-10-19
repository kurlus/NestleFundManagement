package app.beans;

import java.io.Serializable;


public class EmployeeLoanCompositeID implements Serializable 
{
	private static final long serialVersionUID = -8298878058424185029L;


	public EmployeeLoanCompositeID()
	{
	}

	public long getApplyID() 
	{
		return applyID;
	}

	public void setApplyID(long applyID) 
	{
		this.applyID = applyID;
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
		return String.valueOf(getApplyID()).trim().concat(getEmployeeID().trim()).hashCode();
	}

	public boolean equals (Object obj)
	{
		try
		{
			if (obj instanceof EmployeeLoanCompositeID)
			{
				EmployeeLoanCompositeID argObject = (EmployeeLoanCompositeID) obj;
				if((argObject.getApplyID() == this.getApplyID()) && 
						(argObject.getEmployeeID() == this.getEmployeeID()))
					return true;
			}
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}


	private long applyID;
	private String employeeID;

}
