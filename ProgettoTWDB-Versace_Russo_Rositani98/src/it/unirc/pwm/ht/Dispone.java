package it.unirc.pwm.ht;
// Generated 12 dic 2023, 16:08:36 by Hibernate Tools 6.1.3.Final

/**
 * Dispone generated by hbm2java
 */
public class Dispone implements java.io.Serializable {

	private DisponeId id;
	private Articolo articolo;
	private Magazzino magazzino;
	private int quantita;

	public Dispone() {
	}

	public Dispone(DisponeId id, Articolo articolo, Magazzino magazzino, int quantita) {
		this.id = id;
		this.articolo = articolo;
		this.magazzino = magazzino;
		this.quantita = quantita;
	}

	public DisponeId getId() {
		return this.id;
	}

	public void setId(DisponeId id) {
		this.id = id;
	}

	public Articolo getArticolo() {
		return this.articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Magazzino getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
