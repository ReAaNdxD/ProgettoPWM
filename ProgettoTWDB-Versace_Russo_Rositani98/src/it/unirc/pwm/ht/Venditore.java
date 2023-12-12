package it.unirc.pwm.ht;
// Generated 12 dic 2023, 12:22:05 by Hibernate Tools 6.1.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Venditore generated by hbm2java
 */
public class Venditore implements java.io.Serializable {

	private Integer idVenditore;
	private String email;
	private String password;
	private String nomeAzienda;
	private long piva;
	private Set articolos = new HashSet(0);

	public Venditore() {
	}

	public Venditore(String email, String password, String nomeAzienda, long piva) {
		this.email = email;
		this.password = password;
		this.nomeAzienda = nomeAzienda;
		this.piva = piva;
	}

	public Venditore(String email, String password, String nomeAzienda, long piva, Set articolos) {
		this.email = email;
		this.password = password;
		this.nomeAzienda = nomeAzienda;
		this.piva = piva;
		this.articolos = articolos;
	}

	public Integer getIdVenditore() {
		return this.idVenditore;
	}

	public void setIdVenditore(Integer idVenditore) {
		this.idVenditore = idVenditore;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomeAzienda() {
		return this.nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public long getPiva() {
		return this.piva;
	}

	public void setPiva(long piva) {
		this.piva = piva;
	}

	public Set getArticolos() {
		return this.articolos;
	}

	public void setArticolos(Set articolos) {
		this.articolos = articolos;
	}

}
