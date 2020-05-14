package br.fepi.si.financeiro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos = new ArrayList<>();
	
	public void consultar() {
		EntityManager em = JpaUtil.getEntityManager();
		this.lancamentos = new Lancamentos(em).todos();
		em.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
}
