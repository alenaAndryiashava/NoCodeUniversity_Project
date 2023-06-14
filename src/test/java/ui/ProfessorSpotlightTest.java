package ui;

import org.testng.annotations.Test;

public class ProfessorSpotlightTest extends BaseTest {

    @Test(testName = "Test-case 3.2 & 3.5: Verify the display of the list of teachers, search by professor's name",
            dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void searchByProfessorsNameTest(String email, String password) {
        String nameOfTeacher = "Roxanne";
        this.correctAuthTest(email, password);
        homePage.clickProfessorsButton();
        professorSpotlight
                .checkHeaderProfessorSpotlight()
                .fillSearchInput(nameOfTeacher)
                .scrollDownPage()
                .checkProfessorsCardByName(nameOfTeacher);
        homePage.signOut();
    }

    //TODO Bug report: Defect_ID 2.1 & 2.3
    @Test(testName = "Test-case 3.3 & 3.6: Verify the display of the list of teachers, search by student's name",
            dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void searchByStudentsNameTest(String email, String password) {
        String nameOfStudent = "Malik";
        this.correctAuthTest(email, password);
        homePage.clickProfessorsButton();
        professorSpotlight
                .checkHeaderProfessorSpotlight()
                .fillSearchInput(nameOfStudent)
                .scrollDownPage()
                .checkErrorSearch();
        homePage.signOut();
    }

    //TODO Bug report: Defect_ID 2.2 & 2.4
    @Test(testName = "Test-case 3.4 & 3.7: Verify the display of the list of teachers, search by course name",
            dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void searchByCourseNameTest(String email, String password) {
        String nameOfCourse = "Economics";
        this.correctAuthTest(email, password);
        homePage.clickProfessorsButton();
        professorSpotlight.checkHeaderProfessorSpotlight();
        professorSpotlight
                .checkHeaderProfessorSpotlight()
                .fillSearchInput(nameOfCourse)
                .checkNoErrorSearch()
                .checkProfessorsCardByCourse(nameOfCourse);
        homePage.signOut();
    }

    @Test(testName = "Test-case 3.1: Verify the display of the list of teachers by non registered user")
    public void availabilityProfessorSpotlightNonRegisteredUserTest() {
        homePage.clickProfessorsButton();
        professorSpotlight
                .checkHeaderProfessorSpotlight()
                .onlyTeacherAvailable();
    }
}
