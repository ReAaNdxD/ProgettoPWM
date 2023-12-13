package it.unirc.pwm.ht.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unirc.db.ecommerce.util.HibernateUtil;
import it.unirc.db.ecommerce.views.GridProduct;
import it.unirc.db.ecommerce.views.ViewProduct;
import it.unirc.pwm.ht.Articolo;
import it.unirc.pwm.ht.Cliente;

public class ArticoloDAOHibernateImpl implements ArticoloDAO {

	@Override
	public boolean salva(Articolo articolo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean res=false;
		try {
			transaction = session.beginTransaction();
			session.persist(articolo);
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
	public boolean isExist(Articolo articolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Articolo get(Articolo articolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean elimina(Articolo articolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GridProduct getProduct(Articolo articolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifica(Articolo articolo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Articolo result = null;

		try {
			transaction = session.beginTransaction();

			result = session.merge(articolo);

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
	public List<Articolo> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Articolo> result = null;

		try {
			transaction = session.beginTransaction();

			String queryHQL = "from articolo";
			result = session.createQuery(queryHQL, Articolo.class).list();

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
	public Vector<Articolo> getAllAvailableProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<GridProduct> getAllAvailableProducts(String queryField, int currentPage, int recordsPerPage,
			HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<GridProduct> getAllRandomProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllAvailableProductsRows(String queryField, HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<ViewProduct> getAllArticoliCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean aggiornaQuantita(Articolo articolo, int quantita) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int vincoloA1(Articolo articolo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<String> getAllBrand(String queryField, HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
