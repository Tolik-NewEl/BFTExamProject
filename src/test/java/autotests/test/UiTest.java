package autotests.test;

import autotests.pages.AutomationPracticeFormPage;
import autotests.pages.ModalWindowTable;
import autotests.settings.BaseTest;
import autotests.settings.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals((resultTable.getTitleText()), "Thanks for submitting the form");
    }

    @Test
    public void whenFillFullFormThenModalWindowAppearAndCheckCorrectData(){
        ModalWindowTable resultTable = new ModalWindowTable();
        fillFormWithTestData();
        resultTable.validateData();
    }

    @Test
    public void whenIncorrectDataThenRedBorder(){
        AutomationPracticeFormPage formPage = new AutomationPracticeFormPage();
        SoftAssertions softly = new SoftAssertions();
        formPage.openPage("https://demoqa.com/automation-practice-form")
                .submitButtonPress();
        softly.assertThat(formPage.firstNameBorderColorCheck()).isTrue();
        softly.assertThat(formPage.lastNameBorderColorCheck()).isTrue();
        softly.assertThat(formPage.mobileNumberBorderColorCheck()).isTrue();
        softly.assertThat(formPage.radiobuttonBorderColorCheck()).isTrue();
        softly.assertAll();
    }
}
