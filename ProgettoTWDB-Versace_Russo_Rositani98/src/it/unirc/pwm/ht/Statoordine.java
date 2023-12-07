package it.unirc.pwm.ht;
// Generated 7 dic 2023, 14:54:20 by Hibernate Tools 6.1.3.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Statoordine generated by hbm2java
 */
public class Statoordine  implements java.io.Serializable {


     private Integer idStatoOrdine;
     private String nome;
     private Set ordines = new HashSet(0);

    public Statoordine() {
    }

	
    public Statoordine(String nome) {
        this.nome = nome;
    }
    public Statoordine(String nome, Set ordines) {
       this.nome = nome;
       this.ordines = ordines;
    }
   
    public Integer getIdStatoOrdine() {
        return this.idStatoOrdine;
    }
    
    public void setIdStatoOrdine(Integer idStatoOrdine) {
        this.idStatoOrdine = idStatoOrdine;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set getOrdines() {
        return this.ordines;
    }
    
    public void setOrdines(Set ordines) {
        this.ordines = ordines;
    }




}


