Feature: Itinerary Details are not editable

  Scenario: TC115 To verify whether the booked itinerary details are not editable.
    Given the website for online booking
    Then I successfully login to the website with my correct username and password
    When I select the location option to "Adelaide"
    And I select the hotel option to "Hotel Cornice"
    And I select room type option to "Standard"
    And I select number of rooms option to "2"
    And I select check-in to present day and the checkout-date to present day plus one day.
    And I select no-of-adults option to "1"
    And I select number of children option to "0"
    And when I click on Search button
    And I select the hotel by clicking the radio button
    And when I click on Continue button
    And I enter booking details and when I click on Book Now button I am redirected to the Booking Confirmation page
    And when I click on the My Itinerary button I am redirected to the Booked Itinerary page
    Then I verify that the details are not editable


