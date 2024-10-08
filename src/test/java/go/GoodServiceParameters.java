package go;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodServiceParameters {

	public String tor_id;
	public String guidMsgOutb;
	public String TDLNR;
	public String TDLNR_NAME;
	public String DATE_SEND;
	public String DATE_SEND_FR;
	public String SIGN_DEL;
	public String STATUS;
	public String COST;
	public String NTGEW;
	public String ADDRESS_DESCR;
	public String DATE_SEND_BY;
	public String DES_LOC;
	public String DES_LOC_TEXT;
	public String KUNAG;
	public String KUNNR;
	public String MAIL_TUT;
	public String VSTEL_TOR;
	public String VSTEL;
	public String STAGE_ROUTE;
	public String MATNR;
	public String SurnameDriver;
	public String YPOS;
	public String XPOS;
	public Integer Count_DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem;
	public Integer Count_DT_TrOrder_OutDelivDocumentDelivery;
	public Boolean changeSatge_Count_DT_TrOrder_OutDelivDocumentDelivery;
	public String workDay;
	public String TIME_SEND_BY;

	List<String> cityList;

	public GoodServiceParameters() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		tor_id = new Helper().genTorid();
		guidMsgOutb = "165A59AB7CA7RFDABBB" + tor_id.substring(5);
		TDLNR = "147838";
		TDLNR_NAME = "ЛК Туда-Сюда";
		DATE_SEND = formatter.format(date);
		DATE_SEND_FR = new Helper().genDate(-5);
		SIGN_DEL = "";
		STATUS = "01";
		COST = "500";
		NTGEW = "0.080";
		ADDRESS_DESCR = ", улица Черемушкинская набережная, дом 18";
		DATE_SEND_BY = new Helper().genDate(1);
		DES_LOC = "123";
		DES_LOC_TEXT = "Тестовый ПО Москва ";
		KUNAG = "147838";
		KUNNR = "";
		MAIL_TUT = "test@test.ru";
		VSTEL_TOR = "2420";
		VSTEL = "2420";
		STAGE_ROUTE = "00001";
		MATNR = "00000000000000022";
		SurnameDriver = "ТЕСТОВВВ";
		YPOS = "37.774463";
		XPOS = "55.720722";
		Count_DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem = 1;
		Count_DT_TrOrder_OutDelivDocumentDelivery = 1;
		changeSatge_Count_DT_TrOrder_OutDelivDocumentDelivery = false;
		cityList = new ArrayList();
		workDay = "Рабочий день с 11 до 18";
		TIME_SEND_BY = "000000";
	}

	public List<String> getCityList() {
		cityList.add("Москва");
		cityList.add("Тула");
		cityList.add("Рязань");
		cityList.add("Тула");
		cityList.add("Калуга");
		cityList.add("Мурманск");
		cityList.add("Саратов");
		cityList.add("Уфа");
		cityList.add("Владимир");
		cityList.add("Подольск");
		cityList.add("Люберцы");
		return cityList;

	}
}
