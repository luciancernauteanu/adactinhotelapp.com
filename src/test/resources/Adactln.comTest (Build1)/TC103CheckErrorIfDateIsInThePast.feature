Feature: TC103 Check error message if check-out is in the past

  Scenario: To check if error is reported if check-out date field is in the past
    Given the hotel application website.
    Then I login successfully to the application
    When I select location as "Sydney".
    And I select hotel as "Hotel Creek".
    And I select room type as "Standard".
    And I select no-of-rooms as "1".
    And I enter check-in-date to "-5" days from now and the checkout-date to "-3" days from now.
    And I click on Search button
    Then verify that System is reporting the error message ‘Enter Valid dates’.
