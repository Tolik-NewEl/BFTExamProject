package autotests.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Condition.*;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class Input {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Вводим значение")
    public void setValue(String value){
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .setValue(value);
    }

    @Step("Кликаем на Input")
    public void click(){
        selector.click();
    }

    @Step("Вводим значение")
    public void sendKeys(String value){
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .click();
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .sendKeys(value);
    }

    @Step("Проверяем цвет рамки")
    public boolean checkRedBorderColor(){
        return selector.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"))
                .exists();
    }

    @Step("Получаем цвет рамки")
    public String getBorderColor(String expectedColor) {
        selector.shouldBe(visible)
                .shouldHave(cssValue("border-color", expectedColor), Duration.ofSeconds(2));
        return selector.getCssValue("border-color");
    }
}