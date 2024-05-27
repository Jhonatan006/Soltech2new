package py.com.soltech.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;
import py.com.soltech.modelo.entidades.Cliente;

	public class ClienteDAO extends DaoGenerico<Cliente> {
		public ClienteDAO() {
			super(Cliente.class);
		}

		@Override
	    public List<Cliente> recuperarPorFiltro(String filtro) {
	        session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
	        Query<Cliente> query = session.createQuery("from " + clase.getName() + " "
	                + "where lower (nombre) like :filtro "
	                + "or lower (apellido) like :filtro "
	                + "or lower (email) like :filtro "
	                + "order by nombre");
	        query.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
	        List<Cliente> results = query.getResultList();
	        close();
	        return results;
	    }
		public List<Cliente> recuperarPorRango(String nDesde, String nHasta, String aDesde, String aHasta, String dDesde, String dHasta, int idDesde,int idHasta,int indiceOrden) {
			String[] opcionesOrden = {"id","Nombre","Apellido","Documento"};
		    session = HibernateUtil.getSessionFactory().openSession(); // Inicializar la sesión
		    session.beginTransaction();

		    String sql = "from Cliente where nombre between :nDesde and :nHasta "
		            + "and apellido between :aDesde and :aHasta "
		            + "and documento between :dDesde and :dHasta "
		            + "and id between :idDesde and :idHasta "
		            + "order by "+opcionesOrden[indiceOrden];


		    Query<Cliente> query = session.createQuery(sql);
		    query.setParameter("nDesde", nDesde);
		    query.setParameter("nHasta", nHasta);
		    query.setParameter("aDesde", aDesde);
		    query.setParameter("aHasta", aHasta);
		    query.setParameter("dDesde", dDesde);
		    query.setParameter("dHasta", dHasta);
		    query.setParameter("idDesde", idDesde);
		    query.setParameter("idHasta", idHasta);

		    List<Cliente> lista = query.getResultList();
		    session.getTransaction().commit();
		    session.close(); // Cerrar la sesión después de usarla

		    return lista;
		}


	

	}


