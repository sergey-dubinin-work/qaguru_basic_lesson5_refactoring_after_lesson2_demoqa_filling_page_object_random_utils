package guru.qa;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {


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

        open("/automation-practice-form");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // Text boxes

        $("#firstName").setValue("Sergey");
        $("#lastName").setValue("Dubinin");
        $("#userEmail").setValue("sergey@mail.com");

        // Radio button (Custom)

        $("#genterWrapper").$("input[value='Male']").parent().click();

        // Text box

        $("#userNumber").setValue("8900500511");

        // date box

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1994");
        $(".react-datepicker__month-select").selectOption("June");
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").find(exactText("30")).click();
        $("#dateOfBirthInput").sendKeys(Keys.ESCAPE);

        // Drop-down list

        $("#subjectsInput").setValue("English");
        $$(".subjects-auto-complete__option").findBy(exactText("English")).click();

        // Checkbox

        $("#hobbiesWrapper").$(byText("Sports")).click();

        // Select file

        $("#uploadPicture").scrollTo().uploadFile(new File("src/test/resources/img/photo.jpg"));
        $("#uploadPicture").scrollTo().uploadFromClasspath("img/photo.jpg"); // 2nd variant

        // Text box

        $("#currentAddress").setValue("My address");

        // Drop-down list

        $("#state").click();
        $("#state").$(byText("Haryana")).click();

        $("#city").$(byText("Select City")).click();
        $("#city").$(byText("Karnal")).click();

        // Button

        $("#submit").click();

        // Assertions

        // Modal header
        $("div[role='dialog']").$(".modal-title").shouldHave(exactText("Thanks for submitting the form"));

        // Table
        $("div.table-responsive").$(byText("Label")).sibling(0).shouldHave(exactText("Values"));
        $("div.table-responsive").$(byText("Student Name")).sibling(0).shouldHave(exactText("Sergey Dubinin"));
        $("div.table-responsive").$(byText("Student Email")).sibling(0).shouldHave(exactText("sergey@mail.com"));
        $("div.table-responsive").$(byText("Gender")).sibling(0).shouldHave(exactText("Male"));
        $("div.table-responsive").$(byText("Mobile")).sibling(0).shouldHave(exactText("8900500511"));
        $("div.table-responsive").$(byText("Date of Birth")).sibling(0).shouldHave(exactText("30 June,1994"));
        $("div.table-responsive").$(byText("Subjects")).sibling(0).shouldHave(exactText("English"));
        $("div.table-responsive").$(byText("Hobbies")).sibling(0).shouldHave(exactText("Sports"));
        $("div.table-responsive").$(byText("Picture")).sibling(0).shouldHave(exactText("photo.jpg"));
        $("div.table-responsive").$(byText("Address")).sibling(0).shouldHave(exactText("My address"));
        $("div.table-responsive").$(byText("State and City")).sibling(0).shouldHave(exactText("Haryana Karnal"));

        // Close modal

        $("#closeLargeModal").click();

        $(".practice-form-wrapper").$("h1")
                .shouldBe(visible)
                .shouldHave(exactText("Practice Form"));
    }
}
