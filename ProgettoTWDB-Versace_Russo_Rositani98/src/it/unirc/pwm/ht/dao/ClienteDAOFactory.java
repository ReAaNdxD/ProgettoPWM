package it.unirc.pwm.ht.dao;

import it.unirc.pwm.ht.dao.ClienteDAO;


public class ClienteDAOFactory {
	private static ClienteDAO dao = null;
	
	public static synchronized ClienteDAO getDAO() {
	    if ( dao == null ) {
	    	dao = new ClienteDAOHibernateImpl();
	    }
	    return dao;
	  }

}
