package org.example.tests.frontend;

import org.example.backend.models.LoginRequest;
import org.example.backend.models.RegisterRequest;
import org.example.backend.models.SupplierCreateModel;
import org.example.backend.requests.ProductServiceRequest;
import org.example.frontend.models.User;
import org.example.frontend.pages.LoginPage;
import org.example.frontend.pages.ProductsPage;
import org.example.frontend.pages.SuppliersPages;
import org.example.frontend.pages.elements.SuppliersTableRow;
import org.example.tests.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.example.backend.requests.AuthServiceRequest.getRegisterResponse;
import static org.example.backend.requests.AuthServiceRequest.postLogin;
import static org.junit.jupiter.api.Assertions.*;

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

        ProductsPage productsPage = new ProductsPage(driver);
        assertTrue(productsPage.isPrivateAccountDisplayed());

        productsPage.clickSuppliersLink();

    }

    @Test
    void createSupplierTest() {
        String accessToken = postLogin(LoginRequest.builder()
                .email(testUser.getEmail()).password(testUser.getPassword()).build()).getAccessToken();

        SupplierCreateModel createSupplier =
                ProductServiceRequest.executePostCreateSupplier(
                        SupplierCreateModel.generate(), accessToken);


        new LoginPage(driver).loginAs(testUser);
        new ProductsPage(driver).clickSuppliersLink();

        SuppliersPages suppliersPages = new SuppliersPages(driver);
        SuppliersTableRow suppliersTableRow = suppliersPages.getTableRowByName(createSupplier.getName());
        SupplierCreateModel actualSupplier = suppliersTableRow.getSupplierCreateModel();

        assertEquals(createSupplier, actualSupplier);

        suppliersTableRow.clickDeleteButton();
        driver.switchTo().alert().accept();

        assertTrue(suppliersPages.isDeletedSupplierNotificationDisplayed());

        assertFalse(suppliersPages.isDeletedSupplierExistsOnThePage(createSupplier.getName()));

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
