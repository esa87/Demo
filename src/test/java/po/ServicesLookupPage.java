package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ServicesLookupPage extends BasePage {

	private By headerName = By.xpath("/html/body/header/div[3]/div/h1");
	private By buttonScheduleTemplates = By.xpath("//a[contains(text(), 'Шаблоны расписания')]");
	private By loader = By.id("loader");

	public ServicesLookupPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		waitInvisibility(loader);
		return driver.findElement(headerName).getText();
	}

	public ScheduleTemplatesPage clickBtnScheduleTemplates() {
		waitInvisibility(loader);
		waitAndClick(buttonScheduleTemplates);
		return new ScheduleTemplatesPage(driver);
	}
}