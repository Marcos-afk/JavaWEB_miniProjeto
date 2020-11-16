package br.com.farmacia.teste;

import java.sql.SQLException;

import org.junit.Test;

import br.com.farmacia.dominio.Fabricante;
import br.com.farmacia.dominio.Produto;
import br.com.farmacia.operacoes.ProdutoDAO;

public class ProdutoDAOteste{

	@Test
	public void editar() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(1L);
		p.setDescricao("Novalgina");
		p.setPreco(3.50);
		p.setQuantidade(5L);
		Fabricante f = new Fabricante();
		f.setCodigo(39L);
		p.setFabricante(f);
		ProdutoDAO d = new ProdutoDAO();
		d.Editar(p);
	}
}
