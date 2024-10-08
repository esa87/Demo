package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IntegrationDocumentPage extends BasePage {

	private By searchText = By.id("searchText");
	private By pageSearchButton = By.id("pageSearchButton");
	private By trTorid = By.xpath("//tbody/tr[1]/td[1]/div/a");
	private By trStatus = By.xpath("//table/tbody/tr/td[@field='Status']/div/div");
	private By trEkspeditor = By.xpath("//table/tbody/tr[1]/td[3]/div/div");
	private By loader = By.id("loader");

	public IntegrationDocumentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public IntegrationDocumentPage sendTorid(String torid) {
		waitInvisibility(loader);
		driver.findElement(searchText).clear();
		driver.findElement(searchText).sendKeys(torid);
		return new IntegrationDocumentPage(driver);
	}

	public IntegrationDocumentPage pushSearch() {
		waitInvisibility(loader);
		waitAndClick(pageSearchButton);
		return new IntegrationDocumentPage(driver);
	}

	public String getNameTorid() {
		waitInvisibility(loader);
		return waitVisibility(trTorid).getText();
	}

	public String getSatusName() {
		waitInvisibility(loader);
		String rez = "";
		if (driver.findElements(trStatus).isEmpty()) {
			pushSearch();
			waitInvisibility(loader);
		}
		rez = waitVisibility(trStatus).getText();
		return rez;
	}

	public IntegrationDocumentDetailsPage openTOdetail(String torid) {
		waitInvisibility(loader);
		int step = 0;
		while (driver.findElements(By.xpath("//tbody/tr[1]/td[1]/div/a[.='" + torid + "']")).isEmpty() && step < 10) {
			pushSearch();
			waitInvisibility(loader);
			step++;
		}
		waitAndClick(By.xpath("//tbody/tr[1]/td[1]/div/a[.='" + torid + "']"));
		return new IntegrationDocumentDetailsPage(driver);
	}

	public String getEspeditorName() {
		waitInvisibility(loader);
		return waitVisibility(trEkspeditor).getText();
	}

	public Boolean checkVisibleTableDate() {
		waitInvisibility(loader);
		return driver.findElements(trStatus).isEmpty();
	}
}