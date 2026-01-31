package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import entities.Entidades;
import entities.Registro;
import jakarta.persistence.criteria.CriteriaQuery;
import ln.HibernateUtils;

public class EntidadesDAO {
	
	public static List<Entidades> getALL() throws Exception {

		Transaction tr = null;
		List<Entidades> ents=null;
		try (Session sesion = HibernateUtils.getSessionFactory().openSession();) {
			tr = sesion.beginTransaction();
			CriteriaQuery<Entidades> consulta = sesion.getCriteriaBuilder().createQuery(Entidades.class);
			consulta.from(Entidades.class);
			ents = sesion.createQuery(consulta).getResultList();
			tr.commit();
			return ents;
	
		} 
	}

	public static Entidades selectNombre(String nombre) throws Exception {
	    Transaction tr = null;
	    Entidades ent = null;
	    try (Session sesion = HibernateUtils.getSessionFactory().openSession()) {
	        tr = sesion.beginTransaction();
	        ent = sesion.createQuery("FROM Entidades e WHERE e.nombre = :nombre", Entidades.class)
	                    .setParameter("nombre", nombre)
	                    .uniqueResult();
	        tr.commit();
	    } catch (Exception ex) {
	        if (tr != null) tr.rollback();
	        ex.printStackTrace();
	    }
	    return ent;
	}

}