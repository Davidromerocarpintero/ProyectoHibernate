package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.Entidades;
import logic.HibernateUtils;

public class EntidadesDAO {

	public static void insert(Entidades e) {
		try(Session sesion = HibernateUtils.getSessionFactory().openSession()){
			Transaction tr = sesion.beginTransaction();
			sesion.persist(e);
			tr.commit();
		}
	}

	public static Entidades obtenerPorId(int idEntidad) {
		// TODO Auto-generated method stub
		return null;
	}
}