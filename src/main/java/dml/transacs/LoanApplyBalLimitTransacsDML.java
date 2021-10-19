package dml.transacs;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import database.DMLOperations;
import database.DefQueries;
import dml.def.DMLTransWrapper;
import dml.def.DMLHandler;
import app.beans.ImportEmployee;
import app.beans.LoanApply;
import app.beans.LoanApplyBalLimit;
import utilities.AppConstants;
import utilities.AppUtility;

public class LoanApplyBalLimitTransacsDML implements DMLHandler {

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)  
	{
		if (obj instanceof LoanApplyBalLimit)
		{
			dmlResultSet = new DMLTransWrapper();
			loanApprove = (LoanApplyBalLimit) obj;

			 Session session = sessionFactory.openSession();
		        Map<String, Object> map = new HashMap<String, Object>();

		        Query query = null;
		        try 
		        {	
		        	if(loanApprove.getRequestType().equals(AppConstants.loanApplyBalLimit)){
		        		map.put(AppConstants.paramEmployeeID,loanApprove.getEmployeeID());
			        	map.put(AppConstants.paramLoanTypeID,loanApprove.getLoanTypeID());
			        	map.put(AppConstants.paramBalance,loanApprove.getLoanTypeID());
			        	map.put(AppConstants.paramApplyLimit,loanApprove.getLoanTypeID());
			        	Map output = DMLOperations.callProcedure(null, AppConstants.prcLoanApplyBalLimit, map);			        	
//			        	Map output2 = DMLOperations.callProcedure(null, AppConstants.prcLoanApplyPreviousAmount, map);	
			        	loanApprove.setBalance(new BigDecimal((Double)output.get(AppConstants.paramBalance)));
			        	loanApprove.setApplyLimit(new BigDecimal((Double)output.get(AppConstants.paramApplyLimit)));
//			        	loanApprove.setPreviousInterest(new BigDecimal((Double)output.get(AppConstants.paramBalance)));
//			        	loanApprove.setPreviousPrincipal(new BigDecimal((Double)output.get(AppConstants.paramBalance)));
//			        	loanApprove.setZakatAmount(new BigDecimal((Double)output.get(AppConstants.paramBalance)));
			        	loanApprove.setPreviousInterest(new BigDecimal(2000));
			        	loanApprove.setPreviousPrincipal(new BigDecimal(10000));
			        	loanApprove.setZakatAmount(new BigDecimal(3000));
			        	
		        	}else if(loanApprove.getRequestType().equals(AppConstants.loanApplyInterest)){
		        		
//		        		map.put(AppConstants.paramLoanDate,loanApprove.getDate());
//			        	map.put(AppConstants.paramLoanTypeID,loanApprove.getLoanTypeID());
//			        	map.put(AppConstants.paramLoanAmount,loanApprove.getApplyAmount());
//			        	map.put(AppConstants.paramInterestAmount,loanApprove.getLoanTypeID());
//			        	Map output = DMLOperations.callProcedure(null, AppConstants.prcLoanApplyInterestAmount, map);
//			     	    loanApprove.setInterestAmount(new BigDecimal((Double)output.get(AppConstants.paramInterestAmount)));
		        		
		        		loanApprove.setInterestAmount(loanApprove.getApplyAmount().divide(new BigDecimal(10)));
			        	
		        	}
		          
		        } 
		        catch (Exception e) 
		        {
		        	AppUtility.logger.log(Level.SEVERE, "loading data failed ="+e.getMessage()+"Caused By : "+e.getCause());
		             

		        } finally 
		        {
		            session.close();
		        }
			dmlResultSet.setLoanApplyBalLimit(loanApprove);
		}
		return dmlResultSet;
	}

	



	

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private Transaction tx;
	private Session session;
	private LoanApplyBalLimit loanApprove;
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;

}
