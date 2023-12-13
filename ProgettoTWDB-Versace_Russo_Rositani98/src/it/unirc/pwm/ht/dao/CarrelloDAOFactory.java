package it.unirc.pwm.ht.dao;

public class CarrelloDAOFactory {

private static CarrelloDAO dao = null;
	
	public static synchronized CarrelloDAO getDAO() {
	    if ( dao == null ) {
	    	dao = new CarrelloDAOHibernateImpl();
	    }
	    return dao;
	  }
}
