package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import modelos.Reservas;

public class ReservasController {
	private ReservasDAO reservasDao;
	
	public ReservasController() {
		Connection con = new ConnectionFactory().conectar();
		this.reservasDao = new ReservasDAO(con);
	}
	public void guardar(Reservas reserva) {
		this.reservasDao.guardarReserva(reserva);
	}
	public List<Reservas> buscar(){
		return this.reservasDao.buscarReservas();
	}
	public List<Reservas> buscarId(Integer id){
		return this.reservasDao.buscarReservasPorID(id);
	}
	public void actualizar(Date fechaEntrada,Date fechaSalida,String valor,String formaPago,Integer id) {
		this.reservasDao.actualizarReserva(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
	public void eliminar(Integer id) {
		this.reservasDao.eliminarReserva(id);
	}
}
