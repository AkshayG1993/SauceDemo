Feature: Remove Single Item to Inventort page
  Background:
    Given the user is on the login page

  Scenario Outline: Removing Single Item to Inventory page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    And the inventory page components should be loaded
    When the user adds "<product>" to the cart
    Then the cart icon should show 1 count
    When the user remove "<product>" to the cart
    Then the cart icon should show 0 count
    Examples:
      | username      | password     | product               |
      | standard_user | secret_sauce | Sauce Labs Backpack   |
      | standard_user | secret_sauce | Sauce Labs Bike Light |
