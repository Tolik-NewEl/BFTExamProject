package autotests.pages;

import autotests.elements.Table;
import autotests.elements.TextElement;
import autotests.settings.TestData;
import static com.codeborne.selenide.Selenide.$x;

public class ModalWindowTable {
    protected final Table resultTable = new Table("Таблица в модальном окне", $x("//table[@class='table table-dark table-striped table-bordered table-hover']"));
    protected final TextElement title = new TextElement("Заголовок модального окна", $x("//div[@class='modal-title h4']"));

    public boolean validateTitle(){
        return title.getElementText().equals("Thanks for submitting the form");
    }

    public boolean validateStudentName() {
        return resultTable.getValueByFieldName("Student Name")
                .equals(TestData.FIRST_NAME + " " + TestData.LAST_NAME);
    }

    public boolean validateStudentEmail() {
        return resultTable.getValueByFieldName("Student Email")
                .equals(TestData.EMAIL);
    }

    public boolean validateGender() {
        return resultTable.getValueByFieldName("Gender")
                .equals(TestData.GENDER);
    }

    public boolean validateMobile() {
        return resultTable.getValueByFieldName("Mobile")
                .equals(TestData.MOBILE);
    }

    public boolean validateDateOfBirth() {
        return resultTable.getValueByFieldName("Date of Birth")
                .equals(TestData.DATE_OF_BIRTH);
    }

    public boolean validateSubject() {
        return resultTable.getValueByFieldName("Subjects")
                .equals(TestData.SUBJECT);
    }

    public boolean validateHobby() {
        return resultTable.getValueByFieldName("Hobbies")
                .equals(TestData.HOBBY);
    }

    public boolean validatePicture() {
        return resultTable.getValueByFieldName("Picture")
                .equals(TestData.FILE_NAME);
    }

    public boolean validateAddress() {
        return resultTable.getValueByFieldName("Address")
                .equals(TestData.ADDRESS);
    }

    public boolean validateStateAndCity() {
        return resultTable.getValueByFieldName("State and City")
                .equals(TestData.STATE + " " + TestData.CITY);
    }
}
