package tests;

import static io.qameta.allure.SeverityLevel.NORMAL;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automation.remarks.junit5.Video;

import go.GetEmail;
import go.GoodServiceParameters;
import go.Helper;
import go.soup.SoapClient;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import jakarta.mail.MessagingException;
import po.AppointmentPage;
import po.LoginPage;
import po.MenuPage;

@Feature("Тесты проверки операций с записями ")
@ExtendWith(TestListener.class)
public class TestAppointments extends AbstractTest {

	String adminLogin = "test1";
	String adminPwd = "123";

	String expedLogin = "test2";
	String expedPwd = "123";

	String buyLogin = "test3";
	String buyPwd = "123";

	void beforeTest(GoodServiceParameters toParam, String Surname, Date current, String login, String pwd) {
		Allure.step("1  шаг: Отправляется ТЗ, время отправки  " + current);
		new SoapClient(toParam);
		Allure.step("2  шаг: Авторизуемся на сайте под " + login);
		new LoginPage(driver).loginAs(login, pwd);
		Allure.step("3  шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot().edit(Surname);
	}

	@Test
	@DisplayName("Проверка создания записи на услугу под ролью администратора департамента")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 проверяем что статус записи \"ТС назначено\" \n" + "5 Проверяем что пришло письмо с талоном")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateAppointmentAdminKd() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		beforeTest(toParam, "ТЕСТОВВВ", current, adminLogin, adminPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getStatus();
		Allure.step("4  шаг: проверяем статус записи - велнулся такой = " + rez);
		Assertions.assertEquals(rez, "ТС назначено");
		Allure.step("5  шаг:  Проверяем что пришло письмо с талоном " + new Date());
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "ТАЛОН ПРЕДВАРИТЕЛ", current, ""));
	}

	@Test
	@DisplayName("Проверка создания записи на услугу под покупателем")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 проверяем что статус записи \"ТС назначено\" \n" + "5 Проверяем что пришло письмо с талоном")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateAppointmentBuyer() throws InterruptedException, MessagingException, IOException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.TDLNR = "";
		beforeTest(toParam, "АБРАМОВ", current, buyLogin, buyPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getStatus();
		Allure.step("4  шаг: проверяем статус записи - велнулся такой = " + rez);
		Assertions.assertEquals(rez, "ТС назначено");
		Allure.step("5  шаг:  Проверяем что пришло письмо с талоном" + new Date());
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "ТАЛОН ПРЕДВАРИТЕЛ", current, "test2@test.ru"));
	}

	@Test
	@DisplayName("Проверка создания записи на услугу под экспедитором")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 проверяем что статус записи \"ТС назначено\" \n" + "5 Проверяем что пришло письмо с талоном")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateAppointmentEkspeditor() throws InterruptedException, MessagingException, IOException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "АБРАМОВ", current, expedLogin, expedPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getStatus();
		Allure.step("4  шаг: проверяем статус записи - велнулся такой = " + rez);
		Assertions.assertEquals(rez, "ТС назначено");
		Allure.step("5  шаг:  Проверяем что пришло письмо с талоном" + new Date());
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "ТАЛОН ПРЕДВАРИТЕЛ", current, ""));
	}

	@Test
	@DisplayName("Проверка отмены записи по кнопке отменить под администратором деп")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 отменяем запись\n"
			+ "6 Проверяем что пришло письмо с талоном письма об отмене отправляются для записей с текущего дня начиная с 17:00 до следующего дня 23:59:59")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCancelAppointmentOnButton()
			throws InterruptedException, MessagingException, IOException, ParseException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", current, adminLogin, adminPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("4  шаг: получаем код записи = " + rez);
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().selectCheckbox()
				.pushCancelAppointment().selectTypeAppointment("3").pushBtnSearch().getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: отменяем запис, и ищем запись по отменненым, получаем код записи = " + rez2);
		Assertions.assertEquals(rez, rez2);
		String getDateAppointment = new AppointmentPage(driver).getAppointmentDate();
		if (Helper.checkDateForSendingMessageAboutCancelAppointments(getDateAppointment)) {
			Allure.step(
					"6  шаг:  Проверяем что пришло письмо с сообщением об отмене записи представителю пункта отгрузки"
							+ new Date());
			Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id + " была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current,
					"test1@test.ru"));
		}
		Allure.step("7  шаг:  Проверяем что пришло письмо с сообщением об отмене записи заказчику" + new Date());
		Assertions
				.assertTrue(GetEmail.checkMessage(toParam.tor_id + " была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current, ""));

	}

	@Test
	@DisplayName("Проверка отмены записи по кнопке отменить под экспедитором")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 отменяем запись\n"
			+ "6 Проверяем что пришло письмо с талоном письма об отмене отправляются для записей с текущего дня начиная с 17:00 до следующего дня 23:59:59")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCancelAppointmentOnButtonExpeditor()
			throws InterruptedException, MessagingException, IOException, ParseException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "АБРАМОВ", current, expedLogin, expedPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("4  шаг: получаем код записи = " + rez);
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().selectCheckbox()
				.pushCancelAppointment().selectTypeAppointment("3").pushBtnSearch().getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: отменяем запис, и ищем запись по отменненым, получаем код записи = " + rez2);
		Assertions.assertEquals(rez, rez2);
		String getDateAppointment = new AppointmentPage(driver).getAppointmentDate();
		if (Helper.checkDateForSendingMessageAboutCancelAppointments(getDateAppointment)) {
			Allure.step(
					"6  шаг:  Проверяем что пришло письмо с сообщением об отмене записи представителю пункта отгрузки"
							+ new Date());
			Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id + " была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current,
					"test1@test.ru"));
		}
		Allure.step("7  шаг:  Проверяем что пришло письмо с сообщением об отмене записи заказчику" + new Date());
		Assertions
				.assertTrue(GetEmail.checkMessage(toParam.tor_id + " была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current, ""));
	}

	@Test
	@DisplayName("Проверка отмены записи по кнопке отменить под покупателем")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 отменяем запись\n"
			+ "6 Проверяем что пришло письмо с талоном письма об отмене отправляются для записей с текущего дня начиная с 17:00 до следующего дня 23:59:59")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCancelAppointmentOnButtonBuyer()
			throws InterruptedException, MessagingException, IOException, ParseException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.TDLNR = "";
		beforeTest(toParam, "АБРАМОВ", current, buyLogin, buyPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("4  шаг: получаем код записи = " + rez);
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().selectCheckbox()
				.pushCancelAppointment().selectTypeAppointment("3").pushBtnSearch().getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: отменяем запис, и ищем запись по отменненым, получаем код записи = " + rez2);
		Assertions.assertEquals(rez, rez2);
		String getDateAppointment = new AppointmentPage(driver).getAppointmentDate();
		if (Helper.checkDateForSendingMessageAboutCancelAppointments(getDateAppointment)) {
			Allure.step(
					"6  шаг:  Проверяем что пришло письмо с сообщением об отмене записи представителю пункта отгрузки"
							+ new Date());
			Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id + " была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current,
					"test1@test.ru"));
		}

		Allure.step("7  шаг:  Проверяем что пришло письмо с сообщением об отмене записи заказчику" + new Date());
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id + " была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current,
				"test2@test.ru"));
	}

	@Test
	@DisplayName("Проверка что не отменится запись если в ТЗ поменять стоимость заказа")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененной стоимостью\n"
			+ "6 Проверяем что запись не отменилась")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentChangeTO() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи ");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		toParam.COST = "777";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ с измененной стоимость для записи с кодом =" + rez);
		new SoapClient(toParam);
		Allure.step("6  шаг: Проверяем что запись не отменилась");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Assertions.assertEquals(rez2, "ТС назначено");
	}

	@Test
	@DisplayName("Проверка что запись если в ТЗ поменять стату заказа на 03 поменяется на статус \"На погрузке\"")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным статусом 03\n"
			+ "6 Проверяем что статус записи поменялся на \"На погрузке\"")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentBeforeChangeStatus03() throws InterruptedException {
		Allure.step("1  шаг: Отправляется ТЗ");
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("4  шаг: получаем код записи");
		toParam.STATUS = "03";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ со статусом 03 с кодом =" + rez);
		new SoapClient(toParam);
		Thread.sleep(5000);
		Allure.step("6  шаг: Проверяем что статус записи поменялся на \"На погрузке\"");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Assertions.assertEquals(rez2, "На погрузке");
	}

	@Test
	@DisplayName("Проверка что запись если в ТЗ поменять стату заказа на 04 поменяется на статус \"Отгружен\"")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным статусом 04\n"
			+ "6 Проверяем что статус записи поменялся на \"Отгружен\"")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentBeforeChangeStatus04() throws InterruptedException {

		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("4  шаг: получаем код записи");
		toParam.STATUS = "03";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ со статусом 04 для записи с кодом =" + rez);
		new SoapClient(toParam);
		Thread.sleep(5000);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Allure.step("7  шаг: Проверяем что статус записи поменялся на \"Отгружен\"" + rez2);
		Assertions.assertEquals(rez2, "Отгружен");
	}

	@Test
	@DisplayName("Проверка удаления записи при удалении ТЗ")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с пометкой об удалении\n"
			+ "6 Проверяем что запись в статусе удалено")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkDeleteAppointmentSingDel() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		beforeTest(toParam, "ТЕСТОВВВ", current, adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		toParam.SIGN_DEL = "X";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ SIGN_DEL=X для записи с кодом =" + rez);
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).selectTypeAppointment("2").pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("7  шаг: Проверяем что запись в статусе удалено");
		Assertions.assertEquals(rez, rez2);
	}

	@Test
	@DisplayName("Проверка выполнения заказа ")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным статусом 04\n"
			+ "6 На странице ТЗ проставляем дату отгрузки\n" + "7 проверяем что статус поменялся на \"ВЫПОЛНЕННЫЙ\"")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkExecutedStatus() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		toParam.STATUS = "03";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ со статусом 04 для записи с кодом =" + rez);
		new SoapClient(toParam);
		Thread.sleep(5000);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().selectNumberTO()
				.sendDateSended(new Helper().genDateRU(0)).checkStatusTO();
		Allure.step("7  шаг: Проверяем что статус записи поменялся на \"ВЫПОЛНЕННЫЙ\"" + rez2);
		Assertions.assertEquals(rez2, "ВЫПОЛНЕННЫЙ");

	}

	// -------------------------------Feature
	// #44496-----------------------------------------------------------------------------------------------

	@Test
	@DisplayName("Проверка что при смене кода покупателя не отменится запись")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным KUNAG (кодом покупателя)\n"
			+ "6 Получаем статус записи\n" + "7 Сравниваем статус ТЗ с ожидаемым"

	)
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("http://localhost/issues/44496")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentAfterChangeKUNAG() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		toParam.KUNAG = "181688";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ с измененным кодом KUNAG для записи с кодом =" + rez);
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Allure.step("7  шаг: Сравниваем статус \"ТС назначено\"" + rez2);
		Assertions.assertEquals(rez2, "ТС назначено");

	}

	@Test
	@DisplayName("Проверка что при смене кода экспедитора отменится запись")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным TDLNR (кодом экспедитора)\n"
			+ "6 На странице ТЗ проставляем дату отгрузки"

	)
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("http://localhost/issues/44496")
	@TmsLink("Отсутствует")
	public void checkCancelAppointmentAfterChangeTDLNR() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		toParam.TDLNR = "181688";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("5  шаг: Отправляем ТЗ с измененным TDLNR (кодом экспедитора)");
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем код записи отфильтровав список записей по отмененным");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).selectTypeAppointment("2").pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("7  шаг: Получаем код записи" + rez2 + "с кодом запис  " + rez);
		Assertions.assertEquals(rez, rez2);

	}

	@Test
	@DisplayName("Проверка что при смене кода экспедитора не отменится запись")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным KUNNR (кодом грузополучателя)\n"
			+ "6 На странице ТЗ проставляем дату отгрузки"

	)
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("http://localhost/issues/44496")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentAfterChangeKUNNR() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: Отправляем ТЗ с измененным KUNNR (кодом грузополучателя)");
		toParam.KUNNR = "123";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Allure.step("7  шаг: Сравниваем статус \"ТС назначено\"" + rez2);
		Assertions.assertEquals(rez2, "ТС назначено");

	}

	@Test
	@DisplayName("Проверка что при смене пункта доставки не отменится запись")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным кодом пункта доставки\n"
			+ "6 На странице ТЗ проставляем дату отгрузки"

	)
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("http://localhost/issues/44496")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentAfterChangeDES_LOC() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: Отправляем ТЗ с измененным DES_LOC");
		toParam.DES_LOC = "423";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Allure.step("7  шаг: Сравниваем статус \"ТС назначено\"" + rez2);
		Assertions.assertEquals(rez2, "ТС назначено");

	}

	@Test
	@DisplayName("Проверка что при смене пункта отгрузки не отменится запись")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным кодом пункта отгрузки\n"
			+ "6 На странице ТЗ проставляем дату отгрузки"

	)
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("http://localhost:9900/issues/44496")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentAfterChangeVSTEL() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: Отправляем ТЗ с измененным VSTEL");
		toParam.VSTEL = "423";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Allure.step("7  шаг: Сравниваем статус \"ТС назначено\"" + rez2);
		Assertions.assertEquals(rez2, "ТС назначено");

	}

	@Test
	@DisplayName("Проверка что при смене количества пунктов доставки отменится запись")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем код записи \n" + "5 Отправляем ТЗ с измененным кодом пункта доставки\n"
			+ "6 На странице ТЗ проставляем дату отгрузки"

	)
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("http://localhost/issues/44496")
	@TmsLink("Отсутствует")
	public void checkNotCancelAppointmentAfterChangeCountStageRoute() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendFindNumberTO(toParam.tor_id).pushBtnSearch()
				.getDoubleCheckCodeAppointment();
		Allure.step("5  шаг: Отправляем ТЗ с измененным количеством пунктов доставки");
		toParam.changeSatge_Count_DT_TrOrder_OutDelivDocumentDelivery = true;
		toParam.Count_DT_TrOrder_OutDelivDocumentDelivery = 3;
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		Allure.step("6  шаг: Получаем статус записи");
		String rez2 = new AppointmentPage(driver).sendAppointmentCode(rez).pushBtnSearch().getStatus();
		Allure.step("7  шаг: Сравниваем статус \"ТС назначено\"" + rez2);
		Assertions.assertEquals(rez2, "ТС назначено");

	}

	// ------------------------------------------------------------------------------------------------------------------------------

	@Test
	@DisplayName("Проверка поиска записи по названию экспедитора")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем статус записи выполнив поиск указав в фильтре экспедитора строку \"лк т\" \n")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkSearchEkspeditor() throws InterruptedException {

		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем статус записи выполнив поиск указав в фильтре экспедитора строку \"лк т\"");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendBuyerOrEkspeditor("лк т")
				.sendFindNumberTO(toParam.tor_id).pushBtnSearch().getStatus();
		Allure.step("5  шаг: Сравниваем статус \"ТС назначено\"" + rez);
		Assertions.assertEquals(rez, "ТС назначено");

	}

	@Test
	@DisplayName("Проверка поиска записи по названию покупателя")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 получаем статус записи выполнив поиск указав в фильтре покупателя строку \"покупателя\"")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkSearchBuyer() throws InterruptedException {

		GoodServiceParameters toParam = new GoodServiceParameters();
		beforeTest(toParam, "ТЕСТОВВВ", new Date(), adminLogin, adminPwd);
		Allure.step("4  шаг: получаем статус записи выполнив поиск указав в фильтре покупателя строку \"покупателя\"");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendBuyerOrEkspeditor("покупателя")
				.sendFindNumberTO(toParam.tor_id).pushBtnSearch().getStatus();
		Allure.step("5  шаг: Сравниваем статус \"ТС назначено\"" + rez);
		Assertions.assertEquals(rez, "ТС назначено");

	}

	// Bug #45460
	@Disabled
	@Test
	public void checkUnregisteredEntry() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(adminLogin, adminPwd);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot();
		String rez = new MenuPage(driver).SelectTabMenuAppointments().getUnregisteredEntry();
		Assertions.assertEquals(rez, "ТС назначено");

	}

	@Test
	@DisplayName("Проверка поиска записи без заказа")
	@Description("1 Авторизуемся на сайте \n" + "2 Оформляем запись на ТЗ \n" + "3 Получаем код записи"
			+ "4 роверяем что код записи не пустой" + "5 Проверяем что пришло письмо с записью ")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkAppointmentWithOutTO() throws InterruptedException, MessagingException, IOException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		String comment = new Helper().genTorid().substring(5);
		Allure.step("1  шаг: Авторизуемся на сайте под " + adminLogin);
		new LoginPage(driver).loginAs(adminLogin, adminPwd);
		Allure.step("2  шаг: Оформляем запись");
		new MenuPage(driver).selectTabMenuBooking().selectTabHomeServicePage().selectService().getNexDay(1).selectSlot()
				.editNotSendEmailAddComment("ТЕСТОВВВ", comment);
		Allure.step("3  шаг: Получаем код записи");
		String rez = new MenuPage(driver).SelectTabMenuAppointments().sendAdditionally(comment)
				.getDoubleCheckCodeAppointment();
		Allure.step("4  шаг: Проверяем что код записи не пустой " + rez);
		Assertions.assertTrue(!rez.equals(""));
		Allure.step("5  шаг: Проверяем что пришло письмо с записью ");
		Assertions.assertTrue(GetEmail.checkMessage(comment, "ТАЛОН ПРЕДВАРИТЕЛ", current, ""));
	}

	@Test
	@DisplayName("Проверка отмены записи без заказа")
	@Description("1 Авторизуемся на сайте \n" + "2 Оформляем запись на ТЗ \n" + "3 Получаем код записи"
			+ "4 роверяем что код записи не пустой" + "5 Проверяем что пришло письмо с записью ")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkAppointmentWithOutTOCancelOnButton() throws InterruptedException, MessagingException, IOException {
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		String comment = new Helper().genTorid().substring(5);
		Allure.step("1  шаг: Авторизуемся на сайте под " + adminLogin);
		new LoginPage(driver).loginAs(adminLogin, adminPwd);
		Allure.step("2  шаг: Оформляем запись");
		new MenuPage(driver).selectTabMenuBooking().selectTabHomeServicePage().selectService().getNexDay(1).selectSlot()
				.editNotSendEmailAddComment("ТЕСТОВВВ", comment);
		Allure.step("3  шаг: Получаем код записи");
		new MenuPage(driver).SelectTabMenuAppointments().sendAdditionally(comment).selectCheckbox()
				.pushCancelAppointment();
		Allure.step("5  шаг: Проверяем что пришло письмо с записью ");
		Assertions.assertTrue(GetEmail.checkMessage(" была отменена", "ТАЛОН ПРЕДВАРИТЕЛ", current, ""));

	}

}
