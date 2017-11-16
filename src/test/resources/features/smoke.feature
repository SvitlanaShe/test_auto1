Feature: Smoke Test Pack
    In order to check main functionality
    I want to do smoke test for main UI

  @Positive
  @Smoke
  @Ignore
  Scenario: 1 verify filter by manufacture "BMW" applyed
    Given on main page
    When click checkbox "BMW"
    Then filter by manufacture "BMW" applyed

  @Positive
  @Smoke
  @Ignore
  Scenario: 2 verify all car's top names contains "BMW"
    Given on main page
    When click checkbox "BMW"
    Then filter by manufacture "BMW" applyed
    And all car's top names contains "BMW"

  @Positive
  @Smoke
  @Ignore
  Scenario: 3 verify each car has image
    Given on main page
    When click checkbox "BMW"
    Then filter by manufacture "BMW" applyed
    And each car has image

    @Positive
    @Smoke
    @Ignore
    Scenario: 4 verify no empty data in car's description
      Given on main page
      When click checkbox "BMW"
      Then filter by manufacture "BMW" applyed
      And no empty data in car's description

   @CloseDriver
    Scenario: 5 Close Driver
      Given on main page
      Then close driver and browser
