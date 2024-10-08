package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends BasePage {

	private By tabProductWithOutTO = By.xpath("//a[.='Продукты без заказа']");
	private By loader = By.id("loader");

	public IndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String TDLNR_NAMEListXPath() {
		return "/html/body/div[1]/div/div[2]/section/div[1]/div[2]/div[1]/select";
	}

	public String pageSearchButtonId() {
		return "pageSearchButton";
	}

	public HomeServicePage selectTabHomeServicePage() {
		waitInvisibility(loader);
		waitAndClick(tabProductWithOutTO);
		return new HomeServicePage(driver);
	}
}