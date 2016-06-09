package org.guille.parcialsoa.dao;

import java.util.List;

import org.guille.parcialsoa.model.Telefono;

public interface ITelefonoDao extends IGenericDao<Telefono, Long> {

	public List<Telefono> obtenerUsuarioByNumero(String numero);
}
