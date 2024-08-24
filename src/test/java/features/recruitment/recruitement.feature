@smoke @regression 
@recruitment 
Feature: Recruitement search for candidates feature

	Background:
    Given User logged into application and navigates to recruitment page
	

  @candate_default_tab
  Scenario: Check the default tab
    Then the candidate tab is default tab

  @search_by_job_title_no_match
  Scenario: Search for the candidate by job title that has no match
    And The user selects "Account Assistant" job title from the job title dropdown
    When User clicks on the search button
    Then No records found message displays

  @search_by_job_title_match
  Scenario: Search for the candidate by job title that has a match
    And The user selects "QA Lead" job title from the job title dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table

  @search_by_vacancy_no_match
  Scenario: Search for the candidate by vacancy that has no match
    And The user selects "Junior Account Assistant" vacancy from the vacancy dropdown
    When User clicks on the search button
    Then No records found message displays

  @search_by_vacancy_match
  Scenario: Search for the candidate by vacancy that has a match
    And The user selects "Senior QA Lead" vacancy from the vacancy dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table
    
  @search_by_hiring_manager_no_match
  Scenario: Search for the candidate by hiring manager that has no match
    And The user selects "Rahul Patil" manager from the hiring manager dropdown
    When User clicks on the search button
    Then No records found message displays

  @search_by_hirining_manager_match
  Scenario: Search for the candidate by hiring manager that has a match
    And The user selects "First Name Last Name" manager from the hiring manager dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table  
    
  @search_by_status_no_match
  Scenario: Search for the candidate by status that has no match
    And The user selects "Interview Passed" status from the status dropdown
    When User clicks on the search button
    Then No records found message displays

  @search_by_status_match
  Scenario: Search for the candidate by status that has a match
    And The user selects "Shortlisted" status from the status dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table
    
  @search_by_candidate_name_match
  Scenario: Search for the candidate by candidate name that has a match
    And The user types "doe" in candidate name text field for hints
    And Selects one option from hints
    When User clicks on the search button
    Then Matching records shows up in the records found table
    
  @search_by_date_of_application_match
  Scenario: Search for the candidate by date of application calender that has a match
    And The user selects year 2024 month 6 date 2 from date of application calender
    When User clicks on the search button
    Then Matching records shows up in the records found table
    
  @search_by_method_of_application_no_match
  Scenario: Search for the candidate by method of application that has no match
    And The user selects "online" method from the method of application dropdown
    When User clicks on the search button
    Then No records found message displays
    
  @search_by_method_of_application_match
  Scenario: Search for the candidate by method of application that has a match
    And The user selects "manual" method from the method of application dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table