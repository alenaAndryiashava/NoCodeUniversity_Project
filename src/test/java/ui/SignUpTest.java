package ui;


import api.ApiBase;
import api.enums.EndPoint;

import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {
    private final Faker faker = new Faker();
    private final String fullName = faker.name().fullName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();


    @Test(groups = {"positive"}, testName = "Test-case 2.1: Registration a non-existent user (as teacher)")
    public void registrationAsTeacherTest() {
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .selectRole("teacher")
                .fillRequiredFields(fullName, email, password)
                .clickCheckbox()
                .signUp();
        homePage.checkHomePageOpeningTeacher()
                .isAvatarVisible();
    }

    @Test(groups = {"positive"}, testName = "Test-case 2.2: Registration a non-existent user (as student)")
    public void registrationAsStudentTest() {
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .selectRole("student")
                .fillRequiredFields(fullName, email, password)
                .clickCheckbox()
                .signUp();
        homePage.checkHomePageOpeningStudent()
                .isAvatarVisible();

    }

    @Test(testName = "Test-case 2.3: Registration a non-existent user (student) without choosing a role.")
    public void registrationAsStudentWithoutRoleTest() {
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .fillRequiredFields(fullName, email, password)
                .clickCheckbox()
                .signUp();
        signUpPage
                .checkError();
    }

    @Test(testName = "Test-case 2.5: Registration an existing user (teacher).")
    public void registrationAsAnExistingUserTest() {
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .selectRole("teacher")
                .fillRequiredFields("Roxanne ", "roxanne@example.com", "123456")
                .clickCheckbox()
                .signUp();
        signUpPage
                .checkErrorExistUser();
    }

    @Test(testName = "Test-case 2.6: " +
            "Registration a non-existent user (student) without confirmation of the Terms & Privacy Policy.")
    public void registrationAsStudentWithoutCheckBoxConfirmationTest() {
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .selectRole("teacher")
                .fillRequiredFields(fullName, email, password)
                .signUp();
        signUpPage
                .checkError();
    }

    @Test(testName = "Test-case 2.7: " +
            "Registration a non-existent user (student) without full name.")
    public void registrationAsStudentWithoutFullNameTest() {
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .selectRole("teacher")
                .fillRequiredFields("", email, password)
                .clickCheckbox()
                .signUp();
        signUpPage
                .checkError();
    }

    @Test(testName = "Test-case 2.8: " +
            "Check Sign in button in the Sign up form.")
    public void checkSignInButtonInSignUpFormTest() {
        homePage.clickSignUpButton();
        signUpPage.signIn();
        signInPage.checkSignInForm();
    }

    @AfterMethod(onlyForGroups = {"positive"})
    public void tearDown() {
        new ApiBase().doDeleteRequest(EndPoint.DELETE_USER, 200, email);
    }
}
