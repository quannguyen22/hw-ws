package com.viettel.ocs.util;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.pool2.impl.DefaultPooledObject;

public class WebserviceXml {
	
	

	//private Long id;
	private String host ;
	private SOAPConnectionFactory soapConnectionFactory =null;
	private SOAPConnection soapConnection =null;
	private URL endpoint = null;
	

	public WebserviceXml(String host) {
		this.host  = host;
		this.connect();
	}

	
	public void connect() {
		try {

			if (this.host.startsWith("http://"))
				this.host = this.host.substring("http://".length());
			String sericeName = null;
			String linkHTTP = null;

			int lastindex = this.host.indexOf("/");
			if (lastindex <= 0) {
				System.out.println("Không thể xác thực thông tin Server");
				return;
			}
			linkHTTP = this.host.substring(0, lastindex);
			sericeName = this.host.substring(lastindex);

			if (!linkHTTP.startsWith("http://"))
				linkHTTP = "http://" + linkHTTP;
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
			endpoint = new URL(new URL(linkHTTP), sericeName, new URLStreamHandler() {

				@Override
				protected URLConnection openConnection(URL u) throws IOException {
					URL target = new URL(u.toString());
					URLConnection connection = target.openConnection();
					// Connection settings
					connection.setConnectTimeout(20000); // 20 sec
					connection.setReadTimeout(60000); // 60 sec
					return (connection);
				}
			});
			endpoint.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

	}
	
	public void disconnect(){
		try {
			soapConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public SOAPMessage sendCommand (SOAPMessage soapMessage){
		try {
			SOAPMessage soapResponse = soapConnection.call(soapMessage, endpoint);
			return soapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
	
		return true;
	}
	
	
    
}
