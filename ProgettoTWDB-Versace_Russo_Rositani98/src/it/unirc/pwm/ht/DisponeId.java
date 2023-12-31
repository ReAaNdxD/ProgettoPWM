package it.unirc.pwm.ht;
// Generated 12 dic 2023, 16:08:36 by Hibernate Tools 6.1.3.Final

/**
 * DisponeId generated by hbm2java
 */
public class DisponeId implements java.io.Serializable {

	private int codiceMagazzino;
	private int idArticolo;

	public DisponeId() {
	}

	public DisponeId(int codiceMagazzino, int idArticolo) {
		this.codiceMagazzino = codiceMagazzino;
		this.idArticolo = idArticolo;
	}

	public int getCodiceMagazzino() {
		return this.codiceMagazzino;
	}

	public void setCodiceMagazzino(int codiceMagazzino) {
		this.codiceMagazzino = codiceMagazzino;
	}

	public int getIdArticolo() {
		return this.idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DisponeId))
			return false;
		DisponeId castOther = (DisponeId) other;

		return (this.getCodiceMagazzino() == castOther.getCodiceMagazzino())
				&& (this.getIdArticolo() == castOther.getIdArticolo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodiceMagazzino();
		result = 37 * result + this.getIdArticolo();
		return result;
	}

}
