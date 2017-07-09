package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getListAnni() {
		String sql = "SELECT DISTINCT begin FROM exhibitions ORDER BY begin DESC";
		
		List<Integer> result = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("begin"));
				
				 }

			conn.close();
			return result;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeErrorException(null,"Errore duante l'estrazione della lista degli anni");
			
		}
	}
	
	public List<Mostra> getListMostre(Integer anno, Map<Integer, Mostra> idMap) {
		String sql= "Select exhibition_id,exhibition_department,exhibition_title,begin,end From exhibitions WHERE begin>=?";

		List<Mostra> result = new ArrayList<>();	

		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Mostra m = new Mostra(res.getInt("exhibition_id"), res.getString("exhibition_department"),
						res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				
					idMap.put(m.getIdMostra(), m);
					result.add(m);
				
				
				 }

			conn.close();
			return result;
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeErrorException(null,"Errore duante l'estrazione della lista degli anni");
			
		}
		
	}

	public Set<Integer> getListaIdAutori(Mostra m) {
		String sql="SELECT DISTINCT au.artist_id FROM authorship AS au WHERE au.object_id IN (SELECT eo.object_id FROM exhibition_objects AS eo WHERE eo.exhibition_id=?)";
		Set<Integer> id= new HashSet<>();
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, m.getIdMostra());

			ResultSet res = st.executeQuery();
		while (res.next()) {
			
			Integer codice = res.getInt("au.artist_id");
			
			
			
			id.add(codice);
			
			
			 }

		conn.close();
		return id;
	} catch (SQLException e) {
		
		e.printStackTrace();
		throw new RuntimeErrorException(null,"Errore duante l'estrazione della lista degli id degli artisti");
		
	}
		
	}
}
