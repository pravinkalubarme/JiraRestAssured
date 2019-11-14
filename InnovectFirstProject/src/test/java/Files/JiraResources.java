package Files;

public class JiraResources {
	
	public static String JiraLoginResource() {
		String body = "rest/auth/1/session";
		return body;
	}

	
	public static String JiraIssueResource() {
		String body = "rest/api/2/issue";
		return body;
	}

	public static String JiraAddCommentResource() {
		String body = "/rest/api/2/issue/";
		return body;
	}
}
