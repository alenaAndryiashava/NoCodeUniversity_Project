package ui;

import Pages.Header;
import Pages.HomePage;
import Pages.SignInPage;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class SignInTest extends BaseTest{

    @Test(groups = {"auth_tests"}, dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void correctAuthTestWithDataProviderExcel(String email, String password) {
        sleep(2000);
        new Header().
                clickSignInButton();
        new SignInPage().
                getAuth(email, password);
        new HomePage()
                .checkHomePageOpeningByMessage();
    }

     @Test(testName = "CorrectAuthTest")
    public void correctAuthTest() {
        new Header().clickSignInButton();
        sleep(2000);
        SignInPage signInPage = new SignInPage();
        signInPage.checkSignInForm();
        signInPage.setEmail("roxanne@example.com");
        signInPage.setPassword("123456");
        signInPage.signIn();
        new HomePage().checkHomePageOpeningByMessage();

    }

}
