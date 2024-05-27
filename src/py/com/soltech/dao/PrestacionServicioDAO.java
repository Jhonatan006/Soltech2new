package py.com.soltech.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.modelo.entidades.PrestacionServicio;

public class PrestacionServicioDAO extends DaoGenerico<PrestacionServicio> {
    public PrestacionServicioDAO() {
        super(PrestacionServicio.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<PrestacionServicio> recuperarPorFiltro(String filtro) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<PrestacionServicio> query = session.createQuery("from PrestacionServicio "
                + "where lower(descripcion) like :filtro "
                + "or lower(estado) like :filtro "
                + "or lower(funcionario) like :filtro "
                + "or lower(costo) like :filtro "
                + "order by descripcion");
        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        List<PrestacionServicio> results = query.getResultList();
        close();
        return results;
    }

    public List<PrestacionServicio> recuperarPorRango(String dDesde, String dHasta, String eDesde, String eHasta, String fDesde, String fHasta, String cDesde, String cHasta, int indiceOrden) {
        String[] opcionesOrden = {"descripcion", "estado", "funcionario", "costo"};
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "from PrestacionServicio ";
//                + " estado between :eDesde and :eHasta ";
              //  + "order by " + opcionesOrden[indiceOrden];



        Query<PrestacionServicio> query = session.createQuery(sql);
   
//        query.setParameter("eDesde", eDesde);
//        query.setParameter("eHasta", eHasta);
      
        List<PrestacionServicio> lista = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return lista;
    }
}


/**}
public List<PrestacionServicio> recuperarPorFiltro(String filtro) {
	session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	Query<PrestacionServicio> query = session
			.createQuery("from " + clase.getName() + " where UPPER(fecha_inicio) like :filtro " 
					+ "or UPPER(fecha_finalizacion) like :filtro"
					+ "or UPPER(costo_total) like :filtro" 
					+ "or UPPER(estado) like :filtro order by id");
	List<PrestacionServicio> results = query.getResultList();
	close();
	return results;
}**/