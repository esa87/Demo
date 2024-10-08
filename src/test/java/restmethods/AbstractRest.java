package restmethods;

import io.restassured.http.Cookies;

public abstract class AbstractRest {
	static String urlBaseClient = "http://localhost:8484/";
	private Cookies cookies;
	private String tor_id;
	private String dateAppointment;
	private String timeAppointment;
	private String timeAppointmentWindow;
	private String serviceAppointmentId;

	public Cookies getCookies() {
		return cookies;
	}

	public void setCookies(Cookies cookies) {
		this.cookies = cookies;
	}

	public String getTor_id() {
		return tor_id;
	}

	public void setTor_id(String tor_id) {
		this.tor_id = tor_id;
	}

	public String getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(String dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public String getTimeAppointment() {
		return timeAppointment;
	}

	public void setTimeAppointment(String timeAppointment) {
		this.timeAppointment = timeAppointment;
	}

	public String getTimeAppointmentWindow() {
		return timeAppointmentWindow;
	}

	public void setTimeAppointmentWindow(String timeAppointmentWindow) {
		this.timeAppointmentWindow = timeAppointmentWindow;
	}

	public String getServiceAppointmentId() {
		return serviceAppointmentId;
	}

	public void setServiceAppointmentId(String serviceAppointmentId) {
		this.serviceAppointmentId = serviceAppointmentId;
	}
}