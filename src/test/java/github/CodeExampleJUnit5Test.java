package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CodeExampleJUnit5Test {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }


    @Test
    void jUnit5ExampleTest() {
        //1. Открыть страницу Selenide в Github
        open("/selenide/selenide");
        //2. Перейти в раздел Wiki проекта
        $("#wiki-tab").click();
        //3. Убедится, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(".wiki-rightbar")
                .shouldHave(text("SoftAssertions"));
        //4. Открыть страницу SoftAssertions, проверить что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();"));
    }
}

