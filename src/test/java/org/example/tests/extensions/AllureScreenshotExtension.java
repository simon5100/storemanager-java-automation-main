package org.example.tests.extensions;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.WebDriverCreator;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Optional;

@Slf4j
public class AllureScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        log.info("Start afterTestExecution");
        WebDriver driver = WebDriverCreator.getDriver();
        Optional<Throwable> executionException = context.getExecutionException();
        if (executionException.isPresent() && driver != null) {
            log.info("Start screenshoting, exception was, found");
            if (driver instanceof TakesScreenshot) {
                log.info("Taking screenshot");
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment("Screenshot on failure", "image/png", "png", screenshot);
            }
        }
    }
}
