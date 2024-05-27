package py.com.soltech.dao;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import py.com.soltech.conexion.HibernateUtil;

abstract public class DaoGenerico<T> {

	protected Session session;
	protected Class<T> clase;
	protected Transaction tx = null;

	public DaoGenerico(Class<T> clase) {
		this.clase = clase;
	

	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void insertar(T entity) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				String m = e.toString();

				if (m.equals("org.hibernate.exception.ConstraintViolationException: could not execute statement")) {
					JOptionPane.showMessageDialog(null,
							"Este registro esta intentando guardar un dato ya existente \n" + m);
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Se ha generado un error al intentar guardar\n" + m);
				}
			}
			tx.rollback();
		} finally {
			close();
		}

	}

	public void actualizar(T entity) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				System.out.println("no pudo ser guardada");
			}
			tx.rollback();
		} finally {
			close();
		}
	}

	public void eliminar(T entity) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				System.out.println("Entidad no pudo ser eliminada" + e);
			}
			tx.rollback();
		} finally {
			close();
		}

	}

	public T recuperarPorId(Integer id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		T result = (T) session.get(clase, id);
		close();
		return result;

	}

	public List<T> recuperarTodo() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<T> q = session.createQuery("from " + clase.getName());
		List<T> results = q.list();
		close();
		return results;
	}

	abstract public List<T> recuperarPorFiltro(String filtro);

	public void commit() {
		session.getTransaction().commit();
	}

	public void rollback() {
		session.getTransaction().rollback();
	}

	protected void close() {
		session.close();
	}
	
	

}
