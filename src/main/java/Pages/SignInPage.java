package Pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignInPage {

    private final SelenideElement signInForm = $x("//div[@id='signin']");
    private final SelenideElement emailInput = $x("//input[@id='sw-form-capture-email-input']");
    private final SelenideElement passwordInput = $x("//input[@id='sw-form-password-input']");
    private final SelenideElement signInButtonForm = $x("//a[@id='sw-sign-in-submit-btn']");
    private final SelenideElement errorMessageSignIn = $x("//div[@class='error-message login-error d-block']");
    private final SelenideElement forgotPasswordLink = $(byCssSelector("[href='/forgot-password']"));


    @Step("Authentication with correct credentials.")
    public void getAuth(String email, String password) {
        checkSignInForm();
        setEmail(email);
        setPassword(password);
        signIn();
    }

    @Step("Check signIn form")
    public SignInPage checkSignInForm() {
        signInForm.shouldBe(Condition.visible, Duration.ofSeconds(2000));
        return this;

    }

    @Step("Enter '{email}'")
    public SignInPage setEmail(String email) {
        emailInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        emailInput.setValue(email);
        return this;

    }

    @Step("Enter '{password}'")
    public SignInPage setPassword(String password) {
        passwordInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        passwordInput.setValue(password);
        return this;

    }

    @Step("Click sign in button")
    public void signIn() {
        signInButtonForm.shouldBe(Condition.visible).click();

    }

    @Step("Check login-error message")
    public void checkError() {
        errorMessageSignIn.shouldHave(Condition.text("Invalid email or password"));

    }

    @Step(" Click  link 'Forgot password'")
    public void clickForgotPasswordLink() {
        forgotPasswordLink.shouldBe(Condition.visible).click();
    }
}
