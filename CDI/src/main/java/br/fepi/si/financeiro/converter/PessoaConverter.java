package br.fepi.si.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.fepi.si.financeiro.model.Pessoa;
import br.fepi.si.financeiro.repository.Pessoas;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Inject
	private Pessoas pessoas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;

		if (value != null && !"".equals(value)) {
			retorno = pessoas.porId(new Long(value));
		}
		return retorno;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getId() == null ? null : pessoa.getId().toString();
		}
		return null;
	}

}
