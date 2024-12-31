Feature: GooglePage Walk Around
  HealthCheck the Google Page.

  Scenario: Walk Around the Google Page
    Given User in the main page
    When User navigate to About Button
    And User navigate to GoogleApp Button
    And User navigate to Recording Button
    And User navigate to Langaugebutton
    And User navigate to SettingButton
    Then User turn mode