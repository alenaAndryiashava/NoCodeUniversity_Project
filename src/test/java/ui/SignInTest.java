package ui;


import org.testng.annotations.Test;


public class SignInTest extends BaseTest {


    @Test(testName = "Test-cases 1.1 & 1.2:" +
            "Authentication and authorization as a teacher/student correct credentials",
            dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void correctAuthTestWithDataProviderExcel(String email, String password) {
        homePage.clickSignInButton();
        signInPage
                .checkSignInForm()
                .getAuth(email, password);
        homePage.checkHomePageOpeningByMessage();
        homePage.signOut();
    }

    @Test(testName = "Test-cases 1.3 " +
            "Authentication with incorrect credentials",
            dataProvider = "excelWrongDataRead", dataProviderClass = DataProviders.class)
    public void incorrectAuthTestWithDataProviderExcel(String email, String password) {
        homePage.clickSignInButton();
        signInPage.getAuth(email, password);
        signInPage.checkError();


    }

    @Test(testName = "Test-case 1.4:Verify reset password")
    public void verifyResetPasswordTest() {
        String email = "malik@example.com";
        homePage.clickSignInButton();
        signInPage
                .checkSignInForm()
                .setEmail(email)
                .clickForgotPasswordLink();
        forgotPasswordPage.checkForgotPasswordFormOpening();

    }

}
