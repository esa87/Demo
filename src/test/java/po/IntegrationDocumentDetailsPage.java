package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IntegrationDocumentDetailsPage extends BasePage {

	private By onBookingButton = By.xpath("//span[contains(text(), 'ОФОРМИТЬ ЗАПИСЬ')]");
	private By dateSended = By.xpath("//input[@id='_easyui_textbox_input1']");
	private By statusTO = By.id("status");
	private By loader = By.id("loader");
	private By btnCalendar = By.className("calendar-selected");
	private By btnSendMessage = By.xpath("/html/body/div[1]/div/div[1]/div[2]/section/div[2]/button");
	private By buttonSendNotificationBy = By.xpath("//a[contains(text(), 'ОТПРАВИТЬ УВЕДОМЛЕНИЕ')]");
	private By fieldCopyEmail = By.id("AdditionalEmails");
	private By selectionBlock = By.xpath("/html/body/div[1]/div/div[1]/div[1]/section");
	private By buttonSend = By.xpath("//a/span/span[contains(text(), 'Отправить')]");
	private By messageAboutLastSendNotification = By.xpath("//span[contains(text(), 'тправлено')]");
	private By buttonSendOk = By.xpath("//a/span/span[contains(text(), 'Ок')]");
	private By linkDiliveryPoint = By.xpath("//a[contains(text(), 'Тестовый ПО Москва')]");
	private By coordinatValue = By.xpath("/html/body/div[1]/div/div[1]/section/div[4]/div[2]/label");
	private By chatArea = By.id("chat");
	private By checkReadedMessage = By.xpath("//input[@checked='checked']");
	private By areaTextTemplate = By.id("Text");

	public IntegrationDocumentDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public BookingDatePage clickOnBookingButton() {
		waitAndClick(onBookingButton);
		return new BookingDatePage(driver);

	}

	public IntegrationDocumentDetailsPage sendDateSended(String date) {
		waitInvisibility(loader);
		driver.findElement(dateSended).sendKeys(date);
		waitAndClick(btnCalendar);
		return new IntegrationDocumentDetailsPage(driver);
	}

	public String checkStatusTO() {
		waitAndClick(statusTO);
		driver.navigate().refresh();
		waitInvisibility(loader);
		return driver.findElement(statusTO).getText();
	}

	public Integer getCountDeliveryPoint() {
		waitInvisibility(loader);
		return driver.findElements(selectionBlock).size();
	}

	public Boolean buttonSendNotificationIsVisible() {
		waitInvisibility(loader);
		return driver.findElements(buttonSendNotificationBy).isEmpty();
	}

	public Boolean clickNotification(String copiEmail) throws InterruptedException {
		waitInvisibility(loader);
		waitAndClick(buttonSendNotificationBy);
		waitInvisibility(loader);
		driver.findElement(fieldCopyEmail).sendKeys(";" + copiEmail);
		waitAndClick(buttonSend);
		waitAndClick(buttonSendOk);
		return driver.findElements(messageAboutLastSendNotification).isEmpty();
	}

	public String getCordinat() {
		waitAndClick(linkDiliveryPoint);
		waitInvisibility(loader);
		return driver.findElement(coordinatValue).getText();
	}

	public MenuPage sendMessage() {
		waitInvisibility(loader);
		driver.findElement(chatArea).sendKeys("hello world");
		waitAndClick(btnSendMessage);
		waitInvisibility(loader);
		waitAndClick(checkReadedMessage);
		return new MenuPage(driver);
	}

	public String getTextTemplateNotification() {
		waitInvisibility(loader);
		waitAndClick(buttonSendNotificationBy);
		return driver.findElement(areaTextTemplate).getDomProperty("value");
	}

	public Boolean chekIsNotification() throws InterruptedException {
		waitInvisibility(loader);
		return driver.findElements(messageAboutLastSendNotification).isEmpty();
	}
}