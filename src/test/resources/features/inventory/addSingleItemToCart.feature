Feature: Add Single Item to Cart
  Background:
    Given the user is on the login page

  Scenario Outline: Adding Single Item to Cart
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    And the inventory page components should be loaded
    When the user adds "<product>" to the cart
    Then the cart icon should show 1 count
    Examples:
      | username      | password     | product               |
      | standard_user | secret_sauce | Sauce Labs Backpack   |
      | standard_user | secret_sauce | Sauce Labs Bike Light |
