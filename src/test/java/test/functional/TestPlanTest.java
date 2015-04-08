package test.functional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testlink.models.TestPlan;
import testlink.models.User;
import testlink.pages.HomePage;
import testlink.pages.LoginPage;
import testlink.pages.TestPlanEditPage;
import testlink.pages.TestPlanManagementPage;

/**
 * Created by admin on 08.04.2015.
 */
public class TestPlanTest {
    WebDriver driver;

    @BeforeTest
    public void login(){
        driver = new FirefoxDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(new User);

    }

    @Test
    public void positiveTest(){
        HomePage homePage = new HomePage(driver);
        TestPlanManagementPage testPlanManagementPage = homePage.openTestPlanManagement();
        TestPlanEditPage editPage = testPlanManagementPage.createTestPlan();
        TestPlan testPlan = new TestPlan(driver);
        editPage.createTestPlan(testPlan);
        Assert.assertTrue(testPlanManagementPage.isTestPlanPresent(testPlan));
        deleteTestLink(testPlan);
    }

    @AfterTest
    public void shutEnv(){
        logout();
        if (driver != null) {
            driver.quit();
        }
    }

    public void logout(){
        HomePage homePage = new HomePage();
        homePage.logout();
    }

    public void deleteTestLink(TestPlan testPlan){
        TestPlanManagementPage planManagementPage = new TestPlanManagementPage();
        planManagementPage.deleteTestPlan(testPlan);
    }
}
