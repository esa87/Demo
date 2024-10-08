package jasonobject;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class BookingDateAppointmentItems {
	public String Time;
	public Integer WindowNumber;

	public BookingDateAppointmentItems() {
	};

	/*
	 * public String getTime() { return Time; }
	 * 
	 * 
	 * public void setTime(String time) { this.Time=time; }
	 * 
	 * 
	 * public Integer getWindowNumber() { return WindowNumber; }
	 * 
	 * 
	 * public void setWindowNumber(Integer windowNumber) {
	 * this.WindowNumber=windowNumber; }
	 */
}
