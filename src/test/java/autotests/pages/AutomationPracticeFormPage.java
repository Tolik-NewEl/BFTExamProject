package autotests.pages;

import autotests.elements.*;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormPage {

    protected final Input firstName = new Input("Ввод имени", $x("//input[@id='firstName']"));
    protected final Input lastName = new Input("Ввод фамилии", $x("//input[@id='lastName']"));
    protected final Input email = new Input("Ввод эл.почты", $x("//input[@id='userEmail']"));
    protected final Input mobileNumber = new Input("Ввод номера телефона", $x("//input[@id='userNumber']"));
    protected final RadioButton gender = new RadioButton("Радиобаттон Male", $x("//div[@id='genterWrapper']"));
    protected final RadioButton genderMale = new RadioButton("Радиобаттон Male", $x("//label[@for='gender-radio-1']"));
    protected final RadioButton genderFemale = new RadioButton("Радиобаттон Female", $x("//label[@for='gender-radio-2']"));
    protected final RadioButton genderOther = new RadioButton("Радиобаттон Other", $x("//label[@for='gender-radio-3']"));
    protected final Calendar calendar = new Calendar("Календарь для ввода даты рождения", $x("//input[@id='dateOfBirthInput']"));
    protected final DatePicker datePicker = new DatePicker($x("//select[@class='react-datepicker__month-select']"), $x("//select[@class='react-datepicker__year-select']"));
    protected final DropDown subjects = new DropDown("Выбор дисциплин", $x("//input[@id='subjectsInput']"));
    protected final TextArea currentAddress = new TextArea("Текстовое поле для адреса", $x("//textarea[@id='currentAddress']"));
    protected final CheckBox hobbies = new CheckBox("Группа чекбоксов Хобби", $x("//div[@id='hobbiesWrapper']"));
    protected final CheckBox sportsCheckbox = new CheckBox("Чекбокс Sports", $x("//label[@for='hobbies-checkbox-1']"));
    protected final CheckBox readingCheckbox = new CheckBox("Чекбокс Reading", $x("//label[@for='hobbies-checkbox-2']"));
    protected final CheckBox musicCheckbox = new CheckBox("Чекбокс Music", $x("//label[@for='hobbies-checkbox-3']"));
    protected final UploadInput pictureUpload = new UploadInput("Загрузка картинки", $x("//input[@id='uploadPicture']"));
    protected final DropDown stateSelect = new DropDown("Меню выбора штата", $x("//*[@id='state']"));
    protected final DropDown citySelect = new DropDown("Меню выбора города", $x("//*[@id='city']"));
    protected final Button submitButton = new Button("Кнопка подтверждения", $x("//button[@id='submit']"));
    protected final Button closeButton = new Button("Кнопка закрытия модального окна", $x("//button[@id='closeLargeModal']"));

    @Step("Открываем страницу сайта")
    public AutomationPracticeFormPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Вводим Имя")
    public AutomationPracticeFormPage firstNameInput(String value){
        firstName.sendKeys(value);
        return this;
    }

    @Step("Вводим Фамилию")
    public AutomationPracticeFormPage lastNameInput(String value){
        lastName.sendKeys(value);
        return this;
    }

    @Step("Вводим Email")
    public AutomationPracticeFormPage emailInput(String value){
        email.sendKeys(value);
        return this;
    }

    @Step("Вводим номер телефона")
    public AutomationPracticeFormPage mobileNumberInput(String value){
        mobileNumber.sendKeys(value);
        return this;
    }

    @Step("Выбираем пол")
    public AutomationPracticeFormPage genderMaleEnable(){
        genderMale.click();
        return this;
    }

    @Step("Выбираем пол Female")
    public AutomationPracticeFormPage genderFemaleEnable(){
        genderFemale.click();
        return this;
    }

    @Step("Выбираем пол Other")
    public AutomationPracticeFormPage genderOtherEnable(){
        genderOther.click();
        return this;
    }

    @Step("Выбираем пол")
    public AutomationPracticeFormPage selectGenderByValue(String value){
        gender.valueClick(value);
        return this;
    }

    @Step("Нажимаем на календарь")
    public AutomationPracticeFormPage calendarChoose(){
        calendar.calendarClick();
        return this;
    }

    @Step("Выбираем дату")
    public AutomationPracticeFormPage dateChoose(String value){
        datePicker.selectDate(value);
        return this;
    }

    @Step("Выбираем дисциплину")
    public AutomationPracticeFormPage subjectChoose(String value){
        subjects.inputOption(value);
        return this;
    }

    @Step("Выбираем чекбокс Sports")
    public AutomationPracticeFormPage checkboxSportsChoose(){
        sportsCheckbox.setTrue();
        return this;
    }

    @Step("Выбираем чекбокс Reading")
    public AutomationPracticeFormPage checkboxReadingChoose(){
        readingCheckbox.setTrue();
        return this;
    }

    @Step("Выбираем чекбокс Music")
    public AutomationPracticeFormPage checkboxMusicChoose(){
        musicCheckbox.setTrue();
        return this;
    }

    @Step("Выбираем чекбокс хобби")
    public AutomationPracticeFormPage selectHobbyByValue(String value){
        hobbies.valueClick(value);
        return this;
    }

    @Step("Вводим адрес")
    public AutomationPracticeFormPage addressInput(String value){
        currentAddress.setValue(value);
        return this;
    }

    @Step("Загружаем картинку")
    public AutomationPracticeFormPage fileUpload(String file){
        pictureUpload.upLoad(file);
        return this;
    }

    @Step("Выбираем штат")
    public AutomationPracticeFormPage stateChoose(String value){
        stateSelect.inputValue(value);
        return this;
    }

    @Step("Выбираем город")
    public AutomationPracticeFormPage cityChoose(String value){
        citySelect.inputValue(value);
        return this;
    }

    @Step("Нажимаем кнопку Подтвердить")
    public AutomationPracticeFormPage submitButtonPress(){
        submitButton.buttonClick();
        return this;
    }

    @Step("Проверяем цвет рамки инпута Имени")
    public boolean firstNameBorderColorCheck(){
        return firstName.checkRedBorderColor();
    }

    @Step("Проверяем цвет рамки инпута Фамилии")
    public boolean lastNameBorderColorCheck(){
        return lastName.checkRedBorderColor();
    }

    @Step("Проверяем цвет рамки инпута телефона")
    public boolean mobileNumberBorderColorCheck(){
        return mobileNumber.checkRedBorderColor();
    }

    @Step("Проверяем цвет радиобаттона")
    public boolean radiobuttonBorderColorCheck(){
        return genderMale.checkRedBorderColor();
    }

    @Step("Получаем цвет рамки инпута Имени")
    public String getFirstNameInputBorderColor() {
        return firstName.getBorderColor("rgb(220, 53, 69)");
    }

    @Step("Получаем цвет рамки инпута Фамилии")
    public String getLastNameInputBorderColor() {
        return lastName.getBorderColor("rgb(220, 53, 69)");
    }

    @Step("Получаем цвет рамки инпута телефона")
    public String getMobileNumberInputBorderColor() {
        return mobileNumber.getBorderColor("rgb(220, 53, 69)");
    }

    @Step("Получаем цвет радиобаттона")
    public String getGenderRadiobuttonBorderColor() {
        return genderMale.getBorderColor("rgba(220, 53, 69, 1)");
    }

    @Step("Проверяем цвета рамок при валидации")
    public void validateBorderColors() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(getFirstNameInputBorderColor()).isEqualTo("rgb(220, 53, 69)");
        softly.assertThat(getLastNameInputBorderColor()).isEqualTo("rgb(220, 53, 69)");
        softly.assertThat(getMobileNumberInputBorderColor()).isEqualTo("rgb(220, 53, 69)");
        softly.assertThat(getGenderRadiobuttonBorderColor()).isEqualTo("rgba(220, 53, 69, 1)");
        softly.assertAll();
    }

}
