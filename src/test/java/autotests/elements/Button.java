package autotests.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

import static autotests.settings.Expectation.waitForElement;

@AllArgsConstructor
public class Button {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Кликаем на кнопку")
    public Button buttonClick(){
        selector.shouldBe(Condition.enabled, Duration.ofSeconds(waitForElement))
                .click();
        return this;
    }
}
