Feature: Verify log-out button functionality
  Scenario: TC109 To verify when pressed, logout button logs out from the application.
    Given the Booking website Adactin.com
    Then I successfully login to the website with my username and password
    When I select the location option as "Sydney".
    And I select the hotel option as "Hotel Creek".
    And I select the room type option as "Standard".
    And I select the number of rooms option as "2".
    And I select check-in-date to present date and the checkout-date to present date plus one day.
    And I select the no-of-adults option as "1".
    And I select the number of children option as "0".
    And then I click the Search button
    And I select the hotel option that I want to book.
    And then I click Continue button
    And I enter the details and click on book now.
    Then I check the details,click on logout and verify we have been logged out of the application.
