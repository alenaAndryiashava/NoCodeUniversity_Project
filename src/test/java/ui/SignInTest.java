package ui;

import Pages.HomePage;
import Pages.SignInPage;

import org.testng.annotations.Test;


public class SignInTest extends BaseTest{


    @Test(dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void correctAuthTestWithDataProviderExcel(String email, String password) {
        new HomePage().clickSignInButton();
        new SignInPage()
                .checkSignInForm()
                .getAuth(email, password);
       new HomePage().checkHomePageOpeningByMessage();
       new HomePage().signOut();
    }

    @Test(testName = "CorrectAuthTeacherTest")
    public void correctAuthTest() {
        String email  = "roxanne@example.com";
        String password = "123456";
        new HomePage().clickSignInButton();
        SignInPage signInPage = new SignInPage();
        signInPage
                .checkSignInForm()
                .setEmail(email)
                .setPassword(password)
                .signIn();
        new HomePage().checkHomePageOpeningByMessage();

    }

}
