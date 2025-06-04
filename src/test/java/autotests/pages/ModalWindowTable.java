package autotests.pages;

import autotests.elements.Table;
import autotests.elements.TextElement;
import autotests.settings.TestData;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModalWindowTable {
    protected final Table resultTable = new Table("Таблица в модальном окне", $x("//table[@class='table table-dark table-striped table-bordered table-hover']"));
    protected final TextElement title = new TextElement("Заголовок модального окна", $x("//div[@class='modal-title h4']"));

    @Step("Проверяем заголовок")
    public void validateTitle(){
        assertEquals("Thanks for submitting the form", title.getElementText());
    }

    @Step("Получаем значение элемента Имя студента")
    public String getStudentName() {
        return resultTable.getValueByFieldName("Student Name");
    }

    @Step("Получаем значение элемента Email")
    public String getStudentEmail() {
        return resultTable.getValueByFieldName("Student Email");
    }

    @Step("Получаем значение элемента Пол")
    public String getGender() {
        return resultTable.getValueByFieldName("Gender");
    }

    @Step("Получаем значение элемента Номер телефона")
    public String getMobile() {
        return resultTable.getValueByFieldName("Mobile");
    }

    @Step("Получаем значение элемента Дата рождения")
    public String getDateOfBirth() {
        return resultTable.getValueByFieldName("Date of Birth");
    }

    @Step("Получаем значение элемента Специальность")
    public String getSubject() {
        return resultTable.getValueByFieldName("Subjects");
    }

    @Step("Получаем значение элемента Хобби")
    public String getHobby() {
        return resultTable.getValueByFieldName("Hobbies");
    }

    @Step("Получаем значение элемента Имя файла")
    public String getPicture() {
        return resultTable.getValueByFieldName("Picture");
    }

    @Step("Получаем значение элемента Адрес")
    public String getAddress() {
        return resultTable.getValueByFieldName("Address");
    }

    @Step("Получаем значение элемента Штат и город")
    public String getStateAndCity() {
        return resultTable.getValueByFieldName("State and City");
    }

    @Step("Сверяем введенные данные с тестовыми")
    public void validateAllData() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(getStudentName()).isEqualTo(TestData.FIRST_NAME + " " + TestData.LAST_NAME);
        softly.assertThat(getStudentEmail()).isEqualTo(TestData.EMAIL);
        softly.assertThat(getGender()).isEqualTo(TestData.GENDER);
        softly.assertThat(getMobile()).isEqualTo(TestData.MOBILE);
        softly.assertThat(getDateOfBirth()).isEqualTo(TestData.DATE_OF_BIRTH);
        softly.assertThat(getSubject()).isEqualTo(TestData.SUBJECT);
        softly.assertThat(getHobby()).isEqualTo(TestData.HOBBY);
        softly.assertThat(getPicture()).isEqualTo(TestData.FILE_NAME);
        softly.assertThat(getAddress()).isEqualTo(TestData.ADDRESS);
        softly.assertThat(getStateAndCity()).isEqualTo(TestData.STATE + " " + TestData.CITY);
        softly.assertAll();
    }
}
