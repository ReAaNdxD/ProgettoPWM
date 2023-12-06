package it.unirc.db.ecommerce.views;

public class ViewProductSeller {
	private String nomeProdotto;
	private String marcaProdotto;
	private double prezzo;
	private int quantita;
	private String cittaMagazzino;
	private int idArticolo;

	public ViewProductSeller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewProductSeller(String nomeProdotto, String marcaProdotto, double prezzo, int quantita, String cittaMagazzino,
			int idArticolo) {
		super();
		this.nomeProdotto = nomeProdotto;
		this.marcaProdotto = marcaProdotto;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.cittaMagazzino = cittaMagazzino;
		this.idArticolo = idArticolo;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public String getMarcaProdotto() {
		return marcaProdotto;
	}

	public void setMarcaProdotto(String marcaProdotto) {
		this.marcaProdotto = marcaProdotto;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getCittaMagazzino() {
		return cittaMagazzino;
	}

	public void setCittaMagazzino(String cittaMagazzino) {
		this.cittaMagazzino = cittaMagazzino;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cittaMagazzino == null) ? 0 : cittaMagazzino.hashCode());
		result = prime * result + idArticolo;
		result = prime * result + ((marcaProdotto == null) ? 0 : marcaProdotto.hashCode());
		result = prime * result + ((nomeProdotto == null) ? 0 : nomeProdotto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prezzo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ViewProductSeller other = (ViewProductSeller) obj;
		if (cittaMagazzino == null) {
			if (other.cittaMagazzino != null)
				return false;
		} else if (!cittaMagazzino.equals(other.cittaMagazzino))
			return false;
		if (idArticolo != other.idArticolo)
			return false;
		if (marcaProdotto == null) {
			if (other.marcaProdotto != null)
				return false;
		} else if (!marcaProdotto.equals(other.marcaProdotto))
			return false;
		if (nomeProdotto == null) {
			if (other.nomeProdotto != null)
				return false;
		} else if (!nomeProdotto.equals(other.nomeProdotto))
			return false;
		if (Double.doubleToLongBits(prezzo) != Double.doubleToLongBits(other.prezzo))
			return false;
		if (quantita != other.quantita)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "nomeProdotto=" + nomeProdotto + ", marcaProdotto=" + marcaProdotto + ", prezzo="
				+ prezzo + ", quantita=" + quantita + ", cittaMagazzino=" + cittaMagazzino + ", idArticolo="
				+ idArticolo;
	}



	
}