package it.unirc.db.ecommerce.views;

public class ViewOrderProducts {

	private int idArticolo;
	private String nomeProdotto;
	private String marca;
	private String nomeAzienda;
	private double prezzoAcquisto;
	private int quantita;
	private double costoTot;

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
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

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getCostoTot() {
		return costoTot;
	}

	public void setCostoTot(double costoTot) {
		this.costoTot = costoTot;
	}

	@Override
	public String toString() {
		return "ViewOrderProducts [idArticolo=" + idArticolo + ", nomeProdotto=" + nomeProdotto + ", marca=" + marca
				+ ", nomeAzienda=" + nomeAzienda + ", prezzoAcquisto=" + prezzoAcquisto + ", quantita=" + quantita
				+ ", costoTot=" + costoTot + "]";
	}

}
