Feature: Launch W3Schools Website

  Scenario: Open W3Schools homepage
    Given user launches the Chrome browser
    When user opens "https://www.w3schools.com/"
    Then W3Schools homepage should be displayed
