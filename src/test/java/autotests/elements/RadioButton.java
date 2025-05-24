package autotests.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class RadioButton {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Выбираем радиобаттон")
    public RadioButton setTrue(){
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .click();
        return this;
    }

    @Step("Выбираем радиобаттон")
    public RadioButton setOn(){
        selector.setSelected(true);
        return this;
    }

    @Step("Выбираем радиобаттон")
    public RadioButton click(){
        selector.hover().click();
        return this;
    }

    @Step("Проверяем цвет рамки")
    public boolean checkRedBorderColor(){
        return selector.shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"))
                .exists();
    }

    @Step("Выбираем пол")
    public RadioButton valueClick(String gender){
        $x(String.format("//div[@id='genterWrapper']//*[text()='%s']", gender)).click();;
        return this;
    }
}
