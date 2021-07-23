package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class StudentRegistrationFormTest {
    private static final String FIRSTNAME = "Alexander";
    private static final String LASTNAME = "Kaz";
    private static final String EMAIL = "al@kaz.com";
    private static final String GENDER = "Male";
    private static final String PHONE = "4157994433";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.parse("29 Aug 1990", ofPattern("d MMM yyyy"));
    private static final String[] SUBJECTS = {"Computer Science", "Physics", "Math"};
    private static final String[] HOBBIES = {"Reading", "Music"};
    private static final String ADDRESS = "Lucknow University Main Building\n" +
            "University Rd\n" +
            "Babuganj, Hasanganj";
    private static final String STATE = "Uttar Pradesh";
    private static final String CITY = "Lucknow";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.baseUrl = "https://demoqa.com";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("/automation-practice-form");
    }

    @Test
    public void studentRegFormSubmitTest() {
        String resultLocator;

        fillForm();

        SelenideElement resultsTable = $(".modal-content table");

        resultLocator = ".//td[contains(text(), 'Student Name')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(FIRSTNAME + " " + LASTNAME));

        resultLocator = ".//td[contains(text(), 'Student Email')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(EMAIL));

        resultLocator = ".//td[contains(text(), 'Gender')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(GENDER));

        resultLocator = ".//td[contains(text(), 'Mobile')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(PHONE));

        resultLocator = ".//td[contains(text(), 'Date of Birth')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(DATE_OF_BIRTH.format(ofPattern("dd MMMM,yyyy"))));

        resultLocator = ".//td[contains(text(), 'Subjects')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(String.join(", ", SUBJECTS)));

        resultLocator = ".//td[contains(text(), 'Hobbies')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(String.join(", ", HOBBIES)));
//
//        resultLocator = "//td[contains(text(), 'Picture')]/following-sibling::td";
//
        resultLocator = ".//td[contains(text(), 'Address')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(ADDRESS.replace("\n", " ")));

        resultLocator = ".//td[contains(text(), 'State and City')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(STATE + " " + CITY));

    }

    private static void fillForm() {
        $("#firstName").val(FIRSTNAME);
        $("#lastName").val(LASTNAME);
        $("#userEmail").val(EMAIL);

        $$("label[for*=gender-radio]").find(matchText(GENDER)).click();

        $("#userNumber").val(PHONE);

        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select")
                .selectOptionContainingText(DATE_OF_BIRTH.format(ofPattern("MMMM")));
        $(".react-datepicker__year-select")
                .selectOptionContainingText(DATE_OF_BIRTH.format(ofPattern("yyyy")));
        String monthDay = DATE_OF_BIRTH.format(ofPattern(".*MMMM d.*"));
        $$(".react-datepicker__day")
                .filter(attributeMatching("aria-label", monthDay))
                .first().click();

        // Subject
        $("#subjectsContainer").click();
        for (String subject : SUBJECTS) {
            $("#subjectsContainer input").val(subject).pressTab();
        }

        // Hobbies
        for (String hobby : HOBBIES) {
            $$x("//input[contains(@id,'hobbies')]/following-sibling::label").find(text(hobby)).click();
        }

        $("#currentAddress").val(ADDRESS);

        $("#state input").val(STATE).pressTab();

        $("#city input").val(CITY).pressTab();

        $("#submit").click();
    }
}
