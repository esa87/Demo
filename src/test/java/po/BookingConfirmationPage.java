package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingConfirmationPage extends BasePage {

	private By warningCheckbox = By.id("warningCheckbox");
	private By warningCheckboxForFirstDocument = By.id("warningCheckboxForFirstDocument");
	private By warningCheckboxForSecondDocument = By.id("warningCheckboxForSecondDocument");
	private By permissionNumber = By.xpath("//div[@class='proxyData']/div[3]/div[2]/input");
	private By datePermission = By.id("_easyui_textbox_input1");
	private By saveAll = By.className("saveAll");
	private By selectTransport = By
			.xpath("/html/body/div[10]/div[2]/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/div/div");
	private By messageBtn = By.xpath("/html/body/div[13]/div[2]/a/span/span");
	private By messageBtnTwo = By.xpath("/html/body/div[13]/div[2]/a/span/span");
	private By loaderScrean = By.xpath("//*[@id='loader_screen']");
	private By fieldComment = By.xpath("//input[@name='комментарий']");
	private By btnFindDriver = By.xpath("//button[contains (@onclick, 'CompleteBlockA')]");
	private By fieldSearchDriver = By.id("findDriverText");
	private By btnSearchDriver = By.xpath("//div[@id='selectDialogA']/div/div/button[1]");
	private By btnFindTransport = By.xpath("//button[contains (@onclick, 'CompleteBlockB')]");
	private By fieldSearchVehicle = By.id("findTruckText");
	private By btnSearchVehicle = By.xpath("//div[@id='selectDialogB']/div/div/button[1]");

	public BookingConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static By selectDriverName(String nameDriver) {
		return By.xpath("//div[contains(text(), '" + nameDriver + "')]");
	}

	public BookingConfirmationPage clickwarningCheckbox() {
		waitAndClick(warningCheckbox);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage clickwarningCheckboxForFirstDocument() {
		waitAndClick(warningCheckboxForFirstDocument);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage clickwarningCheckboxForSecondDocument() {
		waitAndClick(warningCheckboxForSecondDocument);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage clickbtnFindDriver() {
		waitAndClick(btnFindDriver);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage clickbtnFindTransport() {
		waitAndClick(btnFindTransport);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage permissionNumber() {
		driver.findElement(permissionNumber).sendKeys("14234123");
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage datePermission() {
		driver.findElement(datePermission).sendKeys("11.11.2023");
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage saveAll() {
		waitAndClick(saveAll);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage selectDriver(String nameDriver) {
		waitInvisibility(loaderScrean);
		waitAndClick(selectDriverName(nameDriver));
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage selectTransport() {
		waitAndClick(selectTransport);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage clickMessgeBtn() {
		waitAndClick(messageBtn);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage clickMessgeBtnTwo() {
		waitAndClick(messageBtnTwo);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage setComment(String comment) {
		driver.findElement(fieldComment).sendKeys(comment);
		return new BookingConfirmationPage(driver);
	}

	public IntegrationDocumentDetailsPage edit(String nameDriver) {
		datePermission();
		clickwarningCheckbox();
		clickwarningCheckboxForFirstDocument();
		clickwarningCheckboxForSecondDocument();
		clickbtnFindDriver();
		selectDriver(nameDriver);
		clickbtnFindTransport();
		selectTransport();
		permissionNumber();
		saveAll();
		clickMessgeBtn();
		clickMessgeBtnTwo();
		return new IntegrationDocumentDetailsPage(driver);
	}

	public IntegrationDocumentDetailsPage editNotSendEmail(String nameDriver) {
		datePermission();
		clickwarningCheckbox();
		clickwarningCheckboxForFirstDocument();
		clickwarningCheckboxForSecondDocument();
		clickbtnFindDriver();
		selectDriver(nameDriver);
		clickbtnFindTransport();
		selectTransport();
		permissionNumber();
		saveAll();
		clickMessgeBtn();
		return new IntegrationDocumentDetailsPage(driver);
	}

	public IntegrationDocumentDetailsPage editNotSendEmailAddComment(String nameDriver, String comment) {
		datePermission();
		clickwarningCheckbox();
		clickwarningCheckboxForFirstDocument();
		clickwarningCheckboxForSecondDocument();
		clickbtnFindDriver();
		selectDriver(nameDriver);
		clickbtnFindTransport();
		selectTransport();
		setComment(comment);
		permissionNumber();
		saveAll();
		clickMessgeBtn();
		return new IntegrationDocumentDetailsPage(driver);
	}

	public Boolean searchResultFindingDriver(String nameColumn, String searchText) {
		waitAndClick(btnFindDriver);
		clearAndSend(fieldSearchDriver, searchText);
		waitAndClick(btnSearchDriver);
		return driver.findElements(By.xpath("//div[contains(@class, '" + nameColumn + "')]//mark")).isEmpty();
	}

	public Boolean searchResultFindingVehicle(String nameColumn, String searchText) {
		waitAndClick(btnFindTransport);
		clearAndSend(fieldSearchVehicle, searchText);
		waitAndClick(btnSearchVehicle);
		return driver.findElements(By.xpath("//div[contains(@class, '" + nameColumn + "')]//mark")).isEmpty();
	}
}