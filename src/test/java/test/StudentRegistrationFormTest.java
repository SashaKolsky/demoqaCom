package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import models.StudentRegistrationForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import utils.RandomUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.format.DateTimeFormatter.ofPattern;

public class StudentRegistrationFormTest {
    private static final List<String> GENDER_LIST = Arrays.asList("Male", "Female", "Other");
    private static final List<String> SUBJECTS_LIST = Arrays.asList("Computer Science", "Physics", "Maths");
    private static final List<String> HOBBIES_LIST = Arrays.asList("Sports", "Reading", "Music");
    private static final String address = "Lucknow University Main Building\n" +
            "University Rd\n" +
            "Babuganj, Hasanganj";
    private static final String state = "Uttar Pradesh";
    private static final String city = "Lucknow";
    Faker faker = new Faker(Locale.US);
    private String firstname = faker.name().firstName();
    private String lastname = faker.name().lastName();
    private String email = faker.internet().emailAddress(firstname + lastname)
            .replaceAll("[^A-Za-z.@-]", "");
    private String gender = GENDER_LIST.get(faker.random().nextInt(GENDER_LIST.size()));
    private String phone = faker.phoneNumber().subscriberNumber(10);
    private LocalDate dateOfBirth = faker.date().birthday()
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    private List<String> subjects = RandomUtils.getRandomElementsFromList(SUBJECTS_LIST);
    private List<String> hobbies = RandomUtils.getRandomElementsFromList(HOBBIES_LIST);

    @BeforeAll
    public static void setUpAll() {
        Configuration.baseUrl = "https://demoqa.com";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("/automation-practice-form");
    }

    @RepeatedTest(1)
    public void studentRegFormSubmitTest() {
        String resultLocator;

        fillForm();

        SelenideElement resultsTable = $(".modal-content table");

        resultsTable.find(byText("Student Name")).sibling(0).shouldHave(text(firstname + " " + lastname));
        resultsTable.find(byText("Student Email")).sibling(0).shouldHave(text(email));
        resultsTable.find(byText("Gender")).sibling(0).shouldHave(text(gender));
        resultsTable.find(byText("Mobile")).sibling(0).shouldHave(text(phone));
        resultsTable.find(byText("Date of Birth")).sibling(0)
                .shouldHave(text(dateOfBirth.format(ofPattern("dd MMMM,yyyy"))));
        resultsTable.find(byText("Subjects")).sibling(0)
                .shouldHave(text(String.join(", ", subjects)));
        resultsTable.find(byText("Hobbies")).sibling(0)
                .shouldHave(text(String.join(", ", hobbies)));
//
//        resultLocator = "//td[contains(text(), 'Picture')]/following-sibling::td";
//
        resultsTable.find(byText("Address")).sibling(0).shouldHave(text(address.replace("\n", " ")));
        resultsTable.find(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));

    }

    private void fillForm() {

        new StudentRegistrationForm()
                .fillFirstName(firstname)
                .fillLastName(lastname)
                .fillEmail(email)
                .selectGender(gender)
                .fillPhone(phone)
                .fillDateOfBirth(dateOfBirth)
                .fillSubjects(subjects)
                .fillHobbies(hobbies)
                .fillCurrentAddress(address)
                .fillState(state)
                .fillCity(city)
                .submitForm();
    }
}
