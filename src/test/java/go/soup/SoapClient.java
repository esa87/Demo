package go.soup;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import go.GoodServiceParameters;
import jakarta.xml.soap.*;

public class SoapClient {

	private String namespaceURItem = null;
	private String namespaceURIns = null;
	private String soapUrl = null;
	private String serviceName = null;
	private String namespaceTem = null;
	private String namespaceNs = null;
	private String soapAction = null;
	GoodServiceParameters toParam;

	public SoapClient(GoodServiceParameters toParametrs) {
		toParam = toParametrs;
		setSoapParams();
		callSoapWebService(soapUrl, soapAction);
	}

	public SoapClient(GoodServiceParameters toParametrs, String test) throws Exception {
		toParam = toParametrs;
		setSoapParams();
		createSOAPRequest(soapAction);
	}

	private void setSoapParams() {

		namespaceURItem = "http://tempuri.org/";
		namespaceURIns = "http://schemas.datacontract.org/2004/07/";
		soapUrl = "http://192.168.170.21:8484/Service/GoodsService.svc";
		serviceName = "Load";
		namespaceTem = "tem"; //
		namespaceNs = "ns";
		soapAction = "http://tempuri.org/IGoodsService/Load";
	}

	private void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
		SOAPPart soapPart = soapMessage.getSOAPPart();
		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem;
		SOAPElement soapLoadElem;
		SOAPElement soapPackagElement;
		SOAPElement Document;
		SOAPElement soapDT_TrOrder_OutDelivElement_GuidMsgOutb;
		SOAPElement soapDT_TrOrder_OutDelivElement_SystemReceiver;
		SOAPElement soapDT_TrOrder_OutDelivElement_SystemSender;
		soapBodyElem = soapBody.addChildElement(serviceName, "", namespaceURItem);
		soapLoadElem = soapBodyElem.addChildElement("packages");
		soapLoadElem.setAttribute("xmlns:ns", "http://schemas.datacontract.org/2004/07/");
		soapLoadElem.setAttribute("xmlns:i", "http://www.w3.org/2001/XMLSchema-instance");
		soapPackagElement = soapLoadElem.addChildElement("DT_TrOrder_OutDeliv", namespaceNs);
		Document = soapPackagElement.addChildElement("Document", namespaceNs);
		soapDT_TrOrder_OutDelivElement_GuidMsgOutb = soapPackagElement.addChildElement("GuidMsgOutb", namespaceNs);
		soapDT_TrOrder_OutDelivElement_GuidMsgOutb.addTextNode(toParam.guidMsgOutb);
		soapDT_TrOrder_OutDelivElement_SystemReceiver = soapPackagElement.addChildElement("SystemReceiver",
				namespaceNs);
		soapDT_TrOrder_OutDelivElement_SystemReceiver.addTextNode("123");
		soapDT_TrOrder_OutDelivElement_SystemSender = soapPackagElement.addChildElement("SystemSender", namespaceNs);
		soapDT_TrOrder_OutDelivElement_SystemSender.addTextNode("123");
		SOAPElement DT_TrOrder_OutDelivDocument = Document.addChildElement("DT_TrOrder_OutDelivDocument", namespaceNs);
		SOAPElement COMMENT_TXT = DT_TrOrder_OutDelivDocument.addChildElement("COMMENT_TXT", namespaceNs);
		COMMENT_TXT.addTextNode("123");
		SOAPElement CONT_NUM = DT_TrOrder_OutDelivDocument.addChildElement("CONT_NUM", namespaceNs);
		CONT_NUM.addTextNode("123");
		SOAPElement COST = DT_TrOrder_OutDelivDocument.addChildElement("COST", namespaceNs);
		COST.addTextNode(toParam.COST);
		SOAPElement DATE_ARR = DT_TrOrder_OutDelivDocument.addChildElement("DATE_ARR", namespaceNs);
		DATE_ARR.addTextNode("123");
		SOAPElement dATE_LICENSE = DT_TrOrder_OutDelivDocument.addChildElement("DATE_LICENSE", namespaceNs);
		dATE_LICENSE.addTextNode("123");
		SOAPElement dATE_SEND = DT_TrOrder_OutDelivDocument.addChildElement("DATE_SEND", namespaceNs);
		dATE_SEND.addTextNode(toParam.DATE_SEND);
		SOAPElement dATE_SEND_BY = DT_TrOrder_OutDelivDocument.addChildElement("DATE_SEND_BY", namespaceNs);
		dATE_SEND_BY.addTextNode(toParam.DATE_SEND_BY);
		SOAPElement dATE_SEND_FR = DT_TrOrder_OutDelivDocument.addChildElement("DATE_SEND_FR", namespaceNs);
		dATE_SEND_FR.addTextNode(toParam.DATE_SEND_FR);
		SOAPElement dATE_WU = DT_TrOrder_OutDelivDocument.addChildElement("DATE_WU", namespaceNs);
		dATE_WU.addTextNode("123");
		SOAPElement dRIVER = DT_TrOrder_OutDelivDocument.addChildElement("DRIVER", namespaceNs);
		dRIVER.addTextNode("123");
		SOAPElement dRIVER_LICENSE = DT_TrOrder_OutDelivDocument.addChildElement("DRIVER_LICENSE", namespaceNs);
		dRIVER_LICENSE.addTextNode("123");
		SOAPElement dRIVER_PHONE = DT_TrOrder_OutDelivDocument.addChildElement("DRIVER_PHONE", namespaceNs);
		dRIVER_PHONE.addTextNode("123");
		SOAPElement delivery = DT_TrOrder_OutDelivDocument.addChildElement("Delivery", namespaceNs);

		List<SOAPElement> DT_TrOrder_OutDelivDocumentDelivery = new ArrayList<>();
		Integer i = 0;
		String stageString;
		while (i < toParam.Count_DT_TrOrder_OutDelivDocumentDelivery) {
			DT_TrOrder_OutDelivDocumentDelivery
					.add(delivery.addChildElement("DT_TrOrder_OutDelivDocumentDelivery", namespaceNs));
			if (toParam.changeSatge_Count_DT_TrOrder_OutDelivDocumentDelivery) {
				stageString = i.toString();
			} else
				stageString = "0";
			new OutDelivDocumentSoupModel().outDelivDocumentSoupModel(DT_TrOrder_OutDelivDocumentDelivery.get(i),
					namespaceNs, stageString, toParam);
			i++;
		}

		SOAPElement lICENSE = DT_TrOrder_OutDelivDocument.addChildElement("LICENSE", namespaceNs);
		lICENSE.addTextNode("123");
		SOAPElement nET_DURATION = DT_TrOrder_OutDelivDocument.addChildElement("NET_DURATION", namespaceNs);
		nET_DURATION.addTextNode("180");
		SOAPElement sEND_TO_SYSTEM = DT_TrOrder_OutDelivDocument.addChildElement("SEND_TO_SYSTEM", namespaceNs);
		sEND_TO_SYSTEM.addTextNode("123");
		SOAPElement sIGN_DEL = DT_TrOrder_OutDelivDocument.addChildElement("SIGN_DEL", namespaceNs);
		sIGN_DEL.addTextNode(toParam.SIGN_DEL);
		SOAPElement sTATUS = DT_TrOrder_OutDelivDocument.addChildElement("STATUS", namespaceNs);
		sTATUS.addTextNode(toParam.STATUS);
		SOAPElement sTATUS_RECORD = DT_TrOrder_OutDelivDocument.addChildElement("STATUS_RECORD", namespaceNs);
		sTATUS_RECORD.addTextNode("123");
		SOAPElement tDLNR = DT_TrOrder_OutDelivDocument.addChildElement("TDLNR", namespaceNs);
		tDLNR.addTextNode(toParam.TDLNR);
		SOAPElement tDLNR_NAME = DT_TrOrder_OutDelivDocument.addChildElement("TDLNR_NAME", namespaceNs);
		tDLNR_NAME.addTextNode(toParam.TDLNR_NAME);
		SOAPElement tIME_ARR = DT_TrOrder_OutDelivDocument.addChildElement("TIME_ARR", namespaceNs);
		tIME_ARR.addTextNode("123");
		SOAPElement tIME_SEND = DT_TrOrder_OutDelivDocument.addChildElement("TIME_SEND", namespaceNs);
		tIME_SEND.addTextNode("000000");
		SOAPElement tIME_SEND_BY = DT_TrOrder_OutDelivDocument.addChildElement("TIME_SEND_BY", namespaceNs);
		tIME_SEND_BY.addTextNode(toParam.TIME_SEND_BY);
		SOAPElement tIME_SEND_FR = DT_TrOrder_OutDelivDocument.addChildElement("TIME_SEND_FR", namespaceNs);
		tIME_SEND_FR.addTextNode("000000");
		SOAPElement tOR_ID = DT_TrOrder_OutDelivDocument.addChildElement("TOR_ID", namespaceNs);
		tOR_ID.addTextNode(toParam.tor_id);
		SOAPElement tOR_TYPE = DT_TrOrder_OutDelivDocument.addChildElement("TOR_TYPE", namespaceNs);
		tOR_TYPE.addTextNode("123");
		SOAPElement tRATY = DT_TrOrder_OutDelivDocument.addChildElement("TRATY", namespaceNs);
		tRATY.addTextNode("123");
		SOAPElement tRATY_TXT = DT_TrOrder_OutDelivDocument.addChildElement("TRATY_TXT", namespaceNs);
		tRATY_TXT.addTextNode("123");
		SOAPElement vEHICLE_CONTAINER = DT_TrOrder_OutDelivDocument.addChildElement("VEHICLE_CONTAINER", namespaceNs);
		vEHICLE_CONTAINER.addTextNode("123");
		SOAPElement vEHICLE_NUMBER = DT_TrOrder_OutDelivDocument.addChildElement("VEHICLE_NUMBER", namespaceNs);
		vEHICLE_NUMBER.addTextNode("123");
		SOAPElement vSBED = DT_TrOrder_OutDelivDocument.addChildElement("VSBED", namespaceNs);
		vSBED.addTextNode("123");
		SOAPElement vSBED_TXT = DT_TrOrder_OutDelivDocument.addChildElement("VSBED_TXT", namespaceNs);
		vSBED_TXT.addTextNode("123");
		SOAPElement vSTEL_TOR = DT_TrOrder_OutDelivDocument.addChildElement("VSTEL_TOR", namespaceNs);
		vSTEL_TOR.addTextNode(toParam.VSTEL_TOR);
		SOAPElement vSTEL_TOR_TEXT = DT_TrOrder_OutDelivDocument.addChildElement("VSTEL_TOR_TEXT", namespaceNs);
		vSTEL_TOR_TEXT.addTextNode("Тест 1");

	}

	private SOAPMessage createSOAPRequest(String soapAction) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		createSoapEnvelope(soapMessage);
		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		soapMessage.saveChanges();
		// Печать XML текста запроса
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");
		return soapMessage;
	}

	boolean useXSLT = true;

	private void callSoapWebService(String destination, String soapService) {
		SOAPConnectionFactory soapFactory = null;
		SOAPConnection soapConnect = null;
		SOAPMessage soapRequest = null;
		SOAPMessage soapResponse = null;
		try {
			// Создание SOAP Connection
			soapFactory = SOAPConnectionFactory.newInstance();
			soapConnect = soapFactory.createConnection();

			// Создание SOAP Message для отправки
			soapRequest = createSOAPRequest(soapAction);
			// Получение SOAP Message
			soapResponse = soapConnect.call(soapRequest, destination);

			// Печать SOAP Response
			if (!useXSLT) {
				System.out.println("Response SOAP Message:");
				soapResponse.writeTo(System.out);
				System.out.println();
			} else {
				// ------printSOAPMessage (soapResponse);
				System.out.println("Response SOAP Message:");
				soapResponse.writeTo(System.out);
				System.out.println();
			}

			soapConnect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printSOAPMessage(SOAPMessage soapResponse) {
		TransformerFactory transformerFactory;
		Transformer transformer;
		try {
			// Создание XSLT-процессора
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			// Получение содержимого ответа
			Source content;
			content = soapResponse.getSOAPPart().getContent();
			// Определение выходного потока
			StreamResult result = new StreamResult(System.out);
			transformer.transform(content, result);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
