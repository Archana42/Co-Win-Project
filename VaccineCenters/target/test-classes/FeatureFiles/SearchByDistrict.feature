Feature: Search Vaccine Center by District

  Scenario: Search for District Andhra Pradesh
    Given Browser is open
    And User is on Home Page
    When User clicks on Search by district
    And User selects state Andhra Pradesh and district Kurnool
    Then Vaccine centers slots will be displayed as per inputs
