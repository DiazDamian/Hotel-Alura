package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos.Reservas;

public class ReservasDAO {
	private Connection con;
	
	public ReservasDAO(Connection con) {
		this.con=con;
	}
	public List<Reservas> buscarReservas(){
		List<Reservas> resultado= new ArrayList<>();
		
		try {
			String query="SELECT id, fechaEntrada, fechaSalida, valor, formaPago FROM reservas";
			final PreparedStatement state = con.prepareStatement(query);
				try(state){
					final ResultSet res =state.executeQuery();
					while(res.next()) {
						Reservas reserva =new Reservas(res.getInt(1),res.getDate(2),res.getDate(3),res.getString(4),res.getString(5));
						resultado.add(reserva);
					}
				}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	
	
	public void guardarReserva(Reservas reserva) {
		String query="INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago) VALUES (?,?,?,?)";
		try {
			final PreparedStatement state = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			state.setDate(1, reserva.getFechaEntrada());
			state.setDate(2, reserva.getFechaSalida());
			state.setString(3, reserva.getValor());
			state.setString(4, reserva.getFormaPago());
			state.executeUpdate();
			
			try(final ResultSet res=state.getGeneratedKeys()){
				while(res.next()) {
					reserva.setId(res.getInt(1));
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizarReserva(Date fechaEntrada,Date fechaSalida,String valor,String formaPago,Integer id) {
		String query="UPDATE reservas SET fechaEntrada = ? , fechaSalida = ? , valor = ? , formaPago = ? WHERE id = ?";
		try {
		
		final PreparedStatement state = con.prepareStatement(query);
		state.setDate(1, fechaEntrada);
		state.setDate(2, fechaSalida);
		state.setString(3, valor);
		state.setString(4, formaPago);
		state.setInt(5, id);
		state.execute();
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public void eliminarReserva(Integer id) {
		String query="DELETE FROM reservas WHERE id=?";
		try {
			final PreparedStatement state = con.prepareStatement(query);
			try(state){
				state.setInt(1, id);
				state.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
