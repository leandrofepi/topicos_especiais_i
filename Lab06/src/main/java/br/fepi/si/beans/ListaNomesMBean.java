package br.fepi.si.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ListaNomesMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private List<String> nomes = new ArrayList<>();
	
	public String adicionar() {
		nomes.add(this.nome);
		this.nome = "";
		if (nomes.size() >= 3) return "Alerta?faces-redirect=true";
		return null;
	}
	
	public void matadorSessao() {
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.invalidateSession();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getNomes() {
		return nomes;
	}
	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}	

}
