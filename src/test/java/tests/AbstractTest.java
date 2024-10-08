package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import go.SystemSettings;

abstract public class AbstractTest {

	static WebDriver driver_low;
	static EventFiringWebDriver driver;

	@BeforeEach
	public void setUp() throws InterruptedException {
		try {
			driver_low = new RemoteWebDriver(new URL(SystemSettings.HOST_URL), SystemSettings.chromeOption());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver = new EventFiringWebDriver(driver_low);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.register(new Custom());
		driver.get(SystemSettings.APP_URL);
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
		}
	}
}