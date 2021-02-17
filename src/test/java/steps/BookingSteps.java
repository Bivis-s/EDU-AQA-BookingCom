package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

@Log4j2
public class BookingSteps {
    private String city;
    private WebDriver driver;
    private static final String HOTEL_SCORE_XPATH = "//*[contains(@class,'sr-hotel__name') and contains(text(),'%s')]//ancestor::*[contains(@class,'sr_property_block_main_row')]//*[contains(@class,'bui-review-score__badge')]";
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String SEARCH_BUTTON_CSS = ".sb-searchbox__button";

    @Before
    public void init() {
        log.debug("Driver inited");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void close() {
        log.debug("Driver closed");
        driver.quit();
    }

    @Given("User is looking for {string} city")
    public void userIsLookingForCity(String city) {
        log.info("Looked for city " + city);
        this.city = city;
    }

    @When("User clicks on search button")
    public void userClicksOnSearchButton() {
        log.info("Opened " + BOOKING_URL);
        open(BOOKING_URL);
        log.info("Sent keys " + city);
        $(By.id("ss")).sendKeys(city);
        log.info("Clicked search button " + SEARCH_BUTTON_CSS);
        $(SEARCH_BUTTON_CSS).click();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        log.info("Asserted that first search result page contains hotel " + hotel);
        assertThat($$(".sr-hotel__name").texts(), hasItem(hotel));
    }

    @Then("Hotel {string} review score of first search result page should be {string}")
    public void hotelReviewScoreOfFirstSearchResultPageShouldEqualsScore(String hotel, String score) {
        log.debug("Converted expected score " + score + " to double");
        double expectedScore = Double.parseDouble(score);
        log.info("Got actual double hotel " + hotel + " score rate");
        double actualScore = Double.parseDouble($x(String.format(HOTEL_SCORE_XPATH, hotel))
                .getText().replaceAll(",", "."));
        log.info("Asserted equals expected double score rate " + expectedScore + " and actual " + actualScore);
        assertEquals(actualScore, expectedScore);
    }
}
