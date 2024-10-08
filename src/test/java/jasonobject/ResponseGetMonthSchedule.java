package jasonobject;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class ResponseGetMonthSchedule {
	public String ServiceId;
	public ResponseGetMonthScheduleMonth Month;
	public ResponseGetMonthScheduleCommonLegend CommonLegend;
	public Integer ThirdDecadeOfPreviosMonth;
	public Integer FirstDecadeOfThisMonth;
	public Integer SecondDecadeOfThisMonth;
	public Integer ThirdDecadeOfThisMonth;
	public Integer FirstDecadeOfNextMonth;
	public Integer ReserevedByYouCount;
	public Integer TotalSlots;
	public Integer AvailableSlots;
	
	public ResponseGetMonthSchedule () {};	
	
	public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String ServiceId) {
        this.ServiceId = ServiceId;
    }   
    
    public ResponseGetMonthScheduleMonth getMonth() {
        return Month;
    }

    public void setMonth(ResponseGetMonthScheduleMonth responseGetMonthScheduleMonth) {
        this.Month = responseGetMonthScheduleMonth;
    }	

}