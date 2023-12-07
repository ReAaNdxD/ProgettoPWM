package it.unirc.pwm.ht;
// Generated 7 dic 2023, 12:33:12 by Hibernate Tools 6.1.3.Final

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ordine generated by hbm2java
 */
public class Ordine implements java.io.Serializable {

	private Integer numeroOrdine;
	private Metodospedizione metodospedizione;
	private Indirizzospedizione indirizzospedizione;
	private Carrello carrello;
	private Statoordine statoordine;
	private float costoTotale;
	private Date dataCreazione;
	private Pagamento pagamento;
	private Set spediziones = new HashSet(0);

	public Ordine() {
	}

	public Ordine(Metodospedizione metodospedizione, Carrello carrello, Statoordine statoordine, float costoTotale,
			Date dataCreazione) {
		this.metodospedizione = metodospedizione;
		this.carrello = carrello;
		this.statoordine = statoordine;
		this.costoTotale = costoTotale;
		this.dataCreazione = dataCreazione;
	}

	public Ordine(Metodospedizione metodospedizione, Indirizzospedizione indirizzospedizione, Carrello carrello,
			Statoordine statoordine, float costoTotale, Date dataCreazione, Pagamento pagamento, Set spediziones) {
		this.metodospedizione = metodospedizione;
		this.indirizzospedizione = indirizzospedizione;
		this.carrello = carrello;
		this.statoordine = statoordine;
		this.costoTotale = costoTotale;
		this.dataCreazione = dataCreazione;
		this.pagamento = pagamento;
		this.spediziones = spediziones;
	}

	public Integer getNumeroOrdine() {
		return this.numeroOrdine;
	}

	public void setNumeroOrdine(Integer numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public Metodospedizione getMetodospedizione() {
		return this.metodospedizione;
	}

	public void setMetodospedizione(Metodospedizione metodospedizione) {
		this.metodospedizione = metodospedizione;
	}

	public Indirizzospedizione getIndirizzospedizione() {
		return this.indirizzospedizione;
	}

	public void setIndirizzospedizione(Indirizzospedizione indirizzospedizione) {
		this.indirizzospedizione = indirizzospedizione;
	}

	public Carrello getCarrello() {
		return this.carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Statoordine getStatoordine() {
		return this.statoordine;
	}

	public void setStatoordine(Statoordine statoordine) {
		this.statoordine = statoordine;
	}

	public float getCostoTotale() {
		return this.costoTotale;
	}

	public void setCostoTotale(float costoTotale) {
		this.costoTotale = costoTotale;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Set getSpediziones() {
		return this.spediziones;
	}

	public void setSpediziones(Set spediziones) {
		this.spediziones = spediziones;
	}

}
