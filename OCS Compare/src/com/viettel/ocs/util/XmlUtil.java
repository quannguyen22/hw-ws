package com.viettel.ocs.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.viettel.ocs.bean.XmlResultObj;

public class XmlUtil {
	private HashSet<Node> nodeSet = new HashSet<>();
	private ConcurrentHashMap<Node, HashMap<String, String>> dataMap = new ConcurrentHashMap<>();

	private String msdn;
	private String defaulMsg;
	private String soapAction;
	private String bigTagName;
	private List<String> allFindTag;

	public XmlUtil(String msdn, String defaulMsg, String soapAction, String bigTagName, List<String> allFindTag) {
		this.msdn = msdn;
		this.defaulMsg = defaulMsg;
		this.soapAction = soapAction;
		this.bigTagName = bigTagName;
		this.allFindTag = allFindTag;
	}

	public String setAuthen() {
		String message = this.defaulMsg;
		if (message == null) {
			return null;
		}
		/*
		 * if (message.contains("%USERNAME")) { message =
		 * message.replaceAll("%USERNAME", usename == null ? "" :
		 * usename.trim()); } if (message.contains("%PASSWORD")) { message =
		 * message.replaceAll("%PASSWORD", password == null ? "" :
		 * password.trim()); }
		 */
		return message;
	}

	public SOAPMessage creatSoapMessage()
			throws IOException, SOAPException, SAXException, ParserConfigurationException {
		try {

			String message = this.setAuthen();
			message = message.replace(Constant.MSDN_CODE, msdn);

			InputStream is = new ByteArrayInputStream(message.getBytes());
			MessageFactory messageFactory = null;
			if (message.contains("http://schemas.xmlsoap.org/soap/envelope")) {
				messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			} else {
				if (message.contains("http://www.w3.org/2003/05/soap-envelope")) {
					messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
				}
			}
			SOAPMessage soapMessage = messageFactory.createMessage(null, is);
			if (soapAction != null && !soapAction.trim().isEmpty()) {
				MimeHeaders mimeHeaders = soapMessage.getMimeHeaders();
				mimeHeaders.addHeader("SOAPAction", soapAction);
			}
			soapMessage.saveChanges();
			return soapMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String checkContainLink(List<String> findLinks, String fullLink) {
		for (String link : findLinks) {
			if ((fullLink.trim()).endsWith(link.trim())) {
				return link;
			}
		}
		return null;
	}

	private void addTreeNode(NodeList nodeList, Node parentNode, List<String> allFindTag, String parentName,
			String msdn) {

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String fullLink = parentName.concat("|").concat(node.getNodeName().trim());
			if (this.nodeSet.contains(node))
				continue;

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nodeList1 = node.getChildNodes();
				String nodeName = node.getNodeName().trim();
				if (nodeList1.getLength() == 0) {
					String trueLink = this.checkContainLink(allFindTag, fullLink);
					if (trueLink == null) {
						continue;
					}
					String nodeContent = node.getTextContent();
					// System.out.println(nodeName + " -- " + nodeContent);
					if (dataMap.containsKey(parentNode)) {
						dataMap.get(parentNode).put(trueLink, nodeContent);
					} else {
						HashMap<String, String> map = new HashMap<>();
						map.put(trueLink, nodeContent);
						dataMap.put(parentNode, map);
					}

				} else if (nodeList1.getLength() == 1
						&& nodeList1.item(0).getNodeType() == Node.TEXT_NODE) {

					String trueLink = this.checkContainLink(allFindTag, fullLink);
					if (trueLink == null) {
						continue;
					}

					String nodeContent = node.getTextContent();
					// System.out.println(nodeName + " -- " + nodeContent);
					if (dataMap.containsKey(parentNode)) {
						dataMap.get(parentNode).put(trueLink, nodeContent);
					} else {
						HashMap<String, String> map = new HashMap<>();
						map.put(trueLink, nodeContent);
						dataMap.put(parentNode, map);
					}

				} else {
					String newName = parentName.concat("|").concat(nodeName);
					addTreeNode(nodeList1, parentNode, allFindTag, newName, msdn);
				}
			}

			this.nodeSet.add(node);
		}
	}

	private boolean checkResultCode(String soapRespone) {
		if (soapRespone == null || soapRespone.trim().isEmpty())
			return false;
		soapRespone = soapRespone.replaceAll(" +", "");
		try {
			if (!soapRespone.contains("ResultCode>"))
				return false;
			int beIndex = soapRespone.indexOf("ResultCode>") + "ResultCode>".length();
			int fiIndex = soapRespone.indexOf("</", beIndex);
			if (fiIndex == -1)
				return false;
			String result = soapRespone.substring(beIndex, fiIndex).trim();
			if (result.equals("0")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private XmlResultObj parseXML(SOAPMessage msg) throws UnsupportedEncodingException {

		String strResult = null;
		SOAPMessage soapMessage = null;
		InputStream is = null;
		MessageFactory messageFactory = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<HashMap<String, String>> list = new ArrayList<>();
		XmlResultObj obj = new XmlResultObj();
		obj.setStatus(1);// mac dinh laf sai
		try {
			msg.writeTo(out);
			strResult = new String(out.toByteArray(), "UTF8");
			is = new ByteArrayInputStream(strResult.getBytes());

			if (strResult.contains("http://schemas.xmlsoap.org/soap/envelope")) {
				messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			} else {
				if (strResult.contains("http://www.w3.org/2003/05/soap-envelope")) {
					messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
				}
			}
			soapMessage = messageFactory.createMessage(null, is);

		} catch (SOAPException | IOException e1) {
			e1.printStackTrace();
			obj.setStatus(1);
			return obj;
		} finally {
			try {
				if (out != null)
					out.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.nodeSet.clear();
		this.dataMap.clear();

		if (!checkResultCode(strResult)) {
			obj.setStatus(1);
			return obj;
		}

		try {

			SOAPBody messageBody = soapMessage.getSOAPBody();
			NodeList bodyParam = messageBody.getElementsByTagName("*");

			if (bodyParam.getLength() == 0) {
				obj.setStatus(1);
				return obj;
			}

			if (bodyParam.getLength() >= 2) {

				for (int i = 0; i < bodyParam.getLength(); i++) {

					Node node = bodyParam.item(i);
					if (this.nodeSet.contains(node))
						continue;
					// System.err.println(node.getNodeName());
					if (this.bigTagName != null && !this.bigTagName.trim().isEmpty()
							&& !node.getNodeName().equals(this.bigTagName)) {
						continue;
					}

					if (node.getChildNodes().getLength() >= 2)
						addTreeNode(node.getChildNodes(), node, allFindTag, node.getNodeName(), msdn);
					this.nodeSet.add(node);
				}

			} else if (bodyParam.getLength() == 1) {
				obj.setStatus(1);
				return obj;
			} else {
				obj.setStatus(1);
				return obj;
			}

			list.addAll(this.dataMap.values());
			obj.setStatus(0);
			obj.setDataMap(list);
			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.nodeSet.clear();
			this.dataMap.clear();
		}
		return obj;

	}

	public XmlResultObj execute(String serviceCode) {
		WebserviceXml port = null;
		XmlResultObj obj = new XmlResultObj();
		try {
			if (serviceCode.equals(Constant.CUSTOMER_SERVICE)) {
				port = (WebserviceXml) CustomerUserFactory.getInstance().borrowObject();
			} else if (serviceCode.equals(Constant.RECHARGE_SERVICE)) {
				port = (WebserviceXml) RechargeLogFactory.getInstance().borrowObject();
			}else{
				System.err.println("Khong tim thay service can su dung");
				obj.setStatus(1);
				return obj;
			}

			SOAPMessage soapMessage = this.creatSoapMessage();
			SOAPMessage soapResponse = port.sendCommand(soapMessage);
			if (soapResponse == null) {
				obj.setStatus(1);
				return obj;
			}

			return this.parseXML(soapResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {

			if (port != null) {
				if (serviceCode.equals(Constant.CUSTOMER_SERVICE)) {
					try {
						CustomerUserFactory.getInstance().returnObject(port);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (serviceCode.equals(Constant.RECHARGE_SERVICE)) {
					RechargeLogFactory.getInstance().returnObject(port);
				}
			}

		}

	}

}
