package autotests.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class TextArea {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Кликаем на TextArea")
    public void click(){
        selector.click();
    }

    @Step("Вводим значение")
    public void setValue(String value){
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .setValue(value);
    }

    @Step("Вводим значение")
    public void sendKeys(String value){
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .click();
        sleep(1000);
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .sendKeys(value);
    }
}
