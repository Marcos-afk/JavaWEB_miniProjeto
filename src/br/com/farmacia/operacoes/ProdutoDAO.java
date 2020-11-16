package br.com.farmacia.operacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.dominio.Fabricante;
import br.com.farmacia.dominio.Produto;
import br.com.farmacia.fabrica.Conexao;

public class ProdutoDAO {
	
	public void Salvar(Produto produto ) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao,quantidade,preco,fabricante_codigo) ");
		sql.append("VALUES(?,?,?,?) ");
		
		Connection  conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setDouble(3, produto.getPreco());
		comando.setLong(4 , produto.getFabricante().getCodigo());
		comando.executeUpdate();
	}
	
	public ArrayList<Produto> Exibir()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade , f.codigo, f.descricao ");
		sql.append("FROM produto AS p inner join fabricante AS f ");
		sql.append("ON p.fabricante_codigo = f.codigo ORDER BY p.codigo ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		while(resultado.next()){
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("f.codigo"));
			fabricante.setDescricao(resultado.getString("f.descricao"));
			Produto produto = new Produto();
			produto.setCodigo(resultado.getLong("p.codigo"));
			produto.setDescricao(resultado.getString("p.descricao"));
			produto.setPreco(resultado.getDouble("p.preco"));
			produto.setQuantidade(resultado.getLong("p.quantidade"));
			produto.setFabricante(fabricante);
			
			lista.add(produto);
		}
		
		return lista;
	}
	
	public void Excluir(Produto produto) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong( 1 , produto.getCodigo());
		comando.executeUpdate();
	}
	
	public void Editar(Produto produto) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, quantidade = ? , preco = ? , fabricante_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = Conexao.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, produto.getDescricao());
		comando.setLong(2 , produto.getQuantidade());
		comando.setDouble(3, produto.getPreco());
		comando.setLong(4, produto.getFabricante().getCodigo());
		comando.setLong(5, produto.getCodigo());
		comando.executeUpdate();
		
	}
	
	
	
	
	
}
