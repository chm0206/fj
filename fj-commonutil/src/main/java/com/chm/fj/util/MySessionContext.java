package com.chm.fj.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * 
 *@author riseinfo.cn
 *@date 2014-7-21
 *
 */
public class MySessionContext {
	 private static MySessionContext instance;  
	    private HashMap mymap;  
	  
	    private HttpSession session;  
	      
	    private MySessionContext() {  
	        mymap = new HashMap();  
	    }  
	  
	    public static MySessionContext getInstance() {  
	        if (instance == null) {  
	            instance = new MySessionContext();  
	        }  
	        return instance;  
	    }  
	  
	    public synchronized void AddSession(HttpSession session) {  
	        if (session != null) {  
	            mymap.put(session.getId(), session);  
	            this.session = session;  
	        }  
	    }  
	  
	    public synchronized void DelSession(HttpSession session) {  
	        if (session != null) {  
	            mymap.remove(session.getId());  
	        }  
	    }  
	  
	    public synchronized HttpSession getSession() {        
	        return this.session;  
	    }  
}
