package br.fepi.si.financeiro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.service.CadastroLancamentos;

@Named
@javax.faces.view.ViewScoped
public class ConsultaLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;

	@Inject
	CadastroLancamentos cadastroLancamentos;

	private List<Lancamento> lancamentos = new ArrayList<>();
	private Lancamento lancamentoSelecionado = new Lancamento();

	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			cadastroLancamentos.excluir(lancamentoSelecionado);
			context.addMessage(null,
					new FacesMessage("Lançamento #" + lancamentoSelecionado.getId() 
					+ " removido com sucesso."));

			this.consultar();
		} catch (Exception e) {

			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_FATAL);
			context.addMessage(null, msg);
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
