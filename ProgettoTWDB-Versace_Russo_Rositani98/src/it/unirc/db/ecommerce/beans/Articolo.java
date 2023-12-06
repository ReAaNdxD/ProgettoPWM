package it.unirc.db.ecommerce.beans;

public class Articolo {

	private int idArticolo;
	private float prezzo;
	private int quantita;
	private int idProdotto;
	private int idVenditore;

	public Articolo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Articolo(int idArticolo, float prezzo) {
		super();
		this.idArticolo = idArticolo;
		this.prezzo = prezzo;
	}

	public Articolo(int idArticolo) {
		super();
		this.idArticolo = idArticolo;
	}

	public Articolo(int idArticolo, float prezzo, int quantita, int idProdotto, int idVenditore) {
		super();
		this.idArticolo = idArticolo;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.idProdotto = idProdotto;
		this.idVenditore = idVenditore;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getIdVenditore() {
		return idVenditore;
	}

	public void setIdVenditore(int idVenditore) {
		this.idVenditore = idVenditore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArticolo;
		result = prime * result + idProdotto;
		result = prime * result + idVenditore;
		result = prime * result + Float.floatToIntBits(prezzo);
		result = prime * result + quantita;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		if (idArticolo != other.idArticolo)
			return false;
		if (idProdotto != other.idProdotto)
			return false;
		if (idVenditore != other.idVenditore)
			return false;
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))
			return false;
		if (quantita != other.quantita)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id="+idArticolo + ", prrezzo= " + prezzo + ", q.tà=" + quantita + ", prod= "
				+ idProdotto + ", vend=" + idVenditore;
	}

}
