package po;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage extends BasePage {

	private By loggedInEmail = By.xpath("//span[@class='status']");
	private By panelTabMenu = By.xpath("/html/body/header/div[2]/div/nav/ul/li");
	private By tabMenuMenu = By.xpath("//a[contains(text(),'Меню')]");
	private By tabMenuBooking = By.xpath("//a[contains(text(),'Запись на погрузку')]");
	private By tabMenuTO = By.xpath("/html/body/header/div[2]/div/nav/ul/li[3]/a");
	private By tabMenuAppointments = By.xpath("//a[contains(text(),'Просмотр записей')]");
	private By tabMenuDrivers = By.xpath("/html/body/header/div[2]/div/nav/ul/li[5]/a");
	private By tabMenuQueues = By.xpath("//a[contains(text(),'Очереди')]");
	private By tabMenuAdministration = By.xpath("//a[contains(text(),'Администрирование')]");
	private By tabMenuMessages = By.xpath("//a[contains(text(),'Сообщения')]");
	private By tabMenuDeliveryPoints = By.xpath("//a[contains(text(),'Пункты доставки')]");
	private By tabMenuServicesLookup = By.xpath("//a[contains(text(),'Продукция и расписание')]");
	private By tabMenuUsers = By.xpath("//a[contains(text(),'Пользователи')]");
	private By tabMenuStatistics = By.xpath("//a[contains(text(),'Статистика')]");
	private By tabMenuAbout = By.xpath("//a[contains(text(),'Инструкции')]");
	private By loader = By.id("loader");
	private By namePage = By.xpath("//h2[@id='page-heading']");
	private By btnLogOut = By.xpath("//a[contains(text(), 'Выйти')]");

	public MenuPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public AppointmentPage SelectTabMenuAppointments() {
		waitAndClick(tabMenuAppointments);
		return new AppointmentPage(driver);
	}

	public DriverPage selectTabMenuDrivers() {
		waitAndClick(tabMenuDrivers);
		return new DriverPage(driver);
	}

	public IndexPage selectTabMenuBooking() {
		waitAndClick(tabMenuBooking);
		return new IndexPage(driver);
	}

	public IntegrationDocumentPage selectTabMenuTO() {
		waitAndClick(tabMenuTO);
		return new IntegrationDocumentPage(driver);
	}

	public ServiceProvidersPage selectServiceProvidersPage() {
		waitAndClick(tabMenuAdministration);
		return new ServiceProvidersPage(driver);
	}

	public AboutPage selectAboutPage() {
		waitAndClick(tabMenuAbout);
		return new AboutPage(driver);
	}

	public StatisticPage selectStatisticPage() {
		waitAndClick(tabMenuStatistics);
		return new StatisticPage(driver);
	}

	public UsersPage selectUsersPage() {
		waitAndClick(tabMenuUsers);
		return new UsersPage(driver);
	}

	public DeliveryPointPage selectDeliveryPointPage() {
		waitAndClick(tabMenuDeliveryPoints);
		return new DeliveryPointPage(driver);
	}

	public ServicesLookupPage selectServicesLookupPage() {
		waitAndClick(tabMenuServicesLookup);
		return new ServicesLookupPage(driver);
	}

	public MessagesPage selectMessagesPage() {
		waitAndClick(tabMenuMessages);
		return new MessagesPage(driver);
	}

	public List<String> getListTabMenu() {
		waitInvisibility(loader);
		List<WebElement> elements = driver.findElements(panelTabMenu);
		List<String> nameTabs = new ArrayList<String>();
		for (WebElement element : elements) {
			nameTabs.add(element.findElement(By.xpath("./a")).getText());
		}

		for (String nameTab : nameTabs) {
			System.out.println(nameTab);
		}
		return nameTabs;
	}
}