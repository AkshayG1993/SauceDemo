Feature: Error message disappears after correcting input

  Scenario Outline: Successful login on 2nd try
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should still be in login page
    And the error message should be displayed "<errorMsg>"
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be redirected to the inventory page
    Examples:
      | username        | password     | errorMsg                                                                  |
      | invalid         | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standard_user   | invalid      | Epic sadface: Username and password do not match any user in this service |
      | invalid         | invalid      | Epic sadface: Username and password do not match any user in this service |
      |                 |              | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
