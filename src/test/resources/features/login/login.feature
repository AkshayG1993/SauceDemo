Feature: User Login

  Scenario Outline: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page
    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |