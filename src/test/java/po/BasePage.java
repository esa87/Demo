package po;

import java.time.Duration;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import web.JSExecutor;

public abstract class BasePage {
	protected static final String BASE_URL = "http://localhost:8484";
	private static final Duration DEFAULT_TIMEOUT_SECONDS = Duration.ofSeconds(300);

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JSExecutor js;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS);
		this.js = new JSExecutor(driver);
		this.driver.manage().window().maximize();
	}

	protected void waitAndClick(By locator) {		
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	protected WebElement waitVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void switchToAnotherTab() {
		driver.switchTo().window(driver.getWindowHandles().stream().filter(new Predicate<String>() {
			public boolean test(String h) {
				return !h.equals(driver.getWindowHandle());
			}
		}).findFirst().get());
	}

	protected Boolean waitInvisibility(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void switchToIframe(By locator) {
		driver.switchTo().frame(driver.findElement(locator));
	}

	public void switchToBaseIframe() {
		driver.switchTo().defaultContent();
	}

	public void clearAndSend(By locator, String text) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}
}
