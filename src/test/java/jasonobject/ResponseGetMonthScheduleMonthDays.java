package jasonobject;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ResponseGetMonthScheduleMonthDays {

	public String Date;
	public Integer MaxAppointmentsCount;
	public Integer BlockedOrPlanedByMeCount;
	public Integer AvailableAppointmentsCount;
	public Integer ScheduledAppointmentsCount;

	public ResponseGetMonthScheduleMonthDays() {
	};

	public String getDate() {
		return Date;
	}

	public void setDate(String Date) {
		this.Date = Date;
	}

}