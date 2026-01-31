package ln;

import dao.RegistroDAO;
import entities.Registro;

public class RegistrosLn {

	public static String alta(Registro r) {
	    try {
	        String dni = r.getDniSolicitante();
	        if (dni == null || dni.length() < 9) {
	            throw new Exception("DNI inválido para generar número de registro");
	        }
	        String ultimos3Dni = dni.substring(dni.length() - 3).toUpperCase(); // 2 números + letra

	        String nom2 = (r.getNombreSolicitante() != null && !r.getNombreSolicitante().trim().isEmpty())
	                ? r.getNombreSolicitante().trim().substring(0, 2).toUpperCase()
	                : "XX";

	        String ape2 = (r.getApellidosSolicitante() != null && !r.getApellidosSolicitante().trim().isEmpty())
	                ? r.getApellidosSolicitante().trim().substring(0, 2).toUpperCase()
	                : "XX";

	        String IdRegistro = "REG_" + nom2 + ape2 + ultimos3Dni;

	        r.setIdRegistro(IdRegistro);

	        RegistroDAO.insert(r);
	        return "Registro dado de alta correctamente. Nº: " + IdRegistro;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return "Error al dar de alta el registro: " + ex.getMessage();
	    }
	}
}
