package stepdefinations.mailosaur;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageListParams;
import com.mailosaur.models.MessageListResult;
import com.mailosaur.models.MessageSummary;
import com.mailosaur.models.SearchCriteria;

import config.initialization.ConfigLoader;
import config.initialization.Context;
import io.cucumber.java.en.Given;

public class MailosaurSteps {
	
	private String mailosaurApiKey;
	private String recipientEmail;
	private String senderEmail;
	private String serverId;
	private ConfigLoader configLoader;
	private Context context;
	
	
	public MailosaurSteps(Context context) {
		
		this.context = context;
		this.configLoader = new ConfigLoader();
	}

	
	@Given("user reads emails from the inbox")
	public void user_reads_emails_from_the_inbox() {
		
		mailosaurApiKey = configLoader.getProperty("MAILOSAUR_API_KEY");
		recipientEmail = configLoader.getProperty("RECIPIENT_EMAIL");
		senderEmail = configLoader.getProperty("SENDER_EMAIL");
		serverId = configLoader.getProperty("MAILOSAUR_SERVER_ID");
		
		
		MailosaurClient client = new MailosaurClient(mailosaurApiKey);
		
//		OLD APPROACH 
		
//		SearchCriteria criteria = new SearchCriteria();
//		criteria.withSentTo(recipientEmail);
//		criteria.withSentFrom(senderEmail);
		
		try {
//			Message m = client.messages().get(serverId, criteria, 60000);
//			System.out.println(m.subject());
//			System.out.println(m.text().body());
			
			MessageListParams listParams = new MessageListParams();
			listParams.withServer(serverId);
			
			List<MessageSummary> messages = client.messages().list(listParams).items();
			
			for(MessageSummary ms: messages) {
				
				if(ms.subject().equals("Received your first mailosaur emil")) {
					String messageId = ms.id();
					
					Message message = client.messages().getById(messageId);
					
					System.out.println(message.subject());
					System.out.println(message.text().body());
					System.out.println(message.bcc());
					System.out.println(message.cc());
					System.out.println(message.to().getFirst().email());
					System.out.println(message.from().getFirst().email());
					System.out.println(message.metadata().helo());
					
					break;
				}
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (MailosaurException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	@Given("user reads email from the outbox")
	public void user_reads_email_from_the_outbox() {
		
		mailosaurApiKey = configLoader.getProperty("MAILOSAUR_API_KEY");
		serverId = configLoader.getProperty("MAILOSAUR_SERVER_ID");
		
		MailosaurClient client = new MailosaurClient(mailosaurApiKey);
		
		MessageListParams listParams = new MessageListParams();
		listParams.withServer(serverId);
		
		try {
			List<MessageSummary> messagesSummary = client.messages().list(listParams).items();
			System.out.println(messagesSummary.size());
			
			for(MessageSummary ms: messagesSummary) {
				if(ms.subject().equals("First Email")) {
					
					String messageId = ms.id();
					
					Message message = client.messages().getById(messageId);
					
					System.out.println(message.subject());
					System.out.println(message.text().body());
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (MailosaurException e) {
			System.out.println(e.getMessage());
		}
	}
}
