package tests;

import static io.qameta.allure.SeverityLevel.NORMAL;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.automation.remarks.junit5.Video;

import go.GoodServiceParameters;
import go.soup.SoapClient;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import po.BookingConfirmationPage;
import po.LoginPage;
import po.MenuPage;

@Feature("Тестирование поиска водителей и транспортных средств на странице оформления записи")
@ExtendWith(TestListener.class)
public class TestSearchDriverAndVehicle extends AbstractTest {

	String adminLogin = "adm";
	String adminPwd = "123";

	String orgLogin = "org";
	String orgPwd = "123";

	void beforeTest() {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		Allure.step("1  шаг: Отправляется ТЗ, время отправки  " + current);
		new SoapClient(toParam);
		Allure.step("2  шаг: Авторизуемся на сайте под " + orgLogin);
		new LoginPage(driver).loginAs(orgLogin, orgPwd);
		Allure.step("3  шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot();
	}

	@Test
	@DisplayName("Проверяем поиск водителя по фамилии на странице оформления записи")
	@Description("")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testSearchDriverSurname() {
		beforeTest();
		Allure.step("4  шаг: Проверяем что в колонке фамилии подсветилась искомая строка");
		Boolean rez = new BookingConfirmationPage(driver).searchResultFindingDriver("Surname", "АДИ");
		Assertions.assertFalse(rez);
	}

	@Test
	@DisplayName("Проверяем поиск водителя по номеру ВУ на странице оформления записи")
	@Description("")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testSearchDriverLicense() {
		beforeTest();
		Allure.step("4  шаг: Проверяем что в колонке номер ВУ подсветилась искомая строка");
		Boolean rez = new BookingConfirmationPage(driver).searchResultFindingDriver("DriversLicenseNumber", "64013");
		Assertions.assertFalse(rez);

	}

	@Test
	@DisplayName("Проверяем поиск водителя по имени на странице оформления записи")
	@Description("")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testSearchDriverName() {
		beforeTest();
		Allure.step("4  шаг: Проверяем что в колонке имя подсветилась искомая строка");
		Boolean rez = new BookingConfirmationPage(driver).searchResultFindingDriver("Name", "абу");
		Assertions.assertFalse(rez);

	}

	@Test
	@DisplayName("Проверяем поиск транспортного средства по номеру машины")
	@Description("")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testSearchVehicleTransportNumber() {
		beforeTest();
		Allure.step("4  шаг: Проверяем что в колонке номер машины подсветилась искомая строка");
		Boolean rez = new BookingConfirmationPage(driver).searchResultFindingVehicle("TransportNumber", "А012");
		Assertions.assertFalse(rez);

	}

	@Test
	@DisplayName("Проверяем поиск транспортного средства по номеру прицепа")
	@Description("")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testSearchVehicleTrailerNumber() {
		beforeTest();
		Allure.step("4  шаг: Проверяем что в колонке номер прицепа подсветилась искомая строка");
		Boolean rez = new BookingConfirmationPage(driver).searchResultFindingVehicle("TrailerNumber", "ан682");
		Assertions.assertFalse(rez);
	}

	@Test
	@DisplayName("Проверяем поиск транспортного средства по марке прицепа")
	@Description("")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testSearchVehicleTrailerBrand() {
		beforeTest();
		Allure.step("4  шаг: Проверяем что в колонке марка прицепа подсветилась искомая строка");
		Boolean rez = new BookingConfirmationPage(driver).searchResultFindingVehicle("TrailerBrand", "ис-12");
		Assertions.assertFalse(rez);
	}

}
