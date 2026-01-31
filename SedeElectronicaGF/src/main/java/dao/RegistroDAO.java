package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Registro;
import ln.HibernateUtils;

import java.util.List;

public class RegistroDAO {

    public static void insert(Registro r) throws Exception {
        Session sesion = null;
        Transaction tr = null;
        try {
            sesion = HibernateUtils.getSessionFactory().openSession();
            tr = sesion.beginTransaction();
            sesion.persist(r);
            tr.commit();
        } catch (Exception ex) {
            if (tr != null) {
                try {
                    tr.rollback();
                } catch (Exception ignored) {}
            }
            throw new Exception("Error al insertar registro", ex);
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public static Registro buscarPorIdRegistro(String idRegistro) throws Exception {
        if (idRegistro == null || idRegistro.trim().isEmpty()) {
            return null;
        }

        Session sesion = null;
        Transaction tr = null;
        Registro registro = null;

        try {
            sesion = HibernateUtils.getSessionFactory().openSession();
            tr = sesion.beginTransaction();

            Query<Registro> query = sesion.createQuery("FROM Registro WHERE idRegistro = :id", Registro.class);
            query.setParameter("id", idRegistro.trim());
            registro = query.uniqueResult();

            tr.commit();
        } catch (Exception ex) {
            if (tr != null) {
                try { tr.rollback(); } catch (Exception ignored) {}
            }
            throw ex;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return registro;
    }

    public static boolean existeIdRegistro(String idRegistro) throws Exception {
        return buscarPorIdRegistro(idRegistro) != null;
    }
}