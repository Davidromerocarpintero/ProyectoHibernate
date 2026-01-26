package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.Registro;
import logic.HibernateUtils;

public class RegistroDAO {

	public static void insert(Registro r) throws Exception {
		Transaction tr = null;
		try (Session sesion = HibernateUtils.getSessionFactory().openSession();) {
			tr = sesion.beginTransaction();
			sesion.persist(r);
			tr.commit();
		} catch (Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			throw new Exception("RegistroDAO: Error al realizar el insert del Registro en la base de datos.");
		}

	}

	
	public static Registro selectId(Registro r) throws Exception {

		Transaction tr = null;
		Registro rgto=null;
		try (Session sesion = HibernateUtils.getSessionFactory().openSession();) {
			tr = sesion.beginTransaction();
			//Get permite recuperar el registro de inmediato y recoge null si no existe 
			rgto=sesion.get(Registro.class,r.getIdRegistro());
			
			
			tr.commit();
			
		} catch (Exception ex) {
			if (tr != null) {
				tr.rollback();
			}
			ex.printStackTrace();
		} finally {
			return rgto;
		}


	}
}
