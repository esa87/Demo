package jasonobject;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class ConfirmAppointmentPayload {
	@JsonProperty("\"Комментарии:\"")
	public String comment = "\"\"";

	public ConfirmAppointmentPayload() {
	}
}