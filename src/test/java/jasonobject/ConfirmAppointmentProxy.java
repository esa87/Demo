package jasonobject;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ConfirmAppointmentProxy {
	public String Id;
	public String ProxyNumber = "555";
	public String ProxyDate = "22.07.2024";
	public String ProxyPhoto = "";
	public String FileName = "";
	public Boolean TrySkipProxyPhoto = false;

	public ConfirmAppointmentProxy() {
	}
}
