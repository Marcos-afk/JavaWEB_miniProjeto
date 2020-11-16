package br.com.farmacia.operacoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.farmacia.dominio.*;
import br.com.farmacia.fabrica.Conexao;
public class FabricanteDAO {

	public void Salvar(Fabricante fabricante) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao)");
		sql.append("VALUES(?)");	

		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString( 1 , fabricante.getDescricao());
		comando.executeUpdate();
	}
	
	public void Excluir(Fabricante fabricante) throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1 , fabricante.getCodigo());
		comando.executeUpdate();
		
	}
	
	public void Editar(Fabricante fabricante) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1 , fabricante.getDescricao());
		comando.setLong(2 , fabricante.getCodigo());
		comando.executeUpdate();
	}
	
	public Fabricante Pesquisar(Fabricante fabricante) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo,descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		 
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong( 1, fabricante.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fabricante retorno = null;
		
		if(resultado.next()){
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));	
		}
		
		return retorno;	
	}
	
	public ArrayList<Fabricante> Exibir() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo ,descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY codigo ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while(resultado.next()){
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("codigo"));
			fabricante.setDescricao(resultado.getString("descricao"));
			lista.add(fabricante);
		}
		
		return lista;
	}
	
	public ArrayList<Fabricante> BuscarDesc(Fabricante fabricante) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo ,descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY codigo ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1 , "%" + fabricante.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while(resultado.next()){
			Fabricante fabricante2 = new Fabricante();
			fabricante2.setCodigo(resultado.getLong("codigo"));
			fabricante2.setDescricao(resultado.getString("descricao"));
			lista.add(fabricante2);
		}
		
		return lista;
	}
}