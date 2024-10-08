package po;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScheduleTemplatesPage extends BasePage {

	private By serviceProviderFilter = By.id("serviceProvidersFilter");
	private By serviceProviderOption = By
			.xpath("//select[@id='serviceProvidersFilter']/option[.='2420 - Тестовый НПЗ']");
	private By buttonSearch = By.id("applyServiceProvidersFilterButton");
	private By comentSchedule = By.xpath("//div[.='НЕ ТРОГАТЬ!']");
	private By serviceOption = By.xpath("//div[.='тест']");
	private By buttonDelete = By.id("deleteTemplateButton");
	private By buttonOk = By.xpath("//a/span/span[.='Ок']");
	private By buttonAdd = By.id("addTemplateButton");
	private By serviceOptionClick = By.xpath("//div[.='АВТО']");
	private By totalRecords = By.id("totalCount");
	private By fieldNumberWindow = By.xpath(
			"//tr[@class='datagrid-row   datagrid-row-selected datagrid-row-editing']/td[@field='WindowNumber']/div/table/tbody/tr/td/span/input[contains(@class, 'textbox-text validatebox-text')]");
	private By buttonSave = By.id("saveTemplatesChangesButton");
	private By loader = By.id("loader");

	public ScheduleTemplatesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public Boolean deleteService() throws InterruptedException {
		Integer before;
		Integer after;
		waitInvisibility(loader);
		waitAndClick(serviceProviderFilter);
		waitAndClick(serviceProviderOption);
		Thread.sleep(1000);
		waitAndClick(buttonSearch);
		waitInvisibility(loader);
		waitAndClick(comentSchedule);
		before = Integer.valueOf(driver.findElement(totalRecords).getText().substring(7));
		waitAndClick(serviceOption);
		waitAndClick(buttonDelete);
		waitAndClick(buttonOk);
		after = Integer.valueOf(driver.findElement(totalRecords).getText().substring(7));
		return before > after;
	}

	public Boolean addService() throws InterruptedException {
		Integer before;
		Integer after;
		Random rand = null;
		Integer randomNum = (int) (Math.random() * ((99 - 3) + 1));
		waitInvisibility(loader);
		waitAndClick(serviceProviderFilter);
		waitAndClick(serviceProviderOption);
		Thread.sleep(1000);
		waitAndClick(buttonSearch);
		waitInvisibility(loader);
		waitAndClick(comentSchedule);
		before = Integer.valueOf(driver.findElement(totalRecords).getText().substring(7));
		waitAndClick(serviceOptionClick);
		waitInvisibility(loader);
		waitAndClick(buttonAdd);
		waitInvisibility(loader);
		driver.findElement(fieldNumberWindow).clear();
		driver.findElement(fieldNumberWindow).sendKeys(randomNum.toString());
		waitAndClick(buttonSave);
		waitAndClick(buttonOk);
		after = Integer.valueOf(driver.findElement(totalRecords).getText().substring(7));
		return before < after;
	}
}
