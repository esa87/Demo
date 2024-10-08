package restmethods;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

public class FindAppointments extends AbstractRest {
	public FindAppointments(Cookies cookies, String tor_id) {
		setCookies(cookies);
		setTor_id(tor_id);
	}

	public String getCode() {
		Response findAppointments = given().cookies(getCookies()).formParam("dateFromString", "03.09.2024")
				.formParam("TOR_ID", getTor_id()).post(urlBaseClient + "/ru/Appointments/FindAppointments");
		return findAppointments.body().jsonPath().get("Items.Code").toString();
	}
}