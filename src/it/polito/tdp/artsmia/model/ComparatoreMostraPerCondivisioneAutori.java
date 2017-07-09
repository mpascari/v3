package it.polito.tdp.artsmia.model;

import java.util.Comparator;

public class ComparatoreMostraPerCondivisioneAutori implements Comparator<Mostra> {

	@Override
	public int compare(Mostra o1, Mostra o2) {
		
		return -(o1.getNumeroCondivisi()-o2.getNumeroCondivisi());
	}

	

}
