package Pages;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class SignUpPage {

    private final SelenideElement signUpForm = $x("//div[@id='signup']");
    private final SelenideElement selectRoleMenu = $x("//button[@title='Select your role']");

    private final SelenideElement fullNameInput = $x("//input[@id='sw-form-capture-full_name-input']");
    private final SelenideElement emailInput = $x("//input[@id='sw-form-capture-email-input']");
    private final SelenideElement passwordInput = $x("//input[@id='sw-form-password-input']");
    private final SelenideElement checkBoxInput = $(byCssSelector("[class='checkmark position-relative sw-checkbox']"));

    private final SelenideElement signUpButton = $x("//a[@id='sw-sign-up-submit-btn']");
    private final SelenideElement signInButton = $x("//a[@id='sw-go-to-sign-in-btn']");
    private final SelenideElement errorRequiredFields = $x("//div[contains(@class,'error-message required-errors d-block')]");
    private final SelenideElement errorAnExistingUser = $x("//div[contains(@class,'error-message signup-error d-flex')]");

    @Step("Check signUp form")
    public SignUpPage checkSignUpForm() {
        signUpForm.shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }

    @Step("Select '{role}'")
    public SignUpPage selectRole(String role) {
        SelenideElement teacherRole = $(byCssSelector("#bs-select-1-0"));
        SelenideElement studentRole = $(byCssSelector("#bs-select-1-1"));
        selectRoleMenu.shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        if (role.contains("teacher")) {
            teacherRole.click();
        } else {
            studentRole.click();
        }
        return this;
    }

    @Step("Enter '{full name}', '{email}', '{password}'")
    public SignUpPage fillRequiredFields(String fullName, String email, String password) {
        fullNameInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        fullNameInput.setValue(fullName);
        emailInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        emailInput.setValue(email);
        passwordInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        passwordInput.setValue(password);
        return this;
    }

    @Step("Enter '{email}'")
    public SignUpPage setEmail(String email) {
        emailInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        emailInput.setValue(email);
        return this;

    }

    @Step("Enter '{password}'")
    public SignUpPage setPassword(String password) {
        passwordInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        passwordInput.setValue(password);
        return this;
    }

    @Step("Click on a checkbox to consent to the Terms & Privacy Policy")
    public SignUpPage clickCheckbox() {
        checkBoxInput.click();
        return this;
    }

    @Step("Click sign up button")
    public void signUp() {
        signUpButton.shouldBe(Condition.visible).click();
    }

    @Step("Click sign in button")
    public void signIn() {
        signInButton.shouldBe(Condition.visible).click();
    }

    @Step("Check error message required fields")
    public void checkError() {
        errorRequiredFields.shouldHave(Condition.text("Please make sure there are no empty required fields."));
    }

    @Step("Check error message existing user")
    public void checkErrorExistUser() {
        errorAnExistingUser.shouldHave(Condition.text("User by given email already exists."));

    }
}
