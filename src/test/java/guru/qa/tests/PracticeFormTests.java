package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.TestBase;
import guru.qa.pages.RegistrationPage;
import guru.qa.pages.RegistrationResultsPage;
import org.junit.jupiter.api.Test;

import static guru.qa.testData.TestData.userEmail;
import static guru.qa.utils.RandomUtils.getRandomString;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

    Faker faker = new Faker();

    @Test
    void testFillingPracticeForm(){

        String
                firstName = getRandomString(12),
                lastName = faker.name().lastName(),
                email = userEmail,
                gender = "Male",
                phoneNumber = "8900500511",
                birthYear = "1994",
                birthMonth = "June",
                birthDay = "30",
                subject = "English",
                hobby = "Sports",
                picturePath = "src/test/resources/img/photo.jpg",
                pictureClassPath = "img/photo.jpg",
                address = "My address",
                state = "Haryana",
                city = "Karnal";


        registrationPage
                .openPage()
                .formHeaderHasText("Student Registration Form")
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhoneNumber(phoneNumber)
                .setDateBirthDate(birthYear, birthMonth, birthDay)
                .selectSubject(subject)
                .checkHobby(hobby)
                .selectPicture(picturePath)      // 1st variant
                .selectPictureByClassPath(pictureClassPath)              // 2nd variant
                .typeAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmit()
                ;

        // Assertions

        String[] splittedPicturePath = picturePath.split("/");

        registrationResultsPage
                .formHeaderHasText("Thanks for submitting the form")
                .submittedFormHasRow("Label", "Values")
                .submittedFormHasRow("Student Name", String.format("%s %s", firstName, lastName))
                .submittedFormHasRow("Student Email", email)
                .submittedFormHasRow("Gender", gender)
                .submittedFormHasRow("Mobile", phoneNumber)
                .submittedFormHasRow("Date of Birth", String.format("%s %s,%s", birthDay, birthMonth, birthYear))
                .submittedFormHasRow("Subjects", subject)
                .submittedFormHasRow("Hobbies", hobby)
                .submittedFormHasRow("Picture", splittedPicturePath[splittedPicturePath.length - 1])
                .submittedFormHasRow("Address", address)
                .submittedFormHasRow("State and City", String.format("%s %s", state, city))
                .clickCloseButton();

        registrationPage
                .formHeaderHasText("Student Registration Form");

    }
}
