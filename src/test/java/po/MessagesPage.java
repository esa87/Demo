package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage extends BasePage {

	private By headerName = By.xpath("/html/body/header/div[3]/div/h1");
	private By buttonSearch = By.id("findButton");
	private By typeMessage = By.xpath("/html/body/div[1]/div/div[1]/section/div[1]/div[1]/div[3]/select");
	private By typeMessageRead = By.xpath("/html/body/div[1]/div/div[1]/section/div[1]/div[1]/div[3]/select/option[2]");
	private By loader = By.id("loader");
	private By dataContentCheckAllWork = By.xpath("//a[contains(text(), 'ТЗ №')]");
	private By buttonReadAll = By.id("setAllChatsReadButton");

	public MessagesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		waitInvisibility(loader);
		return driver.findElement(headerName).getText();
	}

	public Boolean checkWorkSearchFromReadMessages() {
		waitInvisibility(loader);
		waitAndClick(typeMessage);
		waitAndClick(typeMessageRead);
		waitAndClick(buttonSearch);
		waitInvisibility(loader);
		return driver.findElements(dataContentCheckAllWork).isEmpty();
	}

	public String checkMessage(String tor_id) {
		return driver.findElement(By.xpath("//a[contains(text(),'" + tor_id + "')]")).getText();
	}

	public Boolean readAll(String tor_id) {
		waitAndClick(buttonReadAll);
		waitInvisibility(loader);
		return driver.findElements(By.xpath("//a[contains(text(),'" + tor_id + "')]")).isEmpty();
	}
}