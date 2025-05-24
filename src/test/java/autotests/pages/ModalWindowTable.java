package autotests.pages;

import autotests.settings.TestData;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class ModalWindowTable {
    protected final SelenideElement title = $x("//div[@class='modal-title h4']");
    protected final SelenideElement resultTable = $x("//table[@class='table table-dark table-striped table-bordered table-hover']");

    public String getTitleText(){
        return title.getText();
    }

    public void validateData() {
        resultTable.$x(".//td[text()='Student Name']/following-sibling::td")
                .shouldHave(text(TestData.FIRST_NAME + " " + TestData.LAST_NAME));
        resultTable.$x(".//td[text()='Student Email']/following-sibling::td")
                .shouldHave(text(TestData.EMAIL));
        resultTable.$x(".//td[text()='Gender']/following-sibling::td")
                .shouldHave(text(TestData.GENDER));
        resultTable.$x(".//td[text()='Mobile']/following-sibling::td")
                .shouldHave(text(TestData.MOBILE));
        resultTable.$x(".//td[text()='Date of Birth']/following-sibling::td")
                .shouldHave(text(TestData.DATE_OF_BIRTH));
        resultTable.$x(".//td[text()='Subjects']/following-sibling::td")
                .shouldHave(text(TestData.SUBJECT));
        resultTable.$x(".//td[text()='Hobbies']/following-sibling::td")
                .shouldHave(text(TestData.HOBBY));
        resultTable.$x(".//td[text()='Picture']/following-sibling::td")
                .shouldHave(text(TestData.FILE_NAME));
        resultTable.$x(".//td[text()='Address']/following-sibling::td")
                .shouldHave(text(TestData.ADDRESS));
        resultTable.$x(".//td[text()='State and City']/following-sibling::td")
                .shouldHave(text(TestData.STATE + " " + TestData.CITY));
    }
}
