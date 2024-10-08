package tests;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import go.GoodServiceParameters;
import go.soup.SoapClient;
import io.qameta.allure.Feature;
import po.LoginPage;
import po.MenuPage;

@Feature("Тесты проверки  транспортных заказов с различным количеством пунктов лоставки и продукций")
@ExtendWith(TestListener.class)
public class TestOutDeliveryPoint extends AbstractTest {

	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";

	@Test
	public void onePoint() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Integer i = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch()
				.openTOdetail(toParam.tor_id).getCountDeliveryPoint();
		Assertions.assertTrue(i == 3);
	}

	@Test
	public void fiveProductOnFiveSamePoint() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.Count_DT_TrOrder_OutDelivDocumentDelivery = 5;
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Integer i = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch()
				.openTOdetail(toParam.tor_id).getCountDeliveryPoint();
		Assertions.assertTrue(i == 3);
	}

	@Test
	public void fiveProductOnFiveDifferentPoint() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.Count_DT_TrOrder_OutDelivDocumentDelivery = 5;
		toParam.changeSatge_Count_DT_TrOrder_OutDelivDocumentDelivery = true;
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Integer i = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch()
				.openTOdetail(toParam.tor_id).getCountDeliveryPoint();
		Assertions.assertTrue(i == 7);
	}

	@Test
	public void fiveProductOnOnePoint() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.Count_DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem = 10;
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Integer i = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch()
				.openTOdetail(toParam.tor_id).getCountDeliveryPoint();
		Assertions.assertTrue(i == 3);
	}

	@Test
	public void checkCoordinat() throws InterruptedException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		Random rnd = new Random();
		Integer number = rnd.nextInt(999) + 1;
		Integer numone = rnd.nextInt(9);
		toParam.DES_LOC = "994" + number.toString();
		toParam.YPOS = "5.8" + numone.toString() + "71638999999998E+01";
		toParam.XPOS = "4.9" + numone.toString() + "08635000000000E+01";
		System.out.println("4.9" + numone);
		new SoapClient(toParam);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String i = new MenuPage(driver).selectTabMenuTO().sendTorid(toParam.tor_id).pushSearch()
				.openTOdetail(toParam.tor_id).getCordinat();
		Assertions.assertTrue(i.contains("49." + numone.toString()));
	}

}
