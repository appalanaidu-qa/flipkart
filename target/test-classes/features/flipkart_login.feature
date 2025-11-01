Feature: Flipkart Login Functionality

  Scenario: User logs in with valid credentials
    Given user navigates to Flipkart login page
    When user enters valid username and password
    And clicks on login button
    Then user should be logged in successfully
