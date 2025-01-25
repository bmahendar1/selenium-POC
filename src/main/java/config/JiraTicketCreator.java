package config;

import java.util.Base64;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class JiraTicketCreator {

    public static void createJiraTicket(String testName, String errorMessage) {
        String jiraUrl = "https://your-jira-instance.atlassian.net/rest/api/2/issue";
        String username = "your-email@example.com";  // Your Jira account email
        String apiToken = "your-api-token";  // Your Jira API token

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(jiraUrl);

        // Set headers
        httpPost.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + apiToken).getBytes()));
        httpPost.setHeader("Content-Type", "application/json");

        // Construct the JSON body for the issue
        JSONObject issue = new JSONObject();
        issue.put("project", new JSONObject().put("key", "PROJECT_KEY"));
        issue.put("summary", "Test Failure: " + testName);
        issue.put("description", errorMessage);
        issue.put("issuetype", new JSONObject().put("name", "Bug"));

        // Send the POST request to create the Jira ticket
        try {
            httpPost.setEntity(new StringEntity(issue.toString()));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

