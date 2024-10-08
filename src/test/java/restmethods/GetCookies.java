package restmethods;

import static io.restassured.RestAssured.given;

import io.restassured.http.Cookies;

public class GetCookies extends AbstractRest {

	private String contentType = "application/x-www-form-urlencoded";

	public Cookies createCookies() {

		return given().contentType(contentType).formParam("Username", "adm").formParam("Password", "123")
				.formParam("RememberMe", "false").post(urlBaseClient).getDetailedCookies();
	}
}