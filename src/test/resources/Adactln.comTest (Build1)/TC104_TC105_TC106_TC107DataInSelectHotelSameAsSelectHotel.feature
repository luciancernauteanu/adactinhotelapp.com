Feature: TC104, TC105, TC106, TC107 Check if input data in Search Hotel page is the same as from Select Hotel page

    #  *****TC104*******

  Scenario: TC104 To verify whether locations in Select Hotel page are displayed according to the location selected in Search Hotel
    Given the Adactin.com Booking website.
    Then I login to website successfully with my credentials.
    When I select my location as "Sydney".
    And I select my hotel as "Hotel Creek".
    And I select my room type as "Standard".
    And I select number of rooms to "1".
    And I enter check-in-date to today’s date and the checkout-date is "1" day from today.
    And I select No-of-adults as "1"
    And number of children as "0"
    And I click on Search button
    Then I verify that hotel displayed in the Select Hotel page is the same as selected in search Hotel form.

    #  *****TC105*******

  Scenario: TC105 To verify whether Check-in date and Check Out date are being displayed in Select Hotel page according to the dates selected in search Hotel.
    Given the Adactin.com Booking website.
    Then I login to website successfully with my credentials.
    When I select my location as "Sydney".
    And I select my hotel as "Hotel Creek".
    And I select my room type as "Standard".
    And I select number of rooms to "1".
    And I enter check-in-date to today’s date and the checkout-date is "1" day from today.
    And I select No-of-adults as "1"
    And number of children as "0"
    And I click on Search button
    Then verify thatCheck-in date and Check Out date selected in the Select Hotel page are the same according to the dates selected in Search Hotel page.

    #  *****TC106*******

  Scenario: TC106 To verify whether no. of rooms entry in Select Hotel page is same as the Number of rooms selected in search hotel page
    Given the Adactin.com Booking website.
    Then I login to website successfully with my credentials.
    When I select my location as "Sydney".
    And I select my hotel as "Hotel Creek".
    And I select my room type as "Standard".
    And I select number of rooms to "3".
    And I enter check-in-date to today’s date and the checkout-date is "1" day from today.
    And I select No-of-adults as "1"
    And number of children as "0"
    And I click on Search button
    Then verify that number of rooms displayed in the Select Hotel page are the same according to the number of rooms selected in Search Hotel page.

    #  *****TC107*******

  Scenario: TC107 To verify whether Room Type in Select Hotel page is same as Room type selected in search hotel page
    Given the Adactin.com Booking website.
    Then I login to website successfully with my credentials.
    When I select my location as "Sydney".
    And I select my hotel as "Hotel Creek".
    And I select my room type as "Deluxe".
    And I select number of rooms to "1".
    And I enter check-in-date to today’s date and the checkout-date is "1" day from today.
    And I select No-of-adults as "1"
    And number of children as "0"
    And I click on Search button
    Then verify that room type displayed in the Select Hotel page are the same according to the room type selected in Search Hotel page.













