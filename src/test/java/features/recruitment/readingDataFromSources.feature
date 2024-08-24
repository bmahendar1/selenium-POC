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

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
