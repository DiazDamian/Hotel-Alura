package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos.Huespedes;

public class HuespedesDAO {
	Connection con;
	
	public HuespedesDAO(Connection con) {
		this.con=con;
	}
	
	public List<Huespedes> buscarHuespedes(){
		List<Huespedes> resultado = new ArrayList<>();
		String query="SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes";
		try {
			final PreparedStatement state = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				try (state){
					ResultSet res = state.getResultSet();
					while(res.next()) {
						Huespedes huesped = new Huespedes(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getString(5),res.getString(6),res.getInt(7));
						resultado.add(huesped);
					}
				}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}
