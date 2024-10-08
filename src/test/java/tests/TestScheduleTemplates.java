package tests;

import static io.qameta.allure.SeverityLevel.NORMAL;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.automation.remarks.junit5.Video;

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

@Feature("Тесты проверки добавления и удаления услуги в шаблон рассписания")
@ExtendWith(TestListener.class)
public class TestScheduleTemplates extends AbstractTest {

	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";

	@Test
	@DisplayName("Проверка удаления услуги/продукта в шаблоне")
	@Description("1 Авторизуемся на сайте \n"
			+ "2 Заходим в шаблоны рассписания выбираем шаблон и удаляем в нем услугу, проверяем, что услуга удалилась")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testDeleteServiceInSchedule() throws InterruptedException, MessagingException, IOException {
		Boolean rez;
		Allure.step("1 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step(
				"2 шаг: Заходим в шаблоны рассписания выбираем шаблон и удаляем в нем услугу, проверяем, что услуга удалилась");
		rez = new MenuPage(driver).selectServicesLookupPage().clickBtnScheduleTemplates().deleteService();
		Assertions.assertTrue(rez);
	}

	@Test
	@DisplayName("Проверка добавления услуги/продукта в шаблон")
	@Description("1 Авторизуемся на сайте\n"
			+ "2 Заходим в шаблоны рассписания выбираем шаблон и добавляем в нем услугу, проверяем, что услуга удалилась")
	@Severity(NORMAL)
	@Video(name = "video1")
	@Owner("Ерченков Сергей")
	@Link(name = "Website", url = "http://localhost:8484/")
	@Issue("Отсутствует")
	@TmsLink("Отсутствует")
	public void testAddServiceInSchedule() throws InterruptedException, MessagingException, IOException {
		Boolean rez;
		Allure.step("1 шаг: Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step(
				"2 шаг: Заходим в шаблоны рассписания выбираем шаблон и добавляем в нем услугу, проверяем, что услуга удалилась");
		rez = new MenuPage(driver).selectServicesLookupPage().clickBtnScheduleTemplates().addService();
		Assertions.assertTrue(rez);
	}

}
