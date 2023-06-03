package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {
    private static final SelenideElement signInForm = $x("//div[@id='signin']");
    private static final SelenideElement emailInput = $x("//input[@id='sw-form-capture-email-input']");
    private static final SelenideElement passwordInput = $x("//input[@id='sw-form-password-input']");
    private static final SelenideElement signInButton = $x("//a[@id='sw-sign-in-submit-btn']");
    private static final SelenideElement errorContainer = $(byClassName("error-message login-error d-block"));


    @Step("Authentication and authorization with correct credentials.")
    public void getAuth(String email, String password) {
        checkSignInForm();
        setEmail(email);
        setPassword(password);
        signIn();
    }

    @Step("Check signIn form")
    public void checkSignInForm() {
        signInForm.shouldBe(Condition.visible, Duration.ofSeconds(2000));

    }

    @Step("Enter '{email}'")
    public void setEmail(String email) {
        emailInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        emailInput.setValue(email);

    }

    @Step("Enter '{password}'")
    public void setPassword(String password) {
        passwordInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        passwordInput.setValue(password);

    }
    @Step("Click sign in button")
    public HomePage signIn() {
        signInButton.shouldBe(Condition.visible).click();
        return new HomePage();
    }

    @Step("Check error message")
    public SignInPage checkError() {
        errorContainer.shouldHave(Condition.cssClass("error"));
        return this;
    }
}
