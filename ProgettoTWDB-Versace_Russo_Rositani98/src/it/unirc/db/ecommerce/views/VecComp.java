package it.unirc.db.ecommerce.views;

public class VecComp {

	
	private int idArticolo;
	private float prezzoAcquisto;
	private int quantit�;
	
	
	
	public VecComp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VecComp(int idArticolo, float prezzoAcquisto, int quantit�) {
		super();
		this.idArticolo = idArticolo;
		this.prezzoAcquisto = prezzoAcquisto;
		this.quantit� = quantit�;
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
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
	@Override
	public String toString() {
		return "VectorCompone [idArticolo=" + idArticolo + ", prezzoAcquisto=" + prezzoAcquisto + ", quantit�="
				+ quantit� + "]";
	}
	
	
	
	
}
