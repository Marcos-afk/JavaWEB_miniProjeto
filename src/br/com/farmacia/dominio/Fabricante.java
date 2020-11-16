package br.com.farmacia.dominio;

public class Fabricante {
	private Long codigo;
	private String descricao;
	
	public void setCodigo(Long codigo){
		this.codigo = codigo;
		
	}
	public Long getCodigo(){
		
		return this.codigo;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public String toString() {
		String saida = codigo + " || " + descricao;
		return saida;
	}
}
