package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.Feature;
import io.qameta.allure.internal.shadowed.jackson.core.exc.StreamWriteException;
import io.qameta.allure.internal.shadowed.jackson.databind.DatabindException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import jasonobject.UpdateDrivers;
import jasonobject.UpdateTrailers;
import po.LoginPage;
import po.MenuPage;
import restmethods.GetCookies;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.StringWriter;

@Feature("Тесты проверки защиты от XSS атак на странице создания ")
@ExtendWith(TestListener.class)
public class TestXSSUpdateDrivers extends AbstractTest {

	String adminLogin = "adm";
	String adminPwd = "123";

	private Boolean createDriver(UpdateDrivers updateDrivers, Cookies cookies)
			throws StreamWriteException, DatabindException, IOException {
		StringWriter writer = new StringWriter();
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.writeValue(writer, updateDrivers);
		Response response = given().cookies(cookies).contentType("application/json; charset=utf-8")
				.body(writer.toString()).post("http://localhost:8484/ru/DriversAndTrailers/UpdateDrivers");

		System.out.println(response.body().asString());

		return response.body().jsonPath().getBoolean("IsSuccessful");

	}

	private String driverId(Cookies cookies, String searchString) {

		Response response2 = given().cookies(cookies).contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("searchText", searchString).formParam("userId", "51ff621d-004e-33d7-398b-00e4f62ba925")
				.post("http://localhost/ru/DriversAndTrailers/GetDrivers");

		System.out.println(response2.body().jsonPath().getString("Id").substring(1, 37));

		return response2.body().jsonPath().getString("Id").substring(1, 37);
	}

	private void removeDriver(Cookies cookies, String driverId) {
		Response response3 = given().cookies(cookies).contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("id", driverId).post("http://localhost/ru/DriversAndTrailers/DeleteDriver");

		System.out.println(response3.body().asString());
	}

	@Test
	public void sendXSSinDriverName() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.Name = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}

		Assertions.assertFalse(result);
	}

	@Test
	public void sendXSSinDriversLicense() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.DriversLicense = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertTrue(result);
	}

	@Test
	public void sendXSSinDriversLicenseDate() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.DriversLicenseDate = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertFalse(result);
	}

	@Test
	public void sendXSSinDriversLicenseName() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.DriversLicenseName = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertTrue(result);
	}

	@Test
	public void sendXSSinDriversLicenseNumber() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.DriversLicenseNumber = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertFalse(result);
	}

	@Test
	public void sendXSSinDriverEmail() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.Email = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertTrue(result);
	}

	@Test
	public void sendXSSinDriverLogin() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.Login = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertTrue(result);
	}

	@Test
	public void sendXSSinDriverPassportNumber() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.PassportNumber = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertFalse(result);
	}

	@Test
	public void sendXSSinDriverPatronymic() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.Patronymic = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertFalse(result);
	}

	@Test
	public void sendXSSinDriverPhoneNumber() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.PhoneNumber = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Surname));
		}
		Assertions.assertFalse(result);
	}

	@Test
	public void sendXSSinDriverSurname() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateDrivers updateDrivers = new UpdateDrivers();
		updateDrivers.Surname = "<script>alert('112')</script>";
		Boolean result = createDriver(updateDrivers, cookies);
		if (result) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().clickSearchButton();
			removeDriver(cookies, driverId(cookies, updateDrivers.Name));
		}
		Assertions.assertFalse(result);
	}

}
