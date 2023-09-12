package guru.qa.demo;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import com.codeborne.selenide.Configuration;

public class WebStepTest extends BaseTest{

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {REPOSITORY}")
    public void searchRepository(String REPOSITORY) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void checkIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }

    /*@Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }*/

    @Test
    void webStepTest() {
        // WebSteps steps = new WebSteps();
        openMainPage();
        searchRepository(REPOSITORY);
        clickOnRepository(REPOSITORY);
        openIssues();
        checkIssueWithNumber(ISSUE);
    }
}
