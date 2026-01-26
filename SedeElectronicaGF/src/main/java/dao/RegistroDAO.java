package dao;

import entities.Registro;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class RegistroDAO {

    public String insertar(Registro registro) {
        Transaction tx = null;
        String codigoGenerado;

        try (Session sesion = HibernateUtiles.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            sesion.save(registro);
            codigoGenerado = registro.getIdRegistro();

            tx.commit();
            System.out.println("Registro insertado correctamente: " + codigoGenerado);

            return codigoGenerado;

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Fallo al insertar registro");
            throw new RuntimeException("No se pudo guardar el registro", ex);
        }
    }

    public Registro obtenerPorNumero(String numeroRegistro) {
        Transaction tx = null;
        Registro resultado = null;

        try (Session sesion = HibernateUtiles.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            resultado = sesion.get(Registro.class, numeroRegistro);

            if (resultado != null && resultado.getEntidad() != null) {
                // Fuerza la carga de la entidad asociada
                resultado.getEntidad();
            }

            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al recuperar el registro con número: " + numeroRegistro);
            ex.printStackTrace();
        }

        return resultado;
    }

    public long contarRegistros() {
        Transaction tx = null;
        long total = 0;

        try (Session sesion = HibernateUtiles.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            Query<Long> consulta = sesion.createQuery(
                    "SELECT COUNT(r) FROM Registros r", Long.class);
            total = consulta.getSingleResult();

            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("No se pudo calcular el número de registros");
            ex.printStackTrace();
        }

        return total;
    }
}
