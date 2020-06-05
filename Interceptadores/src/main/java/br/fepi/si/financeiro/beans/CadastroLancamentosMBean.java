package br.fepi.si.financeiro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.model.TipoLancamentoEnum;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.repository.Pessoas;
import br.fepi.si.financeiro.service.CadastroLancamentos;
import br.fepi.si.financeiro.service.NegocioException;
import br.fepi.si.financeiro.util.jpa.Transactional;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;

	@Inject
	private Pessoas pessoasRepository;

	@Inject
	private CadastroLancamentos cadastroLancamentos;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> pessoas = new ArrayList<>();

	public List<String> pesquisarDescricoes(String descricao) {
		return lancamentos.descricoesQueContem(descricao);
	}

	public void preparaCastrados() {
		this.pessoas = pessoasRepository.todos();
	}

	@Transactional
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cadastroLancamentos.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
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
	 * 
	 * @return
	 */
	public TipoLancamentoEnum[] getTiposLancamento() {
		return TipoLancamentoEnum.values();
	}

}
