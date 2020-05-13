Feature: Check that information from Booked Itinerary is the same from Booking Confirmation page

  Scenario: TC116 To check whether the booked itinerary reflects the correct information in line with the booking.
          & TC118 Verify that all the details of newly generate order number in booked itinerary page are correct and match with data during booking.
          & TC120 To Verify Title of every Page reflects what the page objective is. For example Title of Search Hotel page should have “SearchHotel”

    Given the application for online booking
    Then I use the correct username and password to login to the home webpage
    When I choose location option as "Sydney"
    And I choose hotel option to "Hotel Creek"
    And I choose room type option to "Standard"
    And I choose number of rooms option to "2"
    And I choose check-in to present day and the checkout-date to present day plus one day.
    And I choose no-of-adults option to "1"
    And I choose number of children option to "0"
    And clicking on Search button
    And choose the hotel by clicking the radio button
    And I want to continue to the next page by clicking on Continue button
    When when I enter booking details and then I click on Book Now button, I am redirected to the Booking Confirmation page
    And clicking on My Itinerary button, I am redirected to the Booked Itinerary page
    Then I verify that information from Booked Itinerary is the same from Booking Confirmation page

#  Bug(1) The page title of Booking Confirmation page does not reflect its objective
#         org.junit.ComparisonFailure: expected:<[AdactIn.com - Booking Confirmation]> but was:<[AdactIn.com - Book A Hotel]>
#         Assert to be uncommented and executed after bug has fixed

#  Bug(2) Bug identified in Booking Confirmation page (Last Name field does not display the input text from last name field from Book A Hotel page)
#         org.junit.ComparisonFailure: expected:<[]> but was:<[x]>
#         Assert to be uncommented and executed after bug has fixed

#  Bug(3) Bug identified in Booking Confirmation page (No. of days is not implemented in the Booking Confirmation, so assertion cannot be done)
#         Assert to be stated and executed after bug has fixed

#  Bug(4) Bug identified in Booking Confirmation page (Room type in Booking Confirmation has other value than the value in the Booked Itinerary, so assertion will fail)
#         Assert to be stated and executed after bug has fixed
#         org.junit.ComparisonFailure: expected:<[Deluxe]> but was:<[Standard]>

#  Bug(5) Bug identified in Booking Confirmation page (Final Billed Price in Booking Confirmation has other value than the value in the Booked Itinerary, so assertion will fail)
#         Assert to be uncomment after bug has fixed
#         org.junit.ComparisonFailure: expected:<AUD $ 14[8.5]> but was:<AUD $ 14[9]>



