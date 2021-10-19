package database;

import java.util.Map;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import utilities.AppConstants;



public class DMLOperations 
{

	public static synchronized List <Map<String, Object>> queryList(QueryWrapper qWrapper) throws Exception 
	{
		return JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForList(qWrapper.getQuery(), qWrapper.getParameters());
	}

	public static synchronized void insert(QueryWrapper qWrapper) throws Exception
	{
		JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().update(qWrapper.getQuery(), qWrapper.getParameters());
	}

	public static synchronized long searchForLong(String query, Map parameterSource) throws Exception 
	{
		return 0L;
		//return JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForLong(query, parameterSource);
	}

	public static synchronized  Map callProcedure(String packageName, String procedureName, Map inParameters) throws Exception 
	{
		SimpleJdbcCall procedureCall = null;

		if (packageName != null && !packageName.equals("")) 
		{
			procedureCall = new SimpleJdbcCall(JDBCTemplateFactory.getDataSource()).withProcedureName(procedureName).withCatalogName(packageName);
		} 
		else 
		{
			procedureCall = new SimpleJdbcCall(JDBCTemplateFactory.getDataSource()).withProcedureName(procedureName);
		}		
		SqlParameterSource sourceParams = new MapSqlParameterSource (inParameters);
		return procedureCall.execute(sourceParams);
	}


	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.dateFormat4Web);
}
