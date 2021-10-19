package dml.def;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.sun.org.apache.bcel.internal.generic.NEW;

import database.ADMQueries;
import database.DMLOperations;
import database.DefQueries;
import database.JDBCTemplateFactory;
import dml.def.DMLTransWrapper;
import dml.def.DMLHandler;
import app.beans.GeneralRequestBean;
import app.beans.ImportEmployee;
import app.beans.LoanApply;
import app.beans.GeneralRequestBean;
import utilities.AppConstants;
import utilities.AppUtility;

public class GeneralRequestDML implements DMLHandler {

	@Override
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType)
	{
		if (obj instanceof GeneralRequestBean)
		{
			dmlResultSet = new DMLTransWrapper();
			generalRequest = (GeneralRequestBean) obj;

			Session session = sessionFactory.openSession();
			Map<String, Object> map = new HashMap<String, Object>();

			Query query = null;
			try
			{
				if (generalRequest.getRequestType().equals(AppConstants.bookTypeSequence))
				{
					map.put("gl_form_type", generalRequest.getParam1());
					String result = generalRequest.getParam1();
					String bookTypeMaxSeq = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(DefQueries.getBookTypeMaxSeqQuery, map, String.class);

					String numString = bookTypeMaxSeq.substring(3);
					int num = (Integer.valueOf(numString)) + 1;
					if (num < 10)
						result += "00" + num;
					else if (num < 100)
					{
						result += "0" + num;
					} else
					{
						result += num;
					}

					generalRequest.setResult1(result);

				} else if (generalRequest.getRequestType().equals(AppConstants.validateDate))
				{
					String result = null;
					SimpleJdbcCall procedureCall = null;
					map = new HashMap<String, Object>();
					map.put(AppConstants.paramDate, new SimpleDateFormat("dd/MM/yyyy").parse(generalRequest.getParam1()));
					map.put(AppConstants.paramFundShort, generalRequest.getParam2());

					procedureCall = new SimpleJdbcCall(JDBCTemplateFactory.getDataSource()).withFunctionName(AppConstants.funcVaidateDate);
					SqlParameterSource sourceParams = new MapSqlParameterSource(map);
					Map output = procedureCall.execute(sourceParams);
					result = ((Integer)output.get("RETURN_VALUE")).toString();

					generalRequest.setResult1(result);

				}

			} catch (Exception e)
			{
				AppUtility.logger.log(Level.SEVERE, "loading data failed =" + e.getMessage() + "Caused By : " + e.getCause());
				generalRequest.setResult1(generalRequest.getParam1() + "001");

			} finally
			{
				session.close();
			}
			dmlResultSet.setGeneralRequestBean(generalRequest);
		}
		return dmlResultSet;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	private Transaction tx;
	private Session session;
	private GeneralRequestBean generalRequest;
	private DMLTransWrapper dmlResultSet;
	private SessionFactory sessionFactory;

}
