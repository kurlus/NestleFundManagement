package webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import app.beans.Menus;
import app.DefResultSet;
import app.DefRequestHandler;
import app.beans.LoginEmployee;
import dml.def.DMLTransWrapper;
import requestpool.MenuesLoader;
import dml.def.DMLRequestExecutor;

@WebService(targetNamespace = "http://webservices/", portName = "FundManagementImpPort", serviceName = "FundManagementImpService")
public class FundManagementImp implements IFundManagement
{

	
	@WebMethod(operationName = "loadDefinitionData", action = "urn:LoadDefinitionData")
	@Override
	public DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception
	{
		return new DefRequestHandler(loginEmployee).call();
	}
	
	@WebMethod(operationName = "loadUserRoleWiseMenues", action = "urn:LoadUserRoleWiseMenues")
	public List<Menus> loadUserRoleWiseMenues(String employeeID) throws Exception
	{
		return new MenuesLoader(employeeID).call();
	}
	
	@WebMethod(operationName = "executeDMLRequest", action = "urn:ExecuteDMLRequest")
	@Override
	public DMLTransWrapper executeDMLRequest(DMLTransWrapper dml, String oprType, String identifier) throws Exception 
	{
		return new DMLRequestExecutor(dml, oprType, identifier).call();
		
	}
	
}
