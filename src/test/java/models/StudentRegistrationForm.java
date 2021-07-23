package models;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.Calendar;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {

    private SelenideElement firstName = $("#firstName");
    private SelenideElement lastName = $("#lastName");
    private SelenideElement email = $("#userEmail");
    private ElementsCollection genderOptions = $$("label[for*=gender-radio]");
    private SelenideElement phone = $("#userNumber");
    private SelenideElement subjectsContainer = $("#subjectsContainer");
    private SelenideElement subjects = subjectsContainer.$("input");
    private ElementsCollection hobbyOptions = $$x("//input[contains(@id,'hobbies')]/following-sibling::label");
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
        genderOptions.find(matchText(option)).click();
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

    public StudentRegistrationForm fillSubjects(String[] values) {
        subjectsContainer.click();
        for (String subject : values) {
            subjects.val(subject).pressTab();
        }
        return this;
    }

    public StudentRegistrationForm fillHobbies(String[] values) {
        for (String hobby : values) {
            hobbyOptions.find(text(hobby)).click();
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
        submitButton.click();
    }

}
