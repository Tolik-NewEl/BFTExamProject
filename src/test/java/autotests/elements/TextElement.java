package autotests.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TextElement {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Получаем текст заголовка")
    public String getElementText(){
        return selector.getText();
    }
}
