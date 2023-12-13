package it.unirc.pwm.ht.dao;

import java.util.List;

import it.unirc.db.ecommerce.beans.MetodoSpedizione;
import it.unirc.db.ecommerce.beans.StatoOrdine;
import it.unirc.db.ecommerce.views.Order;
import it.unirc.db.ecommerce.views.ViewOrderProducts;
import it.unirc.pwm.ht.Cliente;
import it.unirc.pwm.ht.Ordine;

public interface OrdineDAO {
	public boolean salva(Ordine ordine);
	public Ordine get(Ordine or);
	public boolean elimina(Ordine or);
	public boolean modifica(Ordine or);
	public List<Ordine> getAll();
	public boolean modificaMetodoSpedizione(Ordine o, MetodoSpedizione m);
	public boolean modificaStato(Ordine or, StatoOrdine s);
	public double calcoloCosto(Ordine ordine);
	public List<Ordine> ordiniDaPagare(Cliente cliente);
	public Ordine ordineByCarrello(int idCarrello);
	public List<Ordine> getAllOrdiniDaSpedire();
	public List<Order> failedOrders(Cliente cliente);
	public Ordine getOrder(Cliente cliente);
	public List<Order> orders(Cliente cliente);
	public List<Order> getAllOrdini(Cliente cliente);
	public List<ViewOrderProducts> prodottiOrdine(Ordine ordine);
	
	
	

}
