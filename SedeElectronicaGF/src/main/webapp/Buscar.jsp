<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>GF - Consulta de Trámite</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            font-family: Verdana, sans-serif;
            background-color: #eef2ee;
        }

        .barra {
            background-color: #1b5e20;
            color: white;
            padding: 18px;
            text-align: center;
            font-size: 24px;
            font-weight: 600;
        }

        .panel {
            max-width: 580px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 35px;
            border-radius: 10px;
        }

        .campo {
            margin-bottom: 25px;
        }

        .campo label {
            display: block;
            font-weight: bold;
            margin-bottom: 6px;
            color: #333;
        }

        .campo input {
            width: 100%;
            padding: 12px;
            border-radius: 6px;
            border: 1px solid #bbb;
            font-size: 15px;
        }

        .campo input:focus {
            outline: none;
            border-color: #2e7d32;
        }

        .obligatorio {
            color: #d32f2f;
        }

        .acciones {
            text-align: center;
            margin-top: 30px;
        }

        .acciones button {
            background-color: #2e7d32;
            color: white;
            padding: 14px 45px;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
        }

        .acciones button:hover {
            background-color: #1b5e20;
        }

        .volver {
            text-align: center;
            margin-top: 25px;
        }

        .volver a {
            color: #2e7d32;
            text-decoration: none;
            font-size: 14px;
        }

        .volver a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<div class="barra">
    SEDE ELECTRÓNICA GF
</div>

<div class="panel">
    <form method="get" action="ServletRegistro">
        <input type="hidden" name="accion" value="buscar">

        <div class="campo">
            <label for="numRegistro">
                Código de registro <span class="obligatorio">*</span>
            </label>
            <input type="text"
                   id="numRegistro"
                   name="numRegistro"
                   required
                   placeholder="REG_000001">
        </div>

        <div class="acciones">
            <button type="submit">Consultar</button>
        </div>
    </form>

    <div class="volver">
        <a href="<%= request.getContextPath() %>/Registro.jsp">
            ← Volver al formulario principal
        </a>
    </div>
</div>

</body>
</html>
