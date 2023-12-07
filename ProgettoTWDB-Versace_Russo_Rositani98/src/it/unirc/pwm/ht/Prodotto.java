package it.unirc.pwm.ht;
// Generated 7 dic 2023, 12:33:12 by Hibernate Tools 6.1.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Prodotto generated by hbm2java
 */
public class Prodotto implements java.io.Serializable {

	private Integer idProdotto;
	private Sottocategoria sottocategoria;
	private String nome;
	private String descrizioneBreve;
	private String descrizioneDettagliata;
	private String marca;
	private Set articolos = new HashSet(0);

	public Prodotto() {
	}

	public Prodotto(Sottocategoria sottocategoria, String nome, String descrizioneBreve, String descrizioneDettagliata,
			String marca) {
		this.sottocategoria = sottocategoria;
		this.nome = nome;
		this.descrizioneBreve = descrizioneBreve;
		this.descrizioneDettagliata = descrizioneDettagliata;
		this.marca = marca;
	}

	public Prodotto(Sottocategoria sottocategoria, String nome, String descrizioneBreve, String descrizioneDettagliata,
			String marca, Set articolos) {
		this.sottocategoria = sottocategoria;
		this.nome = nome;
		this.descrizioneBreve = descrizioneBreve;
		this.descrizioneDettagliata = descrizioneDettagliata;
		this.marca = marca;
		this.articolos = articolos;
	}

	public Integer getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}

	public Sottocategoria getSottocategoria() {
		return this.sottocategoria;
	}

	public void setSottocategoria(Sottocategoria sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizioneBreve() {
		return this.descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

	public String getDescrizioneDettagliata() {
		return this.descrizioneDettagliata;
	}

	public void setDescrizioneDettagliata(String descrizioneDettagliata) {
		this.descrizioneDettagliata = descrizioneDettagliata;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Set getArticolos() {
		return this.articolos;
	}

	public void setArticolos(Set articolos) {
		this.articolos = articolos;
	}

}