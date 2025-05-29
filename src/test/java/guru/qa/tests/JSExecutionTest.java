package guru.qa.tests;

import com.codeborne.selenide.Selenide;

import guru.qa.TestBase;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class JSExecutionTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void checkJSExecution() throws IOException {

        registrationPage
                .openPage();

        String jsScript = Files.readString(Path.of("src/test/resources/js/alert.js1"));

        Selenide.executeJavaScript(jsScript);

        Selenide.confirm();

    }
}
