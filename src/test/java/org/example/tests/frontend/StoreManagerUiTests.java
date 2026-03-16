package org.example.tests.frontend;

import org.example.backend.models.RegisterRequest;
import org.example.frontend.models.User;
import org.example.frontend.pages.LoginPage;
import org.example.frontend.pages.ProductsPage;
import org.example.tests.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.example.backend.AuthServiceRequest.getRegisterResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreManagerUiTests extends BaseTest {

    User testUser;
    WebDriver driver;

    @BeforeEach
    void setUp() {
        testUser = registerTestUser();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(APP_UI_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private User registerTestUser() {
        RegisterRequest request = RegisterRequest.generate();
        getRegisterResponse(request);
        return User.builder().email(request.getEmail()).password(request.getPassword()).build();
    }

    @Test
    void loginTest() {
        new LoginPage(driver).loginAs(testUser);

        assertTrue(new ProductsPage(driver).isPrivateAccountDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
