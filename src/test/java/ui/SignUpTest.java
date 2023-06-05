package ui;


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
        homePage.clickSignUpButton();
        signUpPage
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
        homePage.clickSignUpButton();
        signUpPage
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
        homePage.clickSignUpButton();
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
        homePage.clickSignUpButton();
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
