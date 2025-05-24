package autotests.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static autotests.settings.Expectation.waitForElement;
import static com.codeborne.selenide.Selenide.$$;

@AllArgsConstructor
public class Table {

    @Getter
    private String name;
    private SelenideElement selector;

    public List<SelenideElement> getRows() {
        return selector.$$(By.xpath(".//tr"));
    }

    @NotNull
    private List<List<SelenideElement>> getRowsWithColumns() {
        List<SelenideElement> rows = getRows();
        List<List<SelenideElement>> rowsWithColumns = new ArrayList<List<SelenideElement>>();
        for (SelenideElement row : rows) {
            List<SelenideElement> rowWithColumns = row.$$(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }

    @Step("Проверяем отображение таблицы")
    public Table visibilityCheck() {
        selector.shouldBe(Condition.visible, Duration.ofSeconds(waitForElement));
        return this;
    }

    @Step("Собираем данные из таблицы")
    public Map<String, String> collectActualValues() {
        Map<String, String> actualValues = $$(".modal-body tbody tr").stream()
                .collect(Collectors.toMap(
                        row -> row.$$("td").get(0).getText(),
                        row -> row.$$("td").get(1).getText()
                ));
        return actualValues;
    }
}
