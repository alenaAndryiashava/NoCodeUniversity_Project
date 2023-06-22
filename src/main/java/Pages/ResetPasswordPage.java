package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class ResetPasswordPage {
    String link = "https://erich416.softr.app/reset-password?token=859c4774-da7e-47c5-941e-52fd4c576f8c";


    private final SelenideElement resetPasswordForm = $x("//div[@id='reset-password']");
    private final SelenideElement emailInput = $(byCssSelector("[placeholder='Email']"));
    private final SelenideElement passwordInput = $(byCssSelector("[placeholder='New Password']"));
    private final SelenideElement resetButton = $x("//a[@id='sw-reset-password-submit-btn']");

    @Step("Open email and check link and click")
    public void openResetLink() {
        $(byLinkText("https://erich416.softr.app/reset-password?token=" + "859c4774-da7e-47c5-941e-52fd4c576f8c"))
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Reset password")
    public ResetPasswordPage resetPasswordFormOpening() {
        resetPasswordForm
                .shouldBe(Condition.visible, Duration.ofSeconds(20))
                .shouldHave(Condition.text("Reset password"));
        return this;

    }

    @Step("Enter '{email}'")
    public ResetPasswordPage setEmail(String email) {
        emailInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        emailInput.setValue(email);
        return this;
    }

    @Step("Enter '{password}'")
    public ResetPasswordPage setNewPassword(String password) {
        passwordInput
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        passwordInput.setValue(password);
        return this;
    }

    @Step("Click reset button")
    public HomePage clickResetButton() {
        resetButton.shouldBe(Condition.visible).click();
        return new HomePage();

    }
}
