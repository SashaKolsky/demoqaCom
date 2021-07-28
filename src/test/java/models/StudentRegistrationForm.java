package models;

import com.codeborne.selenide.SelenideElement;
import components.Calendar;

import java.time.LocalDate;
import java.util.List;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class StudentRegistrationForm {

    private SelenideElement firstName = $("#firstName");
    private SelenideElement lastName = $("#lastName");
    private SelenideElement email = $("#userEmail");
    private SelenideElement genderWrapper = $("#genterWrapper");
    private SelenideElement phone = $("#userNumber");
    private SelenideElement subjectsContainer = $("#subjectsContainer");
    private SelenideElement subjects = subjectsContainer.$("input");
    private SelenideElement hobbyWrapper = $("#hobbiesWrapper");
    private SelenideElement currentAddress = $("#currentAddress");
    private SelenideElement state = $("#state input");
    private SelenideElement city = $("#city input");
    private SelenideElement submitButton = $("#submit");

    public StudentRegistrationForm fillFirstName(String value) {
        firstName.val(value);
        return this;
    }

    public StudentRegistrationForm fillLastName(String value) {
        lastName.val(value);
        return this;
    }

    public StudentRegistrationForm fillEmail(String value) {
        email.val(value);
        return this;
    }

    public StudentRegistrationForm selectGender(String option) {
        genderWrapper.find(withText(option)).click();
        return this;
    }

    public StudentRegistrationForm fillPhone(String value) {
        phone.val(value);
        return this;
    }

    public StudentRegistrationForm fillDateOfBirth(LocalDate date) {
        Calendar.setDate(date);
        return this;
    }

    public StudentRegistrationForm fillSubjects(List<String> values) {
        subjectsContainer.click();
        for (String subject : values) {
            subjects.val(subject).pressTab();
        }
        return this;
    }

    public StudentRegistrationForm fillHobbies(List<String> values) {
        for (String hobby : values) {
            hobbyWrapper.find(withText(hobby)).click();
        }
        return this;
    }

    public StudentRegistrationForm fillCurrentAddress(String value) {
        currentAddress.val(value);
        return this;
    }

    public StudentRegistrationForm fillState(String value) {
        state.val(value).pressTab();
        return this;
    }

    public StudentRegistrationForm fillCity(String value) {

        city.val(value).pressTab();
        return this;
    }

    public void submitForm() {
        submitButton.scrollTo().click();
    }

}
