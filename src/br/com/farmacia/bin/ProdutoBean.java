package br.com.farmacia.bin;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dominio.Fabricante;
import br.com.farmacia.dominio.Produto;
import br.com.farmacia.operacoes.FabricanteDAO;
import br.com.farmacia.operacoes.ProdutoDAO;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "ProdutoMB")
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltadros;
	private ArrayList<Fabricante> comboFabricantes;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produto> getItensFiltadros() {
		return itensFiltadros;
	}

	public void setItensFiltadros(ArrayList<Produto> itensFiltadros) {
		this.itensFiltadros = itensFiltadros;
	}

	@PostConstruct
	public void Pesquisa(){
		ProdutoDAO solicitar = new ProdutoDAO();
		try {
			itens = solicitar.Exibir();
		} catch (SQLException e) {
			JSFUtil.MensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void PrepararNovo(){
		produto = new Produto();
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		try {
			comboFabricantes = fabricanteDAO.Exibir();
		} catch (SQLException e) {
			JSFUtil.MensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Cadastrar(){
		ProdutoDAO solicitar = new ProdutoDAO();
		try {
			solicitar.Salvar(produto);
			JSFUtil.MensagemSucesso("Produto salvado com sucesso");
			itens = solicitar.Exibir();
		} catch (SQLException e) {
			JSFUtil.MensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void Excluir(){
		ProdutoDAO excluir = new ProdutoDAO();
		try {
			excluir.Excluir(produto);
			itens = excluir.Exibir();
			JSFUtil.MensagemSucesso("Produto excluido com sucesso!");
		} catch (SQLException e) {
			JSFUtil.MensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void PrepararEditar(){
		FabricanteDAO solicitar = new FabricanteDAO();
		produto = new Produto();
		try{
			comboFabricantes = solicitar.Exibir();
		}catch(SQLException e){
			JSFUtil.MensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Editar(){
		ProdutoDAO editar = new ProdutoDAO();
		try{
			editar.Editar(produto);
			itens = editar.Exibir();
			JSFUtil.MensagemSucesso("Produto editado com sucesso!");
		}catch(SQLException e){
			JSFUtil.MensagemErro("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
