package webservices;

import java.util.List;

import dml.def.DMLTransWrapper;
import app.DefResultSet;
import app.beans.LoginEmployee;
import app.beans.Menus;

public interface IFundManagement 
{
	DefResultSet loadDefinitionData(LoginEmployee loginEmployee) throws Exception;
	List<Menus> loadUserRoleWiseMenues(String employeeID) throws Exception;
	DMLTransWrapper executeDMLRequest(DMLTransWrapper dml, String oprType, String identifier) throws Exception;
}
