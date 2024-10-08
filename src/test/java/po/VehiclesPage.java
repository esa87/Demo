package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehiclesPage extends BasePage {

	private By addButtonBy = By.id("addRowButton");
	private By changeButtonBy = By.id("editRowButton");
	private By deleteButtonBy = By.id("deleteRowButton");
	private By transportNumberBy = By.id("transportNumber");
	private By trailerNumberBy = By.id("trailerNumber");
	private By carBrandBy = By.id("carBrand");
	private By carModelBy = By.id("carModel");
	private By trailerBrandBy = By.id("trailerBrand");
	private By addVehiclesButtonBy = By
			.xpath("//div[@class='dialog-button']/a/span/span[contains( text(),'Добавить')]");
	private By cancelVehiclesButtonBy = By.xpath("/html/body/div[6]/div[3]/a[2]/span/span");
	private By celsCarNumberBy = By.xpath("//tr[@id='datagrid-row-r1-2-0']/td[@field='TransportNumber']/div/div");
	private By searchFieldBy = By.id("pageSearchText");
	private By buttonSearchBy = By.id("applyServiceProvidersFilterButton");
	private By buttonSubmitDeleteVehicle = By.xpath("//span/span[contains( text(),'Ок')]");
	private By loaderScrean = By.xpath("//*[@id='loader_screen']");

	public VehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public VehiclesPage clicAddVehicle() {
		waitAndClick(addButtonBy);
		return new VehiclesPage(driver);
	}

	public VehiclesPage clickChahgeVehicle() {
		waitInvisibility(loaderScrean);
		waitAndClick(changeButtonBy);
		return new VehiclesPage(driver);
	}

	public VehiclesPage clickDeleteVehicle() {
		waitAndClick(deleteButtonBy);
		return new VehiclesPage(driver);
	}

	public VehiclesPage writeTransportNumber(String transportNumberString) {
		driver.findElement(transportNumberBy).sendKeys(transportNumberString);
		return new VehiclesPage(driver);
	}

	public VehiclesPage writeTrailerNumber(String trailerNumberString) {
		driver.findElement(trailerNumberBy).sendKeys(trailerNumberString);
		;
		return new VehiclesPage(driver);
	}

	public VehiclesPage writeCarBrand(String carBrandString) {
		driver.findElement(carBrandBy).sendKeys(carBrandString);
		return new VehiclesPage(driver);
	}

	public VehiclesPage writecarModel(String carModelString) {
		driver.findElement(carModelBy).sendKeys(carModelString);
		return new VehiclesPage(driver);
	}

	public VehiclesPage writetrailerBrand(String trailerBrandString) {
		driver.findElement(trailerBrandBy).sendKeys(trailerBrandString);
		return new VehiclesPage(driver);
	}

	public VehiclesPage clickSubmitCreateVehicle() {
		waitAndClick(addVehiclesButtonBy);
		waitInvisibility(loaderScrean);
		return new VehiclesPage(driver);
	}

	public VehiclesPage createVehicle() {
		waitInvisibility(loaderScrean);
		clicAddVehicle();
		waitInvisibility(loaderScrean);
		writeTransportNumber("12355");
		writeTrailerNumber("77777");
		writetrailerBrand("МаркаПрицепа");
		writecarModel("МодельМашины");
		writeCarBrand("МаркаМашины");
		clickSubmitCreateVehicle();
		waitInvisibility(loaderScrean);
		return new VehiclesPage(driver);
	}

	public String getCarNumberValue() {
		driver.findElement(searchFieldBy).sendKeys("12355");
		waitAndClick(buttonSearchBy);
		waitInvisibility(loaderScrean);
		return driver.findElement(celsCarNumberBy).getText();
	}

	public Boolean deleteVehicle() {
		waitAndClick(buttonSearchBy);
		driver.findElement(searchFieldBy).clear();
		driver.findElement(searchFieldBy).sendKeys("12355");
		waitAndClick(buttonSearchBy);
		waitInvisibility(loaderScrean);
		driver.findElement(celsCarNumberBy).click();
		waitAndClick(deleteButtonBy);
		waitInvisibility(loaderScrean);
		waitAndClick(buttonSubmitDeleteVehicle);
		waitInvisibility(loaderScrean);
		return driver.findElements(celsCarNumberBy).isEmpty();
	}
}