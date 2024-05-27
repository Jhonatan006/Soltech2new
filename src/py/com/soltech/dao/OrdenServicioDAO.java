package py.com.soltech.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.modelo.entidades.Funcionario;
import py.com.soltech.modelo.entidades.OrdenServicio;

public class OrdenServicioDAO extends DaoGenerico<OrdenServicio> {
	public OrdenServicioDAO() {
	super(OrdenServicio.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenServicio> recuperarPorFiltro(String filtro) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<OrdenServicio> query = session
				.createQuery("from " + clase.getName() + " where UPPER(fecha) like :filtro " 
						+ "or UPPER(estado) like :filtro"
						+ "or UPPER(color) like :filtro"
						+ "or UPPER(serial) like :filtro"
						+ "or UPPER(password) like :filtro"
						+ "or UPPER(descripcion) like :filtro order by id");
		List<OrdenServicio> results = query.getResultList();
		close();
		return results;
	}

	public List<OrdenServicio> recuperarPorEstado() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<OrdenServicio> query = session
				.createQuery("from " + clase.getName() + " where estado = 'EN PROCESO' order by id");
		List<OrdenServicio> results = query.getResultList();
		close();
		return results;
	}

	public List<OrdenServicio> recuperarPorOrdenGeneral(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<OrdenServicio> query = session
				.createQuery("from " + clase.getName() + " where id = "+ id +"");
		List<OrdenServicio> results = query.getResultList();
		close();
		return results;
	
}

public List<OrdenServicio> recuperarPorRango(String fDesde, String fHasta, String eDesde, String eHasta, String cDesde, String cHasta, String dDesde, String dHasta, int idDesde, int idHasta, int indiceOrden) {
    String[] opcionesOrden = {"id", "fecha", "estado", "color", "descripcion"};
    session = HibernateUtil.getSessionFactory().openSession(); // Inicializar la sesión
    session.beginTransaction();

    String sql = "from " + clase.getName() + " where "
            + "estado between :eDesde and :eHasta "
            + "and color between :cDesde and :cHasta "
            + "and descripcion between :dDesde and :dHasta "
            + "and id between :idDesde and :idHasta "
            + "order by " + opcionesOrden[indiceOrden];

    Query<OrdenServicio> query = session.createQuery(sql);
//    query.setParameter("fDesde", fDesde);
//    query.setParameter("fHasta", fHasta);
    query.setParameter("eDesde", eDesde);
    query.setParameter("eHasta", eHasta);
    query.setParameter("cDesde", cDesde);
    query.setParameter("cHasta", cHasta);
    query.setParameter("dDesde", dDesde);
    query.setParameter("dHasta", dHasta);
    query.setParameter("idDesde", idDesde);
    query.setParameter("idHasta", idHasta);
   

    List<OrdenServicio> lista = query.getResultList();
    session.getTransaction().commit();
    session.close(); // Cerrar la sesión después de usarla

    return lista;
}
}
