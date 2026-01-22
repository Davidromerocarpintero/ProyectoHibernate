package dao;

import org.hibernate.Session;


import org.hibernate.Transaction;

import entities.Registro;
import logic.HibernateUtils;

public class RegistroDAO {

	public static void insert(Registro r) {
		try(Session sesion = HibernateUtils.getSessionFactory().openSession()){
			Transaction tr = sesion.beginTransaction();
			sesion.persist(r);
			tr.commit();
		}
	}

	public Registro buscarPorNumero(String trim) {
		// TODO Auto-generated method stub
		return null;
	}
}
