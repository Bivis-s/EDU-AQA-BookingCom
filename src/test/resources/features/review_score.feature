Feature: Check hotels review score on Booking.com

  @checkHotelScore
  Scenario Outline: Check hotel '<Hotel>' review score on search result page
    Given User is looking for '<City>' city
    When User clicks on search button
    Then Hotel '<Hotel>' review score of first search result page should be '<Score>'

    Examples:
      | City     | Hotel                            | Score |
      | Istanbul | Swissotel The Bosphorus Istanbul | 9.0   |
      | Minsk    | DoubleTree by Hilton Минск       | 9.2   |
      | Minsk    | Гостиница Пекин Минск            | 9.0   |
      | Tirana   | Maritim Hotel Plaza Tirana       | 9.4   |
