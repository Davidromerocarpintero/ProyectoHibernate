package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.EntidadesDAO;
import dao.RegistroDAO;
import entities.Entidades;
import entities.Registro;
import logic.RegistroLN;

@WebServlet("/ServletHibernate")
public class ServletHibernate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletHibernate() {
        super();
    }

    private EntidadesDAO entidadesDAO;
    private RegistroDAO registroDAO;

    @Override
    public void init() throws ServletException {
        entidadesDAO = new EntidadesDAO();
        registroDAO = new RegistroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boton = request.getParameter("boton");
        String page = "";

        switch (boton) {

        case "Nuevo Registro":
            page = "Registro.jsp";
            break;

        case "Consulta Registro":
            page = "Buscar.jsp";
            break;

        case "Buscar":
            try {
                int numRegistro = Integer.parseInt(request.getParameter("numRegistro"));
                Registro r = registroDAO.buscarPorNumero(numRegistro);

                if (r != null) {
                    request.setAttribute("registro", r);
                } else {
                    request.setAttribute("mensaje", "El registro no existe");
                }
                page = "Consultar.jsp";

            } catch (Exception e) {
                request.setAttribute("mensaje", "Error en la búsqueda");
                page = "Mensaje.jsp";
            }
            break;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boton = request.getParameter("boton");
        String mensaje = "";
        boolean error = false;

        try {
            if ("Guardar".equals(boton)) {

                Registro r = new Registro();
                r.setSolicitante(request.getParameter("solicitante"));
                r.setAsunto(request.getParameter("asunto"));
                r.setDescripcion(request.getParameter("descripcion"));

                int idEntidad = Integer.parseInt(request.getParameter("entidad"));
                Entidades e = entidadesDAO.buscarPorId(idEntidad);
                r.setEntidad(e);

                mensaje = RegistroLN.alta(r);
            }

        } catch (Exception ex) {
            error = true;
            ex.printStackTrace();
        } finally {
            if (error) {
                mensaje = "Error al guardar el registro";
            }
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("Mensaje.jsp").forward(request, response);
        }
    }
}
