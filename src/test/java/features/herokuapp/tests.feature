@herokuapp
Feature: Herokuapp
    
  @basic_auth
  Scenario: Herokuapp basic auth via url
    Given user opens the app keeping username and password in the url
    Then a successful "Congratulations! You must have the proper credentials." message displays
    
    
  @context_menu
  Scenario: View context menu
  Given user navigate to the herokuapp menu landing page
  And clicks on the context menu option
  And user will navigate to the context menu page
  And user right clicks on the box
  Then a popup will display with message "You selected a context menu"
  
  
  @disappering_elements
  Scenario: Disappering element on refresh
  Given user navigate to the herokuapp menu landing page
  And clicks on the disappearing elements option
  Then user will navigate to the disappearing elements page
  And user refreshes the page
  Then user clicks on the gallary option
  And user will navigate to gallary page
  
  
  @drag_and_drop
  Scenario: Drag and Drop
  Given user navigate to the herokuapp menu landing page
  And clicks on the drag and drag option
  Then user will navigate to the drag and drop page
  And user drags box A towards box B
  Then the box A and box B places interchanges
  And user refreshes the page
  And user drags box B towards box A
  Then the box A and box B places interchanges
  
  
  @dropdown
  Scenario: Dropdown
  Given user navigate to the herokuapp menu landing page
  And clicks on the dropdown option
  Then user navigates to dropdown page
  And user selects option1 from the dropdown
  Then dropdown shows option1 as its value
  And user selects option2 from the dropdown
  Then dropdown shows option2 as its value
  
  
  @dynamic_controls_checkbox
  Scenario: Dynamic controls checkbox
  Given user navigate to the herokuapp menu landing page
  And clicks on the Dynamic Controls option
  Then user navigates to dynamic controls page
  And user checks the checkbox and clicks on remove button
  Then The checkbox will disappear
  And user clicks on the add button
  Then the checkbox will reappear
  
  
  @dynamic_controls_txt_field
  Scenario: Dynamic controls text field
  Given user navigate to the herokuapp menu landing page
  And clicks on the Dynamic Controls option
  Then user navigates to dynamic controls page
  And user clicks on enable button
  Then The text field is enabled
  And user clicks on the disable button
  Then the text field is disabled
  
  
  @dynamic_loading
  Scenario: Dynamic loading
  Given user navigate to the herokuapp menu landing page
  And clicks on the Dynamic loading option
  Then user navigates to dynamic loading page
  And user clicks on element on page that is hidden link text
  Then user navigate to element on page that is hidden page
  And user clicks on the start button
  Then hello world message displays
  
  
  @entry_ad
  Scenario: Entry ad
  Given user navigate to the herokuapp menu landing page
  And clicks on the Entry Ad option
  Then user navigates to entry ad page
  And the enty ad modal display
  Then user clicks on the close button
  Then the enty ad modal will close
  
  
  @exit_intent_modal
  Scenario: Exit intent modal
  Given user navigate to the herokuapp menu landing page
  And clicks on the Exit Intent option
  Then user navigates to exit intent page
  Then the user mouse over to the url bar
  And the exit intent modal display
  Then user clicks on the close button
  Then the exit intent modal is closed
  
  
  @file_upload
  Scenario: File upload
  Given user navigate to the herokuapp menu landing page
  And clicks on the File Upload option
  Then user navigates to file upload page
  Then the user uploads a file
  And clicks on upload button
  Then the user receives success message
  And the file name matches
  
  
  @file_download
  Scenario: File download
  Given user navigates to the herokuapp menu landing page with custom chrom options
  And click on the File Download option
  Then user navigates to file download page
  And user clicks on a random file from the list
  Then file will be downloaded in the specified location
  
  
  @floating_menu
  Scenario: Floating menu
  Given user navigate to the herokuapp menu landing page
  And click on the Floating Menu option
  Then user navigates to floating menu page
  And user scroll down to the middle of the page
  Then clicks on one of the buttons
  And user scroll down to the bottom of the page
  Then clicks on one of the buttons
  
  
  @form_authentication
  Scenario: Form authentication
  Given user navigate to the herokuapp menu landing page
  And click on the Form Authentication option
  Then user navigates to form authentication page
  And user enters "tomsmith" username and "SuperSecretPassword!" password
  Then user clicks on the login button
  Then login will be successfull and success message displays
  Then user clicks on the logout page
  Then user navigates to the form authencation and success message displays
  
  
  @frames
  Scenario: Frames
  Given user navigate to the herokuapp menu landing page
  And clicks on the Frames option
  Then user navigates to frames page
  And user clicks on the nested frames option
  Then user navigates to nested frames page
  And user switchs between the frames
  Then user navigates back to frames page
  And user clicks on the ifrmaes option
  Then user navigates to iframe page
  
  
  @horizontal_slider
  Scenario: Horizontal slider
  Given user navigate to the herokuapp menu landing page
  And clicks on the Horizontal Slider option
  Then user navigates to holizontal slider page
  Then user slides the slider to the extreme right then to the extreme left using keys
  Then user slides the slider to the extreme right then to the extreme left using drag and drop
  
  
  @hovers
  Scenario: Hovers
  Given user navigate to the herokuapp menu landing page
  And clicks on the Hovers option
  Then user navigates to hovers page
  Then user mouse over each image and view additional information
  
  
  @infinite_scroll
  Scenario: Infinite scroll
  Given user navigate to the herokuapp menu landing page
  And clicks on the Infinite Scroll option
  Then user navigates to infinite scroll page
  Then user scroll down until he reaches 2000 length height
  
  
  @jquery_elements
  Scenario: JQuery elements
  Given user navigates to the herokuapp menu landing page with custom chrom options
  And clicks on the JQuery UI Menus option
  Then user navigates to jquery ui menus page
  Then user mouse over enabled option and downloads a file
  Then user mouse over enabled option and clicks on back jquery ui menu option
  Then user navigates jqueryui page
  
  
  @javascript_alerts
  Scenario: Javascript alerts
  Given user navigate to the herokuapp menu landing page
  And clicks on the JavaScript Alerts option
  Then user navigates to the javascript alerts page
  Then user clicks on the js alert button
  Then js alert popup displays and user accepts it
  Then user clicks on the js confirm button
  Then js confirm popup displays and user accepts it
  Then user clicks on the js prompt button
  Then js prompt popup displays user enters "This is a Javascript prompt" and accepts it
  
  
  @javascript_error
  Scenario: Javascript errorKey Presses
  Given user navigate to the herokuapp menu landing page
  And clicks on the JavaScript onload event error option
  Then user navigates to the javascript error page
  Then javascript error displays on the page
  
  
  @key_presses
  Scenario: Key presses
  Given user navigate to the herokuapp menu landing page
  And clicks on the Key Presses option
  Then user navigates to the key presses page
  Then user types a character into the field
  Then you entered message will display with the key typed in the field
  Then user enters the backspace in the field
  Then you entered message will display with backspace
  Then user types number into the field
  Then you entered message will display with the number typed in the field
  
  
  @multiple_windows
  Scenario: Multiple windows
  Given user navigate to the herokuapp menu landing page
  And clicks on the Multiple Windows option
  Then user navigates to the multiple windowns page
  Then user clicks on the click here link
  Then user will automatically navigate to new window
  Then user navigate back to parent window
  
  
  @notification_messages
  Scenario: Notification messages
  Given user navigate to the herokuapp menu landing page
  And clicks on the Notification Messages option
  Then user navigates to the notification messages page
  Then one of the following messages will shows on the page
  	|Action successful|
  	|Action unsuccesful, please try again|
  Then user clicks on the click here link
  Then one of the following messages will shows on the page
  	|Action successful|
  	|Action unsuccesful, please try again|
  	
  	
  @large_and_deep_dom
  Scenario: Large and deep dom
  Given user navigate to the herokuapp menu landing page
  And clicks on the Large & Deep Dom option
  Then user navigates to the large and deep dom page
  Then user reads the value from "20" column and the values meet expected
  Then user reads the value from "10" row and the values meet expected