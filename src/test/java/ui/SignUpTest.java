package ui;


import Pages.HomePage;
import Pages.SignUpPage;
import api.ApiBase;
import api.enums.EndPoint;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    private String email;

    public SignUpTest(String email) {
        this.email = email;
    }


    @Test(groups = {"positive"}, testName = "Test-case 2.1: Registration a non-existent user (as teacher)")
    public void registrationAsTeacherTest() {
        email = "selabiv702@syinxun.com";
        HomePage homePage = new HomePage();
        homePage.clickSignUpButton();
        new SignUpPage()
                .checkSignUpForm()
                .selectRole("teacher")
                .fillRequiredFields("Tony Reid", email, "123456")
                .clickCheckbox()
                .signUp();
        homePage.checkHomePageOpeningTeacher()
                .isAvatarVisible();
    }

    @Test(groups = {"positive"}, testName = "Test-case 2.2: Registration a non-existent user (as student)")
    public void registrationAsStudentTest() {
        email = "juliana@gmail.com";
        HomePage homePage = new HomePage();
        homePage.clickSignUpButton();
        new SignUpPage()
                .checkSignUpForm()
                .selectRole("student")
                .fillRequiredFields("Juliana Palmer", email, "456778")
                .clickCheckbox()
                .signUp();
        homePage.checkHomePageOpeningStudent()
                .isAvatarVisible();

    }

    @Test(testName = "Test-case 2.5: Registration an existing user (teacher).")
    public void registrationAsAnExistingUserTest() {
        email = "roxanne@example.com";
        HomePage homePage = new HomePage();
        homePage.clickSignUpButton();
        SignUpPage signUpPage = new SignUpPage();
        signUpPage
                .checkSignUpForm()
                .selectRole("teacher")
                .fillRequiredFields("Roxanne ", email, "123456")
                .clickCheckbox()
                .signUp();
        signUpPage
                .checkErrorExistUser();

    }

    @Test(testName = "Test-case 2.6: Registration a non-existent user (student) without choosing a role.")
    public void registrationAsStudentWithoutRoleTest() {
        email = "juliana@gmail.com";
        HomePage homePage = new HomePage();
        homePage.clickSignUpButton();
        SignUpPage signUpPage = new SignUpPage();
        signUpPage
                .checkSignUpForm()
                .fillRequiredFields("Juliana Palmer", email, "456778")
                .clickCheckbox()
                .signUp();
        signUpPage
                .checkError();

    }

    @AfterMethod(onlyForGroups = {"positive"})
    public void tearDown() {
        new ApiBase().doDeleteRequest(EndPoint.DELETE_USER, 200, email);
    }
}
