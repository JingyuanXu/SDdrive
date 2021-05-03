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

        this.driver.get("http://localhost:" + this.port + "/home");
    }

    private void deleteNote() {
        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement notesTab = this.driver.findElement(By.id("nav-notes-tab"));

        notesTab.click();

        WebElement deleteBtn = this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/td[1]/a"));

        this.wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));

        deleteBtn.click();
    }

    private void updateNote() {
        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement notesTab = this.driver.findElement(By.id("nav-notes-tab"));

        notesTab.click();

        WebElement updateBtn = this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/td[1]/button"));

        this.wait.until(ExpectedConditions.elementToBeClickable(updateBtn));

        updateBtn.click();

        WebElement noteTitle = this.driver.findElement(By.id("note-title"));

        this.wait.until(ExpectedConditions.visibilityOf(noteTitle));

        noteTitle.sendKeys("s");

        WebElement noteDescription = this.driver.findElement(By.id("note-description"));

        noteDescription.sendKeys("sss");

        WebElement saveNote = this.driver.findElement(By.id("saveNote"));

        saveNote.click();
    }

    private void insertNewNote() throws InterruptedException {
        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement notesTab = this.driver.findElement(By.id("nav-notes-tab"));

        notesTab.click();

        this.wait.until(ExpectedConditions.elementToBeClickable(By.id("note-creation-btn")));

        WebElement noteCreationBtn = driver.findElement(By.id("note-creation-btn"));

        noteCreationBtn.click();

        WebElement noteTitle = this.driver.findElement(By.id("note-title"));

        this.wait.until(ExpectedConditions.visibilityOf(noteTitle));

        noteTitle.sendKeys("t");

        WebElement noteDescription = this.driver.findElement(By.id("note-description"));

        noteDescription.sendKeys("ttt");

        WebElement saveNote = this.driver.findElement(By.id("saveNote"));

        saveNote.click();

    }
    @Test
    @Order(1)
    public void signUpLoginLogOut() {
        /*the home page is not accessible without logging in*/
        this.driver.get("http://localhost:" + this.port + "/home");

        Assertions.assertEquals("Login", driver.getTitle());

        /* test that signs up a new user*/
        this.signupUser();

        Assertions.assertEquals("Login", driver.getTitle());

        /*logs that user in, and they can access the home page*/
        this.loginUser();

        Assertions.assertEquals("Home", driver.getTitle());

        /*logs out and the home page is no longer accessible*/
        this.logout();

        Assertions.assertEquals("Login", driver.getTitle());


    }
    @Test
    @Order(2)
    public void NotesOperation() throws InterruptedException {
        this.signupUser();

        this.loginUser();
        /*logs in an existing user, creates a note and  the note details are visible in the note list*/
        this.insertNewNote();
        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/th"));
            this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/td[2]"));
        });

        /* clicks the edit note button on an existing note, changes the note data, saves the changes*/
        this.updateNote();
        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/th"));
            this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/td[2]"));
        });

        /*clicks the delete note button on an existing note*/
        this.deleteNote();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/th"));
            this.driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/td[2]"));
        });
    }



}
