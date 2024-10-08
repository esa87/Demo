package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import po.MenuPage;

public class LoginPage extends BasePage {

	private By loginBox = By.id("Username");
	private By passwordBox = By.id("Password");
	private By loginBtn = By.xpath("/html/body/div[1]/section/div/div/form/div[5]/div[1]/button");

	public LoginPage(WebDriver driver) {
		super(driver);
		driver.get(BASE_URL);
		// TODO Auto-generated constructor stub
	}

	public LoginPage typeLogin(String email) {
		driver.findElement(loginBox).sendKeys(email);
		return this;
	}

	public LoginPage typePassword(String password) {
		driver.findElement(passwordBox).sendKeys(password);
		return this;
	}

	public MenuPage clickLogin() {
		waitAndClick(loginBtn);
		return new MenuPage(driver);
	}

	public MenuPage loginAs(String email, String password) {
		typeLogin(email);
		typePassword(password);
		return clickLogin();
	}
}