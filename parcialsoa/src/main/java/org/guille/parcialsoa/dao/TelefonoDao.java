package org.guille.parcialsoa.dao;

import java.util.List;

import org.guille.parcialsoa.model.Telefono;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TelefonoDao extends GenericDaoImp<Telefono, Long> implements ITelefonoDao {

	public List<Telefono> obtenerUsuarioByNumero(String numero){
		return this.getByCriteria(Restrictions.eq("numero", numero));
	}
}
