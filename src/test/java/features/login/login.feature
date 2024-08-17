@smoke
@regression
@login
Feature: Login feature

  @valid_login
  Scenario: Log into the application with a valid credentials
    Given User opens his or her favourite browser and types url
    When The login page appears then the user enters username, password and clicks on login
    Then The user navigate to home or landing page
    And the user logs out of the application