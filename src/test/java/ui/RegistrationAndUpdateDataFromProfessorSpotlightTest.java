package ui;

import api.ApiBase;
import api.enums.EndPoint;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class RegistrationAndUpdateDataFromProfessorSpotlightTest extends BaseTest {
    private final Faker faker = new Faker();
    private final String fullName = faker.name().fullName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();
    private final String about = faker.lorem().sentence();

    @Test(testName = "Test-case 3.8: Registration a non-existent user (as teacher) and update profile page from Professor Spotlight")
    public void registrationAsTeacherAndUpdateProfileTest() {
        String newFullName = faker.name().fullName();
        String teacherRole = "teacher";
        homePage.clickSignUpButton();
        signUpPage
                .checkSignUpForm()
                .selectRole(teacherRole)
                .fillRequiredFields(fullName, email, password)
                .clickCheckbox()
                .signUp();
        homePage.checkHomePageOpeningTeacher()
                .isAvatarVisible()
                .clickProfessorsButton();
        professorSpotlight
                .checkHeaderProfessorSpotlight()
                .fillSearchInput(fullName)
                .scrollDownPage()
                .isSelectedNameInTheList(fullName);

        professorSpotlight.checkProfessorsCardByName(fullName)
                .clickViewProfileButton();
        teacherDetailsPage
                .isTeachersDetailsOpen(fullName, email)
                .clickEditButton();
        editRecordForm
                .isEditFormOpen()
                .changeName(fullName, newFullName)
                .setAbout(about)
                .clickSave();
        teacherDetailsPage
                .checkAboutInTeacherDetails(newFullName, about, email);
        homePage.signOut();
        signInPage.getAuth(email, password);
        homePage.clickProfessorsButton();
        professorSpotlight
                .fillSearchInput(newFullName)
                .scrollDownPage()
                .checkProfessorsCardByNameAndAbout(newFullName, about)
                .fillSearchInput(fullName)
                .checkErrorSearch();
        homePage.signOut();
    }

    @AfterMethod()
    public void tearDown() {
        new ApiBase().doDeleteRequest(EndPoint.DELETE_USER, 200, email);
    }
}

