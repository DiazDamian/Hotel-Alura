package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HuespedesDAO;
import factory.ConnectionFactory;
import modelos.Huespedes;

public class HuespedesController {
	private HuespedesDAO huespedesDao;
	
	public HuespedesController () {
		Connection con = new ConnectionFactory().conectar();
		this.huespedesDao = new HuespedesDAO(con);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesDao.guardar(huespedes);
	}
	public List<Huespedes> listarHuespedes(){
		return this.huespedesDao.buscarHuespedes();
	}
	public List<Huespedes> listarHuespedesId(Integer id){
		return this.huespedesDao.buscarHuespedesPorID(id);
	}
	public List<Huespedes> listarHuespedesApellido(String apellido){
		return this.huespedesDao.buscarHuespedesPorApellido(apellido);
	}
	public void actualizar(String nombre,String apellido,Date fechaNacimiento,String nacionalidad,String telefono,Integer idReserva, Integer id) {
		this.huespedesDao.actualizarHuesped(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
	}
	public void eliminar(Integer id) {
		this.huespedesDao.eliminar(id);
	}
}
