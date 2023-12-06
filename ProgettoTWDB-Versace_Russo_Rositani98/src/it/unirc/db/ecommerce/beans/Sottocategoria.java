package it.unirc.db.ecommerce.beans;

public class Sottocategoria {

	private int idSottocategoria;
	private String nome;
	private String descrizione;
	private Integer idCategoria;

	public Sottocategoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sottocategoria(int idSottocategoria, String nome, String descrizione, Integer idCategoria) {
		super();
		this.idSottocategoria = idSottocategoria;
		this.nome = nome;
		this.descrizione = descrizione;
		this.idCategoria = idCategoria;
	}

	public Sottocategoria(String nome, String descrizione, Integer idCategoria) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.idCategoria = idCategoria;
	}

	public Sottocategoria(int idSottocategoria) {
		super();
		this.idSottocategoria = idSottocategoria;
	}

	public int getIdSottocategoria() {
		return idSottocategoria;
	}

	public void setIdSottocategoria(int idSottocategoria) {
		this.idSottocategoria = idSottocategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + idSottocategoria;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Sottocategoria other = (Sottocategoria) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (idSottocategoria != other.idSottocategoria)
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	public String toString(boolean a) {
		return "SottoCategoria [id=" + idSottocategoria + ", nome=" + nome + ", descrizione=" + descrizione + ", idCategoria="
				+ idCategoria + "]";
	}

}