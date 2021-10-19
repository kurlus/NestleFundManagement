package database;


public interface TransacQueries 
{

	String getEmployeeObjByID = "select employee_id, nic, fname, lname, doj, doc, gender, dob, active, post from employee where employee_id = :employee_id";
	
}
