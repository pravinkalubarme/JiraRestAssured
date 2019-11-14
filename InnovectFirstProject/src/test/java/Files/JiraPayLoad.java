package Files;

public class JiraPayLoad {
	
	public static String loginBoday()
	{
		String b = "{\r\n" + 
    			"	\"username\":\"pravinkalubarme1150\",\r\n" + 
    			"	\"password\":\"Pravin@123\"\r\n" + 
    			"}";
		return b;
	}

    
    public static String issueBodyNew(String ProName, String summary, String description)
    {
    	String body="{\r\n" + 
    			"\"fields\":{\"project\":{\"key\": \""+ProName+"\"},\r\n" + 
    			"              \"summary\": \""+summary+"\",\r\n" + 
    			"              \"description\": \""+description+"\",\r\n" + 
    			"              \"issuetype\":{\"name\": \"Bug\"}\r\n" + 
    			"}\r\n" + 
    			"}";
    	return body;
    }
    
    public static String commentBody(String comment)
    {
    	String body="{\r\n" + 
    			"\"body\": \""+comment+"\",\r\n" + 
    			"\"visibility\":  {\r\n" + 
    			"                     \"type\":\"role\",\r\n" + 
    			"                      \"value\":\"Administrators\"\r\n" + 
    			"                   }\r\n" + 
    			"}";
    	return body;
    }
}
