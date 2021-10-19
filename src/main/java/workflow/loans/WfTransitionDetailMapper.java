package workflow.loans;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import app.beans.WfDetail;

public class WfTransitionDetailMapper implements RowMapper<WfDetail> 
{

	public WfDetail mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		WfDetail transition = new WfDetail();
		
		transition.setWfDetailID(rs.getLong(LoanWfQueries.wfDetID));
		transition.setWorkFlowId(rs.getLong(LoanWfQueries.wfID));
		transition.setFromTransition(rs.getString(LoanWfQueries.wfFromTransition));
		transition.setToTransition(rs.getString(LoanWfQueries.wfToTransition));
		transition.setWorkFlowSeq(rs.getInt(LoanWfQueries.wfSequence));
		transition.setIsMemberEmail(rs.getInt(LoanWfQueries.wfMemberEmail));
		
		return transition;
	}
	
}
