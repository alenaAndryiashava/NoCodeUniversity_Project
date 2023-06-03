package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private static final SelenideElement message = $x("//header//h1[1]");


    @Step("Check redirect to Home page")
    public void checkHomePageOpeningByMessage(){
        message.shouldBe(Condition.exactText("Welcome to NoCode University's Student Portal"));
    }
}
