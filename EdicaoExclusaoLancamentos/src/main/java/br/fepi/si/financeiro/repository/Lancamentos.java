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
	 * @param id
	 * @return
	 */
	public Lancamento porId (Long id) {
		return em.find(Lancamento.class, id);		
	}
	
	/**
	 * @param lancamento
	 */
	public void remover (Lancamento lancamento) {
		this.em.remove(lancamento);
	}
	
	/**
	 * 
	 * @param descricao
	 * @return lista de descrições em tempo real.
	 */
	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = em.createQuery(
				"select distinct descricao from Lancamento " 
				+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	/**
	 * Método que insere dados de Lançamentos.	 
	 * @param lancamento
	 */
	public void adicionar(Lancamento lancamento) {
		this.em.persist(lancamento);
	}
	
	/**
	 * Método atualiza e insere Lançamento.
	 * @param lancamento
	 */
	public void guardar (Lancamento lancamento) {
		this.em.merge(lancamento);
	}

	/**
	 * @return Retorna todos os lançamentos.
	 */
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}

}
