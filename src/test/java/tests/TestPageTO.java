package tests;

import static io.qameta.allure.SeverityLevel.NORMAL;

import java.io.IOException;

import java.util.Date;

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
import po.IntegrationDocumentPage;
import po.LoginPage;
import po.MenuPage;

@Feature("Тесты проверки изменения транспортных заказов")
@ExtendWith(TestListener.class)
public class TestPageTO extends AbstractTest {

	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";

	@Test
	public void testStatusJnuhepbnmToday() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		toParam.DATE_SEND_BY = new Helper().genDate(0);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Отгрузить до сегодня"));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОРТНЫЙ ЗАКАЗ", current, ""));
	}

	@Test
	public void testStatusNew() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.DATE_SEND_BY = new Helper().genDate(1);
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Новый"));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОРТНЫЙ ЗАКАЗ", current, ""));
	}

	@Test
	public void testStatusExpiredOneDay() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.DATE_SEND_BY = new Helper().genDate(-1);
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Просрочен на 1 день"));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОРТНЫЙ ЗАКАЗ", current, ""));
	}

	@Test
	public void testStatusExpiredMoreTwoDays() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.DATE_SEND_BY = new Helper().genDate(-2);
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Просрочен более 2-х дней"));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОРТНЫЙ ЗАКАЗ", current, ""));
	}

	@Test
	@DisplayName("Проверка поиска ТЗ по экспедитору")
	@Description("1 Отправляется ТЗ \n" + "2 Авторизуемся на сайте \n"
			+ "3 Переходим на страницу списка ТЗ и вводим часть названия экспедитора \n"
			+ "4 проверяем что название экспедитора \"ЛК Туда-Сюда\" соответствует полученному результату ")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testNotRegisterChar() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Allure.step("шаг 1 : Отправляется ТЗ");
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		new SoapClient(toParam);
		Allure.step("шаг 2: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("шаг 3: Переходим на страницу списка ТЗ и вводим часть названия экспедитора");
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid("лк туда").pushSearch().getEspeditorName();
		Allure.step(
				"шаг 4 : роверяем что название экспедитора \\\"ЛК Туда-Сюда\\\" соответствует полученному результату "
						+ getParam);
		Assertions.assertEquals("ЛК Туда-Сюда", getParam);
		Allure.step("шаг 5: Проверяем что пришло письмо по заказу " + toParam.tor_id);
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОРТНЫЙ ЗАКАЗ", current, ""));
	}

	@Test
	public void testChangeExpeditor() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		toParam.guidMsgOutb = "165A59AB7CA7RFDABBB" + new Helper().genTorid().substring(5);
		toParam.TDLNR = "981";
		new SoapClient(toParam);
		System.out.println(GetEmail.checkMessage(toParam.tor_id, "ОТМЕНА ТРАНСПОРТН", current, ""));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "ОТМЕНА ТРАНСПОРТН", current, ""));
		System.out.println(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОР", current, "is.bylin@inforser.ru"));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.tor_id, "НОВЫЙ ТРАНСПОР", current, "is.bylin@inforser.ru"));
	}

	@Test
	public void testNotMATRN() throws InterruptedException, MessagingException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.MATNR = "12846123451";
		Date current = new Date(System.currentTimeMillis() - 60 * 1000);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Новый"));
		Assertions.assertTrue(GetEmail.checkMessage(toParam.MATNR, "Отсутствие прод", current, ""));
	}

}
