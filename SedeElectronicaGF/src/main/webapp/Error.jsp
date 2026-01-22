+<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>GF - Incidencia del sistema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Helvetica, sans-serif;
            background-color: #ececec;
        }

        .barra-superior {
            background-color: #1b5e20;
            color: #ffffff;
            padding: 18px;
            text-align: center;
            font-size: 22px;
        }

        .panel-error {
            background-color: #ffffff;
            max-width: 620px;
            margin: 50px auto;
            padding: 35px;
            border-radius: 8px;
        }

        .icono {
            text-align: center;
            font-size: 52px;
            color: #d32f2f;
            margin-bottom: 15px;
        }

        .titulo-error {
            text-align: center;
            color: #d32f2f;
            font-size: 20px;
            margin-bottom: 25px;
        }

        .detalle {
            background-color: #fdecea;
            border-left: 6px solid #d32f2f;
            padding: 15px;
            font-size: 14px;
            color: #333;
        }

        .acciones {
            margin-top: 35px;
            text-align: center;
        }

        .acciones a {
            background-color: #2e7d32;
            color: #ffffff;
            padding: 12px 35px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 15px;
            display: inline-block;
        }

        .acciones a:hover {
            background-color: #1b5e20;
        }
    </style>
</head>

<body>

<div class="barra-superior">
    SEDE ELECTRÓNICA GF
</div>

<div class="panel-error">
    <div class="icono">⛔</div>

    <div class="titulo-error">
        Se ha producido un problema durante la operación
    </div>

    <%
        String errorMsg = (String) request.getAttribute("error");
        Exception ex = (Exception) request.getAttribute("exception");
    %>

    <% if (errorMsg != null) { %>
        <div class="detalle">
            <strong>Información del error:</strong><br>
            <%= errorMsg %>
        </div>
    <% } else if (ex != null) { %>
        <div class="detalle">
            <strong>Información del error:</strong><br>
            <%= ex.getMessage() %>
        </div>
    <% } else { %>
        <div class="detalle">
            <strong>Error no identificado.</strong><br>
            Por favor, vuelva a intentarlo más tarde.
        </div>
    <% } %>

    <div class="acciones">
        <a href="<%= request.getContextPath() %>/Registro.jsp">
            Regresar al inicio
        </a>
    </div>
</div>

</body>
</html>
