package py.com.soltech.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.modelo.entidades.Equipo;

public class EquipoDAO extends DaoGenerico<Equipo> {
    public EquipoDAO() {
        super(Equipo.class);
    }

    @Override
    public List<Equipo> recuperarPorFiltro(String filtro) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Equipo> query = session.createQuery("from " + clase.getName() + " "
        		+ "where lower(modelo) like :filtro "
                + "or lower(marca) like :filtro "
                + "order by modelo");
        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        List<Equipo> results = query.getResultList();
        close();
        return results;
    }
    public List<Equipo> recuperarPorRango(String MDesde, String MHasta, String mDesde, String mHasta, String estadoDesde, String estadoHasta, int idDesde, int idHasta, int indiceOrden) {
        String[] opcionesOrden = {"id", "modelo", "marca", "estado"};
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "from Equipo where modelo between :MDesde and :MHasta "
                + "and marca between :mDesde and :mHasta "
                + "and id between :idDesde and :idHasta "
                + "order by " + opcionesOrden[indiceOrden];

        Query<Equipo> query = session.createQuery(sql);
        query.setParameter("MDesde", MDesde);
        query.setParameter("MHasta", MHasta);
        query.setParameter("mDesde", mDesde);
        query.setParameter("mHasta", mHasta);
        query.setParameter("idDesde", idDesde);
        query.setParameter("idHasta", idHasta);

        List<Equipo> lista = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return lista;
    }


}