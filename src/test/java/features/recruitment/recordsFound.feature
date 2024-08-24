@smoke 
@regression 
@recruitment 
@records_found_table
Feature: Records found table delete last records
	
	The delete operation lets the user delete the candidate from records found
	table. The record will then permanantly removed from the table.

	Background:
    Given User logged into application and navigates to recruitment page
    And The user scrolldown to the bottom of the page
    When The user clicks on the trash icon of the last candidate in table
	

  @remove_candidate_cancel
  Scenario: Remove the last candidate in the table
    Then A confimation popup is disaplayed with title "Are you Sure?" and message "The selected record will be permanently deleted. Are you sure you want to continue?"
    Then The user clicks on the No Cancel button
    Then The popup closes and the record stays in the table
    
  @remove_candidate_confirm
  Scenario: Remove the last candidate in the table
    Then A confimation popup is disaplayed with title "Are you Sure?" and message "The selected record will be permanently deleted. Are you sure you want to continue?"
    Then The user clicks on the Yes Confirm button
    Then The popup closes and the success message displays
    Then The record removes from the table