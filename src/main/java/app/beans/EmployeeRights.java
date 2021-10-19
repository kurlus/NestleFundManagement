package app.beans;

import java.io.Serializable;

public class EmployeeRights implements Serializable
{
	
	private static final long serialVersionUID = -7128309392813591824L;
	
	public EmployeeRights()
	{
		
	}
	
	public EmployeeRights(boolean isDelRight, boolean isAddRight, boolean isRunRight,
			 				  boolean isSaveRight, boolean isViewRight, boolean isEditRight, boolean isPostRight) 
			
	{
		this.isDelRight = isDelRight;
		this.isAddRight = isAddRight;
		this.isRunRight = isRunRight;
		this.isSaveRight = isSaveRight;
		this.isViewRight = isViewRight;
		this.isEditRight = isEditRight;
		this.isPostRight = isPostRight;
	}
	
	public boolean isDelRight() 
	{
		return isDelRight;
	}
	
	public void setDelRight(boolean isDelRight) 
	{
		this.isDelRight = isDelRight;
	}
	
	public boolean isAddRight() 
	{
		return isAddRight;
	}
	
	public void setAddRight(boolean isAddRight) 
	{
		this.isAddRight = isAddRight;
	}
	
	public boolean isRunRight() 
	{
		return isRunRight;
	}
	
	public void setRunRight(boolean isRunRight) 
	{
		this.isRunRight = isRunRight;
	}
	
	public boolean isSaveRight() 
	{
		return isSaveRight;
	}
	
	public void setSaveRight(boolean isSaveRight) 
	{
		this.isSaveRight = isSaveRight;
	}
	
	public boolean isViewRight() 
	{
		return isViewRight;
	}
	
	public void setViewRight(boolean isViewRight) 
	{
		this.isViewRight = isViewRight;
	}
	
	public boolean isEditRight() 
	{
		return isEditRight;
	}
	
	public void setEditRight(boolean isEditRight) 
	{
		this.isEditRight = isEditRight;
	}

	public boolean isPostRight() 
	{
		return isPostRight;
	}

	public void setPostRight(boolean isPostRight) 
	{
		this.isPostRight = isPostRight;
	}

	private boolean isDelRight;
	private boolean isAddRight;
	private boolean isRunRight;
	private boolean isPostRight;
	private boolean isSaveRight;
	private boolean isViewRight;
	private boolean isEditRight;
	
	
}
