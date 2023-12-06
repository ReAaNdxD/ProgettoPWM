package it.unirc.db.ecommerce.views;

public class VecComp {

	
	private int idArticolo;
	private float prezzoAcquisto;
	private int quantità;
	
	
	
	public VecComp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VecComp(int idArticolo, float prezzoAcquisto, int quantità) {
		super();
		this.idArticolo = idArticolo;
		this.prezzoAcquisto = prezzoAcquisto;
		this.quantità = quantità;
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
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	@Override
	public String toString() {
		return "VectorCompone [idArticolo=" + idArticolo + ", prezzoAcquisto=" + prezzoAcquisto + ", quantità="
				+ quantità + "]";
	}
	
	
	
	
}
