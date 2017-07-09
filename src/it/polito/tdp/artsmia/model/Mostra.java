	package it.polito.tdp.artsmia.model;

	import it.polito.tdp.artsmia.model.ArtObject;

	import java.util.HashSet;
	import java.util.List;
	import java.util.Set;

	public class Mostra {
		
		private int idMostra;
		private String dipartimento;
		private String titolo;
		private int begin;
		private int end;
		private Set<Integer> idAutoriPresenti;
		private int numeroCondivisi;
		
		
		
		public int getNumeroCondivisi() {
			return numeroCondivisi;
		}
		public void setNumeroCondivisi(int numeroCondivisi) {
			this.numeroCondivisi = numeroCondivisi;
		}
		public Mostra(int idMostra, String dipartimento, String titolo, int begin, int end) {
			super();
			this.idMostra = idMostra;
			this.dipartimento = dipartimento;
			this.titolo = titolo;
			this.begin = begin;
			this.end = end;
			this.idAutoriPresenti=new HashSet<>();
		}
		public int getIdMostra() {
			return idMostra;
		}
		public void setIdMostra(int idMostra) {
			this.idMostra = idMostra;
		}
		public String getDipartimento() {
			return dipartimento;
		}
		public void setDipartimento(String dipartimento) {
			this.dipartimento = dipartimento;
		}
		public String getTitolo() {
			return titolo;
		}
		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}
		public int getBegin() {
			return begin;
		}
		public void setBegin(int begin) {
			this.begin = begin;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		
		
		
		public Set<Integer> getIdAutoriPresenti() {
			return idAutoriPresenti;
		}
		public void setIdAutoriPresenti(Set<Integer> idAutoriPresenti) {
			this.idAutoriPresenti = idAutoriPresenti;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idMostra;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Mostra other = (Mostra) obj;
			if (idMostra != other.idMostra)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Mostra [idMostra=" + idMostra + ", titolo=" + titolo + ", begin=" + begin + "]";
		}
		
		public String toString2() {
			return "Mostra [idMostra=" + idMostra + ", titolo=" + titolo + ", condivisi=" + this.numeroCondivisi + "]";
		}
		

	}



