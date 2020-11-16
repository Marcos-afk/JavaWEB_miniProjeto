package br.com.farmacia.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String URL = "jdbc:mysql://localhost:3306/farmacia?useSSL=false&useTimezone=true&serverTimezone=UTC";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
}
