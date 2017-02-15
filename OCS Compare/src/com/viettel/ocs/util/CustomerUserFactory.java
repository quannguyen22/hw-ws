package com.viettel.ocs.util;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hungnd40
 */


import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;



public class CustomerUserFactory
        extends BasePooledObjectFactory<WebserviceXml> {

    private static GenericObjectPool<WebserviceXml> wsFactory = null;
    public static synchronized  GenericObjectPool<WebserviceXml> getInstance() {
        if (wsFactory == null) {
            wsFactory = new GenericObjectPool<WebserviceXml>(new CustomerUserFactory());
            wsFactory.setMaxTotal(500);
            wsFactory.setMinIdle(1);   
        }
        return wsFactory;
    }

    private WebserviceXml createConnect() {
    	
    	
    	String link = null;
        try {
        	
        	Properties prop = new Properties();
			InputStream input = null;
			try {
				input = this.getClass().getResourceAsStream("/config.properties");
				prop.load(input);
				link = prop.getProperty("query.accumulation.usage");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
						prop.clone();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
        	
         
            WebserviceXml port = new WebserviceXml(link);
            return port;
        } catch (Exception exx) {
             exx.printStackTrace();
            return null;
        }

    }

    @Override
    public WebserviceXml create() {
        return createConnect();
    }

    /**
     * When an object is returned to the pool, clear the buffer.
     */
    @Override
    public void passivateObject(PooledObject<WebserviceXml> pooledObject) {
//        pooledObject.getObject().setLength(0);
    }
    @Override
    public PooledObject<WebserviceXml> wrap(WebserviceXml t) {
        return new DefaultPooledObject<WebserviceXml>(t);
    }
}
