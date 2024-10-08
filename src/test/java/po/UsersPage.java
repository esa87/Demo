package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsersPage extends BasePage {

	private By headerName = By.xpath("/html/body/header/div[3]/div/h1");
	private By loader = By.id("loader");

	public UsersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		waitInvisibility(loader);
		return driver.findElement(headerName).getText();
	}
}