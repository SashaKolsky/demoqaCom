package com.example.demoqaCom;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import okhttp3.Address;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    private static final String FIRSTNAME = "Alexander";
    private static final String LASTNAME = "Kaz";
    private static final String EMAIL = "al@kaz.com";
    private static final String GENDER = "Male";
    private static final String PHONE = "4157994433";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.parse("13 Aug 1984",
                                                                   DateTimeFormatter.ofPattern("dd MMM yyyy"));
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
        fillForm();

        System.out.println("Verify Data");
        // todo: write form verification here
        String resultLocator;

        SelenideElement resultsTable = $(".modal-content table");
        System.out.println(resultsTable);
        resultLocator = ".//td[contains(text(), 'Student Name')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(FIRSTNAME + " " + LASTNAME));


        resultLocator = ".//td[contains(text(), 'Student Email')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(EMAIL));

        resultLocator = ".//td[contains(text(), 'Gender')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(GENDER));

        resultLocator = ".//td[contains(text(), 'Mobile')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(PHONE));

        resultLocator = ".//td[contains(text(), 'Date of Birth')]/following-sibling::td";
        resultsTable.find(By.xpath(resultLocator)).shouldHave(text(
                DATE_OF_BIRTH.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy"))));

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
        $("#firstName").sendKeys(FIRSTNAME);
        $("#lastName").sendKeys(LASTNAME);
        $("#userEmail").sendKeys(EMAIL);

        $$("label[for*=gender-radio]").find(matchText(GENDER)).click();
        //$(By.name("gender")).selectRadio("Other");    // Q: why doesn't this work and throws ElementClickInterceptedException instead? (saying it is overlayed by label element)

        $("#userNumber").sendKeys(PHONE);

        // Date of Birth
//        LocalDate date = LocalDate.parse(DATE_OF_BIRTH, DateTimeFormatter.ofPattern("dd MMM yyyy"));
//        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
//        $("#dateOfBirthInput").clear();
//        $("#dateOfBirthInput").sendKeys(formattedDate);   // Q: Why does sendKeys repopulate the field? Does it click on an element,before send in the text?
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select")
                .selectOptionContainingText(DATE_OF_BIRTH.format(DateTimeFormatter.ofPattern("MMMM")));
        $(".react-datepicker__year-select")
                .selectOptionContainingText(DATE_OF_BIRTH.format(DateTimeFormatter.ofPattern("yyyy")));
        $$(".react-datepicker__day")
                .find(text(DATE_OF_BIRTH.format(DateTimeFormatter.ofPattern("dd")))).click();

        // Subject
        $("#subjectsContainer").click();
        for (String subject : SUBJECTS) {
            $("#subjectsContainer input").sendKeys(subject);
            $("#subjectsContainer input").pressEnter();
        }

        // Hobbies
//        $("#hobbies-checkbox-2").click();   // Reading    // Q: checkboxes are blocked by label tag here as well.
        for (String hobbie : HOBBIES) {
            $$x("//input[contains(@id,'hobbies')]/following-sibling::label").find(text(hobbie)).click();
        }

        $("#currentAddress").sendKeys(ADDRESS);

        $("#state input").sendKeys(STATE);
        $("#state input").pressEnter();

        $("#city input").sendKeys(CITY);
        $("#city input").pressEnter();
        $("#submit").click();
    }
}
