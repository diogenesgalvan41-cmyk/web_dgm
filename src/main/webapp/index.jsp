<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Iniciar Sesion</h1>
        <form action="loginservlet" method="post">
            <label for="Username">Usuario:</label>
            <input type="text" id="Username" name="username" required><br><br>
            
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br><br>  
            
            <input type="submit" value="Entrar">
        </form>
    </body>
</html>

