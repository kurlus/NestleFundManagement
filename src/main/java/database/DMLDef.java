package database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import utilities.AppConstants;
import utilities.AppUtility;

public class DMLDef 
{
	public  static synchronized  String getGlmfCode(String parentGLmfCode) throws Exception 
	{
			try 
			{
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put(AppConstants.glMFCode, parentGLmfCode);
				
				String glmfCode = JDBCTemplateFactory.getNamedParamJdbcTemplateInstance().queryForObject(DefQueries.getGlmfCode, parameters, String.class);
				if(glmfCode!=null)
				{
					 long i = Long.valueOf(glmfCode.trim().replaceAll("'", "")) + 1;
					 glmfCode = String.valueOf(i);
				}
				
				return glmfCode;
			} 
			catch (Exception e) 
			{
				AppUtility.logger.log(Level.SEVERE, e.getMessage());
				throw e;
			}
	}

}
