package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;


public class ForgotPasswordPage {
    private final SelenideElement forgotPasswordForm = $(byClassName("form-header"));
    private final SelenideElement emailInput = $(byCssSelector("[placeholder='Email']"));
    private final SelenideElement recoverPasswordButton = $x("//button[@type='button']");
    private final SelenideElement resetPasswordMessage = $(".sw-font-size-l.sw-text-color-000000.sw-font-family-default.sw-padding-top-none.sw-padding-bottom-none.sw-padding-left-none.sw-padding-right-none");

    @Step("check form opening forgot password")
    public ForgotPasswordPage checkFormOpeningForgotPassword() {
        forgotPasswordForm
                .shouldBe(Condition.visible)

                .shouldHave(Condition.exactText("Forgot password"));
        return this;
    }

    @Step("Enter '{email}'")
    public ForgotPasswordPage setEmail(String email) {
        emailInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        emailInput.setValue(email);
        return this;

    }

    @Step("Click recover password button")
    public ForgotPasswordPage clickRecoverPassword() {
        recoverPasswordButton.shouldBe(Condition.visible).click();
        return this;

    }

    @Step("Check reset password message appeared")
    public ForgotPasswordPage checkMessageResetPassword() {
        resetPasswordMessage
                .shouldBe(Condition.visible)
                .shouldHave(Condition.
                        exactText("Please check your email and click on the provided link to reset your password."));

        return this;
    }
}
