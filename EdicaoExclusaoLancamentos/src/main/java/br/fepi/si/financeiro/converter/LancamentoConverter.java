package br.fepi.si.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.fepi.si.financeiro.model.Lancamento;
import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.repository.Lancamentos;
import br.fepi.si.financeiro.util.JpaUtil;

@FacesConverter (forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		EntityManager em = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Lancamentos lancamentos = new Lancamentos(em);
				retorno = lancamentos.porId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Lancamento lancamento = (Lancamento) value;
			return lancamento.getId() == null?null:lancamento.getId().toString();
		}
		return null;		
	}

}
