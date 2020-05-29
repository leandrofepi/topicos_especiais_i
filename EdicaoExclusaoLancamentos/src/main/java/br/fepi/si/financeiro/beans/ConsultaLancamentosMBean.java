package br.fepi.si.financeiro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.service.CadastroLancamentos;
import br.fepi.si.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos = new ArrayList<>();
	private Lancamento lancamentoSelecionado = new Lancamento();
	
	public void consultar() {
		EntityManager em = JpaUtil.getEntityManager();
		this.lancamentos = new Lancamentos(em).todos();
		em.close();
	}
	
	public void excluir () {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		CadastroLancamentos cadastroLancamentos = 
				new CadastroLancamentos(new Lancamentos(em));
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			cadastroLancamentos.excluir(lancamentoSelecionado);
			context.addMessage(null, new FacesMessage("Lançamento #"
					+ lancamentoSelecionado.getId()+" removido com sucesso."));
			et.commit();
			this.consultar();
		} catch (Exception e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_FATAL);
			context.addMessage(null, msg);
		}finally {
			em.close();
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
}
