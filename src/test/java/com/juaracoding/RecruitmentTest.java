package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.RecruitmentPage;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RecruitmentTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;

    @BeforeClass
    public void setUp() {
        DriverSingleton.getInstance("firefox");
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage();
        recruitmentPage = new RecruitmentPage();
    }

    @AfterClass
    public void finish() {
        Utils.delay(5);
        DriverSingleton.closeObjectInstance();
    }

    @Test
    public void testAddCandidate() {
        // step pre-condition:
        loginPage.loginUser("Admin", "admin123");

        // step action:
        recruitmentPage.setMenuRecruitment();
        recruitmentPage.addCandidate();
        // step validation:
        Assert.assertEquals(recruitmentPage.getTxtApplicationStage(), "Application Stage");
        Assert.assertEquals(recruitmentPage.getTxtFirstValidation(), "Juara Coding");
        Assert.assertTrue(driver.getCurrentUrl().contains("/recruitment/addCandidate"));
    }
}
