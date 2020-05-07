package br.fepi.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.fepi.model.Produto;

@ManagedBean
@ViewScoped
public class ListaComprasMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<>();
	
	public void inserir() {
		this.produtos.add(produto);
		this.produto = new Produto();
		FacesMessage msg = new FacesMessage("Item salvo na lista");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance()
					.addMessage(null, msg);				
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
