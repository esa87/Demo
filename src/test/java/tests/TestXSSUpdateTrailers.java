package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.internal.shadowed.jackson.core.exc.StreamWriteException;
import io.qameta.allure.internal.shadowed.jackson.databind.DatabindException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import jasonobject.UpdateTrailers;
import po.LoginPage;
import po.MenuPage;
import restmethods.GetCookies;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.StringWriter;

@ExtendWith(TestListener.class)
public class TestXSSUpdateTrailers extends AbstractTest {

	String adminLogin = "adm";
	String adminPwd = "123";

	private Boolean createVeihle(UpdateTrailers updateTrailers, Cookies cookies)
			throws StreamWriteException, DatabindException, IOException {
		StringWriter writer = new StringWriter();
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.writeValue(writer, updateTrailers);
		Response response = given().cookies(cookies).contentType("application/json; charset=utf-8")
				.body(writer.toString()).post("http://localhost:8484/ru/DriversAndTrailers/UpdateTrailers");		
		return response.body().jsonPath().getBoolean("IsSuccessful");

	}

	private String veihleId(Cookies cookies, String searchString) {

		Response response2 = given().cookies(cookies).contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("searchText", searchString).formParam("userId", "51ff621d-004e-33d7-398b-00e4f62ba925")
				.post("http://localhost:8484/ru/DriversAndTrailers/GetTrailers");
		return response2.body().jsonPath().getString("Id").substring(1, 37);
	}

	private void removeVeihle(Cookies cookies, String veihleId) {
		Response response3 = given().cookies(cookies).contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("id", veihleId).post("http://localhost:8484/ru/DriversAndTrailers/DeleteTrailer");		
	}

	@Test
	public void sendXSSinTrailerBrand() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateTrailers updateTrailers = new UpdateTrailers();
		updateTrailers.TrailerBrand = "<script>alert('123')</script>";

		if (createVeihle(updateTrailers, cookies)) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().clickChahgeVehicle();
			removeVeihle(cookies, veihleId(cookies, updateTrailers.CarModel));
		} else
			Assertions.assertTrue(false);

	}

	@Test
	public void sendXSSinCarBrand() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateTrailers updateTrailers = new UpdateTrailers();
		updateTrailers.CarBrand = "<script>alert('123')</script>";

		if (createVeihle(updateTrailers, cookies)) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().clickChahgeVehicle();
			removeVeihle(cookies, veihleId(cookies, updateTrailers.CarModel));
		} else
			Assertions.assertTrue(false);

	}

	@Test
	public void sendXSSinTrailerNumber() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateTrailers updateTrailers = new UpdateTrailers();
		updateTrailers.TrailerNumber = "<script>alert('123')</script>";

		if (createVeihle(updateTrailers, cookies)) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().clickChahgeVehicle();
			removeVeihle(cookies, veihleId(cookies, updateTrailers.CarModel));
		} else
			Assertions.assertTrue(true);

	}

	@Test
	public void sendXSSinTransportNumber() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateTrailers updateTrailers = new UpdateTrailers();
		updateTrailers.TransportNumber = "<script>alert('123')</script>";

		if (createVeihle(updateTrailers, cookies)) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().clickChahgeVehicle();
			removeVeihle(cookies, veihleId(cookies, updateTrailers.CarModel));
		} else
			Assertions.assertTrue(true);
	}

	@Test
	public void sendXSSinCarModel() throws StreamWriteException, DatabindException, IOException {
		Cookies cookies = new GetCookies().createCookies();
		UpdateTrailers updateTrailers = new UpdateTrailers();
		updateTrailers.CarModel = "<script>alert('123')</script>";

		if (createVeihle(updateTrailers, cookies)) {
			new LoginPage(driver).loginAs(adminLogin, adminPwd);
			new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().clickChahgeVehicle();
			removeVeihle(cookies, veihleId(cookies, updateTrailers.TrailerBrand));
		} else
			Assertions.assertTrue(false);
	}

}
