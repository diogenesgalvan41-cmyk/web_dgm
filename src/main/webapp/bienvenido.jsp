<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Página de Bienvenida</title>
</head>
<body>
    <h1>Bienvenido al sistema</h1>

    <%
        String usuario = (String) session.getAttribute("usuario");
        if (usuario != null) {
    %>
        <p>Hola, <strong><%= usuario %></strong>!</p>
    <%
        } else {
    %>
        <p>No hay sesión activa.</p>
    <%
        }
    %>
</body>
</html>

