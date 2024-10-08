package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import go.GoodServiceParameters;
import go.soup.SoapClient;
import io.qameta.allure.Feature;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.core.exc.StreamWriteException;
import io.qameta.allure.internal.shadowed.jackson.databind.DatabindException;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonMappingException;

import jasonobject.ConfirmAppointment;
import jasonobject.ConfirmAppointmentPayload;
import jasonobject.ConfirmAppointmentProxy;

import restmethods.GetCookies;
import restmethods.MonthSchedule;

import java.io.IOException;

@Feature("Тесты проверки защиты от XSS атак на странице оформления записи")
public class TestXSSAppointment {

	Integer generateRequest(ConfirmAppointment comfAppo, ConfirmAppointmentProxy confirmAppointmentProxy)
			throws JsonMappingException, StreamWriteException, DatabindException, JsonProcessingException, IOException,
			InterruptedException {
		GoodServiceParameters toGoodServiceParameters = new GoodServiceParameters();
		new SoapClient(toGoodServiceParameters);

		Thread.sleep(5000);

		ConfirmAppointmentPayload confirmAppointmentPayload = new ConfirmAppointmentPayload();

		if (confirmAppointmentProxy == null) {
			confirmAppointmentProxy = new ConfirmAppointmentProxy();
		}

		Integer rez = new MonthSchedule(new GetCookies().createCookies(), toGoodServiceParameters.tor_id)
				.getMonthSchedule().getDateGrid().getDateResponse()
				.getServiceAppointmentIdFromRequestBookingConfirmation()
				.responseConfirm(comfAppo, confirmAppointmentPayload, confirmAppointmentProxy);
		return rez;
	}

	@Test
	@DisplayName("Проверка поля имя водителя")
	public void setXSSinDriverName() throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.Name = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля фамилия водителя")
	public void setXSSinDriverSurame()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.Surname = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля отчество водителя")
	public void setXSSinDriverPatronymic()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.Patronymic = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля имя водителя")
	public void setXSSinDriverBirthDate()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.BirthDate = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля марка машины")
	public void setXSSinVeihleCarBrand()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.CarBrand = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля модель машины")
	public void setXSSinVeihleCarModel()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.CarModel = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля контейнер")
	public void setXSSinVeihleContainer()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.Container = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля дата выдачи водительского удостоверения")
	public void setXSSinDriversLicenseDate()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.DriversLicenseDate = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля номера водительского удостоверения")
	public void setXSSinDriversLicenseNumber()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.DriversLicenseNumber = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля емайла водителя")
	public void setXSSinDriverEmail()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.Email = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля номер паспорта водителя")
	public void setXSSinDriverPassportNumber()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.PassportNumber = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля номер тлефона водителя")
	public void setXSSinDriverPhoneNumber()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.PhoneNumber = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля марки прицепа")
	public void setXSSinVeihleTrailerBrand()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.TrailerBrand = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля модели прицепа")
	public void setXSSinVeihleTrailerNumber()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		comfAppo.TrailerNumber = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, null) == 500);
	}

	@Test
	@DisplayName("Проверка поля номера доверенности ")
	public void setXSSinProxyNumber()
			throws StreamWriteException, DatabindException, IOException, InterruptedException {
		ConfirmAppointment comfAppo = new ConfirmAppointment();
		ConfirmAppointmentProxy confirmAppointmentProxy = new ConfirmAppointmentProxy();
		confirmAppointmentProxy.ProxyNumber = "<script>alert('123')</script>";
		Assertions.assertTrue(generateRequest(comfAppo, confirmAppointmentProxy) == 500);
	}

}
