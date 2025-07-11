Feature: Accessing the inventory page directly

  Scenario Outline: Accessing the inventory page directly
    Given the user try to open the inventory page without login
    Then the login page should be loaded
    And the error message should be displayed "<errorMsg>"
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    Examples:
      | username      | password     | errorMsg                                                                    |
      | standard_user | secret_sauce | Epic sadface: You can only access '/inventory.html' when you are logged in. |