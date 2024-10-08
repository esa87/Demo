package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v115.network.model.PrivateNetworkRequestPolicy;

public class ServiceProvidersPage extends BasePage {

	private By label = By.xpath("/html/body/header/div[3]/div/h1");
	private By loader = By.id("loader");

	public ServiceProvidersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		waitInvisibility(loader);
		return driver.findElement(label).getText();
	}
}