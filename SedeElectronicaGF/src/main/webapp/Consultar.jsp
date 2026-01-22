<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sede.model.Registros"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>GF - Resultado de la Consulta</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Verdana, sans-serif;
            background-color: #eef2ee;
        }

        .barra {
            background-color: #1b5e20;
            color: #ffffff;
            text-align: center;
            padding: 18px;
            font-size: 24px;
            font-weight: 600;
        }

        .contenedor {
            background-color: #ffffff;
            max-width: 750px;
            margin: 45px auto;
            padding: 35px;
            border-radius: 12px;
        }

        .titulo {
            text-align: center;
            color: #2e7d32;
            margin-bottom: 25px;
            font-size: 22px;
        }

        .ficha {
            border: 1px solid #c8e6c9;
            border-radius: 8px;
            overflow: hidden;
            margin-bottom: 35px;
        }

        .fila {
            display: flex;
            padding: 14px 18px;
            border-bottom: 1px solid #e0e0e0;
        }

        .fila:nth-child(even) {
            background-color: #f9fdf9;
        }

        .etiqueta {
            width: 40%;
            font-weight: bold;
            color: #555;
        }

        .valor {
            width: 60%;
        }

        .aviso {
            text-align: center;
            color: #c62828;
            font-size: 18px;
            margin-bottom: 30px;
        }

        .acciones {
            text-align: center;
        }

        .acciones a {
            display: inline-block;
            background-color: #2e7d32;
            color: white;
            padding: 12px 30px;
            margin: 5px 10px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 15px;
        }

        .acciones a:hover {
            background-color: #1b5e20;
        }
    </style>
</head>

<body>

<div class="barra">
    SEDE ELECTRÓNICA GF
</div>

<div class="contenedor">
    <%
        Boolean encontrado = (Boolean) request.getAttribute("encontrado");
        Registros registro = (Registros) request.getAttribute("registro");
        String mensaje = (String) request.getAttribute("mensaje");
    %>

    <% if (Boolean.TRUE.equals(encontrado) && registro != null) { %>

        <div class="titulo">Información del Registro</div>

        <div class="ficha">
            <div class="fila">
                <div class="etiqueta">Número de registro</div>
                <div class="valor"><%= registro.getNumRegistro() %></div>
            </div>
            <div class="fila">
                <div class="etiqueta">DNI solicitante</div>
                <div class="valor"><%= registro.getDniSolicitante() %></div>
            </div>
            <div class="fila">
                <div class="etiqueta">Nombre</div>
                <div class="valor"><%= registro.getNombreSolicitante() %></div>
            </div>
            <div class="fila">
                <div class="etiqueta">Apellidos</div>
                <div class="valor"><%= registro.getApellidosSolicitante() %></div>
            </div>
            <div class="fila">
                <div class="etiqueta">Trámite</div>
                <div class="valor"><%= registro.getTramite() %></div>
            </div>
            <div class="fila">
                <div class="etiqueta">Entidad</div>
                <div class="valor"><%= registro.getEntidad().getNombreEntidad() %></div>
            </div>
            <div class="fila">
                <div class="etiqueta">Fecha de registro</div>
                <div class="valor"><%= registro.getFechaRegistro() %></div>
            </div>
        </div>

        <div class="acciones">
            <a href="<%= request.getContextPath() %>/Registro.jsp">Nuevo registro</a>
            <a href="<%= request.getContextPath() %>/Buscar.jsp">Otra consulta</a>
        </div>

    <% } else { %>

        <div class="aviso">
            <%= mensaje != null ? mensaje : "No se ha encontrado ningún registro con los datos indicados." %>
        </div>

        <div class="acciones">
            <a href="<%= request.getContextPath() %>/Registro.jsp">Nuevo registro</a>
            <a href="<%= request.getContextPath() %>/Buscar.jsp">Volver a buscar</a>
        </div>

    <% } %>
</div>

</body>
</html>
