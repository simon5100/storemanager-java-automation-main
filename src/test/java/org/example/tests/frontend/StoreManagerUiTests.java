package org.example.tests.frontend;

import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.models.LoginRequest;
import org.example.backend.models.SupplierCreateModel;
import org.example.backend.requests.ProductServiceRequest;
import org.example.frontend.models.User;
import org.example.frontend.pages.LoginPage;
import org.example.frontend.pages.ProductsPage;
import org.example.frontend.pages.SuppliersPages;
import org.example.frontend.pages.elements.SuppliersTableRow;
import org.example.tests.BaseTest;
import org.example.tests.extensions.AllureScreenshotExtension;
import org.example.utils.WebDriverCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.example.backend.requests.AuthServiceRequest.executePostLogin;
import static org.example.backend.requests.ProductServiceRequest.executePostCreateSupplier;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Feature("UI tests")
@ExtendWith(AllureScreenshotExtension.class)
public class StoreManagerUiTests extends BaseTest {

    User testUser;
    WebDriver driver;

    @BeforeEach
    void setUp() {
        testUser = registerTestUser();
        driver = WebDriverCreator.getDriver();
        driver.get(APP_UI_URL);
    }

    @Test
    @DisplayName("Login")
    void loginTest() {
        log.info("Start login test");
        new LoginPage(driver).loginAs(testUser);

        ProductsPage productsPage = new ProductsPage(driver);
        assertTrue(productsPage.isPrivateAccountDisplayed());
    }

    @Test
    @DisplayName("Delete supplier")
    void deleteSupplierTest() {
        log.info("Start delete supplier test");
        LoginRequest loginRequest  = LoginRequest.builder()
                .email(testUser.getEmail()).password(testUser.getPassword()).build();
        String accessToken = executePostLogin(loginRequest).getAccessToken();
        SupplierCreateModel createSupplier = executePostCreateSupplier(
                        SupplierCreateModel.generate(), accessToken);


        new LoginPage(driver).loginAs(testUser);
        new ProductsPage(driver).clickSuppliersLink();

        SuppliersPages suppliersPages = new SuppliersPages(driver);
        SupplierCreateModel actualSupplier = suppliersPages
                .getTableRowByName(createSupplier.getName()).getSupplierCreateModel();
        assertEquals(createSupplier, actualSupplier);

        suppliersPages.getTableRowByName(createSupplier.getName()).clickDeleteButton();
        driver.switchTo().alert().accept();
        assertTrue(suppliersPages.isDeletedSupplierNotificationDisplayed());
        assertTrue(suppliersPages.isSupplierExistsOnThePage(createSupplier.getName())); //fals
    }

    @AfterEach
    void tearDown() {
        log.info("Test ending, quit driver");
        WebDriverCreator.closeDriver();
    }
}
