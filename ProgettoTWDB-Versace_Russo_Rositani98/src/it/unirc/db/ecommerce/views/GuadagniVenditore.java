package it.unirc.db.ecommerce.views;


public class GuadagniVenditore {
	
	private int idVenditore;
	private String nomeAzienda;
	private String nomeProdotto;
	private int prodottiVenduti;
	private double guadagnoTot;
	public GuadagniVenditore() {
		super();
	}
	public GuadagniVenditore(int idVenditore, String nomeAzienda, String nomeProdotto, int prodottiVenduti,
			double guadagnoTot) {
		super();
		this.idVenditore = idVenditore;
		this.nomeAzienda = nomeAzienda;
		this.nomeProdotto = nomeProdotto;
		this.prodottiVenduti = prodottiVenduti;
		this.guadagnoTot = guadagnoTot;
	}
	public int getIdVenditore() {
		return idVenditore;
	}
	public void setIdVenditore(int idVenditore) {
		this.idVenditore = idVenditore;
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
	public int getProdottiVenduti() {
		return prodottiVenduti;
	}
	public void setProdottiVenduti(int prodottiVenduti) {
		this.prodottiVenduti = prodottiVenduti;
	}
	public double getGuadagnoTot() {
		return guadagnoTot;
	}
	public void setGuadagnoTot(double guadagnoTot) {
		this.guadagnoTot = guadagnoTot;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(guadagnoTot);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + idVenditore;
		result = prime * result + ((nomeAzienda == null) ? 0 : nomeAzienda.hashCode());
		result = prime * result + ((nomeProdotto == null) ? 0 : nomeProdotto.hashCode());
		result = prime * result + prodottiVenduti;
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
		GuadagniVenditore other = (GuadagniVenditore) obj;
		if (Double.doubleToLongBits(guadagnoTot) != Double.doubleToLongBits(other.guadagnoTot))
			return false;
		if (idVenditore != other.idVenditore)
			return false;
		if (nomeAzienda == null) {
			if (other.nomeAzienda != null)
				return false;
		} else if (!nomeAzienda.equals(other.nomeAzienda))
			return false;
		if (nomeProdotto == null) {
			if (other.nomeProdotto != null)
				return false;
		} else if (!nomeProdotto.equals(other.nomeProdotto))
			return false;
		if (prodottiVenduti != other.prodottiVenduti)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GuadagniVenditore [idVenditore=" + idVenditore + ", nomeAzienda=" + nomeAzienda + ", nomeProdotto="
				+ nomeProdotto + ", prodottiVenduti=" + prodottiVenduti + ", guadagnoTot=" + guadagnoTot + "]";
	}
	

}
