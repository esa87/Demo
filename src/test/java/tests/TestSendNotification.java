package tests;

import static io.qameta.allure.SeverityLevel.*;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
import po.LoginPage;
import po.MenuPage;

@Feature("Тесты проверки наличия кнопки отправить уведомление в разных статусах")
@ExtendWith(TestListener.class)
public class TestSendNotification extends AbstractTest {

	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";

	@Test
	@DisplayName("Проверка присутствия кнопки оповестить в статусе ТС Назначено (01)")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 проверяем что кнопка присутствует \n")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateAppointmentStatus01() throws InterruptedException {

		GoodServiceParameters toParam = new GoodServiceParameters();
		Allure.step("1 шаг: Отправляется ТЗ");
		new SoapClient(toParam);
		Allure.step("2 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("3 шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot().edit("ТЕСТОВВВ");
		Allure.step("4 шаг: проверяем что кнопка присутствует");
		Boolean rez = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().pushSearch()
				.openTOdetail(toParam.tor_id).buttonSendNotificationIsVisible();

		Assertions.assertFalse(rez);
	}

	@Test
	@DisplayName("Проверка присутствия кнопки оповестить в статусе На погрузке (03)")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 Отправляем ТЗ со статусом 03 \n" + "5 проверяем что кнопка присутствует")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")

	public void checkCreateAppointmentStatus03() throws InterruptedException {

		GoodServiceParameters toParam = new GoodServiceParameters();
		Allure.step("1 шаг: Отправляется ТЗ");
		new SoapClient(toParam);
		Allure.step("2 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("3 шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot().edit("ТЕСТОВВВ");
		toParam.STATUS = "03";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("4 шаг: Отправляем ТЗ со статусом 03");
		new SoapClient(toParam);
		Allure.step("5 шаг: проверяем что кнопка присутствует");
		Boolean rez = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().pushSearch()
				.openTOdetail(toParam.tor_id).buttonSendNotificationIsVisible();

		Assertions.assertFalse(rez);
	}

	@Test
	@DisplayName("Проверка присутствия кнопки оповестить в статусе отгружено (04), что проставляется дата отправки, что письмо отправилась копия письма")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 Отправляем ТЗ со статусом 04 \n" + "5 проверяем что кнопка присутствует")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateAppointmentStatus04() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		Allure.step("1 шаг: Отправляется ТЗ");
		new SoapClient(toParam);
		Allure.step("2 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("3 шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot().edit("ТЕСТОВВВ");
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("4 шаг: Отправляем ТЗ со статусом 04");
		new SoapClient(toParam);
		Allure.step("5 шаг: проверяем что дата отправки присутствует");
		Boolean rez = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().pushSearch()
				.openTOdetail(toParam.tor_id).clickNotification("user1@test.ru");
		Assertions.assertFalse(rez);
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "Уведомление о до", current, "user1@test.ru"));

	}

	@Test
	@ExtendWith(TestListener.class)
	@DisplayName("Проверка отображения даты отправки уведомления об отгрузке отправленного вручную, что в письме 5 продуктов, проверяем что при повторном присвоении статуса 04 не слетает отметка об отправке уведомления задача 46080")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 отправляем уведомление и проверяем, что дата отправки присутствует")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateDateSended() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		Allure.step("1 шаг: Отправляется ТЗ");
		toParam.Count_DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem = 5;
		toParam.TDLNR = "";
		new SoapClient(toParam);
		Allure.step("2 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("3 шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot().edit("ТЕСТОВВВ");
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("4 шаг: Отправляем ТЗ со статусом 04");
		new SoapClient(toParam);
		Allure.step("5 шаг: отправляем уведомление и проверяем, что дата отправки присутствует");
		Boolean rez = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().pushSearch()
				.openTOdetail(toParam.tor_id).clickNotification("user1@test.ru");
		Assertions.assertFalse(rez);
		String rez2 = GetEmail.getBodyMessage(toParam.tor_id, "Уведомление о до", current, "user1@test.ru");
		Integer productInteger;
		Integer weghtInteger;
		Integer emailCopy;
		Allure.step("6 шаг: Проверяем что в письме 5 продуктов");
		productInteger = Helper.getCountMatcher(rez2, "Продукция");
		weghtInteger = Helper.getCountMatcher(rez2, "Вес");
		emailCopy = Helper.getCountMatcher(rez2, "user3@test.ru");
		Assertions.assertTrue(productInteger == 5);
		Assertions.assertTrue(weghtInteger == 5);
		Assertions.assertTrue(emailCopy == 1);
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step(
				"7 шаг: Проверяем что после повторной отправки сообщения со статустом 04 не слетела отметка об отправке уведомления");
		new SoapClient(toParam);
		Boolean rez3 = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().pushSearch()
				.openTOdetail(toParam.tor_id).chekIsNotification();
		Assertions.assertFalse(rez3);

	}

	@Test
	@ExtendWith(TestListener.class)
	@DisplayName("Проверка отображения даты отправки уведомления об отгрузке отправленного автоматически, что в письме 5 продуктов, проверяем что при повторном присвоении статуса 04 не слетает отметка об отправке уведомления задача 46080")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n" + "3 Оформляем запись на ТЗ \n"
			+ "4 отправляем уведомление и проверяем, что дата отправки присутствует")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void checkCreateDateSendedAuto() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		Allure.step("1 шаг: Отправляется ТЗ");
		toParam.Count_DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem = 5;
		toParam.DES_LOC = "997";
		toParam.TDLNR = "";
		new SoapClient(toParam);
		Allure.step("2 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("3 шаг: Оформляем запись на ТЗ " + toParam.tor_id);
		new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().openTOdetail(toParam.tor_id)
				.clickOnBookingButton().getNexDay(1).selectSlot().editNotSendEmail("ТЕСТОВВВ");
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step("4 шаг: Отправляем ТЗ со статусом 04");
		new SoapClient(toParam);
		String rez2 = GetEmail.getBodyMessage(toParam.tor_id, "Уведомление о до", current, "user1@test.ru");
		Integer productInteger;
		Integer weghtInteger;
		Integer emailCopy;
		Allure.step("5 шаг: Проверяем что в письме 5 продуктов");
		productInteger = Helper.getCountMatcher(rez2, "Продукция");
		weghtInteger = Helper.getCountMatcher(rez2, "Вес");
		emailCopy = Helper.getCountMatcher(rez2, "user2@test.ru");
		Assertions.assertTrue(productInteger == 5);
		Assertions.assertTrue(weghtInteger == 5);
		Assertions.assertTrue(emailCopy == 1);
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		Allure.step(
				"6 шаг: Проверяем что после повторной отправки сообщения со статустом 04 не слетела отметка об отправке уведомления");
		new SoapClient(toParam);
		Boolean rez3 = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch().pushSearch()
				.openTOdetail(toParam.tor_id).chekIsNotification();
		Assertions.assertFalse(rez3);
		toParam.STATUS = "01";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		Date current1 = new Date(System.currentTimeMillis() - 5 * 1000);
		Thread.sleep(1000);
		toParam.STATUS = "03";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		Thread.sleep(1000);
		toParam.STATUS = "04";
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		new SoapClient(toParam);
		String rez4 = GetEmail.getBodyMessage(toParam.tor_id, "Уведомление о до", current1, "user1@test.ru");
		System.out.println(rez4);
		Assertions.assertTrue(rez4.equals(""));

	}

}
