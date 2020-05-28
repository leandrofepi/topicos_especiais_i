package br.fepi.si.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.repository.Pessoas;
import br.fepi.si.financeiro.util.JpaUtil;

@FacesConverter (forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManager em = JpaUtil.getEntityManager();
		Pessoa retorno = null;
		try {
			if (value != null && !"".equals(value))
				retorno = new Pessoas(em).porId(new Long(value));
			return retorno;
		} finally {
			em.close();
		}		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getId() == null?null:pessoa.getId().toString();
		}
		return null;
	}

}
