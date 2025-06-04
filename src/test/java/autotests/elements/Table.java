package autotests.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Table {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Получаем текст селектора")
    public String getSelectorText(SelenideElement selector){
        return selector.getText();
    }

    @Step("Получаем данные текста в ячейке для поля '{fieldName}'")
    public String getValueByFieldName(String fieldName) {
        String xpath = String.format(".//td[normalize-space()='%s']/following-sibling::td[1]", fieldName);
        return selector.$x(xpath).shouldBe(Condition.visible).getText();
    }
}
