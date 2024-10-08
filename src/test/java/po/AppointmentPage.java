package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentPage extends BasePage {

	private By findNumberTO = By.id("TOR_ID");
	private By typeAppintments = By
			.xpath("/html/body/div[1]/div/div[1]/section/div[1]/div[1]/div[3]/div[1]/div/select");
	private By findAppointmentsCode = By.id("code");
	private By findBuyerOrEkspeditor = By.id("CUSNMOrEXPNM");
	private By findDepartmentPoint = By.id("serviceProviderFilter");
	private By selectDepartmentPointItem = By
			.xpath("//select[@id='serviceProviderFilter']/option[contains(text(), 'Тестовый НПЗ')]");
	private By findDateFrom = By.id("_easyui_textbox_input1");
	private By findAdditionally = By.id("additional");
	private By appointmentsStatusesFilter = By.id("appointmentsStatusesFilter");
	private By errorMessage = By.xpath("//div[contains(text(), 'error Internal Server Error')]");
	private By btnSearch = By.id("pageSearchButton");
	private By status = By.xpath("//td[@field='DocStatus']/div/div");
	private By codeAppointment = By.xpath("//td[@field='Code']/div/div");
	private By checkbox = By.id("cb_0");
	private By numberTO = By.xpath("//table/tbody/tr[1]/td[@field='TOR_ID']/div/div/a");
	private By dateAppointment = By.xpath("//td[@field='AppointmentDateTime']/div/div/a");
	private By btnCancel = By.id("cancelAppointments");
	private By btnSubmitCancel = By.xpath("/html/body/div[8]/div[3]/a[1]/span/span");
	private By btnAppointmentIsCancel = By.xpath("/html/body/div[8]/div[3]/a[1]/span/span");
	private By btnUnregisteredEntry = By.id("countOfBlockedApp");
	private By loader = By.id("loader");

	public AppointmentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public AppointmentPage sendFindNumberTO(String tor_id) {
		waitInvisibility(loader);
		driver.findElement(findNumberTO).sendKeys(tor_id);
		return new AppointmentPage(driver);
	}

	public AppointmentPage pushBtnSearch() {
		waitInvisibility(loader);
		waitAndClick(btnSearch);
		waitInvisibility(loader);
		return new AppointmentPage(driver);
	}

	public String getStatus() {
		String rez = "";
		int step = 1;
		waitInvisibility(loader);
		do {
			pushBtnSearch();
			waitInvisibility(loader);
			if (!driver.findElements(status).isEmpty())
				rez = driver.findElement(status).getText();
			step++;
		} while (driver.findElements(status).isEmpty() && step < 10);
		return rez;
	}

	public AppointmentPage selectTypeAppointment(String type) {
		waitInvisibility(loader);
		waitVisibility(typeAppintments);
		driver.findElement(typeAppintments).findElement(By.xpath("//option[@value='" + type + "']")).click();
		return new AppointmentPage(driver);
	}

	public AppointmentPage sendAppointmentCode(String code) {
		waitInvisibility(loader);
		driver.findElement(findAppointmentsCode).sendKeys(code);
		return new AppointmentPage(driver);
	}

	public AppointmentPage selectCheckbox() throws InterruptedException {
		waitInvisibility(loader);
		Thread.sleep(15000);
		waitAndClick(checkbox);
		return new AppointmentPage(driver);
	}

	public AppointmentPage pushCancelAppointment() throws InterruptedException {
		waitInvisibility(loader);
		waitAndClick(btnCancel);
		waitInvisibility(loader);
		waitAndClick(btnSubmitCancel);
		Thread.sleep(1000);
		waitInvisibility(loader);
		waitVisibility(btnAppointmentIsCancel);
		waitAndClick(btnAppointmentIsCancel);
		waitInvisibility(loader);
		Thread.sleep(5000);
		return new AppointmentPage(driver);
	}

	public boolean cancelButtonIsVisible() {
		waitInvisibility(loader);
		return driver.findElement(btnCancel).isDisplayed();
	}

	public IntegrationDocumentDetailsPage selectNumberTO() {
		waitInvisibility(loader);
		waitAndClick(numberTO);
		return new IntegrationDocumentDetailsPage(driver);
	}

	public AppointmentPage sendBuyerOrEkspeditor(String name) {
		waitInvisibility(loader);
		driver.findElement(findBuyerOrEkspeditor).sendKeys(name);
		return new AppointmentPage(driver);
	}

	public AppointmentPage selectedDepartmentPoint() {
		waitInvisibility(loader);
		waitAndClick(findDepartmentPoint);
		waitAndClick(selectDepartmentPointItem);
		return new AppointmentPage(driver);
	}

	public AppointmentPage refreshPage() {
		waitInvisibility(loader);
		driver.navigate().refresh();
		waitInvisibility(loader);
		return new AppointmentPage(driver);
	}

	public Boolean checkErrorMessage() {
		return driver.findElements(errorMessage).isEmpty();
	}

	public Boolean checkViewResultSearch() {
		waitInvisibility(loader);
		return driver.findElements(codeAppointment).isEmpty();
	}

	public AppointmentPage sendDateFrom(String name) {
		driver.findElement(findDateFrom).clear();
		driver.findElement(findDateFrom).sendKeys(name);
		waitAndClick(findAppointmentsCode);
		return new AppointmentPage(driver);
	}

	public String getDoubleCheckCodeAppointment() {
		String rez = "";
		pushBtnSearch();
		waitInvisibility(loader);
		if (driver.findElements(codeAppointment).isEmpty()) {
			pushBtnSearch();
			waitInvisibility(loader);
			rez = driver.findElement(codeAppointment).getText();
		} else
			rez = driver.findElement(codeAppointment).getText();
		return rez;
	}

	public String getUnregisteredEntry() {
		waitAndClick(btnUnregisteredEntry);
		waitInvisibility(loader);
		return driver.findElement(appointmentsStatusesFilter).getText();
	}

	public AppointmentPage sendAdditionally(String comment) {
		waitInvisibility(loader);
		driver.findElement(findAdditionally).sendKeys(comment);
		waitAndClick(btnSearch);
		return new AppointmentPage(driver);
	}

	public String getAppointmentDate() {
		waitInvisibility(loader);
		return driver.findElement(dateAppointment).getText();
	}

}