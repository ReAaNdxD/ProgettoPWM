package it.unirc.pwm.ht.dao;

public class ArticoloDAOFactory {
private static ArticoloDAO dao = null;
	
	public static synchronized ArticoloDAO getDAO() {
	    if ( dao == null ) {
	    	dao = new ArticoloDAOHibernateImpl();
	    }
	    return dao;
	  }

}
