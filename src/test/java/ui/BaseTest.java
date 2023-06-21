package ui;

import Pages.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;


import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    ProfessorSpotlight professorSpotlight = new ProfessorSpotlight();
    HomePage homePage = new HomePage();
    SignInPage signInPage = new SignInPage();
    SignUpPage signUpPage = new SignUpPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    TeacherDetailsPage teacherDetailsPage = new TeacherDetailsPage();
    EditRecordForm editRecordForm = new EditRecordForm();
    String noCodeUniversityUrl = "https://jere237.softr.app/";
    String newNoCodeUniversityUrl = "https://erich416.softr.app/";

    @BeforeMethod
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().savePageSource(false));

        open(newNoCodeUniversityUrl);
    }

    public void correctAuthTest(String email, String password) {
        homePage.clickSignInButton();
        signInPage
                .checkSignInForm()
                .setEmail(email)
                .setPassword(password)
                .signIn();
    }
}
