package tests;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import io.qameta.allure.Allure;

public class TestListener implements TestWatcher {
	@Override
	public void testFailed(ExtensionContext context,Throwable cause) {
		Allure.getLifecycle().addAttachment("Скриншот на месте падения", "image/png", "png", ((TakesScreenshot) AbstractTest.driver).getScreenshotAs(OutputType.BYTES));		
		Allure.addAttachment("Логи после падения теста", String.valueOf(AbstractTest.driver.manage().logs().get(LogType.BROWSER).getAll()));
		AbstractTest.driver.quit();
	}	
	
	@Override
	public void testSuccessful(ExtensionContext context) {
		Allure.getLifecycle().addAttachment("Скриншот экрана после завершения теста", "image/png", "png", ((TakesScreenshot) AbstractTest.driver).getScreenshotAs(OutputType.BYTES));		
		AbstractTest.driver.quit();
	}
	
	
	public void testAborted​(ExtensionContext context, Throwable cause) {
		Allure.addAttachment("Скриншот на месте пропущенного тест", new ByteArrayInputStream(((TakesScreenshot)AbstractTest.driver).getScreenshotAs(OutputType.BYTES)));
		System.out.printf("Test Successful for test {}: ", context.getDisplayName());
		Allure.addAttachment("Логи после падения теста", String.valueOf(AbstractTest.driver.manage().logs().get(LogType.BROWSER).getAll()));
		AbstractTest.driver.quit();		
	}	
	

	public void testDisabled​(ExtensionContext context, Optional reason) {
		Allure.addAttachment("Скриншот на месте отключенного теста", new ByteArrayInputStream(((TakesScreenshot)AbstractTest.driver).getScreenshotAs(OutputType.BYTES)));
		System.out.printf("Test Disabled for test {}: with reason :- {}", 
			      context.getDisplayName(),
			      reason.orElse("No reason"));
		AbstractTest.driver.quit();
	}
}
