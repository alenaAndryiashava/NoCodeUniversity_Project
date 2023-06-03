package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header{
    private static final SelenideElement signInButton_Header = $(byCssSelector("[href='/sign-in']:nth-child(1)"));

    @Step("Click sign in button")
    public void clickSignInButton(){
        signInButton_Header.shouldBe(Condition.visible, Duration.ofSeconds(2000)).click();
    }
}

