package br.fepi.si.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.repository.Lancamentos;

public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamentos lancamentos;

	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	/**
	 * RN01 - O sistema não deverá permitir lançamentos com data de pgto futura.
	 * @since 22/05/2020
	 * @author leandro	 
	 * @param lancamento
	 * @throws NegocioException
	 */
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null 
				&& lancamento.getDataPagamento().after(new Date()))
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		this.lancamentos.adicionar(lancamento);
	}

}
