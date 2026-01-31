<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.EntidadesDAO"%>
<%@ page import="entities.Entidades"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>GF - Alta de Trámite</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Verdana, sans-serif;
            background-color: #eef1ee;
        }

        .top-bar {
            background-color: #1b5e20;
            color: #fff;
            padding: 15px;
            text-align: center;
            font-size: 22px;
            letter-spacing: 1px;
        }

        .box {
            width: 90%;
            max-width: 620px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 35px;
            border-radius: 10px;
        }

        .campo {
            margin-bottom: 22px;
        }

        .campo label {
            font-weight: 600;
            margin-bottom: 6px;
            display: block;
            color: #2f2f2f;
        }

        .obligatorio {
            color: #c62828;
        }

        .campo input,
        .campo select {
            width: 100%;
            padding: 11px;
            font-size: 14px;
            border-radius: 6px;
            border: 1px solid #bbb;
        }

        .campo input:focus,
        .campo select:focus {
            border-color: #1b5e20;
            outline: none;
        }

        .acciones {
            text-align: center;
            margin-top: 35px;
        }

        .acciones button {
            background-color: #1b5e20;
            color: white;
            padding: 12px 45px;
            font-size: 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .acciones button:hover {
            background-color: #2e7d32;
        }

        .mensaje-error {
            font-size: 12px;
            color: red;
            margin-top: 4px;
        }
        .campo.error input,
        .campo.error select {
            border: 1px solid #c62828;
            background-color: #ffebee;
        }
    </style>
</head>

<body>

<div class="top-bar">
    SEDE ELECTRÓNICA GF
</div>

<div class="box">
    <form method="post" action="ServletHibernate">
        <input type="hidden" name="accion" value="grabar">

        <%
            Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
            Map<String, String> valores = (Map<String, String>) request.getAttribute("valores");
            if (errores == null) errores = new HashMap<>();
            if (valores == null) valores = new HashMap<>();
        %>

        <div class="campo <%= errores.containsKey("dni") ? "error" : "" %>">
            <label for="dni">DNI del solicitante <span class="obligatorio">*</span></label>
            <input type="text"
                   id="dni"
                   name="dni"
                   required
                   placeholder="00000000X"
                   pattern="[0-9]{8}[A-Za-z]"
                   title="8 números y una letra"
                   value="<%= valores.getOrDefault("dni", "") %>">
            <% if (errores.containsKey("dni")) { %>
                <div class="mensaje-error"><%= errores.get("dni") %></div>
            <% } %>
        </div>

        <div class="campo <%= errores.containsKey("nombre") ? "error" : "" %>">
            <label for="nombre">Nombre <span class="obligatorio">*</span></label>
            <input type="text"
                   id="nombre"
                   name="nombre"
                   required
                   maxlength="100"
                   placeholder="Introduce el nombre"
                   value="<%= valores.getOrDefault("nombre", "") %>">
            <% if (errores.containsKey("nombre")) { %>
                <div class="mensaje-error"><%= errores.get("nombre") %></div>
            <% } %>
        </div>

        <div class="campo <%= errores.containsKey("apellidos") ? "error" : "" %>">
            <label for="apellidos">Apellidos <span class="obligatorio">*</span></label>
            <input type="text"
                   id="apellidos"
                   name="apellidos"
                   required
                   maxlength="150"
                   placeholder="Introduce los apellidos"
                   value="<%= valores.getOrDefault("apellidos", "") %>">
            <% if (errores.containsKey("apellidos")) { %>
                <div class="mensaje-error"><%= errores.get("apellidos") %></div>
            <% } %>
        </div>

        <div class="campo <%= errores.containsKey("tramite") ? "error" : "" %>">
            <label for="tramite">Trámite solicitado <span class="obligatorio">*</span></label>
            <input type="text"
                   id="tramite"
                   name="tramite"
                   required
                   maxlength="200"
                   placeholder="Detalle del trámite"
                   value="<%= valores.getOrDefault("tramite", "") %>">
            <% if (errores.containsKey("tramite")) { %>
                <div class="mensaje-error"><%= errores.get("tramite") %></div>
            <% } %>
        </div>

        <div class="campo <%= errores.containsKey("entidad") ? "error" : "" %>">
            <label for="entidad">Entidad responsable <span class="obligatorio">*</span></label>
            <select id="entidad" name="entidad" required>
                <option value="">Seleccione una opción</option>
                <%
                    try {
                        EntidadesDAO dao = new EntidadesDAO();
                        List<Entidades> lista = dao.getALL();
                        String entidadSel = valores.getOrDefault("entidad", "");
                        for (Entidades e : lista) {
                %>
                    <option value="<%= e.getNombre() %>" <%= e.getNombre().equals(entidadSel) ? "selected" : "" %>>
                        <%= e.getNombre() %>
                    </option>
                <%
                        }
                    } catch (Exception ex) {
                        out.println("<option>Error al cargar entidades</option>");
                        ex.printStackTrace();
                    }
                %>
            </select>
            <% if (errores.containsKey("entidad")) { %>
                <div class="mensaje-error"><%= errores.get("entidad") %></div>
            <% } %>
        </div>

        <div class="acciones">
            <button type="submit" name="boton" value="guardarRegistro">Guardar registro</button>
        </div>
    </form>
</div>

</body>
</html>