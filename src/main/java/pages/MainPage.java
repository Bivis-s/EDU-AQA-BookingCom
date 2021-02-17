package pages;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class MainPage extends AbstractPage {
    private static final String MAIN_PAGE_LANG_EN_URL = URL + "/index.html?lang=en-us";
    private static final String SEARCH_FIELD_XPATH = "//*[@id='ss']";
    private static final String SEARCH_BUTTON_XPATH = "//*[contains(@class,'sb-searchbox__button')]";

    public MainPage openPage() {
        log.info("Opened " + MAIN_PAGE_LANG_EN_URL);
        open(MAIN_PAGE_LANG_EN_URL);
        return this;
    }

    public MainPage sendKeysToSearchField(String... keys) {
        log.info("Sent keys " + keys);
        $x(SEARCH_FIELD_XPATH).sendKeys(keys);
        return this;
    }

    public SearchResultPage clickSearchButton() {
        log.info("Clicked search button " + SEARCH_BUTTON_XPATH);
        $x(SEARCH_BUTTON_XPATH).click();
        return new SearchResultPage();
    }
}
