package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import go.SystemSettings;
import io.qameta.allure.Feature;
import po.LoginPage;
import po.MenuPage;

@Feature("Тесты проверки загрузки страниц сайта")
@ExtendWith(TestListener.class)
public class TestCheckWorkPage extends AbstractTest {

	static String loginAdminKD = "adm";
	static String pwdAdminKD = "123";

	@Test
	public void checkServiceProvidersLogo() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectServiceProvidersPage().getName();
		Assertions.assertEquals("Пункт отгрузки", rez);
	}

	@Test
	public void checkStatisticLogo() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectStatisticPage().getName();
		Assertions.assertEquals("Статистика", rez);
	}

	@Test
	public void checkAboutLogo() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectAboutPage().getName();
		Assertions.assertEquals("Инструкции", rez);
	}

	@Test
	public void checkUsersLogo() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectUsersPage().getName();
		Assertions.assertEquals("Пользователи", rez);
	}

	@Test
	public void checkDeliveryPoint() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectDeliveryPointPage().getName();
		Assertions.assertEquals("Пункты доставки", rez);
	}

	@Test
	public void checkServicesLookupPage() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectServicesLookupPage().getName();
		Assertions.assertEquals("Список продукции", rez);
	}

	@Test
	public void checkMessagesPage() {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rez = new MenuPage(driver).selectMessagesPage().getName();
		Assertions.assertEquals("Просмотр всех сообщений", rez);
	}
}