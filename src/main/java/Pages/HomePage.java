package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement signInButton_Header = $(byCssSelector("[href='/sign-in']:nth-child(1)"));
    private final SelenideElement signUpButton_Header = $(byCssSelector("[href='/sign-up']:nth-child(2)"));
    private final SelenideElement professorsButton = $(byCssSelector("[href='#teacher-spotlight-heading']"));
    private final SelenideElement avatarImg = $(".MuiBox-root.css-4tv0ih");
    private final SelenideElement signOutButton = $(byCssSelector("[href='#']"));
    private final SelenideElement messageNonRegisteredUser = $(byText("Welcome to NoCode University's Student Portal"));
    private final SelenideElement messageRole = $x("//div[@class='col-lg-5 pb-5 pb-lg-0 text-center text-lg-left'] //p");

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
        return this;
    }

    @Step("Sign Out")
    public void signOut() {
        avatarImg.shouldBe(Condition.visible).click();
        signOutButton.shouldBe(Condition.visible).click();

    }

    @Step("Check avatar image")
    public HomePage isAvatarVisible() {
        avatarImg.isEnabled();
        return this;
    }

    @Step("Check redirect to Home page")
    public void checkHomePageOpeningByMessage() {
        messageNonRegisteredUser.shouldBe(Condition.visible);
    }

    @Step("Check redirect to Home page as a teacher")
    public HomePage checkHomePageOpeningTeacher() {
        messageRole.shouldBe(Condition
                .text("As a teacher, you can peruse courses and the student directory"));
        return this;
    }

    @Step("Check redirect to Home page as a student")
    public HomePage checkHomePageOpeningStudent() {
        messageRole.shouldBe(Condition.
                text("As a student you can use this portal to learn more about the courses we offer."));
        return this;
    }

    @Step("Check header is not visible")
    public void checkHeaderIsNotVisible() {
        signInButton_Header.shouldNotBe(Condition.visible);
        avatarImg.shouldNotBe(Condition.visible);
    }
}
