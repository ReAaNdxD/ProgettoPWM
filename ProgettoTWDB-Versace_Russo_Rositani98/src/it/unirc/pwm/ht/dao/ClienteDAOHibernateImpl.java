package it.unirc.pwm.ht.dao;

import java.sql.Date;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unirc.db.ecommerce.util.HibernateUtil;
import it.unirc.db.ecommerce.views.Customer;
import it.unirc.pwm.ht.Carrello;
import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.Ordine;

public class ClienteDAOHibernateImpl implements ClienteDAO {

	protected ClienteDAOHibernateImpl(){
	}
	
	@Override
	public boolean salva(Cliente cliente) {
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
	public Cliente get(Cliente customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getByNotId(Cliente customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getIdByEmail(Cliente customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getIdByCarrello(Carrello carrello) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean elimina(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifica(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Cliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Cliente> getAllClientiNonPrime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean disdiciPrime1(Cliente cl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assegnaCarrello(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<Cliente> cercaClienteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Cliente> cercaClienteByNome_Email(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Customer> clientiMaxAcquisti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente ordineEffettuatoDa(Ordine ordine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkEmailForUpdate(String email, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date disdiciPrime(Date dataInizio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
