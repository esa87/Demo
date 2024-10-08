package go.soup;

import go.GoodServiceParameters;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class DeliveryItemSoupModel {
	public SOAPElement deliveryItemSoapModel(SOAPElement parent, String namespaceNs, String stage,
			GoodServiceParameters toParam) throws SOAPException {
		SOAPElement aRKTX = parent.addChildElement("ARKTX", namespaceNs);
		aRKTX.addTextNode("Масло мот Л Аванг Ультра PLUS SAE 10W-40 " + stage);
		SOAPElement bISMT = parent.addChildElement("BISMT", namespaceNs);
		bISMT.addTextNode("123456");
		SOAPElement bRGEW = parent.addChildElement("BRGEW", namespaceNs);
		bRGEW.addTextNode("217.880");
		SOAPElement cHARG = parent.addChildElement("CHARG", namespaceNs);
		cHARG.addTextNode("123");
		SOAPElement dENSITY = parent.addChildElement("DENSITY", namespaceNs);
		dENSITY.addTextNode("123");
		SOAPElement gEWEI = parent.addChildElement("GEWEI", namespaceNs);
		gEWEI.addTextNode("KG");
		SOAPElement kDMAT = parent.addChildElement("KDMAT", namespaceNs);
		kDMAT.addTextNode("KG");
		SOAPElement kMEIN = parent.addChildElement("KMEIN", namespaceNs);
		kMEIN.addTextNode("KG");
		SOAPElement kPEIN = parent.addChildElement("KPEIN", namespaceNs);
		kPEIN.addTextNode("KG");
		SOAPElement kZWI4 = parent.addChildElement("KZWI4", namespaceNs);
		kZWI4.addTextNode("KG");
		SOAPElement kZWI5 = parent.addChildElement("KZWI5", namespaceNs);
		kZWI5.addTextNode("KG");
		SOAPElement lFIMG = parent.addChildElement("LFIMG", namespaceNs);
		lFIMG.addTextNode("123");
		SOAPElement lGMNG = parent.addChildElement("LGMNG", namespaceNs);
		lGMNG.addTextNode("217.650");
		SOAPElement lGORT = parent.addChildElement("LGORT", namespaceNs);
		lGORT.addTextNode("0001");
		SOAPElement mATKL = parent.addChildElement("MATKL", namespaceNs);
		mATKL.addTextNode("3127");
		SOAPElement mATNR = parent.addChildElement("MATNR", namespaceNs);
		mATNR.addTextNode(toParam.MATNR + stage);
		SOAPElement mATWA = parent.addChildElement("MATWA", namespaceNs);
		mATWA.addTextNode("123");
		SOAPElement mEINS = parent.addChildElement("MEINS", namespaceNs);
		mEINS.addTextNode("KG");
		SOAPElement nETPR = parent.addChildElement("NETPR", namespaceNs);
		nETPR.addTextNode("KG");
		SOAPElement nETWR = parent.addChildElement("NETWR", namespaceNs);
		nETWR.addTextNode("KG");
		SOAPElement nTGEW = parent.addChildElement("NTGEW", namespaceNs);
		nTGEW.addTextNode(toParam.NTGEW);
		SOAPElement pOSNR = parent.addChildElement("POSNR", namespaceNs);
		pOSNR.addTextNode("000020");
		SOAPElement tEMPB = parent.addChildElement("TEMPB", namespaceNs);
		tEMPB.addTextNode("123");
		SOAPElement vISCOSITY = parent.addChildElement("VISCOSITY", namespaceNs);
		vISCOSITY.addTextNode("123");
		SOAPElement vRKME = parent.addChildElement("VRKME", namespaceNs);
		vRKME.addTextNode("KG");
		SOAPElement wAERK = parent.addChildElement("WAERK", namespaceNs);
		wAERK.addTextNode("123");
		SOAPElement wERKS = parent.addChildElement("WERKS", namespaceNs);
		wERKS.addTextNode("2420");
		SOAPElement zBP_TEXT = parent.addChildElement("ZBP_TEXT", namespaceNs);
		zBP_TEXT.addTextNode(stage + "_ZBP__322_" + stage + "_123");
		SOAPElement zMT_TEXT = parent.addChildElement("ZMT_TEXT", namespaceNs);
		zMT_TEXT.addTextNode(
				"Дополнительный текст для проверки вместимости выделленого  под комментарий поля ZMT_TEXT_123 "
						+ stage);
		return parent;
	}
}
