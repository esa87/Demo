package go.soup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import go.GoodServiceParameters;
import io.netty.handler.codec.string.StringEncoder;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class OutDelivDocumentSoupModel {

	private Boolean randomGenerator(String nameNode) {
		boolean rez = false;
		Random random = new Random();
		rez = random.nextBoolean();
		return true;
	}

	List<SOAPElement> DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem = new ArrayList<>();

	public SOAPElement outDelivDocumentSoupModel(SOAPElement parent, String namespaceNs, String stage,
			GoodServiceParameters toParam) throws SOAPException {

		SOAPElement ABLAD = parent.addChildElement("ABLAD", namespaceNs);
		ABLAD.addTextNode("123");
		SOAPElement ADDRESS_DESCR = parent.addChildElement("ADDRESS_DESCR", namespaceNs);
		ADDRESS_DESCR.addTextNode(
				"город " + toParam.getCityList().get(Integer.valueOf(stage)) + toParam.ADDRESS_DESCR + stage);
		if (randomGenerator("AUDAT")) {
			SOAPElement AUDAT = parent.addChildElement("AUDAT", namespaceNs);
			AUDAT.addTextNode("123");
		}
		SOAPElement bLDAT = parent.addChildElement("BLDAT", namespaceNs);
		bLDAT.addTextNode("123");
		SOAPElement bOLNR = parent.addChildElement("BOLNR", namespaceNs);
		bOLNR.addTextNode("123");
		if (randomGenerator("BSTDK")) {
			SOAPElement bSTDK = parent.addChildElement("BSTDK", namespaceNs);
			bSTDK.addTextNode("123");
		}
		SOAPElement bSTKD = parent.addChildElement("BSTKD", namespaceNs);
		bSTKD.addTextNode("123");
		SOAPElement dES_LOC = parent.addChildElement("DES_LOC", namespaceNs);
		dES_LOC.addTextNode(stage + toParam.DES_LOC);
		SOAPElement dES_LOC_TEXT = parent.addChildElement("DES_LOC_TEXT", namespaceNs);
		dES_LOC_TEXT.addTextNode(toParam.DES_LOC_TEXT + stage);
		SOAPElement deliveryItem = parent.addChildElement("DeliveryItem", namespaceNs);
		List<SOAPElement> DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem = new ArrayList<>();
		Integer i = 0;
		String stageItem;
		while (i < toParam.Count_DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem) {
			DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem
					.add(deliveryItem.addChildElement("DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem", namespaceNs));
			if (i == 0)
				stageItem = "";
			else {
				stageItem = i.toString();
			}
			new DeliveryItemSoupModel().deliveryItemSoapModel(DT_TrOrder_OutDelivDocumentDeliveryDeliveryItem.get(i),
					namespaceNs, stageItem, toParam);
			i++;
		}

		SOAPElement gLN = parent.addChildElement("GLN", namespaceNs);
		gLN.addTextNode("123");
		SOAPElement iNCO1 = parent.addChildElement("INCO1", namespaceNs);
		iNCO1.addTextNode("CPT");
		SOAPElement iNCO2 = parent.addChildElement("INCO2", namespaceNs);
		iNCO2.addTextNode("(ТНП) А/П ДОМОДЕДОВО");
		if (randomGenerator("KON_PERS")) {
			SOAPElement kON_PERS = parent.addChildElement("KON_PERS", namespaceNs);
			kON_PERS.addTextNode("тел +7111111111");
		}
		SOAPElement kUNAG = parent.addChildElement("KUNAG", namespaceNs);
		kUNAG.addTextNode(toParam.KUNAG);
		SOAPElement kUNAG_NAME = parent.addChildElement("KUNAG_NAME", namespaceNs);
		kUNAG_NAME.addTextNode("ООО РИК покупателя ");
		SOAPElement kUNNR = parent.addChildElement("KUNNR", namespaceNs);
		kUNNR.addTextNode(toParam.KUNNR);
		SOAPElement kUNNR_NAME = parent.addChildElement("KUNNR_NAME", namespaceNs);
		kUNNR_NAME.addTextNode("ООО  РИК Грузополучатель" + stage);
		SOAPElement lADGR = parent.addChildElement("LADGR", namespaceNs);
		lADGR.addTextNode("123");
		SOAPElement lADGR_TEXT = parent.addChildElement("LADGR_TEXT", namespaceNs);
		lADGR_TEXT.addTextNode("123");
		SOAPElement lFART = parent.addChildElement("LFART", namespaceNs);
		lFART.addTextNode("ZMLI");
		SOAPElement lGOBE = parent.addChildElement("LGOBE", namespaceNs);
		lGOBE.addTextNode("234");
		if (randomGenerator("LPRIO")) {
			SOAPElement lPRIO = parent.addChildElement("LPRIO", namespaceNs);
			lPRIO.addTextNode("123");
		}
		if (randomGenerator("MAIL_TUT")) {
			SOAPElement mAIL_TUT = parent.addChildElement("MAIL_TUT", namespaceNs);
			mAIL_TUT.addTextNode(toParam.MAIL_TUT);
		}
		if (randomGenerator("OPER_MODE")) {
			SOAPElement oPER_MODE = parent.addChildElement("OPER_MODE", namespaceNs);
			oPER_MODE.addTextNode("Режим работы с 07 до 17");
		}
		SOAPElement pAREX = parent.addChildElement("PAREX", namespaceNs);
		pAREX.addTextNode("123");
		SOAPElement rSNUM = parent.addChildElement("RSNUM", namespaceNs);
		rSNUM.addTextNode("123");
		SOAPElement sIGN_EDI = parent.addChildElement("SIGN_EDI", namespaceNs);
		sIGN_EDI.addTextNode("123");
		SOAPElement sIGN_NOPRICE = parent.addChildElement("SIGN_NOPRICE", namespaceNs);
		sIGN_NOPRICE.addTextNode("123");
		if (randomGenerator("SPECIAL_COND")) {
			SOAPElement sPECIAL_COND = parent.addChildElement("SPECIAL_COND", namespaceNs);
			sPECIAL_COND.addTextNode("Комментарий 321");
		}
		SOAPElement sRC_LOC = parent.addChildElement("SRC_LOC", namespaceNs);
		sRC_LOC.addTextNode("SP_2420");
		SOAPElement sRC_LOC_TXT = parent.addChildElement("SRC_LOC_TXT", namespaceNs);
		sRC_LOC_TXT.addTextNode("ПО 2420 НВЖ");
		SOAPElement sTAGE_ROUTE = parent.addChildElement("STAGE_ROUTE", namespaceNs);
		sTAGE_ROUTE.addTextNode(toParam.STAGE_ROUTE + stage);
		SOAPElement tRATY_STAGE = parent.addChildElement("TRATY_STAGE", namespaceNs);
		tRATY_STAGE.addTextNode("9018");
		SOAPElement uMLGO = parent.addChildElement("UMLGO", namespaceNs);
		uMLGO.addTextNode("123");
		SOAPElement vBELN = parent.addChildElement("VBELN", namespaceNs);
		vBELN.addTextNode("7000239609");
		SOAPElement vBTYP = parent.addChildElement("VBTYP", namespaceNs);
		vBTYP.addTextNode("J");
		if (randomGenerator("VGBEL")) {
			SOAPElement vGBEL = parent.addChildElement("VGBEL", namespaceNs);
			vGBEL.addTextNode("123");
		}
		SOAPElement vKORG = parent.addChildElement("VKORG", namespaceNs);
		vKORG.addTextNode("2400");
		SOAPElement vSTEL = parent.addChildElement("VSTEL", namespaceNs);
		vSTEL.addTextNode(toParam.VSTEL);
		SOAPElement vUNTDAT = parent.addChildElement("VUNTDAT", namespaceNs);
		vUNTDAT.addTextNode("123");
		if (randomGenerator("WORKDAY")) {
			SOAPElement WORKDAY = parent.addChildElement("WORKDAY", namespaceNs);
			WORKDAY.addTextNode(toParam.workDay);
		}

		SOAPElement xABLN = parent.addChildElement("XABLN", namespaceNs);
		xABLN.addTextNode("123");
		SOAPElement xPOS = parent.addChildElement("XPOS", namespaceNs);
		xPOS.addTextNode(toParam.XPOS);
		SOAPElement yPOS = parent.addChildElement("YPOS", namespaceNs);
		yPOS.addTextNode(toParam.YPOS);
		if (randomGenerator("ZPP_TXT")) {
			SOAPElement zPP_TXT = parent.addChildElement("ZPP_TXT", namespaceNs);
			zPP_TXT.addTextNode("123");
		}
		SOAPElement zPR_TEXT = parent.addChildElement("ZPR_TEXT", namespaceNs);
		zPR_TEXT.addTextNode("123");
		SOAPElement zUONR = parent.addChildElement("ZUONR", namespaceNs);
		zUONR.addTextNode("123");
		return parent;
	}

}
