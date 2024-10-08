package restmethods;

import static io.restassured.RestAssured.given;

import go.Helper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

public class CurrentK extends AbstractRest {
	public CurrentK(Cookies cookies) {
		setCookies(cookies);
	}

	public String getCurrentK() {
		Response getCurrentK = given().cookies(getCookies()).post(urlBaseClient + "/ru/Account/GetCurrentK");
		return Helper.Calculate(Integer.valueOf(getCurrentK.body().asString()));
	}
}