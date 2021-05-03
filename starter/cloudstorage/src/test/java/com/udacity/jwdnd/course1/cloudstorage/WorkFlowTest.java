package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkFlowTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {

        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait (driver, 1000);

    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
    private void loginUser() {

        driver.get("http://localhost:" + this.port + "/login");

        WebElement loginUsername = driver.findElement(By.id("loginUsername"));

        loginUsername.sendKeys("sss");

        WebElement loginPassword = driver.findElement(By.id("loginPassword"));

        loginPassword.sendKeys("sss");

        WebElement loginBtn = driver.findElement(By.id("loginBtn"));

        Assertions.assertEquals("Login", loginBtn.getText());

        loginBtn.click();
    }

    private void signupUser() {

        this.driver.get("http://localhost:" + this.port + "/signup");

        WebElement inputFirstName = this.driver.findElement(By.id("inputFirstName"));

        inputFirstName.sendKeys("sss");

        WebElement inputLastName = this.driver.findElement(By.id("inputLastName"));

        inputLastName.sendKeys("sss");

        WebElement inputUsername = driver.findElement(By.id("inputUsername"));

        inputUsername.sendKeys("sss");

        WebElement inputPassword = driver.findElement(By.id("inputPassword"));

        inputPassword.sendKeys("sss");

        WebElement signupBtn = driver.findElement(By.id("signupBtn"));

        signupBtn.click();
    }

    private void logout() {
        this.driver.get("http://localhost:" + this.port + "/home");
        WebElement logoutButton = driver.findElement(By.id("logoutBtn"));
        logoutButton.click();
    }

    @Test
    @Order(1)
    public void signUpLoginLogOut() {
        this.signupUser();
        Assertions.assertEquals("Login", driver.getTitle());
        this.loginUser();
        Assertions.assertEquals("Home", driver.getTitle());
        this.logout();
        Assertions.assertEquals("Login", driver.getTitle());

    }


}
