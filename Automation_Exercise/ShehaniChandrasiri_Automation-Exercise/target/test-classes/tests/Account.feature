Feature: Account statement

  Background:
    Given Account exists for Acc No. "12345678" with Name "Bob Smith"
    And deposits are made
      | Transaction | Amount |
      | INIT        | 200    |
      | DEP1        | 100    |
      | DEP2        | 450    |
      | DEP3        | 50     |
    And withdrawals are made
      | Transaction | Amount  |
      | CHQ001      | 675.55  |
    When statement is produced

  @regression
  Scenario: Statement includes account details
    Then the statement includes "Name: Bob Smith"
    And the statement includes "Account: 12345678"

  @regression
  Scenario: Balance is calculated on the statement
    Then the statement includes "Balance: 124.45"

  @regression
  Scenario: Statement includes transaction details
    Then the statement includes "INIT"
    And the statement includes "DEP1"
    And the statement includes "DEP2"
    And the statement includes "DEP3"
    And the statement includes "CHQ001"
