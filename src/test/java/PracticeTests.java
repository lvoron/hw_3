import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.selector.ByText;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

public class PracticeTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
    }

    @Test
    void fillFormTest() {
        String userName = "Liubov";
        String lastName = userName + "Ya";
        String userEmail =  userName + "@dot.com";
        String userPhone  ="8934345161";
        String monthBirth = "July";
        String yearBirth  ="2000";
        String subject  ="Math";
        String state = "Haryana";
        String city  ="Karnal";
        String addres  = userName + "Current Address";
        String userGender  = "Female";
        String hobby  = "Music";

        open("automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(by("for","gender-radio-2")).click();
        // $(".custom-control-label").click();
        $("#userNumber").setValue(userPhone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(monthBirth);
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption(yearBirth);
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--017").click();

        $("#subjectsInput").click();
        $("#subjectsInput").setValue(subject);
        $("#subjectsInput").pressEnter();
        $("#subjectsInput").pressTab();

        $("#uploadPicture").uploadFile(new File("src/test/resources/HW_3.jpg"));
        $(by("for","hobbies-checkbox-3")).click();
        $("#currentAddress").setValue(addres);
        $("#state").click();
        $("#stateCity-wrapper").$(new ByText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(new ByText(city)).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(userName + " " + lastName),
                text(userEmail),
                text(userGender),
                text(userPhone),
                text("17 " + monthBirth + "," + yearBirth),
                text(subject),
                text(hobby),
                text("HW_3.jpg"),
                text(addres),
                text(state + " " + city)
        );
    }
}