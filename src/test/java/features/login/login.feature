@smoke
@regression
@login
@login_not_required
Feature: Login feature

	Background:
    Given User opens his or her favourite browser and types url
    And The login page appears
		

  @valid_login
  Scenario: Log into the application with a valid credentials
    And User enters username, password and clicks on login
    Then The user navigate to home or landing page
    And The user logs out of the application
    
    
  Scenario Outline:
  	And The user enters "<username>" and "<password>" and clicks on login
  	Then An error message "<message>" appears on the login page
  	
  	Examples:
  		| username | password   | message 						|
  		| invalid  | admin123   | Invalid credentials |
  		| Admin		 | invalid123 | Invalid credentials |
  		| invalid  | invalid123 | Invalid credentials |