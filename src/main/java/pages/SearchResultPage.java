package pages;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SearchResultPage {
    private static final String HOTEL_NAME_XPATH = "//*[contains(@class, 'sr-hotel__name')]";
    private static final String HOTEL_SCORE_XPATH = "//*[contains(@class, 'sr-hotel__name') and contains(text(),'%s')]//ancestor::*[contains(@class,'sr_property_block_main_row')]//*[contains(@class,'bui-review-score__badge')]";

    public List<String> getHotelNamesOnThePage() {
        return $$x(HOTEL_NAME_XPATH).texts();
    }

    public double getReviewScoreByHotelName(String hotelName) {
        log.info("Got actual double hotel " + hotelName + " score rate");
        return Double.parseDouble($x(String.format(HOTEL_SCORE_XPATH, hotelName))
                .getText().replaceAll(",", "."));
    }
}
