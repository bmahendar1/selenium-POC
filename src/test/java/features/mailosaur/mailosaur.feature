@mailosaur
@login_not_required
Feature: Mailosaur tests


  @read_email_from_inbox
  Scenario: Read email from inbox
    Given user reads emails from the inbox
    
  @read_email_from_outbox
  Scenario: Read email from outbox
  	Given user reads email from the outbox