@smoke 
@regression 
@recruitment 
@vacancies
@orange_hrm
Feature: Vacancies search

	Background:
    Given User logged into application and navigates to recruitment page
    And User clicks on vacancies header and navigate to vacancies tab

  @search_by_job_title_no_match
  Scenario: Search for a vacancy by job title that has no match
    And The user selects "Chief Technical Officer" job title from the job title dropdown
    When User clicks on the search button
    Then No records found message displays
    
    
  @search_by_job_title_match
  Scenario: Search for a vacancy by job title that has no match
    And The user selects "Account Assistant" job title from the job title dropdown
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

  @search_by_hiring_manager_match
  Scenario: Search for the candidate by hiring manager that has a match
    And The user selects "manda user" manager from the hiring manager dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table 
    
  @search_by_status_no_match
  Scenario: Search for the candidate by status that has no match
    And The user selects "Closed" status from the status dropdown
    When User clicks on the search button
    Then No records found message displays

  @search_by_status_match
  Scenario: Search for the candidate by status that has a match
    And The user selects "Active" status from the status dropdown
    When User clicks on the search button
    Then Matching records shows up in the records found table