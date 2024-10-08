package restmethods;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import go.Helper;
import io.qameta.allure.internal.shadowed.jackson.core.exc.StreamWriteException;
import io.qameta.allure.internal.shadowed.jackson.databind.DatabindException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import jasonobject.ConfirmAppointment;
import jasonobject.ConfirmAppointmentPayload;
import jasonobject.ConfirmAppointmentProxy;

public class BookingConfirm extends AbstractRest {

	public BookingConfirm(Cookies cookies, String torId, String dateAppointments, String serviceAppointmentId) {
		setCookies(cookies);
		setTor_id(torId);
		setDateAppointment(dateAppointments);
		setServiceAppointmentId(serviceAppointmentId);
	}

	public Integer responseConfirm(ConfirmAppointment comfAppo, ConfirmAppointmentPayload confirmAppointmentPayload,
			ConfirmAppointmentProxy confirmAppointmentProxy)
			throws StreamWriteException, DatabindException, IOException {
		StringWriter writer = new StringWriter();
		ObjectMapper mapper1 = new ObjectMapper();
		ArrayList<ConfirmAppointment> confirmAppointments = new ArrayList<ConfirmAppointment>();
		ArrayList<ConfirmAppointmentPayload> confirmAppointmentPayloads = new ArrayList<ConfirmAppointmentPayload>();
		ArrayList<ConfirmAppointmentProxy> confirmAppointmentProxies = new ArrayList<ConfirmAppointmentProxy>();
		confirmAppointmentProxy.Id = new Helper().getUid();
		confirmAppointmentPayloads.add(confirmAppointmentPayload);
		confirmAppointmentProxies.add(confirmAppointmentProxy);
		comfAppo.ServiceAppointmentId = getServiceAppointmentId();
		comfAppo.Proxy = confirmAppointmentProxies;
		confirmAppointments.add(comfAppo);
		mapper1.writeValue(writer, confirmAppointments);
		Response responseConfirm = given().cookies(getCookies())
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.header("Referer",
						urlBaseClient + "ru/Booking/Confirmation/" + Helper.convertMomentutc(getDateAppointment(), 2)
								+ "?serviceId=e20b92a9-1494-2f14-41ab-2f7581d0d110&tor_id=" + getTor_id())
				.body(writer.toString()).post(urlBaseClient + "ru/Booking/Confirm");
		System.out.println(responseConfirm.body().asString());
		System.out.println(writer.toString());
		return responseConfirm.getStatusCode();
	}

}
