package app;

import app.beans.LoginEmployee;

public interface Definition 
{
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception;
}
