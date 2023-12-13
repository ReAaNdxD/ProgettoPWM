package it.unirc.pwm.ht.dao;

import java.util.HashMap;
import java.util.List;


import it.unirc.db.ecommerce.views.GridProduct;
import it.unirc.db.ecommerce.views.ViewProduct;
import it.unirc.pwm.ht.Articolo;
import it.unirc.pwm.ht.Cliente;

public interface ArticoloDAO {
	public boolean salva(Articolo articolo);
	public boolean isExist(Articolo articolo);
	public Articolo get(Articolo articolo);
	public boolean elimina(Articolo articolo);
	public GridProduct getProduct(Articolo articolo);
	public boolean modifica(Articolo articolo);
	public List<Articolo> getAll();
	public List<Articolo> getAllAvailableProducts();
	public List<GridProduct> getAllAvailableProducts(String queryField, int currentPage, int recordsPerPage,
			HashMap<String, Object> param);
	public List<GridProduct> getAllRandomProducts();
	public int getAllAvailableProductsRows(String queryField, HashMap<String, Object> param);
	public List<ViewProduct> getAllArticoliCliente(Cliente cliente);
	public boolean aggiornaQuantita(Articolo articolo, int quantita);
	public int vincoloA1(Articolo articolo);
	public List<String> getAllBrand(String queryField, HashMap<String, Object> param);
	
}
