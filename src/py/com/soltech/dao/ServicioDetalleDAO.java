package py.com.soltech.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.modelo.entidades.ServicioDetalle;

public class ServicioDetalleDAO extends DaoGenerico<ServicioDetalle> {
	public ServicioDetalleDAO(){
		super(ServicioDetalle.class);
	}

	@Override
	public List<ServicioDetalle> recuperarPorFiltro(String filtro) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<ServicioDetalle> query = session
				.createQuery("from " + clase.getName() + " where UPPER(descripcion) like :filtro "  
						+ "or UPPER(costo) like :filtro order bi id");
		List<ServicioDetalle> results = query.getResultList();
		close();
		return results;
	}

}
