package jasonobject;

import java.util.List;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class BookingDate {
	public String ServiceId;
	public List<BookingDateAppointmentItems> AppointmentItems;
	public String Date;
	public String Captcha = "";
	public Integer K;
	public String tor_id;
	public String MoveId = "";
	public Boolean OnlyLocked = false;

	public BookingDate() {
	};

	public void setK(Integer k) {
		this.K = k;
	}

	public void setDate(String date) {
		this.Date = date;
	}

	public void setTorId(String tor_id) {
		this.tor_id = tor_id;
	}

	public void setServiceId(String serviceId) {
		this.ServiceId = serviceId;
	}

	public void setAppointmentItems(List<BookingDateAppointmentItems> responseGetMonthScheduleMonthDays) {
		this.AppointmentItems = responseGetMonthScheduleMonthDays;
	}

}