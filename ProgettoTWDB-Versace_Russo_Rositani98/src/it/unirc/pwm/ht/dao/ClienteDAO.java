package it.unirc.pwm.ht.dao;

import java.sql.Date;
import java.util.List;

import it.unirc.db.ecommerce.views.Customer;
import it.unirc.pwm.ht.Carrello;
import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.Ordine;

public interface ClienteDAO {
	public boolean salva(Cliente cliente);
	public Cliente get(Cliente customer);
	public Cliente getByNotId(Cliente customer);
	public Cliente getIdByEmail(Cliente customer);
	public Cliente getIdByCarrello(Carrello carrello);
	public boolean elimina(Cliente cliente);
	public boolean modifica(Cliente cliente);
	public List<Cliente> getAll();
	public List<Cliente> getAllClientiNonPrime();
	public boolean disdiciPrime1(Cliente cl);
	public boolean assegnaCarrello(Cliente cliente);
	public List<Cliente> cercaClienteById(String id);
	public List<Cliente> cercaClienteByNome_Email(String string);
	public List<Customer> clientiMaxAcquisti();
	public Cliente ordineEffettuatoDa(Ordine ordine);
	public boolean checkEmail(String email);
	public boolean checkEmailForUpdate(String email, int id);
	public Date disdiciPrime(Date dataInizio);
	public Integer login(String email, String password);
}
