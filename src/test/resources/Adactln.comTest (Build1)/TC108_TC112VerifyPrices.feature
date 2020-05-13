Feature: TC108 Verify that  the total price is calculated as “price per night, per no. of nights, per no. of rooms”.

  Scenario: TC108 To verify whether the total price is calculated as “price per night, no. of nights, no of rooms”.
    Given the Adactin Booking website.
    Then I login successfully to the website with my username and password
    When I select the location as "Sydney".
    And I select the hotel as "Hotel Creek".
    And I select the room type as "Standard".
    And I select the number of rooms to "2".
    And I select check-in-date to today’s date and the checkout-date is "1" day from today.
    And I select the no-of-adults as "1".
    And I select the number of children as "0".
    And then I click on Search button
    And I select the hotel I want to book.
    And I click Continue button
    Then I verify that total price is being calculated as price-per-night,no-of-rooms,no-of-days.

#   The test failed ----> Total price is not updated correctly (the number of rooms * price per night is not calculated correctly)
#   Bug to be fixed!

  Scenario: TC112 To check correct Final billed price is Total Price + 10% Total price in Book a Hotel page
    Given the Adactin Booking website.
    Then I login successfully to the website with my username and password
    When I select the location as "Sydney".
    And I select the hotel as "Hotel Creek".
    And I select the room type as "Standard".
    And I select the number of rooms to "2".
    And I select check-in-date to today’s date and the checkout-date is "1" day from today.
    And I select the no-of-adults as "1".
    And I select the number of children as "0".
    And then I click on Search button
    And I select the hotel I want to book.
    And I click Continue button
    Then I verify that Final billed price in Book a Hotel page is Total Price plus "10" percent Total price.

    #   The test failed ----> Total price is not updated correctly (the number of rooms * price per night + GST is not calculated correctly)
    #   Bug to be fixed!