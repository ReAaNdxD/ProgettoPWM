package it.unirc.db.ecommerce.views;

public class ViewProduct {

	private String nomeProdotto;
	private int quantita;
	private double prezzoAcquisto;
	private String nomeAzienda;
	private int disponibilita;
	private int idArticolo;
	private int idVenditore;
	private int idCarrello;
	
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}
	public String getNomeAzienda() {
		return nomeAzienda;
	}
	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
	public int getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	public int getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}
	public int getIdVenditore() {
		return idVenditore;
	}
	public void setIdVenditore(int idVenditore) {
		this.idVenditore = idVenditore;
	}
	public int getIdCarrello() {
		return idCarrello;
	}
	public void setIdCarrello(int idCarrello) {
		this.idCarrello = idCarrello;
	}

	

}
