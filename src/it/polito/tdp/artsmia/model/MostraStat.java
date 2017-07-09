package it.polito.tdp.artsmia.model;

public class MostraStat implements Comparable<MostraStat> {
	
	private String titoloMostra;
	private int numeroAutoriCondivisi;
	public MostraStat(String titoloMostra, int numeroAutoriCondivisi) {
		super();
		this.titoloMostra = titoloMostra;
		this.numeroAutoriCondivisi = numeroAutoriCondivisi;
	}
	public String getTitoloMostra() {
		return titoloMostra;
	}
	public void setTitoloMostra(String titoloMostra) {
		this.titoloMostra = titoloMostra;
	}
	public int getNumeroAutoriCondivisi() {
		return numeroAutoriCondivisi;
	}
	public void setNumeroAutoriCondivisi(int numeroAutoriCondivisi) {
		this.numeroAutoriCondivisi = numeroAutoriCondivisi;
	}
	
	@Override
	public String toString() {
		return "MostraStat [titoloMostra=" + titoloMostra + ", numeroAutoriCondivisi=" + numeroAutoriCondivisi + "]";
	}
	@Override
	public int compareTo(MostraStat o) {
		
		return this.getNumeroAutoriCondivisi()-o.getNumeroAutoriCondivisi();
	}
	
	

}
