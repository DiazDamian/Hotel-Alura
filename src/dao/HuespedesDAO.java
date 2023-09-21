package dao;

import java.sql.Connection;
import java.sql.Date;
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
		String query="SELECT id, nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, idReserva FROM huespedes";
		try {
			final PreparedStatement state = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				try (state){
					ResultSet res = state.executeQuery();
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
	
	public List<Huespedes> buscarHuespedesPorApellido(String apellido){
		List<Huespedes> resultado = new ArrayList<>();
		String query="SELECT id, nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE apellido = ?";
		try {
			final PreparedStatement state = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				try (state){
					state.setString(1, apellido);
					ResultSet res = state.executeQuery();
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
	
	public List<Huespedes> buscarHuespedesPorID(Integer id){
		List<Huespedes> resultado = new ArrayList<>();
		String query="SELECT id, nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE id = ?";
		try {
			final PreparedStatement state = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				try (state){
					state.setInt(1, id);
					ResultSet res = state.executeQuery();
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
	
	public void eliminar(Integer id) {
		String query="DELETE FROM huespedes WHERE id = ?";
		try {
			final PreparedStatement state = con.prepareStatement(query);
			try(state){
				state.setInt(1, id);
				state.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void guardar(Huespedes huesped) {
		String query="INSERT INTO huespedes(nombre,apellido,fechaDeNacimiento,nacionalidad,telefono,idReserva) VALUES (?,?,?,?,?,?)";
		try {
			final PreparedStatement state = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			state.setString(1, huesped.getNombre());
			state.setString(2, huesped.getApellido());
			state.setDate(3, huesped.getFechaNacimiento());
			state.setString(4, huesped.getNacionalidad());
			state.setString(5, huesped.getTelefono());
			state.setInt(6, huesped.getIdReserva());
			state.executeUpdate();
			try (final ResultSet res=state.getGeneratedKeys()){
				while(res.next()) {
					huesped.setId(res.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarHuesped(String nombre,String apellido,Date fechaNacimiento,String nacionalidad,String telefono,Integer idReserva,Integer id) {
		String query="UPDATE huespedes SET nombre = ?, apellido = ?, fechaDeNacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ? WHERE id = ?";
		try {
			final PreparedStatement state = con.prepareStatement(query);
			state.setString(1, nombre);
			state.setString(2, apellido);
			state.setDate(3, fechaNacimiento);
			state.setString(4, nacionalidad);
			state.setString(5, telefono);
			state.setInt(6, idReserva);
			state.setInt(7, id);
			state.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
