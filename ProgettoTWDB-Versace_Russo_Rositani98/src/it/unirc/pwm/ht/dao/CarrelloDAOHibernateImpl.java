package it.unirc.pwm.ht.dao;

import java.util.List;

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
			session.persist(carrello);
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Carrello result = null;

		try {
			transaction = session.beginTransaction();

			result = session.merge(carrello);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			result = null;
		} finally {
			session.close();
		}
		return result != null;
	}

	@Override
	public boolean modificaStato(Carrello carrello) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Carrello> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Carrello> result = null;

		try {
			transaction = session.beginTransaction();

			String queryHQL = "from Carrello";
			result = session.createQuery(queryHQL, Carrello.class).list();

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			result = null;
		} catch (Exception e) {
			result = null;
		} finally {
			session.close();
		}

		return result;
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
