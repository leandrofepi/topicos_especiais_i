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
import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.model.TipoLancamentoEnum;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.repository.Pessoas;
import br.fepi.si.financeiro.service.CadastroLancamentos;
import br.fepi.si.financeiro.service.NegocioException;
import br.fepi.si.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> pessoas = new ArrayList<>();
	
	public void preparaCastrados() {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			this.pessoas = new Pessoas(em).todos();
		} finally {
			em.close();
		}
		
	}
	
	public List<String> pesquisarDescricoes(String descricao){
		EntityManager em = JpaUtil.getEntityManager();
		return new Lancamentos(em).descricoesQueContem(descricao);
	}
	
	public void salvar() {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroLancamentos cadastroLancamentos = 
					new CadastroLancamentos(new Lancamentos(em));
			cadastroLancamentos.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			em.close();
		}
		
	}	
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}	
	
	/**
	 * Método que retorna as constantes do seu Enumerado.
	 * @return
	 */
	public TipoLancamentoEnum[] getTiposLancamento() {
		return TipoLancamentoEnum.values();
	}

}
