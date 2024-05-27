package py.com.soltech.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.modelo.entidades.Funcionario;

public class FuncionarioDAO extends DaoGenerico<Funcionario> {
    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> recuperarPorFiltro(String filtro) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Funcionario> query = session.createQuery("from " + clase.getName() + " "
                + "where lower(nombre) like :filtro "
                + "or lower(apellido) like :filtro "
                + "or lower(documento) like :filtro "
                + "or lower(email) like :filtro "
                + "or lower(telefono) like :filtro "
                + "or lower(cargo) like :filtro "
                + "order by nombre");
        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        List<Funcionario> results = query.getResultList();
        close();
        return results;
    }

    public List<Funcionario> recuperarPorRango(String nDesde, String nHasta, String aDesde, String aHasta, String dDesde, String dHasta, String cDesde, String cHasta, int idDesde, int idHasta, int indiceOrden) {
        String[] opcionesOrden = {"id", "nombre", "apellido", "documento", "cargo"};
        session = HibernateUtil.getSessionFactory().openSession(); // Inicializar la sesión
        session.beginTransaction();

        String sql = "from Funcionario where nombre between :nDesde and :nHasta "
                + "and apellido between :aDesde and :aHasta "
                + "and documento between :dDesde and :dHasta "
                + "and id between :idDesde and :idHasta "
                + "and cargo between :cDesde and :cHasta "
                + "order by " + opcionesOrden[indiceOrden];

        Query<Funcionario> query = session.createQuery(sql);
        query.setParameter("nDesde", nDesde);
        query.setParameter("nHasta", nHasta);
        query.setParameter("aDesde", aDesde);
        query.setParameter("aHasta", aHasta);
        query.setParameter("dDesde", dDesde);
        query.setParameter("dHasta", dHasta);
        query.setParameter("idDesde", idDesde);
        query.setParameter("idHasta", idHasta);
        query.setParameter("cDesde", cDesde);
        query.setParameter("cHasta", cHasta);

        List<Funcionario> lista = query.getResultList();
        session.getTransaction().commit();
        session.close(); // Cerrar la sesión después de usarla

        return lista;
    }
}
