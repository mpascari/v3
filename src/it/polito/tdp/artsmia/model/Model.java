package it.polito.tdp.artsmia.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	public List<Integer> anni;
	private ArtsmiaDAO dao;
	public List<Mostra> mostreCorrenti;
	private Map<Integer, Mostra> idMapMostreCorrenti;
	private WeightedGraph<Mostra, DefaultWeightedEdge> grafo;
	
	public Model(){
		this.dao=new ArtsmiaDAO();
		this.idMapMostreCorrenti=new HashMap<>();
	}

	public List<Integer> getAnni() {
		if(this.anni==null)
			this.anni=this.dao.getListAnni();
		return this.anni;
	}
	
	public void getMostreCorrenti(int anno){
		this.mostreCorrenti=this.dao.getListMostre(anno, idMapMostreCorrenti);
	}
	
		public void creaGrafo(Integer anno) {
		
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		this.getMostreCorrenti(anno);
		
		Graphs.addAllVertices(grafo, this.mostreCorrenti);
		
		//ad ogni esibizione aggiungo una lista di id degli autori che ha presentato
		for (Mostra m : grafo.vertexSet()) {
			m.setIdAutoriPresenti(this.dao.getListaIdAutori(m));
		}
		
		int peso =0;
		
		for (Mostra m1 : grafo.vertexSet()) {
			for (Mostra m2 : grafo.vertexSet()) {
				if(!m1.equals(m2)){
					peso=this.getAutoriInComune(m1,m2);
					if(peso>0){
						Graphs.addEdgeWithVertices(grafo, m1, m2, peso);
						
					}
				}
					
				
			}
		}
		
		System.out.println("Grafo creato!");
		System.out.println("# vertici: "+grafo.vertexSet().size());
		System.out.println("# archi: "+grafo.edgeSet().size());
		
	}

    private int getAutoriInComune(Mostra m1, Mostra m2) {
    	Set<Integer> intersection = new HashSet<Integer>(m1.getIdAutoriPresenti());
		intersection.retainAll(m2.getIdAutoriPresenti());
		
		return intersection.size();
}

	public List<Mostra> getClassifica() {
		
		
		
		for (Mostra m : grafo.vertexSet()) {
			m.setNumeroCondivisi(0);
			for (DefaultWeightedEdge e : grafo.edgesOf(m)) {
				m.setNumeroCondivisi(m.getNumeroCondivisi()+(int)grafo.getEdgeWeight(e));
			}
			
			
		}
		
		List<Mostra> classifica = null;
		
	//	classifica=new LinkedList<>(grafo.vertexSet());
		
		Collections.sort(classifica, new ComparatoreMostraPerCondivisioneAutori());
		
		return classifica;
		
		
	}
	
	

}
