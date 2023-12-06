package it.unirc.db.ecommerce.views;

public class GridProduct {

	private int idProdotto;
	private int idArticolo;
	private double prezzo;
	private int disponibilita;
	private String nomeAzienda;
	private String nomeProdotto;
	private String marca;
	private int idSottocategoria;
	private String descrizioneBreve;
	private String descrizioneDettagliata;
	private int idVenditore;

	public GridProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GridProduct(int idProdotto, int idArticolo, double prezzo, int disponibilita, String nomeAzienda,
			String nomeProdotto, String marca, int idSottocategoria, String descrizioneBreve,
			String descrizioneDettagliata, int idVenditore) {
		super();
		this.idProdotto = idProdotto;
		this.idArticolo = idArticolo;
		this.prezzo = prezzo;
		this.disponibilita = disponibilita;
		this.nomeAzienda = nomeAzienda;
		this.nomeProdotto = nomeProdotto;
		this.marca = marca;
		this.idSottocategoria = idSottocategoria;
		this.descrizioneBreve = descrizioneBreve;
		this.descrizioneDettagliata = descrizioneDettagliata;
		this.idVenditore = idVenditore;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getIdSottocategoria() {
		return idSottocategoria;
	}

	public void setIdSottocategoria(int idSottocategoria) {
		this.idSottocategoria = idSottocategoria;
	}

	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

	public String getDescrizioneDettagliata() {
		return descrizioneDettagliata;
	}

	public void setDescrizioneDettagliata(String descrizioneDettagliata) {
		this.descrizioneDettagliata = descrizioneDettagliata;
	}

	public int getIdVenditore() {
		return idVenditore;
	}

	public void setIdVenditore(int idVenditore) {
		this.idVenditore = idVenditore;
	}

}
