package br.fepi.si;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class VerificaTextoMBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer quantidade;
	private String nome;
	
	public void executar() {
		this.nome = this.nome.toUpperCase();
		this.quantidade = this.nome.length();
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
