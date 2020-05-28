package br.fepi.si.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.si.financeiro.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public Lancamentos(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * Método que insere dados de Lançamentos
	 * @param lancamento
	 */
	public void adicionar (Lancamento lancamento) {
		this.em.persist(lancamento);
	}
		
	/** 
	 * @return Retorna todos os lançamentos.
	 */
	public List<Lancamento> todos(){
		TypedQuery<Lancamento> query = 
				em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();		
	}	
	
	public List<String> descricoesQueContem(String descricao){
		/*
		 * Select distinct l.descricao as descricoes 
		 * From lancamento l 
		 * Where l.descricao ilike '%paramentro%'
		 */
		String consulta = "select distinct descricao from Lancamento"
				+ " where upper(descricao) like upper(:descricao)";
		TypedQuery<String> query = em.createQuery(consulta, String.class);
		query.setParameter("descricao", "%"+descricao+"%");
		return query.getResultList();		
	}
	

}
