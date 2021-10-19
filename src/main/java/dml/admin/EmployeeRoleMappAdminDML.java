package dml.admin;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.sql.PreparedStatement;

import dml.def.DMLHandler;
import database.ADMQueries;
import utilities.AppUtility;
import app.beans.RoleDetail;
import utilities.AppConstants;
import dml.def.DMLTransWrapper;
import database.JDBCTemplateFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class EmployeeRoleMappAdminDML implements DMLHandler 
{
	public EmployeeRoleMappAdminDML()
	{
	}

	@Override
	public synchronized DMLTransWrapper dmlDataExecutor(Object obj, String oprType)  
	{
		if(obj instanceof DMLTransWrapper)
		{
			dmlResultSet = (DMLTransWrapper) obj;			
			
			toBeActivatedEmplRoleList = dmlResultSet.getRoleDetailList();
			activatedEmplRoleList = dmlResultSet.getRoleDetailLookUpList();
			
			if(activatedEmplRoleList == null)
			{
				activatedEmplRoleList=new ArrayList<>();
			}
			if(toBeActivatedEmplRoleList == null){
				toBeActivatedEmplRoleList=new ArrayList<>();
			}
				
			AppUtility.logger.log(Level.INFO, oprType);
				
				switch(oprType)
				{
					case AppConstants.oprSave:
						save();
						break;
					default:
						AppUtility.logger.log(Level.SEVERE, "No opreration type is defined");
				}	
				dmlResultSet.setRoleDetailList(toBeActivatedEmplRoleList);
		}
		return dmlResultSet;
	}
	
	public synchronized DMLTransWrapper getDmlResultSet() 
	{
		return dmlResultSet;
	}

	public synchronized void setDmlResultSet(DMLTransWrapper dmlResultSet) 
	{
		this.dmlResultSet = dmlResultSet;
	}

	public synchronized List<RoleDetail> getTmpDeletionList() 
	{
		return tmpDeletionList;
	}

	public synchronized void setTmpDeletionList(List<RoleDetail> tmpDeletionList) 
	{
		this.tmpDeletionList = tmpDeletionList;
	}

	public synchronized List<RoleDetail> getTmpUpdationList() 
	{
		return tmpUpdationList;
	}

	public synchronized void setTmpUpdationList(List<RoleDetail> tmpUpdationList) 
	{
		this.tmpUpdationList = tmpUpdationList;
	}

	public synchronized List<RoleDetail> getTmpInsertionList() 
	{
		return tmpInsertionList;
	}

	public synchronized void setTmpInsertionList(List<RoleDetail> tmpInsertionList) 
	{
		this.tmpInsertionList = tmpInsertionList;
	}

	public synchronized List<RoleDetail> getActivatedEmplRoleList() 
	{
		return activatedEmplRoleList;
	}

	public synchronized void setActivatedEmplRoleList(List<RoleDetail> activatedEmplRoleList) 
	{
		this.activatedEmplRoleList = activatedEmplRoleList;
	}

	public synchronized List<RoleDetail> getToBeActivatedEmplRoleList() 
	{
		return toBeActivatedEmplRoleList;
	}

	public synchronized void setToBeActivatedEmplRoleList(List<RoleDetail> toBeActivatedEmplRoleList) 
	{
		this.toBeActivatedEmplRoleList = toBeActivatedEmplRoleList;
	}

	private void save()  
	{
		populateListByLookUp();
		
		if(tmpDeletionList.size() > AppConstants.zero)
			deleteRoleDetailTransactions(tmpDeletionList);
		
		if(tmpInsertionList.size() > AppConstants.zero)
			insertRoleDetailTransactions(tmpInsertionList);
		
		if(tmpUpdationList.size() > AppConstants.zero)
			updateRoleDetailTransactions(tmpUpdationList);
	}
	
	private void populateListByLookUp()
	{
		tmpDeletionList = new ArrayList<RoleDetail>();
		tmpUpdationList = new ArrayList<RoleDetail>();
		tmpInsertionList = new ArrayList<RoleDetail>();
		
		if(this.getActivatedEmplRoleList().size() > AppConstants.zero && this.getToBeActivatedEmplRoleList().size() > AppConstants.zero)
		{
			for(RoleDetail empRole: this.getToBeActivatedEmplRoleList())
			{
				if (this.getActivatedEmplRoleList().contains(empRole)) 
				{
		            this.getTmpUpdationList().add(empRole);
		            this.getActivatedEmplRoleList().remove(empRole);
		        }
				else
					this.getTmpInsertionList().add(empRole);
			}
			if(this.getActivatedEmplRoleList().size() > AppConstants.zero)
			{
				this.tmpDeletionList = this.getActivatedEmplRoleList();
			}
			
		}
		else if(this.getActivatedEmplRoleList().size() == AppConstants.zero 
				&& this.getToBeActivatedEmplRoleList().size() > AppConstants.zero)
		{
			this.tmpInsertionList = this.getToBeActivatedEmplRoleList();
		}else if(this.getActivatedEmplRoleList().size() > AppConstants.zero)
		{
			this.tmpDeletionList = this.getActivatedEmplRoleList();
		}
			
	}
	
	private void insertRoleDetailTransactions(final List<RoleDetail> roleDetailInstances)  
	{
		try
		{
			JDBCTemplateFactory.getJdbcTemplateInstance().batchUpdate(ADMQueries.insertRoleDetailQuery, new BatchPreparedStatementSetter()
			{
				public void setValues(PreparedStatement ps, int i)
				{
					try
					{
						RoleDetail roleDetail = roleDetailInstances.get(i);

						ps.setLong(AppConstants.one, roleDetail.getMenuID());
						ps.setString(AppConstants.two, roleDetail.getOperationID());
						ps.setInt(AppConstants.three, roleDetail.getPost());
						ps.setString(AppConstants.four, roleDetail.getRoleID());
					}
					catch(Exception e)
					{
						AppUtility.getLogger().log(Level.SEVERE, e.getMessage()+"Caused By : "+e.getCause());
					}
				}

				public int getBatchSize()
				{
					return roleDetailInstances.size();
				}
			});

		}
		catch(Exception e)
		{
			AppUtility.getLogger().log(Level.SEVERE, e.getMessage()+"Caused By : "+e.getCause());
			 
		}
	}
	
	private void updateRoleDetailTransactions(final List<RoleDetail> roleDetailInstances)  
	{
		try
		{
			JDBCTemplateFactory.getJdbcTemplateInstance().batchUpdate(ADMQueries.updateRoleDetailQuery, new BatchPreparedStatementSetter()
			{
				public void setValues(PreparedStatement ps, int i)
				{
					try
					{
						RoleDetail roleDetail = roleDetailInstances.get(i);
						
						ps.setInt(AppConstants.one, roleDetail.getPost());
						ps.setLong(AppConstants.two, roleDetail.getMenuID());
						ps.setString(AppConstants.four, roleDetail.getRoleID());
						ps.setString(AppConstants.three, roleDetail.getOperationID());
					}
					catch(Exception e)
					{
						AppUtility.getLogger().log(Level.SEVERE, e.getMessage()+"Caused By : "+e.getCause());
					}
				}

				public int getBatchSize()
				{
					return roleDetailInstances.size();
				}
			});

		}
		catch(Exception e)
		{
			AppUtility.getLogger().log(Level.SEVERE, e.getMessage()+"Caused By : "+e.getCause());
			 
		}
	}
	
	private void deleteRoleDetailTransactions(final List<RoleDetail> roleDetailInstances)  
	{
		try
		{
			JDBCTemplateFactory.getJdbcTemplateInstance().batchUpdate(ADMQueries.deleteRoleDetailQuery, new BatchPreparedStatementSetter()
			{
				public void setValues(PreparedStatement ps, int i)
				{
					try
					{
						RoleDetail roleDetail = roleDetailInstances.get(i);
						ps.setLong(AppConstants.one, roleDetail.getMenuID());
						ps.setString(AppConstants.two,roleDetail.getRoleID());
						ps.setString(AppConstants.three, roleDetail.getOperationID());
						
					}
					catch(Exception e)
					{
						AppUtility.getLogger().log(Level.SEVERE, e.getMessage()+"Caused By : "+e.getCause());
					}
				}

				public int getBatchSize()
				{
					return roleDetailInstances.size();
				}
			});

		}
		catch(Exception e)
		{
			AppUtility.getLogger().log(Level.SEVERE, e.getMessage()+"Caused By : "+e.getCause());
			 
		}
	}
	
	private DMLTransWrapper dmlResultSet;
	private List<RoleDetail> tmpDeletionList;
	private List<RoleDetail> tmpUpdationList;
	private List<RoleDetail> tmpInsertionList;	
	private List<RoleDetail> activatedEmplRoleList;
	private List<RoleDetail> toBeActivatedEmplRoleList;	

}
