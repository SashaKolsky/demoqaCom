package components;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.format.DateTimeFormatter.ofPattern;

public class Calendar {
    public static void setDate(LocalDate date) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(date.format(ofPattern("MMMM")));
        $(".react-datepicker__year-select").selectOptionContainingText(date.format(ofPattern("yyyy")));
        String monthDay = date.format(ofPattern(".*MMMM d.*"));
        $$(".react-datepicker__day")
                .filter(attributeMatching("aria-label", monthDay))
                .first().click();
    }
}
