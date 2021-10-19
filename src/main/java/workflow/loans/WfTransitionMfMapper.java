package workflow.loans;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import app.beans.WfMF;

public class WfTransitionMfMapper implements RowMapper<WfMF> 
{

	public WfMF mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		WfMF transitionMF = new WfMF();
		
		transitionMF.setWorkFlowId(rs.getLong(LoanWfQueries.wfID));
		transitionMF.setWorkFlowName(rs.getString(LoanWfQueries.wfName));
		transitionMF.setEventType(rs.getString(LoanWfQueries.wfEventType));
		transitionMF.setActionID(rs.getInt(LoanWfQueries.wfActionID));
		transitionMF.setLogID(rs.getLong(LoanWfQueries.logID));
		
		return transitionMF;
	}
}
