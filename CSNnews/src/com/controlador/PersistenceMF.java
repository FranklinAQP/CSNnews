package com.controlador;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PersistenceMF {
	private static final PersistenceManagerFactory pmfInstance =
	        JDOHelper.getPersistenceManagerFactory("transactions-optional");

	    private PersistenceMF() {}
	    
	    public static PersistenceManagerFactory get() {
	        return pmfInstance;
	    }
}
