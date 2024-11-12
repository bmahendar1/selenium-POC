@login_not_required
@read_data_from_sources
Feature: Reading data from various resources

	Background:
    Given User has the excel file		

  @read_data_from_excel
  Scenario: Read data from excel document
    When The user reads the date, it reads properly
    
    
  @write_data_to_excel
  Scenario: Read data from excel document
    When The user updates the execution date in the document