package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement signInButton_Header = $(byCssSelector("[href='/sign-in']:nth-child(1)"));
    private final SelenideElement signUpButton_Header = $(byCssSelector("[href='/sign-up']:nth-child(2)"));
    private final SelenideElement professorsButton = $(byCssSelector("[href='#teacher-spotlight-heading']"));
    private final SelenideElement avatarImg = $x("//header/div[1]/div[1]/div[2]/button[1]");
    private final SelenideElement signOutButton = $(byCssSelector("[class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters css-vsdmmi'][href='#']"));
    private final SelenideElement messageNonRegisteredUser = $x("//header//h1[1]");
    private final SelenideElement messageRole = $x("//header/div[1]/div[1]/div[1]/p[1]");

    @Step("Click sign in button")
    public SignInPage clickSignInButton() {
        signInButton_Header.shouldBe(Condition.visible, Duration.ofSeconds(2000)).click();
        return new SignInPage();
    }

    @Step("Click sign up button")
    public SignUpPage clickSignUpButton() {
        signUpButton_Header.shouldBe(Condition.visible, Duration.ofSeconds(2000)).click();
        return new SignUpPage();
    }

    @Step("Click Professors button")
    public HomePage clickProfessorsButton() {
        professorsButton.shouldBe(Condition.visible, Duration.ofSeconds(2000)).click();
        return new HomePage();
    }

    @Step("Sign Out")
    public void signOut() {
        avatarImg.shouldBe(Condition.visible).click();
        signOutButton.shouldBe(Condition.visible).click();

    }

    @Step("Check avatar image")
    public void isAvatarVisible() {
        avatarImg.isEnabled();
    }

    @Step("Check redirect to Home page")
    public void checkHomePageOpeningByMessage() {
        messageNonRegisteredUser.shouldBe(Condition
                .exactText("Welcome to NoCode University's Student Portal"));

    }

    @Step("Check redirect to Home page as a teacher")
    public HomePage checkHomePageOpeningTeacher() {
        messageRole.shouldBe(Condition
                .text("As a teacher, you can peruse courses and the student directory"));
        return this;
    }

    @Step("Check redirect to Home page as a student")
    public HomePage checkHomePageOpeningStudent() {
        messageRole.shouldBe(Condition
                .exactText("As a student you can use this portal to learn more about the courses we offer."));
        return this;
    }
}
