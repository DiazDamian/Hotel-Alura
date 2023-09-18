package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource data;
	final private String dbUrl="jdbc:mysql://localhost/hotelalura?useTimezone=true&serverTimezone=UTC";
	final private String usuario="root";
	final private String pass="k4s3nMb9lS";
	
	public ConnectionFactory() {
		ComboPooledDataSource conPool = new ComboPooledDataSource();
		conPool.setJdbcUrl(dbUrl);
		conPool.setUser(usuario);
		conPool.setPassword(pass);
		this.data = conPool;
	}
	
	public Connection conectar() {
		try {
		 	return this.data.getConnection();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
