package Jira.InnovectFirstProject;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Files.JiraPayLoad;
import Files.JiraResources;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class CreateJiraIssue {
	
	@Test
	public void riseIssueInJira() throws IOException
	{
		//Login and Creating session ID
		
		Properties pro=new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\pravi\\eclipse-workspace\\APIUsingTestNG\\src\\test\\java\\JiraAPI\\file.properties");
		pro.load(fis);
		
		RestAssured.baseURI = pro.getProperty("JiraHost");

		String res = given().header("Content-Type", "application/json").
					body(JiraPayLoad.loginBoday()).
					when().post(JiraResources.JiraLoginResource()).
					then().extract().response().asString();
		JsonPath js = new JsonPath(res);
		String sessionId = js.get("session.value");
		System.out.println("My Session id : " + sessionId);
		
		//Creating Issue (Bug)
		
		String res1 = given().header("Content-Type", "application/json").
					header("Cookie", "JSESSIONID=" + sessionId).
					body(JiraPayLoad.issueBodyNew("IN", "i am summary of this issue", "I am description of this issue")).
					when().post(JiraResources.JiraIssueResource()).
					then().extract().response().asString();	

        System.out.println("response after creating issue : "+res1);
		JsonPath js1 = new JsonPath(res1);
		String issueId = js1.get("id");
        System.out.println("Issue id of Bug: " + issueId);
        
        
      //Adding comment to above issue
        
        System.out.println("URI to add comment is: "+JiraResources.JiraAddCommentResource()+issueId+"/comment");
        ExtractableResponse<Response> respo = given().header("Content-Type", "application/json").
                 header("Cookie", "JSESSIONID=" + sessionId).
 		        body(JiraPayLoad.commentBody("I am comment for issue no"+issueId+"")).
 		        when().post(JiraResources.JiraAddCommentResource()+issueId+"/comment").
 		        then().extract();
      
      	int stCd = respo.statusCode();
      	String resp = respo.response().asString(); 
         System.out.println("status code after adding comment is: "+stCd);
         System.out.println("Final response is: "+resp);
	}
	

}
