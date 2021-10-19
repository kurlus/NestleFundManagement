package database;

import java.util.Map;


public class QueryWrapper 
{

    public QueryWrapper(String query, Map parameters) 
    {
        this.query = query;
        this.parameters = parameters;
    }

    public String getQuery() 
    {
        return query;
    }

    public void setQuery(String query) 
    {
        this.query = query;
    }

    public Map getParameters() 
    {
        return parameters;
    }

    public void setParameters(Map parameters) 
    {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "QueryBean{" + "query=" + query + ", parameters=" + parameters + '}';
    }
    
    
    private String query;
    private Map parameters;
}
