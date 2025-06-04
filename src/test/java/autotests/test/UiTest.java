package autotests.test;

import autotests.pages.AutomationPracticeFormPage;
import autotests.pages.ModalWindowTable;
import autotests.settings.BaseTest;
import autotests.settings.TestData;
import org.junit.jupiter.api.Test;

public class UiTest extends BaseTest {

    public void fillFormWithTestData(){
        AutomationPracticeFormPage formPage = new AutomationPracticeFormPage();
        formPage.openPage("https://demoqa.com/automation-practice-form");
        formPage
                .firstNameInput(TestData.FIRST_NAME)
                .lastNameInput(TestData.LAST_NAME)
                .emailInput(TestData.EMAIL)
                .selectGenderByValue(TestData.GENDER)
                .mobileNumberInput(TestData.MOBILE)
                .calendarChoose()
                .dateChoose(TestData.DATE_OF_BIRTH)
                .subjectChoose(TestData.SUBJECT)
                .selectHobbyByValue(TestData.HOBBY)
                .fileUpload(TestData.FILE_NAME)
                .addressInput(TestData.ADDRESS)
                .stateChoose(TestData.STATE)
                .cityChoose(TestData.CITY)
                .submitButtonPress();
    }

    @Test
    public void whenFillFormThenModalWindowAppear(){
        AutomationPracticeFormPage formPage = new AutomationPracticeFormPage();
        ModalWindowTable resultTable = new ModalWindowTable();
        formPage.openPage("https://demoqa.com/automation-practice-form");
        formPage
                .firstNameInput(TestData.FIRST_NAME)
                .lastNameInput(TestData.LAST_NAME)
                .selectGenderByValue(TestData.GENDER)
                .mobileNumberInput(TestData.MOBILE)
                .submitButtonPress();
        resultTable.validateTitle();
    }

    @Test
    public void whenFillFullFormThenModalWindowAppearAndCheckCorrectData(){
        ModalWindowTable resultTable = new ModalWindowTable();
        fillFormWithTestData();
        resultTable.validateAllData();
    }

    @Test
    public void whenIncorrectDataThenRedBorder(){
        AutomationPracticeFormPage formPage = new AutomationPracticeFormPage();
        formPage.openPage("https://demoqa.com/automation-practice-form")
                .submitButtonPress();
        formPage.validateBorderColors();
    }
}
