<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado del trámite</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fafafa;
        }

        .titulo {
            background-color: #388e3c;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 23px;
        }

        .contenido {
            width: 90%;
            max-width: 600px;
            margin: 50px auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
        }

        .ok {
            color: #2e7d32;
            font-size: 18px;
            text-align: center;
            margin-bottom: 20px;
        }

        .ko {
            color: #d32f2f;
            font-size: 18px;
            text-align: center;
            margin-bottom: 20px;
        }

        .info {
            border: 1px solid #c8e6c9;
            background-color: #f1f8e9;
            padding: 15px;
            margin-bottom: 25px;
        }

        .info p {
            margin: 6px 0;
        }

        .numero {
            font-weight: bold;
            font-size: 19px;
        }

        .botones {
            text-align: center;
        }

        .botones a {
            background-color: #388e3c;
            color: #fff;
            padding: 10px 25px;
            margin: 5px;
            display: inline-block;
            text-decoration: none;
            border-radius: 4px;
        }

        .botones a:hover {
            background-color: #1b5e20;
        }
    </style>
</head>

<body>

<div class="titulo">SEDE ELECTRÓNICA GF</div>

<div class="contenido">
    <%
        Boolean exito = (Boolean) request.getAttribute("exito");
        String numRegistro = (String) request.getAttribute("numRegistro");
        Date fechaRegistro = (Date) request.getAttribute("fechaRegistro");
        String mensaje = (String) request.getAttribute("mensaje");
    %>

    <% if (Boolean.TRUE.equals(exito)) { %>
        <div class="ok">
            Registro realizado correctamente
        </div>

        <div class="info">
            <p>Nº Registro: <span class="numero"><%= numRegistro %></span></p>
            <p>Fecha de alta: <%= fechaRegistro %></p>
        </div>
    <% } else { %>
        <div class="ko">
            Error al registrar el trámite.<br>
            <%= mensaje != null ? mensaje : "" %>
        </div>
    <% } %>

    <div class="botones">
        <a href="<%= request.getContextPath() %>/Registro.jsp">Nuevo registro</a>
        <a href="<%= request.getContextPath() %>/Buscar.jsp">Consultar</a>
    </div>
</div>

</body>
</html>
