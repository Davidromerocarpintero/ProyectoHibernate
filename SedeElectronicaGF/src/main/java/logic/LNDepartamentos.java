package logic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.DAODepartamentos;
import dao.DAOEmpleado;
import entities.Departamentos;
import entities.Empleados;
import resources.HibernateUtils;

public class LNDepartamentos {


	@SuppressWarnings("finally")
	public static String baja(Departamentos d) throws Exception {
		String msj = "";
		Transaction tr = null;
		Departamentos dpto = null;
		try (Session sesion = HibernateUtils.getSessionFactory().openSession();) {
			//Lista empleados del departamento
			List<Empleados> listEmpl = DAOEmpleado.selectByDpto(d);
			//Creo transacci�n
			tr = sesion.beginTransaction();
			//Existe dpto
			dpto = sesion.get(Departamentos.class, d.getDepNo());
			if (dpto != null) {
				// Elimino los empleados del departamento
				if (listEmpl.size() != 0) {
					for (Empleados empleado : listEmpl) {
							sesion.delete(empleado);
					}
				}
				// Elimino departamento
				sesion.delete(dpto);
				//Confirmo transacci�n
				tr.commit();
				msj = "Baja realizada correctamente";
			} else {
				msj = "El departamento no existe";
			}
		} catch (Exception ex) {
			// Si hay excepcion deshacer transacci�n
			tr.rollback();
		} finally {
			return msj;

		}
		
	}

	public static String actualiza(Departamentos d) throws Exception {
		String mensaje = "";
		Departamentos dpto = DAODepartamentos.selectId(d);
		if (dpto != null) {
			if (d.getDnombre() != null)
				dpto.setDnombre(d.getDnombre());
			if (d.getDloc() != null)
				dpto.setDloc(d.getDloc());
			//Actualiza
			DAODepartamentos.update(dpto);
			mensaje = "Actualización realizada correctamente";
		} else {
			mensaje = "El departamento no existe";
		}
		return mensaje;
	}

	public static String alta(Departamentos d) throws Exception {
		String mensaje = "";
		Departamentos dpto = DAODepartamentos.selectId(d);
		if (dpto == null) {
			DAODepartamentos.insert(d);
			mensaje = "Alta realizada correctamente";
		} else {
			mensaje = "El departamento existe";
		}
		return mensaje;
	}

}
