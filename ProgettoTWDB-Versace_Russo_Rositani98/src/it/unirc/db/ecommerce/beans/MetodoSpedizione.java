package it.unirc.db.ecommerce.beans;

public class MetodoSpedizione {

	private int idMetodoSpedizione;
	private String tipo;
	private double speseSpedizione;

	public MetodoSpedizione() {
		super();
	}

	public MetodoSpedizione(int idMetodoSpedizione) {
		super();
		this.idMetodoSpedizione = idMetodoSpedizione;
	}

	public MetodoSpedizione(int idMetodoSpedizione, String tipo, double speseSpedizione) {
		super();
		this.idMetodoSpedizione = idMetodoSpedizione;
		this.tipo = tipo;
		this.speseSpedizione = speseSpedizione;
	}

	public int getIdMetodoSpedizione() {
		return idMetodoSpedizione;
	}

	public void setIdMetodoSpedizione(int idMetodoSpedizione) {
		this.idMetodoSpedizione = idMetodoSpedizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getSpeseSpedizione() {
		return speseSpedizione;
	}

	public void setSpeseSpedizione(double speseSpedizione) {
		this.speseSpedizione = speseSpedizione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMetodoSpedizione;
		long temp;
		temp = Double.doubleToLongBits(speseSpedizione);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		MetodoSpedizione other = (MetodoSpedizione) obj;
		if (idMetodoSpedizione != other.idMetodoSpedizione)
			return false;
		if (Double.doubleToLongBits(speseSpedizione) != Double.doubleToLongBits(other.speseSpedizione))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tipo + " - " + speseSpedizione+"€";
	}
	public String toString1() {
		return "MetodoSpedizione [idMetodoSpedizione=" + idMetodoSpedizione + ", tipo=" + tipo + ", speseSpedizione="
				+ speseSpedizione + "]";
	}

}
