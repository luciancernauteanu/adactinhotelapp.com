Feature: To My Itinerary page functionality

  Scenario: TC117 To check whether "search order id” query is working and displaying the relevant details
    Given the book a hotel site
    Then I use my current user and pass to login to the app
    When the location option is selected as "Sydney"
    And the hotel option is selected as "Hotel Creek"
    And the room type option is selected as "Standard"
    And the number of rooms option  is selected as "2"
    And the check-in is selected as present day and the checkout-date is selected to next day
    And the no-of-adults is selected as "1"
    And the number of children option is selected as none
    And when Search is clicked
    And the hotel is selected by clicking the radio button
    And if Continue button is clicked, the next page is opened
    When booking details is entered in the fields and clicking on Book Now button and the Booking Confirmation page is opened
    Then a order number is generated
    When My Itinerary button is clicked, I am redirected to the Booked Itinerary page
    And I enter the the order Id into the "Search Order Id" field in the upper right corner of the page and click Go button
    Then all the relevant details for this order Id are displayed


  Scenario: TC119 To check whether "search order id” query is working and displaying the relevant details
    Given the book a hotel site
    Then I use my current user and pass to login to the app
    When the location option is selected as "Sydney"
    And the hotel option is selected as "Hotel Creek"
    And the room type option is selected as "Standard"
    And the number of rooms option  is selected as "2"
    And the check-in is selected as present day and the checkout-date is selected to next day
    And the no-of-adults is selected as "1"
    And the number of children option is selected as none
    And when Search is clicked
    And the hotel is selected by clicking the radio button
    And if Continue button is clicked, the next page is opened
    When booking details is entered in the fields and clicking on Book Now button and the Booking Confirmation page is opened
    Then a order number is generated
    When My Itinerary button is clicked, I am redirected to the Booked Itinerary page
    And I enter the the order Id into the "Search Order Id" field in the upper right corner of the page and click Go button
    Then all the relevant details for this order Id are displayed
    Then clicking Cancel order will cancel the order


