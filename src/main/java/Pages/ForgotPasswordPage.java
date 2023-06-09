package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;


public class ForgotPasswordPage {
    //private final SelenideElement forgotPasswordForm = $(byClassName("MuiGrid-root MuiGrid-container css-1d3bbye"));
    private final SelenideElement forgotPasswordForm = $(byCssSelector("[class='MuiGrid-root MuiGrid-container css-1d3bbye']"));
    private final SelenideElement emailInput = $(byCssSelector("[placeholder='Email']"));

    public ForgotPasswordPage checkForgotPasswordFormOpening() {
        forgotPasswordForm.shouldBe(Condition.visible);
        emailInput.shouldBe(Condition.visible);
        return this;
    }
}
