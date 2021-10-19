package dml.def;

public interface DMLHandler 
{
	public DMLTransWrapper dmlDataExecutor(Object obj, String oprType) throws Exception;
	
}
