package it.unirc.db.ecommerce.utils;

import java.math.BigDecimal;

import it.unirc.db.ecommerce.beans.ProdottoDAO;

public class Prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProdottoDAO dao= new ProdottoDAO();
		System.out.println(dao.cercaProdottoByNome("e"));

	}

}
