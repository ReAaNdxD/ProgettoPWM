package it.unirc.pwm.ht.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unirc.db.ecommerce.beans.MetodoSpedizione;
import it.unirc.db.ecommerce.beans.StatoOrdine;
import it.unirc.db.ecommerce.util.HibernateUtil;
import it.unirc.db.ecommerce.views.Order;
import it.unirc.db.ecommerce.views.ViewOrderProducts;
import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.Ordine;

public class OrdineDAOHibernateImpl implements OrdineDAO {

	@Override
	public boolean salva(Ordine ordine) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean res=false;
		try {
			transaction = session.beginTransaction();
			session.persist(ordine);
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
	public Ordine get(Ordine or) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean elimina(Ordine or) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifica(Ordine or) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Ordine result = null;

		try {
			transaction = session.beginTransaction();

			result = session.merge(or);

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
	public List<Ordine> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Ordine> result = null;

		try {
			transaction = session.beginTransaction();

			String queryHQL = "from ordine";
			result = session.createQuery(queryHQL, Ordine.class).list();

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
	public boolean modificaMetodoSpedizione(Ordine o, MetodoSpedizione m) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificaStato(Ordine or, StatoOrdine s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double calcoloCosto(Ordine ordine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Ordine> ordiniDaPagare(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine ordineByCarrello(int idCarrello) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> getAllOrdiniDaSpedire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> failedOrders(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine getOrder(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> orders(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrdini(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewOrderProducts> prodottiOrdine(Ordine ordine) {
		// TODO Auto-generated method stub
		return null;
	}

}
