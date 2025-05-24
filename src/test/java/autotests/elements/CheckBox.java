package autotests.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class CheckBox {

    @Getter
    private String name;
    private SelenideElement selector;

    @Step("Устанавливаем галочку")
    public CheckBox setTrue(){
        selector.shouldBe(visible, ofSeconds(waitForElement))
                .click();
        return this;
    }

    @Step("Устанавливаем галочку")
    public CheckBox setOn(){
        selector.setSelected(true);
        return this;
    }

    @Step("Отмечаем чекбокс")
    public CheckBox valueClick(String hobby){
        $x(String.format("//div[@id='hobbiesWrapper']//*[text()='%s']", hobby)).click();
        return this;
    }
}
