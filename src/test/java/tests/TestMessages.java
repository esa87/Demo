package tests;

import static io.qameta.allure.SeverityLevel.NORMAL;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.automation.remarks.junit5.Video;

import go.GoodServiceParameters;
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
import po.MessagesPage;
@Feature("Тесты проверки страницы сообщения")
@ExtendWith(TestListener.class)
public class TestMessages extends AbstractTest{	
	
	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";
	
	@Test
	@DisplayName("Проверка что есть прочитанные сообщения")
    @Description(
    		"1 Авторизуемся на сайте \n"    		
    		+ "2 осуществляем поиск по прочитанным сообщениям \n"
    		+ "3 проверяем что сообщение отображается в непрочитанных "
    		)
    @Severity(NORMAL)
	@Video(name="video1")
    @Owner("Ерченков Сергей")
    @Link(name = "Website", url = "http://localhost:8484/")
    @Issue("Отсутствует")
    @TmsLink("Отсутствует")	
	public void testMessagesRead() throws InterruptedException, MessagingException, IOException {		
		Allure.step("1 Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);		
		Allure.step("2 осуществляем поиск по прочитанным сообщениям");
		Boolean getParam= new MenuPage(driver).selectMessagesPage().checkWorkSearchFromReadMessages() ;		
		Allure.step("3 проверяем что сообщение отображается в непрочитанных");
		Assertions.assertFalse(getParam);				
	}	
	
	@Test
	@DisplayName("Проверка создания создание сообщение и прочтения всех сообщений")
    @Description("1 Отправляется ТЗ \n"
    		+ "2 Авторизуемся на сайте \n"
    		+ "3 Создаем сообщение \n"
    		+ "4 проверяем что сообщение отображается в непрочитанных \n"
    		+ "5 Нажимаем кнопку процесть всё и проверяем что сообение исчезло из непрочитанных "
    		)
    @Severity(NORMAL)
	@Video(name="video1")
    @Owner("Ерченков Сергей")
    @Link(name = "Website", url = "http://localhost:8484/")
    @Issue("Отсутствует")
    @TmsLink("Отсутствует")	
	public void testCreateMessageAndReadAll() {
		GoodServiceParameters toParameters = new GoodServiceParameters();
		Allure.step("1 Отправляется ТЗ ");
		new SoapClient(toParameters);
		Allure.step("2 Авторизуемся на сайте");
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Allure.step("3 Создаем сообщение ");
		String rez= new MenuPage(driver).selectTabMenuTO().sendTorid(toParameters.tor_id).pushSearch().openTOdetail(toParameters.tor_id).sendMessage().selectMessagesPage().checkMessage(toParameters.tor_id);
		Allure.step("4 проверяем что сообщение отображается в непрочитанных ");
		Assertions.assertEquals(rez.substring(5).toLowerCase(), toParameters.tor_id);
		Allure.step("5 Нажимаем кнопку процесть всё и проверяем что сообение исчезло из непрочитанных ");
		Boolean rez2 = new MessagesPage(driver).readAll(toParameters.tor_id);
		Assertions.assertTrue(rez2);
	}

}
