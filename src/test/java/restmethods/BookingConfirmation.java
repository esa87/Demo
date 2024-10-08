package restmethods;

import static io.restassured.RestAssured.given;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import go.Helper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

public class BookingConfirmation extends AbstractRest {

	public BookingConfirmation(Cookies cookies, String torId, String dateAppointments) {
		setCookies(cookies);
		setTor_id(torId);
		setDateAppointment(dateAppointments);
	}

	public BookingConfirm getServiceAppointmentIdFromRequestBookingConfirmation() {
		Response serviceAppointmentId = given().cookies(getCookies())
				.get(urlBaseClient + "ru/Booking/Confirmation/" + Helper.convertMomentutc(getDateAppointment(), 2)
						+ "?serviceId=e20b92a9-1494-2f14-41ab-2f7581d0d110&tor_id=" + getTor_id());
		Document documentConfirmation1 = Jsoup.parse(serviceAppointmentId.getBody().asString());
		setServiceAppointmentId(documentConfirmation1.body().selectXpath("//div[@class='service_card']").attr("id"));
		return new BookingConfirm(getCookies(), getTor_id(), getDateAppointment(), getServiceAppointmentId());
	}
}