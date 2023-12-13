package it.unirc.pwm.ht.dao;

import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unirc.db.ecommerce.util.HibernateUtil;
import it.unirc.pwm.ht.Carrello;
import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.Ordine;

public class CarrelloDAOHibernateImpl implements CarrelloDAO {

	@Override
	public boolean salva(Carrello carrello) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean res=false;
		try {
			transaction = session.beginTransaction();
			session.persist(cliente);
			transaction.commit();
			res = true;
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			if (session!=null) //spesso omesso
				session.close();
		}
		return res;
	}

	@Override
	public Carrello get(Carrello carrello) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carrello getCarrelloByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carrello getById(int carrello) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean elimina(Carrello carrello) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifica(Carrello carrello) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificaStato(Carrello carrello) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Carrello> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCartFull(Ordine ordine) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Carrello getAttivo(int cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carrello getUltimoAcquisto(int cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
