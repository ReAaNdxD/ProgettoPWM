package it.unirc.pwm.ht;
// Generated 7 dic 2023, 14:54:20 by Hibernate Tools 6.1.3.Final


import java.sql.Date;

/**
 * Pagamento generated by hbm2java
 */
public class Pagamento  implements java.io.Serializable {


     private int numeroOrdine;
     private Cartacredito cartacredito;
     private Ordine ordine;
     private Date data;

    public Pagamento() {
    }

    public Pagamento(Cartacredito cartacredito, Ordine ordine, Date data) {
       this.cartacredito = cartacredito;
       this.ordine = ordine;
       this.data = data;
    }
   
    public int getNumeroOrdine() {
        return this.numeroOrdine;
    }
    
    public void setNumeroOrdine(int numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }
    public Cartacredito getCartacredito() {
        return this.cartacredito;
    }
    
    public void setCartacredito(Cartacredito cartacredito) {
        this.cartacredito = cartacredito;
    }
    public Ordine getOrdine() {
        return this.ordine;
    }
    
    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }




}


