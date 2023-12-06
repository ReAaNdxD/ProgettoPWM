package it.unirc.db.ecommerce.utils;

import it.unirc.db.ecommerce.beans.Cliente;
import it.unirc.db.ecommerce.beans.ClientePrimeDAO;

public class Prove {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente c = new Cliente(1);
		ClientePrimeDAO cpDAO = new ClientePrimeDAO();
		System.out.println(cpDAO.isPrime(c));
//		AND ( current_date() BETWEEN  dataInizioIscrizione AND dataFineIscrizione)"
	}

}
