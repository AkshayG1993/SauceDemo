Feature: Inventory Page Functionality

  Scenario Outline: Inventory page successful loaded post login
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    And the inventory page components should be loaded
    And all inventory items should have a name, price, and image
    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |