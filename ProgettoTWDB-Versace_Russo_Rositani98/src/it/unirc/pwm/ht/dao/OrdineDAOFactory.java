package it.unirc.pwm.ht.dao;

public class OrdineDAOFactory {
private static OrdineDAO dao = null;
	
	public static synchronized OrdineDAO getDAO() {
	    if ( dao == null ) {
	    	dao = new OrdineDAOHibernateImpl();
	    }
	    return dao;
	  }

}
