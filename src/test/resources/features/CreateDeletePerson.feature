Feature: Create Person

  Scenario: Test single person creation
    Given I can get all person
    When I request creation person with:
      | id  | name           |
      | 1   | Alexander Puszkin    |
    Then I should get successful result of creation
    And I make sure, that one person was added

  Scenario: Test multiply person creation
    Given I can get all person
    When I request creation person with:
      | id  | name           |
      | 1   | Janusz Kowalski    |
      | 2   | Grazyna Kowalska   |
    Then I should get successful result of creation
    And I make sure, that multiply person were added

  Scenario: Test person deletion
    Given I can get all person
    When I request to remove person with id 1
    Then I can see that this person deleted from output

  Scenario: Test unsuccessful creation single person
    Given I can get all person
    When I request creation person with invalid data:
      | id       | name           |
      | qwerty   | Ivan Ivanov    |
    Then I should get creation error

  Scenario: Test unsuccessful creation multiply person
    Given I can get all person
    When I request creation person with invalid data:
      | id       | name           |
      | qwerty   | Ivan Ivanov    |
      | 2        | Petr Petrov    |
    Then I should get creation error
