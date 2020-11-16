package br.com.farmacia.bin;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dominio.Fabricante;
import br.com.farmacia.operacoes.FabricanteDAO;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "FabricanteMB")
@ViewScoped
public class FabricanteBean {
	private ArrayList<Fabricante> itensFiltadros;
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	
	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltadros() {
		return itensFiltadros;
	}

	public void setItensFiltadros(ArrayList<Fabricante> itensFiltadros) {
		this.itensFiltadros = itensFiltadros;
	}


	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}



	@PostConstruct
	public void Pesquisa(){
		try {
			FabricanteDAO solicitar = new FabricanteDAO();
			itens = solicitar.Exibir();
		} catch (SQLException ex){
				JSFUtil.MensagemErro("Erro: " + ex.getMessage());
				ex.printStackTrace();
		}
	}
	
	public void PrepararNovo(){
		fabricante = new Fabricante();
		
	}
	
	public void gravar(){
		try{
			FabricanteDAO novo = new FabricanteDAO();
			novo.Salvar(fabricante);
			itens = novo.Exibir();
			JSFUtil.MensagemSucesso("Fabricante salvo com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.MensagemErro("Erro: " + ex.getMessage());
		}
	}

	
	public void Excluir(){
		try{
		FabricanteDAO excluir = new FabricanteDAO();
		excluir.Excluir(fabricante);
		itens = excluir.Exibir();
		JSFUtil.MensagemSucesso("Exclusão bem sucedida");
	}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.MensagemErro("Erro: " + ex.getMessage());
		}
	}
	

	
	public void Editar(){
		try{
			FabricanteDAO editar = new FabricanteDAO();
			editar.Editar(fabricante);
			itens = editar.Exibir();
			JSFUtil.MensagemSucesso("Fabricante editado com sucesso!");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.MensagemErro("Erro: " +ex.getMessage());
		}
		
	}

}
