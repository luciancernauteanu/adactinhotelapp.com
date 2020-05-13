Feature: Verify that the information in Booking confirmation
  Scenario: TC111 To check Hotel name, Location, Room type, Total Day, Price per night are same in Booking confirmation page as they were selected in previous screen.
    Given the website Adactin.com
    Then I successfully login to the website with my credentials
    When I select the location option to "Sydney".
    And I select the hotel option to "Hotel Creek".
    And I select the room type option to "Standard".
    And I select the number of rooms option to "2".
    And I select check-in-date to today date and the checkout-date to today date plus one day.
    And I select the no-of-adults option to "1".
    And I select the number of children option to "0".
    And when I click the Search button
    And I select the hotel that I want to book.
    And when I click Continue
    And I enter the booking details and click on book now.
    Then I check Hotel name, Location, Room type, Total Day, Price per night are same in Booking confirmation page as they were selected in previous screen.

#   Bugs are identified in Booking Confirmation page:
#   - room type is incorrect displayed  ----> expected:<[Standard]> but was:<[Deluxe]>
#     /////Assert to be executed after bug is fixed
#   - total Days field is not implemented
#     /////Assert to be executed after bug is fixed

  Scenario: TC114 Verify Order number is generated in Booking confirmation page
    Given the website Adactin.com
    Then I successfully login to the website with my credentials
    When I select the location option to "Sydney".
    And I select the hotel option to "Hotel Creek".
    And I select the room type option to "Standard".
    And I select the number of rooms option to "2".
    And I select check-in-date to today date and the checkout-date to today date plus one day.
    And I select the no-of-adults option to "1".
    And I select the number of children option to "0".
    And when I click the Search button
    And I select the hotel that I want to book.
    And when I click Continue
    And I enter the booking details and click on book now.
    Then I verify if the Order number is generated in Booking confirmation page.
