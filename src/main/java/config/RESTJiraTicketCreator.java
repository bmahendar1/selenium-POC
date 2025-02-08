package config;

import static io.restassured.RestAssured.*;

import java.util.Base64;

import config.initialization.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class RESTJiraTicketCreator {
	
	private String username;
	private String apiKey;
	private ConfigLoader configLoader;
	private String path;

	public RESTJiraTicketCreator() {
		this.configLoader = new ConfigLoader();
		this.username = configLoader.getProperty("JIRA_USERNAME");
		this.apiKey = configLoader.getProperty("JIRA_API_TOKEN");
		RestAssured.baseURI = configLoader.getProperty("JIRA_ISSUE_BASE_URI");
		this.path = configLoader.getProperty("JIRA_ISSUE_PATH");
	}
	
	
	public Response createIssue(String summary, String contentText, String projectId, String issueTypeId, String parentKey) {
		
		String auth = username+":"+apiKey;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
		
		return 
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Basic "+encodedAuth)
			.body(getPayload(summary, contentText, projectId, issueTypeId, parentKey)).
		when()
			.request(Method.POST, path).
		then()
			.log().ifError()
			.extract()
			.response();
	}
	
	private String getPayload(String summary, String contentText, String projectId, String issueTypeId, String parentKey) {
		
		return
		
		"{\r\n"
		+ "    \"fields\": {\r\n"
		+ "        \"description\": {\r\n"
		+ "            \"content\": [\r\n"
		+ "                {\r\n"
		+ "                    \"content\": [\r\n"
		+ "                        {\r\n"
		+ "                            \"text\": \""+contentText+"\",\r\n"
		+ "                            \"type\": \"text\"\r\n"
		+ "                        }\r\n"
		+ "                    ],\r\n"
		+ "                    \"type\": \"paragraph\"\r\n"
		+ "                }\r\n"
		+ "            ],\r\n"
		+ "            \"type\": \"doc\",\r\n"
		+ "            \"version\": 1\r\n"
		+ "        },\r\n"
		+ "        \"issuetype\": {\r\n"
		+ "            \"id\": \""+issueTypeId+"\"\r\n"
		+ "        },\r\n"
		+ "        \"labels\": [\r\n"
		+ "            \"bug\",\r\n"
		+ "            \"automated_test\"\r\n"
		+ "        ],\r\n"
		+ "        \"parent\": {\r\n"
		+ "            \"key\": \""+parentKey+"\"\r\n"
		+ "        },\r\n"
		+ "        \"project\": {\r\n"
		+ "            \"id\": \"10000\"\r\n"
		+ "        },\r\n"
		+ "        \"summary\": \""+summary+"\"\r\n"
		+ "    },\r\n"
		+ "    \"update\": {}\r\n"
		+ "}";
	}
}
