package factory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import controllers.HuespedesController;
import controllers.ReservasController;
import dao.HuespedesDAO;
import dao.ReservasDAO;
import modelos.Huespedes;
import modelos.Reservas;

public class ConnectionTest {
	public static void main(String[] args) throws ParseException {
	
		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.conectar();
		ReservasDAO rdao = new ReservasDAO(con);
		HuespedesDAO hdao = new HuespedesDAO(con);
		HuespedesController huespedController = new HuespedesController();
		ReservasController reservasController;
		try {
			System.out.println("probando conexion");
			
			//List<Reservas> reservas = new ArrayList<>();
			//reservas = rdao.buscarReservas();
			
			//List<Huespedes> huespedes =new ArrayList<>();
			//huespedes = hdao.buscarHuespedes();
			
			/*
			Date fechaE=new Date(123,05,13) ;
			Date fechaS = new Date(124,06,02);
			Reservas res= new Reservas(fechaE,fechaS,"5000","Dinero en efectivo");
			rdao.actualizarReserva(fechaE, fechaS, "2000", "Dinero en efectivo", 5);
			//rdao.guardarReserva(res);
			*/
			Huespedes huesped = new Huespedes("paco","guerrero",new Date(123,2,2),"argentino","123459999",1);
			huespedController.guardar(huesped);
			//hdao.guardar(huesped);
			//hdao.actualizarHuesped(huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getIdReserva(), 6);
			//rdao.eliminarReserva(10);
			
			
			con.close();
			
			//System.out.println(reservas.toString());
			//System.out.println(huespedes.get(0).getApellido());
			System.out.println("conexion cerrada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
