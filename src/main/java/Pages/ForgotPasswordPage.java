package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage {
    private SelenideElement forgotPasswordForm = $x("");
    private final SelenideElement emailInput = $(byCssSelector("[placeholder='Email']"));

    public ForgotPasswordPage checkForgotPasswordFormOpening() {
        forgotPasswordForm.shouldBe(Condition.visible);
        emailInput.shouldBe(Condition.visible);
        return this;
    }
}
