package it.unirc.pwm.ht;
// Generated 12 dic 2023, 12:22:05 by Hibernate Tools 6.1.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Sottocategoria generated by hbm2java
 */
public class Sottocategoria implements java.io.Serializable {

	private Integer idSottocategoria;
	private Categoria categoria;
	private String nome;
	private String descrizione;
	private Set prodottos = new HashSet(0);

	public Sottocategoria() {
	}

	public Sottocategoria(Categoria categoria, String nome, String descrizione) {
		this.categoria = categoria;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Sottocategoria(Categoria categoria, String nome, String descrizione, Set prodottos) {
		this.categoria = categoria;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prodottos = prodottos;
	}

	public Integer getIdSottocategoria() {
		return this.idSottocategoria;
	}

	public void setIdSottocategoria(Integer idSottocategoria) {
		this.idSottocategoria = idSottocategoria;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set getProdottos() {
		return this.prodottos;
	}

	public void setProdottos(Set prodottos) {
		this.prodottos = prodottos;
	}

}
