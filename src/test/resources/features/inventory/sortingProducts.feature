Feature: Inventory Page Functionality

  Background:
    Given the user is on the login page

  Scenario Outline: Inventory page successful loaded post login
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    And the inventory page components should be loaded
    When select sorting option "<sortOption>" from the inventory page
    And changes as per sorting type "<sortOption>" been observed
    Examples:
      | username      | password     | sortOption          |
      | standard_user | secret_sauce | Name (A to Z)       |
      | standard_user | secret_sauce | Name (Z to A)       |
      | standard_user | secret_sauce | Price (low to high) |
      | standard_user | secret_sauce | Price (high to low) |
