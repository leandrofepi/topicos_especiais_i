package br.fepi.si.financeiro.model;

public enum TipoLancamentoEnum {	
	
	RECEITA("Receita"), DESPESA("Despesa");

	private String descricao;

	
	TipoLancamentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
