package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeServicePage extends BasePage {
	
	private By buttonService = By.xpath("//div/p[contains (text(),'без заказа')]");
	private By loader = By.id("loader");
	
	public HomeServicePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	public BookingDatePage selectService() {
		waitInvisibility(loader);
		waitAndClick(buttonService);
		return new BookingDatePage(driver);
	}
}