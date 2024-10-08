package jasonobject;

import java.util.List;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ResponseGetMonthScheduleMonth {

	public List<ResponseGetMonthScheduleMonthDays> Days;
	public Integer Year;
	public Integer Month;

	public ResponseGetMonthScheduleMonth() {
	};

	public List<ResponseGetMonthScheduleMonthDays> getDays() {
		return Days;
	}

	public void setMonth(List<ResponseGetMonthScheduleMonthDays> responseGetMonthScheduleMonthDays) {
		this.Days = responseGetMonthScheduleMonthDays;
	}
}