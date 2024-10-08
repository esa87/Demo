package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DriverPage extends BasePage {

	private By addButton = By.id("addRowButton");
	private By deleteButton = By.id("deleteRowButton");
	private By editButton = By.id("editRowButton");
	private By surnameEdit = By.id("surname");
	private By nameEdit = By.id("name");
	private By patronymicEdit = By.id("patronymic");
	private By birthdateEdit = By.id("birthdate");
	private By passportNumberEdit = By.id("passportNumber");
	private By phoneNumberEdit = By.id("phoneNumber");
	private By driversLicenseNumberEdit = By.id("driversLicenseNumber");
	private By driversLicenseDateEdit = By.id("driversLicenseDate");
	private By addDriversLicenseNameDate = By.id("addDriversLicenseName");
	private By loginEdit = By.id("login");
	private By emailEdit = By.id("email");
	private By warningCheckboxEdit = By.id("warningCheckbox");
	private By addButtonEdit = By.xpath("//div[@class='dialog-button']/a[1]/span/span");
	private By cancelButtonEdit = By.xpath("/html/body/div[9]/div[3]/a[2]");
	private By searchButton = By.id("applyServiceProvidersFilterButton");
	private By search = By.id("pageSearchText");
	private By checkSurname = By.xpath(
			"/html/body/div[1]/div/div[6]/div[1]/div/section/div[1]/div/div/div[2]/div[2]/table/tbody/tr[1]/td[1]");
	private By loaderScrean = By.xpath("//*[@id='loader_screen']");
	private By bigPhotoMessage = By.xpath("/html/body/div[6]/div[3]/a/span/span");
	private By buttonSubmitDeleteDriver = By.xpath("//span/span[contains( text(),'Ок')]");
	private By buttonVehiclesBy = By.xpath("/html/body/div[1]/div/div[1]/ul/li[2]/a");

	public DriverPage(WebDriver driver) {
		super(driver);

		// TODO Auto-generated constructor stub
	}

	public DriverPage clickAddDriver() {
		waitInvisibility(loaderScrean);
		waitAndClick(addButton);
		waitInvisibility(loaderScrean);
		return new DriverPage(driver);
	}

	public DriverPage writeSurname(String surname) {
		driver.findElement(surnameEdit).sendKeys(surname);
		return new DriverPage(driver);
	}

	public DriverPage writeName(String name) {
		driver.findElement(nameEdit).sendKeys(name);
		return new DriverPage(driver);
	}

	public DriverPage writePatronymic(String patronymic) {
		driver.findElement(patronymicEdit).sendKeys(patronymic);
		return new DriverPage(driver);
	}

	public DriverPage writeBirthdate(String birthdate) {
		driver.findElement(birthdateEdit).sendKeys(birthdate);
		return new DriverPage(driver);
	}

	public DriverPage writePassportNumber(String passportNumber) {
		driver.findElement(passportNumberEdit).sendKeys(passportNumber);
		return new DriverPage(driver);
	}

	public DriverPage writePhoneNumber(String phoneNumber) {
		driver.findElement(phoneNumberEdit).sendKeys(phoneNumber);
		return new DriverPage(driver);
	}

	public DriverPage writeDriversLicenseNumber(String driversLicenseNumber) {
		driver.findElement(driversLicenseNumberEdit).sendKeys(driversLicenseNumber);
		return new DriverPage(driver);
	}

	public DriverPage writeDriversLicense(String driversLicense) {
		driver.findElement(driversLicenseDateEdit).sendKeys(driversLicense);
		return new DriverPage(driver);
	}

	public DriverPage writeLogin(String login) {
		driver.findElement(loginEdit).sendKeys(login);
		return new DriverPage(driver);
	}

	public DriverPage writeEmail(String email) {
		driver.findElement(emailEdit).sendKeys(email);
		return new DriverPage(driver);
	}

	public DriverPage clickWarningCheckbox() {
		waitAndClick(warningCheckboxEdit);
		return new DriverPage(driver);
	}

	public DriverPage clickAddButton() {
		waitInvisibility(loaderScrean);
		waitAndClick(addButtonEdit);
		return new DriverPage(driver);
	}

	public DriverPage cancelButton() {
		waitAndClick(cancelButtonEdit);
		return new DriverPage(driver);
	}

	public DriverPage clickSearchButton() {
		waitInvisibility(loaderScrean);
		waitAndClick(searchButton);
		waitInvisibility(loaderScrean);
		return new DriverPage(driver);
	}

	public DriverPage writeSearchString(String searchString) {
		driver.findElement(search).sendKeys(searchString);
		return new DriverPage(driver);
	}

	public String getSurname(String surname) {
		waitInvisibility(loaderScrean);
		writeSearchString(surname);
		clickSearchButton();
		waitInvisibility(loaderScrean);
		return driver.findElement(checkSurname).getText();
	}

	public String getClassSurname() {
		return driver.findElement(surnameEdit).getClass().toString();

	}

	public Boolean bigPhotoMessageEmpty() {
		return driver.findElements(bigPhotoMessage).isEmpty();
	}

	public DriverPage clickBigPhotoMessage() {
		waitAndClick(bigPhotoMessage);
		return new DriverPage(driver);
	}

	public DriverPage createDriverSmallData() {
		clickAddDriver();
		writeSurname("Сидоров");
		writeName("Сидор");
		writeBirthdate("11.11.1990");
		writePhoneNumber("+79991111111");
		clickWarningCheckbox();
		clickAddButton();
		waitInvisibility(loaderScrean);
		return new DriverPage(driver);
	}

	public String deleteDriver(String surname) {
		driver.findElement(search).clear();
		writeSearchString(surname);
		clickSearchButton();
		waitInvisibility(loaderScrean);
		driver.findElement(checkSurname).click();
		driver.findElement(deleteButton).click();
		waitInvisibility(loaderScrean);
		driver.findElement(buttonSubmitDeleteDriver).click();
		waitInvisibility(loaderScrean);
		return driver.findElement(checkSurname).getText();
	}

	public VehiclesPage goToVehiclesPage() {
		waitInvisibility(loaderScrean);
		waitAndClick(buttonVehiclesBy);
		return new VehiclesPage(driver);
	}

}
