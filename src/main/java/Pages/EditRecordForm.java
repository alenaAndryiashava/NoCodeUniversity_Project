package Pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class EditRecordForm {
    private final SelenideElement editForm = $(".modal__container");
    private final SelenideElement aboutField = $x("//div[@class='ProseMirror toastui-editor-contents']");
    private final SelenideElement nameInput = $x("//input[@name='Name']");
    private final SelenideElement saveButton = $x("//button[contains(text(),'Save')]");

    @Step("Check the opening edit form")
    public EditRecordForm isEditFormOpen() {
        editForm
                .shouldHave(Condition.text("Edit record"));

        return this;
    }

    @Step("Enter '{about}'")
    public EditRecordForm setAbout(String about) {
        aboutField.scrollTo();
        aboutField.shouldBe(Condition.visible, Duration.ofSeconds(10));
        aboutField.setValue(about);
        return this;

    }

    @Step("Change '{name}'")
    public EditRecordForm changeName(String name, String newName) {
        nameInput.shouldBe(Condition.visible).shouldHave(Condition.value(name)).clear();
        nameInput.setValue(newName);
        return this;

    }

    @Step("Click save button")
    public void clickSave() {
        saveButton.shouldBe(Condition.visible).click();

    }
}
