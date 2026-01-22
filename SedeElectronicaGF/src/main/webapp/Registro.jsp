<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.EntidadesDAO"%>
<%@ page import="entities.Entidades"%>
<%@ page import="java.util.List"%>
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
    </style>
</head>

<body>

<div class="top-bar">
    SEDE ELECTRÓNICA GF
</div>

<div class="box">
    <form method="post" action="ServletRegistro">
        <input type="hidden" name="accion" value="grabar">

        <div class="campo">
            <label for="dni">DNI del solicitante <span class="obligatorio">*</span></label>
            <input type="text"
                   id="dni"
                   name="dni"
                   required
                   placeholder="00000000X"
                   pattern="[0-9]{8}[A-Za-z]"
                   title="8 números y una letra">
            <div class="mensaje-error" id="errorDni"></div>
        </div>

        <div class="campo">
            <label for="nombre">Nombre <span class="obligatorio">*</span></label>
            <input type="text"
                   id="nombre"
                   name="nombre"
                   required
                   maxlength="100"
                   placeholder="Introduce el nombre">
        </div>

        <div class="campo">
            <label for="apellidos">Apellidos <span class="obligatorio">*</span></label>
            <input type="text"
                   id="apellidos"
                   name="apellidos"
                   required
                   maxlength="150"
                   placeholder="Introduce los apellidos">
        </div>

        <div class="campo">
            <label for="tramite">Trámite solicitado <span class="obligatorio">*</span></label>
            <input type="text"
                   id="tramite"
                   name="tramite"
                   required
                   maxlength="200"
                   placeholder="Detalle del trámite">
        </div>

        <div class="campo">
            <label for="idEntidad">Entidad responsable <span class="obligatorio">*</span></label>
            <select id="idEntidad" name="idEntidad" required>
                <option value="">Seleccione una opción</option>
                <%
                    try {
                        EntidadesDAO dao = new EntidadesDAO();
                        List<Entidad> lista = dao.listarEntidades();

                        for (Entidad e : lista) {
                %>
                    <option value="<%= e.getIdEntidad() %>">
                        <%= e.getNombreEntidad() %>
                    </option>
                <%
                        }
                    } catch (Exception ex) {
                        out.println("<option>Error al cargar entidades</option>");
                    }
                %>
            </select>
        </div>

        <div class="acciones">
            <button type="submit">Guardar trámite</button>
        </div>
    </form>
</div>

<script>
    const dniInput = document.getElementById("dni");
    const error = document.getElementById("errorDni");
    const regexDni = /^[0-9]{8}[A-Z]$/;

    dniInput.addEventListener("input", () => {
        dniInput.value = dniInput.value.toUpperCase();
    });

    dniInput.addEventListener("blur", () => {
        if (dniInput.value !== "" && !regexDni.test(dniInput.value)) {
            error.textContent = "El DNI debe tener 8 números y una letra.";
        } else {
            error.textContent = "";
        }
    });
</script>

</body>
</html>
