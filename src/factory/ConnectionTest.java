package factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.HuespedesDAO;
import dao.ReservasDAO;
import modelos.Huespedes;
import modelos.Reservas;

public class ConnectionTest {
	public static void main(String[] args) {
	
		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.conectar();
		ReservasDAO rdao = new ReservasDAO(con);
		HuespedesDAO hdao = new HuespedesDAO(con);
		try {
			System.out.println("probando conexion");
			List<Reservas> reservas = new ArrayList<>();
			reservas = rdao.buscarReservas();
			List<Huespedes> huespedes =new ArrayList<>();
			huespedes = hdao.buscarHuespedes();
			con.close();
			
			System.out.println(reservas.toString());
			System.out.println(huespedes.toString());
			System.out.println("conexion cerrada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
