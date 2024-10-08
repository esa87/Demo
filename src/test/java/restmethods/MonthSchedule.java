package restmethods;

import static io.restassured.RestAssured.given;

import java.util.Date;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonMappingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import jasonobject.ResponseGetMonthSchedule;

public class MonthSchedule extends AbstractRest {

	public MonthSchedule(Cookies cookies, String torId) {
		setCookies(cookies);
		setTor_id(torId);
	}

	public DateGrid getMonthSchedule() throws JsonMappingException, JsonProcessingException {
		Date date = new Date();
		ObjectMapper mapper = new ObjectMapper();
		Response monthSchedule = given().cookies(getCookies())
				.formParam("serviceId", "e20b92a9-1494-2f14-41ab-2f7581d0d110")
				.formParam("month", date.getMonth() + 1)
				.formParam("year", "2024").formParam("day", "1")
				.formParam("k", new CurrentK(getCookies()).getCurrentK())
				.formParam("tor_d", getTor_id())
				.post(urlBaseClient + "ru/Booking/GetMonthSchedule");
		ResponseGetMonthSchedule responseGetMonthSchedule = mapper.readValue(monthSchedule.body().asString(),
				ResponseGetMonthSchedule.class);
		setDateAppointment(responseGetMonthSchedule.getMonth().getDays().get(0).getDate().substring(6, 19));
		return new DateGrid(getCookies(), getTor_id(), getDateAppointment());
	}
}