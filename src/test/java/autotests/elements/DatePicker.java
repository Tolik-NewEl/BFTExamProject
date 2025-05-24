package autotests.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
@Setter
@AllArgsConstructor
public class DatePicker {
    private final SelenideElement monthSelector;
    private final SelenideElement yearSelector;

    private static final Map<String, Integer> MONTHS = new HashMap<>();
    static {
        MONTHS.put("January", 0);
        MONTHS.put("February", 1);
        MONTHS.put("March", 2);
        MONTHS.put("April", 3);
        MONTHS.put("May", 4);
        MONTHS.put("June", 5);
        MONTHS.put("July", 6);
        MONTHS.put("August", 7);
        MONTHS.put("September", 8);
        MONTHS.put("October", 9);
        MONTHS.put("November", 10);
        MONTHS.put("December", 11);
    }

    public void selectDate(String date) {
        String[] parts = date.split("\\s+|,\\s*");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        monthSelector.selectOptionByValue(String.valueOf(MONTHS.get(month)));
        yearSelector.selectOptionByValue(year);
        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(day))
                .click();
    }
}
