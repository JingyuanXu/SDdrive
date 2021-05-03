package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialTest {

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


    private void insertNewCredential() {

        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement credentialsTab = this.driver.findElement(By.id("nav-credentials-tab"));

        credentialsTab.click();

        WebElement addCredentialBtn = driver.findElement(By.id("addCredentialBtn"));

        this.wait.until(ExpectedConditions.elementToBeClickable(addCredentialBtn));

        addCredentialBtn.click();

        WebElement urlInput = this.driver.findElement(By.id("credential-url"));

        this.wait.until(ExpectedConditions.visibilityOf(urlInput));

        urlInput.sendKeys("http://ttt.com");

        WebElement usernameInput = this.driver.findElement(By.id("credential-username"));

        usernameInput.sendKeys("test");

        WebElement passwordInput = this.driver.findElement(By.id("credential-password"));

        passwordInput.sendKeys("test");

        WebElement saveCredential = this.driver.findElement(By.id("saveCredential"));

        saveCredential.click();

    }

    public void updateCredential() {
        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement credentialsTab = this.driver.findElement(By.id("nav-credentials-tab"));

        credentialsTab.click();

        WebElement editBtn = this.driver.findElement(
                By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[1]/button"));

        this.wait.until(ExpectedConditions.elementToBeClickable(editBtn));

        editBtn.click();

        WebElement urlInput = this.driver.findElement(By.id("credential-url"));

        this.wait.until(ExpectedConditions.visibilityOf(urlInput));

        urlInput.clear();
        urlInput.sendKeys("http://sss.com");

        WebElement usernameInput = this.driver.findElement(By.id("credential-username"));

        usernameInput.clear();
        usernameInput.sendKeys("test");

        WebElement saveCredential = this.driver.findElement(By.id("saveCredential"));

        saveCredential.click();

    }

    private void deleteCredential() {
        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement credentialsTab = this.driver.findElement(By.id("nav-credentials-tab"));

        credentialsTab.click();

        WebElement deleteBtn = this.driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[1]/a"));

        this.wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));

        deleteBtn.click();
    }


    @Test
    @Order(1)
    public void CredentialOperation() throws InterruptedException {
        this.signupUser();

        this.loginUser();

        this.insertNewCredential();
        //logs in an existing user, creates a credential and verifies that the credential details are visible in the credential list
        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//th[text()='http://ttt.com']"));
        });

        //changes the credential data, saves the changes, and verifies that the changes appear in the credential list
        this.updateCredential();
        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//th[text()='http://sss.com']"));
        });

        // clicks the delete credential button on an existing credential, and verifies that the credential no longer appears in the credential list.
        this.deleteCredential();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.xpath("//th[text()='http://sss.com'']"));
        });
    }



}
