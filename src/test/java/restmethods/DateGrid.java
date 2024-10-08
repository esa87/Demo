package restmethods;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import go.Helper;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import jasonobject.BookingDate;
import jasonobject.BookingDateAppointmentItems;

public class DateGrid extends AbstractRest {

	public DateGrid(Cookies cookies, String torId, String dateAppointments) {
		// TODO Auto-generated constructor stub
		setCookies(cookies);
		setTor_id(torId);
		setDateAppointment(dateAppointments);
	}

	public DateGrid getDateGrid() {

		Response dateGrid = given().cookies(getCookies()).formParam("serviceId", "e20b92a9-1494-2f14-41ab-2f7581d0d110")
				.formParam("date", Helper.convertMomentutc(getDateAppointment(), 1))
				.formParam("k", new CurrentK(getCookies()).getCurrentK()).formParam("tor_id", getTor_id())
				.post(urlBaseClient + "ru/Booking/DateGrid");
		Document documentConfirmation = Jsoup.parse(dateGrid.getBody().asString());
		setTimeAppointment(
				documentConfirmation.body().selectXpath("//li[contains(@class,'available')]").attr("data-time"));
		setTimeAppointmentWindow(
				documentConfirmation.body().selectXpath("//li[contains(@class,'available')]").attr("data-window"));

		return this;
	}

	public BookingConfirmation getDateResponse() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		BookingDate bookingDate = new BookingDate();
		ArrayList<BookingDateAppointmentItems> bookingDateAppointmentItems = new ArrayList<BookingDateAppointmentItems>();
		BookingDateAppointmentItems bookingDateAppointmentItem = new BookingDateAppointmentItems();
		bookingDateAppointmentItem.Time = getTimeAppointment();
		bookingDateAppointmentItem.WindowNumber = Integer.valueOf(getTimeAppointmentWindow());
		bookingDateAppointmentItems.add(bookingDateAppointmentItem);
		bookingDate.AppointmentItems = bookingDateAppointmentItems;
		bookingDate.setServiceId("e20b92a9-1494-2f14-41ab-2f7581d0d110");
		bookingDate.setDate(Helper.convertMomentutc(getDateAppointment(), 1));
		bookingDate.setTorId(getTor_id());
		bookingDate.setK(Integer.valueOf(new CurrentK(getCookies()).getCurrentK()));
		String json = objectMapper.writeValueAsString(bookingDate);
		given().cookies(getCookies()).contentType("application/x-www-form-urlencoded; charset=UTF-8").body(json)
				.post(urlBaseClient + "ru/Booking/Date");
		return new BookingConfirmation(getCookies(), getTor_id(), getDateAppointment());
	}
}
