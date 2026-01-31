package ln;


import dao.RegistroDAO;
import entities.Entidades;
import entities.Registro;

import java.util.regex.Pattern;

public class RegistrosLn {

    private RegistroDAO registroDAO;

    private static final Pattern DNI_PATTERN = Pattern.compile("^[0-9]{8}[A-Za-z]$");

    public RegistrosLn() {
        this.registroDAO = new RegistroDAO();
    }

    public boolean validarDatos(String dni, String nombre, String apellidos, String tramite, int idEntidad) {
        if (dni == null || dni.trim().isEmpty()) {
            System.out.println("Validación fallida: DNI vacío");
            return false;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Validación fallida: Nombre vacío");
            return false;
        }

        if (apellidos == null || apellidos.trim().isEmpty()) {
            System.out.println("Validación fallida: Apellidos vacíos");
            return false;
        }

        if (tramite == null || tramite.trim().isEmpty()) {
            System.out.println("Validación fallida: Trámite vacío");
            return false;
        }

        if (idEntidad <= 0) {
            System.out.println("Validación fallida: ID de entidad inválido");
            return false;
        }

        if (!DNI_PATTERN.matcher(dni.trim().toUpperCase()).matches()) {
            System.out.println("Validación fallida: Formato de DNI incorrecto");
            return false;
        }

        if (nombre.length() > 100) {
            System.out.println("Validación fallida: Nombre demasiado largo");
            return false;
        }

        if (apellidos.length() > 150) {
            System.out.println("Validación fallida: Apellidos demasiado largos");
            return false;
        }

        if (tramite.length() > 200) {
            System.out.println("Validación fallida: Trámite demasiado largo");
            return false;
        }

        System.out.println("Validación exitosa");
        return true;
    }
    

    public String generarNumeroRegistro() {
        long contador = registroDAO.obtenerContador();
        contador++;

        String numRegistro = String.format("REG_%06d", contador);

        System.out.println("Número de registro generado: " + numRegistro);
        return numRegistro;
    }

    public String procesarRegistro(Registro registro) {
        String numRegistro = generarNumeroRegistro();
        registro.setIdRegistro(numRegistro);

        String resultado = registroDAO.guardarRegistro(registro);

        System.out.println("Registro procesado: " + resultado);
        return resultado;
    }

    public boolean validarFormatoDNI(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return false;
        }
        return DNI_PATTERN.matcher(dni.trim().toUpperCase()).matches();
    }
}