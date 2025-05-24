package autotests.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class DropDown {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Нажимаем на выпадающий список")
    public DropDown click(){
        selector.click();
        return this;
    }

    @Step("Ввод значения")
    public DropDown inputOption(String value){
        selector.shouldBe(Condition.visible).setValue(value).pressEnter();
        return this;
    }

    @Step("Ввод значения")
    public DropDown inputValue(String value){
        selector.click();
        $x(String.format("//div[contains(@class, 'menu')]//*[text()='%s']", value)).click();
        return this;
    }
}
