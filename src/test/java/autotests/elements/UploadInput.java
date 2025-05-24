package autotests.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
public class UploadInput {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Загружаем файл")
    public void upLoad(String file) {
        selector.uploadFromClasspath(file);
    }

    @Step("Загружаем файл")
    public void uploadFile(File file) {
        selector.click();
        selector.uploadFile(file);
    }

    @Step("Кликаем на кнопку загрузки")
    public void click(){
        selector.shouldBe(Condition.visible).click();
    }
}
