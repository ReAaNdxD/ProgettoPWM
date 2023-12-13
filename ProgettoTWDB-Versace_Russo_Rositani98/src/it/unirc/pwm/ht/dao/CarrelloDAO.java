package it.unirc.pwm.ht.dao;

import java.util.List;

import it.unirc.pwm.ht.Carrello;
import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.Ordine;

public interface CarrelloDAO {
	public boolean salva(Carrello carrello);
	public Carrello get(Carrello carrello);
	public Carrello getCarrelloByCliente(Cliente cliente);
	public Carrello getById(int carrello);
	public boolean elimina(Carrello carrello);
	public boolean modifica(Carrello carrello);
	public boolean modificaStato(Carrello carrello);
	public List<Carrello> getAll();
	public boolean isCartFull(Ordine ordine);
	public Carrello getAttivo(int cliente);
	public Carrello getUltimoAcquisto(int cliente);
}
