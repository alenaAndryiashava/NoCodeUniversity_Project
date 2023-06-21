package Pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class TeacherDetailsPage {
    private final SelenideElement editButton = $x("//div[@id='modal-trigger']");
    private final SelenideElement teachersDetailsForm = $x("//div [@category='List Details']");
    private final ElementsCollection teacherDetailsField = $$("[class='css-kv7b6j']");

    @Step("Check the opening teachers details form")
    public TeacherDetailsPage isTeachersDetailsOpen(String name, String email) {
        teachersDetailsForm
                .shouldBe(Condition.visible, Duration.ofSeconds(2000))
                .shouldHave(Condition.text(name))
                .shouldHave(Condition.text(email))
                .shouldHave(Condition.text("teacher"));
        return this;

    }

    @Step("Click edit button on teacher details page")
    public void clickEditButton() {
        editButton
                .shouldBe(Condition.editable, Duration.ofSeconds(2000))
                .click();
    }

    @Step("Check teachers details")
    public TeacherDetailsPage checkAboutInTeacherDetails(String name, String about, String email) {
        teacherDetailsField.shouldHave(CollectionCondition.texts(name))
                .shouldHave(CollectionCondition.texts(about))
                .shouldHave(CollectionCondition.texts(email));
        return this;

    }
}
