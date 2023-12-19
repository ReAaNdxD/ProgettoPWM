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
	public List<GridProduct> getAllRandomProducts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<GridProduct> result = null;

		try {
			transaction = session.beginTransaction();

			String queryHQL = "SELECT  *\r\n" + 
					"FROM   (\r\n" + 
					"    SELECT  a.idprodotto,\r\n" + 
					"            a.idarticolo,\r\n" + 
					"            a.prezzo,\r\n" + 
					"            a.quantita AS disponibilit�,\r\n" + 
					"            V.nomeAzienda, \r\n" + 
					"            p.nome, \r\n" + 
					"            p.marca,\r\n" + 
					"            p.idsottocategoria,\r\n" + 
					"            p.descrizionebreve,\r\n" + 
					"            p.descrizionedettagliata,\r\n" + 
					"            v.idvenditore \r\n" + 
					"FROM articolo A JOIN prodotto P ON A.idprodotto=P.idprodotto\r\n" + 
					"                JOIN sottocategoria S ON p.idsottocategoria=s.idsottocategoria\r\n" + 
					"                JOIN categoria C ON s.idcategoria=c.idcategoria\r\n" + 
					"                JOIN venditore V ON a.idvenditore=v.idvenditore\r\n" + 
					"    ORDER BY DBMS_RANDOM.RANDOM) a\r\n" + 
					"WHERE  rownum < 21";
			result = session.createQuery(queryHQL, GridProduct.class).list();

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
	public int getAllAvailableProductsRows(String queryField, HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ViewProduct> getAllArticoliCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<ViewProduct> result = null;

		try {
			transaction = session.beginTransaction();

			String queryHQL = "SELECT A.idArticolo, p.nome, CO.quantita, CO.prezzoAcquisto, V.idVenditore, V.nomeAzienda, C.idCarrello, A.quantita as disponibilit�\r\n"
					+ "FROM carrello C JOIN cliente CL ON C.idCliente=CL.idCliente\r\n"
					+ "				JOIN compone CO ON C.idCarrello=CO.idCarrello\r\n"
					+ "                JOIN articolo A ON CO.idArticolo=A.idArticolo\r\n"
					+ "                JOIN prodotto P ON A.idProdotto=P.idProdotto\r\n"
					+ "                JOIN venditore V ON A.idVenditore=V.idVenditore\r\n"
					+ "WHERE CL.idCliente=? AND C.attivo=1";
			result = session.createQuery(queryHQL, ViewProduct.class).list();

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
