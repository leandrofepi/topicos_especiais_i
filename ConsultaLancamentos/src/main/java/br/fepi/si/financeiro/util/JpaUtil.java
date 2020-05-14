package br.fepi.si.financeiro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

		private static EntityManagerFactory emf;
		
		static {
			emf = Persistence.createEntityManagerFactory("FinanceiroPU");
		}
		
		public static EntityManager getEntityManager() {
			return emf.createEntityManager();			
		}
		
}
