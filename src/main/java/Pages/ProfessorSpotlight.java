package Pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ProfessorSpotlight {
    private final SelenideElement headerProfessorSpotlight = $x("//h1[@class='ql-align-center']//span");
    private final SelenideElement cardOfProfessors = $x("//div[@class='css-j7qwjs']");
    private final SelenideElement listOfProfessors = $x("//div[@class='horizontal-list-item']");
    private final SelenideElement searchInput = $x("(//input[@id=':r0:'])[1]");
    private final SelenideElement errorProfessorsSpotlightSearch = $("[class='inbox-list-container'] div");
    private final SelenideElement img = $x("//div[@class='css-e69bo8']//div[@class='static-image']");
    private final SelenideElement name = $("[class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-8 css-wh0kks'] h3");
    private final SelenideElement description = $("[class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-8 css-wh0kks'] p");
    private final SelenideElement viewProfileButton = $("[class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-sm-8 css-wh0kks'] [type='button']");
    private final ElementsCollection listOfCourses = $$("[class='element-container sw-text-align-left MuiBox-root css-bns4tv'] [class='css-1vykty2']");

    @Step("Home page scrolls to Professor Spotlight")
    public ProfessorSpotlight checkHeaderProfessorSpotlight() {
        headerProfessorSpotlight
                .shouldBe(Condition.visible, Duration.ofSeconds(2000))
                .shouldBe(Condition.exactText("Professor spotlight"));
        return this;
    }

    @Step("Click and enter selected course on the <Search by course name or professor> field")
    public ProfessorSpotlight fillSearchInput(String name) {
        searchInput.shouldBe(Condition.visible, Duration.ofSeconds(2000)).click();
        searchInput.setValue(name);
        return this;
    }

    @Step("Check the selected name, found in the list of teachers")
    public ProfessorSpotlight checkSelectedNameInTheList(String name) {
        $x("//div[@class='horizontal-list-item'] //h3[contains(text(),'" + name + "')]")
                .shouldHave(Condition.text(name));
        return this;
    }

    @Step("Check professor's card by professor's name")
    public ProfessorSpotlight checkProfessorsCardByName() {
        img.shouldBe(Condition.visible);
        String expectedNameOfProfessor = cardOfProfessors.$x(".//h3").getText();
        name.shouldHave(Condition.exactText(expectedNameOfProfessor));
        String expectedDescription = cardOfProfessors.$x(".//p").getText();
        description.shouldHave(Condition.text(expectedDescription));
        String expectedListOfCourse = cardOfProfessors.$x(".//div[@class='css-1vykty2']").getText();
        listOfCourses.shouldHave(CollectionCondition.texts(expectedListOfCourse));
        viewProfileButton.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check created user is visible")
    public ProfessorSpotlight checkCreatedUser() {
        String expectedNameOfProfessor = cardOfProfessors.$x(".//h3").getText();
        name.shouldHave(Condition.exactText(expectedNameOfProfessor));
        return this;
    }

    @Step("Check professor's card by course")
    public ProfessorSpotlight checkProfessorsCardByCourse(String nameOfCourse) {
        listOfCourses.shouldHave(CollectionCondition.texts(nameOfCourse));
        viewProfileButton.shouldBe(Condition.visible);
        return new ProfessorSpotlight();
    }

    @Step("Check error in search")
    public ProfessorSpotlight checkErrorSearch() {
        errorProfessorsSpotlightSearch.shouldHave(Condition.
                text("No results found, try adjusting your search and filters."));
        return new ProfessorSpotlight();
    }

    @Step("Check error is not visible in search")
    public ProfessorSpotlight checkNoErrorSearch() {
        errorProfessorsSpotlightSearch.shouldNotHave(Condition.
                text("No results found, try adjusting your search and filters."));
        return new ProfessorSpotlight();
    }

    public ProfessorSpotlight scrollDownPage() {
        headerProfessorSpotlight.scrollTo();
        return this;
    }

    public ProfessorSpotlight onlyTeacherAvailable() {
        listOfProfessors.shouldHave(Condition.text("teacher"));
        listOfProfessors.shouldNotHave(Condition.text("student"));
        return this;
    }
}


    /*@Step("Open professor's card")
    public ProfessorSpotlight openCardPage(String name) {
        SelenideElement professor = $x("//div[@class='horizontal-list-item'] //h3[contains(text(),'" + name + "')]");
        professor.shouldBe(Condition.visible, Duration.ofSeconds(2000)).click();
        return this;
    }
     */
