package guru.qa;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.RegistrationPage;
import guru.qa.pages.RegistrationResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PracticeFormTests {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void testFillingPracticeForm(){

        registrationPage
                .openPage()
                .formHeaderHasText("Student Registration Form")
                .typeFirstName("Sergey")
                .typeLastName("Dubinin")
                .typeEmail("sergey@mail.com")
                .selectGender("Male")
                .typePhoneNumber("8900500511")
                .setDateBirthDate("1994", "June", "30")
                .selectSubject("English")
                .checkHobby("Sports")
                .selectPicture("src/test/resources/img/photo.jpg")      // 1st variant
                .selectPictureByClassPath("img/photo.jpg")              // 2nd variant
                .typeAddress("My address")
                .selectState("Haryana")
                .selectCity("Karnal")
                .clickSubmit()
                ;

        // Assertions

        registrationResultsPage
                .formHeaderHasText("Thanks for submitting the form")
                .submittedFormHasRow("Label", "Values")
                .submittedFormHasRow("Student Name", "Sergey Dubinin")
                .submittedFormHasRow("Student Email", "sergey@mail.com")
                .submittedFormHasRow("Gender", "Male")
                .submittedFormHasRow("Mobile", "8900500511")
                .submittedFormHasRow("Date of Birth", "30 June,1994")
                .submittedFormHasRow("Subjects", "English")
                .submittedFormHasRow("Hobbies", "Sports")
                .submittedFormHasRow("Picture", "photo.jpg")
                .submittedFormHasRow("Address", "My address")
                .submittedFormHasRow("State and City", "Haryana Karnal")
                .clickCloseButton();

        registrationPage
                .formHeaderHasText("Student Registration Form");

    }
}
