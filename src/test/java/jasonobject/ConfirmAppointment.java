package jasonobject;

import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class ConfirmAppointment {
	public String ServiceAppointmentId;
	public String PhoneNumber = "+7999111-22-33";
	public String BirthDate = "21.01.1988";
	public String Email = "test@test.ru";
	public String Surname = "Сидоров";
	public String Name = "Сидор";
	public String Patronymic = "Сидорович";
	public String Payload = "\"[{\"Комментарии\":\"\"}]\"";
	public String PassportNumber = "111111111";
	public String TransportNumber = "o000oo000";
	public String CarBrand = "kamaz";
	public String CarModel = "3329";
	public String TrailerBrand = "ekarus";
	public String TrailerNumber = "oo00000";
	public String Container = "";
	public String DriversLicenseNumber = "222222222";
	public String DriversLicenseDate = "21.11.2023";
	public ArrayList<ConfirmAppointmentProxy> Proxy;
	public ConfirmAppointment() {
	}
}
