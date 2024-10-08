package tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.xml.sax.SAXException;

import go.ConnectToPostgresql;
import go.GoodServiceParameters;
import go.Helper;
import go.XmlDomParser;
import go.soup.SoapClient;
import io.qameta.allure.Feature;
import po.IntegrationDocumentPage;
import po.LoginPage;
import po.MenuPage;

@Feature("Тесты проверки изменения транспортных заказов посредством изменения данных в ТЗ")
@ExtendWith(TestListener.class)
public class TestControlValidDataTransportOrder extends AbstractTest {

	private static Integer timeoutWait = 13000;
	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";

	@Test
	public void checkNormalTO() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("0", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Новый"));
	}

	@Test
	public void checkNoDES_LOC() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.DES_LOC = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("0", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Новый"));
	}

	@Test
	public void checkNoDES_LOC_TEXT()
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.DES_LOC_TEXT = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("0", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Новый"));
	}

	@Test
	public void checkNoVSTEL() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.VSTEL = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("1", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		Boolean getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch()
				.checkVisibleTableDate();
		Assertions.assertTrue(getParam);
	}

	@Test
	public void checkNoKUNAGandTDLNR()
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.TDLNR = "";
		toParam.KUNAG = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("1", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		Boolean getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch()
				.checkVisibleTableDate();
		Assertions.assertTrue(getParam);
	}

	@Test
	public void checkNoSTAGE_ROUTE()
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.STAGE_ROUTE = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("0", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		String getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch().getSatusName();
		Assertions.assertTrue(getParam.contains("Новый"));
	}

	@Test
	public void checkNoMATNR() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.MATNR = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("1", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		Boolean getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch()
				.checkVisibleTableDate();
		Assertions.assertTrue(getParam);
	}

	@Test
	public void checkNoTOR_ID() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		toParam.tor_id = "";
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.guidMsgOutb);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("1", statusTO);
	}

	@Test
	public void checkDuplicateMessageUid()
			throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		GoodServiceParameters toParam = new GoodServiceParameters();
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		toParam.tor_id = new Helper().genTorid();
		new SoapClient(toParam);
		Thread.sleep(timeoutWait);
		String xmlText = new ConnectToPostgresql().getSelect(toParam.tor_id);
		String statusTO = new XmlDomParser().getStatus(xmlText);
		Assertions.assertEquals("W", statusTO);
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		new MenuPage(driver).selectTabMenuTO();
		Boolean getParam = new IntegrationDocumentPage(driver).sendTorid(toParam.tor_id).pushSearch()
				.checkVisibleTableDate();
		Assertions.assertTrue(getParam);
	}
}