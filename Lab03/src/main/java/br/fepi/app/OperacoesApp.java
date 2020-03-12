package br.fepi.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.fepi.model.Carro;
import br.fepi.model.Cliente;
import br.fepi.model.Locacao;

public class OperacoesApp {

	public static void inserir(EntityManager em) throws ParseException {

		Cliente cliente1 = new Cliente("11111111111", "Silvio Santos");
		Cliente cliente2 = new Cliente("22211111111", "Fausto Silva");
		Cliente cliente3 = new Cliente("33311111111", "Raul Gil");

		Carro carro1 = new Carro("Fusca", "PXP4505");
		Carro carro2 = new Carro("Camaro", "CXF8005");
		Carro carro3 = new Carro("Opala", "PPX4185");

		Locacao locacao1 = new Locacao(cliente3, carro3, new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2020"), null);
		Locacao locacao2 = new Locacao(cliente1, carro2, new SimpleDateFormat("dd/MM/yyyy").parse("20/01/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("27/01/2020"));
		Locacao locacao3 = new Locacao(cliente2, carro1, new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2020"), null);

		em.persist(cliente1);
		em.persist(cliente2);
		em.persist(cliente3);

		em.persist(carro1);
		em.persist(carro2);
		em.persist(carro3);

		em.persist(locacao1);
		em.persist(locacao2);
		em.persist(locacao3);
	}

	public static void atualizar(EntityManager em) {
		Cliente cliente = em.find(Cliente.class, 3L);
		cliente.setCpfCliente("00000011154");
		em.merge(cliente);
	}

	public static void remover(EntityManager em) {
		em.remove(em.find(Cliente.class, 1L));
	}

	public static void buscaSingle(EntityManager em) {
		String parametro = "1111111111";

		Cliente cliente = em.createQuery("from Cliente c where c.cpfCliente = :cpf", Cliente.class)
				.setParameter("cpf", parametro).getSingleResult();

		System.out.println(cliente.getNomeCliente().toUpperCase());
	}

	public static void buscaList(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery("from Locacao", Locacao.class);
		for (Locacao locacao : locacoes.getResultList()) {
			System.out.println(locacao.getCarro().getModeloCarro().toUpperCase() + " foi alugado em "
					+ new SimpleDateFormat("dd/MM/yyyy").format(locacao.getDataLocacao().getTime()) + " por "
					+ locacao.getCliente().getNomeCliente().toUpperCase());
		}
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			// et.begin();
			// inserir(em);
			// atualizar(em);
			// remover(em);
			// et.commit();
			// System.out.println("Sucesso na operação!");
			// buscaSingle(em);
			buscaList(em);
		} catch (Exception e) {
			// et.rollback();
			System.out.println("Erro na operação. " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}

	}

}
