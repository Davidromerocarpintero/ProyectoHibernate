package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import dao.RegistroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Registro;
import ln.RegistrosLn;

@WebServlet("/ServletHibernate")
public class ServletHibernate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boton = request.getParameter("boton");
        String page = "";

        if ("nuevoRegistro".equalsIgnoreCase(boton)) {
            page = "Registro.jsp";
        } else if ("Consultar".equalsIgnoreCase(boton)) {
            page = "Buscar.jsp";
        } else if ("Buscar".equalsIgnoreCase(boton)) {

            String id = request.getParameter("idRegistro");  
            Registro registro = null;
            boolean encontrado = false;
            String mensaje = "No se ha encontrado ningún registro con ese código.";

            try {
                if (id != null && !id.trim().isEmpty()) {
                    registro = RegistroDAO.buscarPorIdRegistro(id.trim());

                    if (registro != null) {
                        encontrado = true;
                        mensaje = "Registro encontrado correctamente.";
                    }
                } else {
                    mensaje = "Introduce un código de registro válido.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensaje = "Error al consultar: " + e.getMessage();
            }

            request.setAttribute("encontrado", encontrado);
            request.setAttribute("registro", registro);
            request.setAttribute("mensaje", mensaje);

            page = "Consultar.jsp";

        } else {
            page = "Registro.jsp";
        }

        if (!page.isEmpty()) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect("Registro.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boton = request.getParameter("boton");
        String mensaje = "";
        String idRegistroGenerado = null;
        String fechaHora = null;
        boolean exito = false;

        Map<String, String> errores = new HashMap<>();
        Map<String, String> valores = new HashMap<>();

        if ("guardarRegistro".equals(boton)) {

            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String tramite = request.getParameter("tramite");
            String entidad = request.getParameter("entidad");

            valores.put("dni", dni != null ? dni.trim() : "");
            valores.put("nombre", nombre != null ? nombre.trim() : "");
            valores.put("apellidos", apellidos != null ? apellidos.trim() : "");
            valores.put("tramite", tramite != null ? tramite.trim() : "");
            valores.put("entidad", entidad != null ? entidad.trim() : "");

            if (dni == null || dni.trim().isEmpty()) {
                errores.put("dni", "El DNI es obligatorio");
            } else if (!dni.trim().matches("[0-9]{8}[A-Za-z]")) {
                errores.put("dni", "Formato inválido: 8 números + letra");
            }

            if (nombre == null || nombre.trim().isEmpty()) errores.put("nombre", "Nombre obligatorio");
            if (apellidos == null || apellidos.trim().isEmpty()) errores.put("apellidos", "Apellidos obligatorios");
            if (tramite == null || tramite.trim().isEmpty()) errores.put("tramite", "Trámite obligatorio");
            if (entidad == null || entidad.trim().isEmpty()) errores.put("entidad", "Seleccione entidad");

            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("valores", valores);
                request.getRequestDispatcher("Registro.jsp").forward(request, response);
                return;
            }

            try {
                Registro r = new Registro();
                r.setDniSolicitante(dni.trim().toUpperCase());
                r.setNombreSolicitante(nombre.trim());
                r.setApellidosSolicitante(apellidos.trim());
                r.setTramite(tramite.trim());
                r.setEntidad(entidad.trim());

                mensaje = RegistrosLn.alta(r);

                exito = mensaje != null && (mensaje.contains("correctamente") || mensaje.contains("alta"));

                if (exito) {
                    idRegistroGenerado = r.getIdRegistro(); 
                    LocalDateTime ahora = LocalDateTime.now();
                    fechaHora = ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                mensaje = "Error al guardar el registro: " + ex.getMessage();
                exito = false;
            }
        }

        request.setAttribute("exito", exito);
        request.setAttribute("idRegistro", idRegistroGenerado); 
        request.setAttribute("fechaHora", fechaHora);
        request.setAttribute("mensaje", mensaje);

        request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
    }
}