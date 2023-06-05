package ui;

import Pages.*;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;


import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    ProfessorSpotlight professorSpotlight = new ProfessorSpotlight();
    HomePage homePage = new HomePage();
    SignInPage signInPage = new SignInPage();
    SignUpPage signUpPage = new SignUpPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    String noCodeUniversityUrl = "https://jere237.softr.app/";


    @BeforeMethod
    public void setUp() {
        //Configuration.fastSetValue = true;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        open(noCodeUniversityUrl);
    }
    public void correctAuthTest(String email, String password) {
        homePage.clickSignInButton();
        signInPage
                .checkSignInForm()
                .setEmail(email)
                .setPassword(password)
                .signIn();
        homePage.checkHomePageOpeningByMessage();

    }



}
