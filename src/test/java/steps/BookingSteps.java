package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import pages.MainPage;
import pages.SearchResultPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

@Log4j2
public class BookingSteps {
    private MainPage mainPage;
    private SearchResultPage searchResultPage;
    private String city;

    @Given("User is looking for {string} city")
    public void userIsLookingForCity(String city) {
        log.info("Looked for city " + city);
        this.city = city;
        mainPage = new MainPage();
        searchResultPage = new SearchResultPage();
    }

    @When("User clicks on search button")
    public void userClicksOnSearchButton() {
        mainPage
                .openPage()
                .sendKeysToSearchField(city)
                .clickSearchButton();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        log.info("Asserted that first search result page contains hotel " + hotel);
        assertThat(searchResultPage.getHotelNamesOnThePage(), hasItem(hotel));
    }

    @Then("Hotel {string} review score of first search result page should be {string}")
    public void hotelReviewScoreOfFirstSearchResultPageShouldEqualsScore(String hotel, String score) {
        log.debug("Converted expected score " + score + " to double");
        double expectedScore = Double.parseDouble(score);
        double actualScore = searchResultPage.getReviewScoreByHotelName(hotel);
        log.info("Asserted equals expected double score rate " + expectedScore + " and actual " + actualScore);
        assertEquals(actualScore, expectedScore);
    }
}
