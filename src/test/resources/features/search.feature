Feature: Search on Booking.com

  Scenario Outline: Search by city criteria
    Given User is looking for '<City>' city
    When User clicks on search button
    Then Hotel '<Hotel>' should be on the first page

    Examples:
      | City  | Hotel                      |
      | Minsk | DoubleTree by Hilton Минск |
      | Brest | Отель ЭРМИТАЖ              |