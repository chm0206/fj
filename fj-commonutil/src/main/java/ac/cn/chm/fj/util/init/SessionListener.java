package com.chm.fj.util.init;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 *@author riseinfo.cn
 *@date 2014-7-21
 *
 */
public class SessionListener implements HttpSessionListener {  
    @SuppressWarnings("rawtypes")
	public static Map userMap = new HashMap();  
    private   MySessionContext myc=MySessionContext.getInstance();  
  
        public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
        myc.AddSession(httpSessionEvent.getSession());  
    }  
  
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
        HttpSession session = httpSessionEvent.getSession();  
        myc.DelSession(session);  
    }  
}  
