package it.unirc.db.ecommerce.views;

public class Compone {

	private int idArticolo;
	private float prezzoAcquisto;
	private int quantita;

	public Compone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compone(int idArticolo, float prezzoAcquisto, int quantita) {
		super();
		this.idArticolo = idArticolo;
		this.prezzoAcquisto = prezzoAcquisto;
		this.quantita = quantita;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public float getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(float prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public int getquantita() {
		return quantita;
	}

	public void setquantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "VectorCompone [idArticolo=" + idArticolo + ", prezzoAcquisto=" + prezzoAcquisto + ", quantita="
				+ quantita + "]";
	}

}
