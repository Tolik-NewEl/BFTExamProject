package autotests.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class Calendar {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Выбираем дату календаря")
    public Calendar calendarClick() {
        selector.shouldBe(Condition.visible, Duration.ofSeconds(waitForElement))
                .click();
        return this;
    }
}
