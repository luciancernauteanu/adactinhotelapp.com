Feature: TC102 Search date error message

  Scenario: To verify whether the check-out date field accepts a later date than check-in date.
    Given the hotel reservation application website.
    Then I login to the application using valid username and password.
    When I select location to "Sydney".
    And I select hotel to "Hotel Creek".
    And I select room type to "Standard".
    And I select no-of-rooms to "1".
    And I enter check-in-date to "7" days from now and the checkout-date to "5" days from now.
    And I click on Search button.
    Then verify that system gives an error saying‘check-in-date should not be later than check-out-date’.






