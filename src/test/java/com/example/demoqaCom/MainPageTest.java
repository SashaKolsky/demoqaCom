package com.example.demoqaCom;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

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
    }

    private static void fillForm() {
        $("#firstName").sendKeys("Alexander");
        $("#lastName").sendKeys("Karovin");
        $("#userEmail").sendKeys("al@karovin.com");

        $$("label[for*=gender-radio]").find(matchText("[Ff]emale")).click();
        //$(By.name("gender")).selectRadio("Other");    // Q: why doesn't this work and throws ElementClickInterceptedException instead? (saying it is overlayed by label element)

        $("#userNumber").sendKeys("4159994433");

        // Date of Birth
//        LocalDate date = LocalDate.of(1984, 8, 13);
//        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
//        $("#dateOfBirthInput").clear();
//        $("#dateOfBirthInput").sendKeys(formattedDate);   // Q: Why does sendKeys repopulate the field? Does it click on an element,before send in the text?
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("August");
        $(".react-datepicker__year-select").selectOptionContainingText("1984");
        $$(".react-datepicker__day").find(text("13")).click();

        // Subject
        $("#subjectsContainer").click();
        $("#subjectsContainer input").sendKeys("Computer Sc");
        $("#subjectsContainer input").pressEnter();
        $("#subjectsContainer input").sendKeys("Physics");
        $("#subjectsContainer input").pressEnter();
        $("#subjectsContainer input").sendKeys("Math");
        $("#subjectsContainer input").pressEnter();

        // Hobbies
//        $("#hobbies-checkbox-2").click();   // Reading    // Q: checkboxes are blocked by label tag here as well.
//        $("#hobbies-checkbox-3").click();   // Music
        $$x("//input[contains(@id,'hobbies')]/following-sibling::label").find(text("Reading")).click();
        $$x("//input[contains(@id,'hobbies')]/following-sibling::label").find(text("Music")).click();

        String address = "Lucknow University Main Building\nUniversity Rd\nBabuganj, Hasanganj";
        $("#currentAddress").sendKeys(address);
        $("#state input").sendKeys("Uttar Pradesh");
        $("#state input").pressEnter();
        $("#city input").sendKeys("Lucknow");
        $("#city input").pressEnter();
        $("#submit").click();
    }
}
