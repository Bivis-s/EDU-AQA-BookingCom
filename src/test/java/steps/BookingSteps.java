package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BookingSteps {
    private String city;
    private WebDriver driver;

    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void close() {
        driver.quit();
    }

    @Given("User is looking for {string} city")
    public void userIsLookingForCity(String city) {
        this.city = city;
    }

    @When("User clicks on search button")
    public void userClicksOnSearchButton() {
        open("https://www.booking.com/");
        $(By.id("ss")).sendKeys(city);
        $(".sb-searchbox__button").click();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        assertThat($$(".sr-hotel__name").texts(), hasItem(hotel));
    }
}
