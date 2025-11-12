package com.mycompany.web_dgm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/loginservlet"})
public class LoginServlet extends HttpServlet {

    // Configuración de la base de datos
    private final String DB_URL = "jdbc:mysql://localhost:3306/login_db";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Capturar datos del formulario
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                out.println("<h3>Por favor, ingrese sus credenciales desde el formulario.</h3>");
                return;
            }

            // Conexión y consulta a la base de datos
            try {
                // Cargar driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement ps = conn.prepareStatement(
                             "SELECT * FROM usuarios WHERE username=? AND password=?")) {

                    ps.setString(1, username);
                    ps.setString(2, password);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            // Usuario encontrado, crear sesión
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", username);
                            response.sendRedirect("bienvenido.jsp");
                        } else {
                            // Usuario no encontrado
                            response.sendRedirect("error.jsp");
                        }
                    }

                }

            } catch (ClassNotFoundException | SQLException e) {
                out.println("<h3>Error al conectar a la base de datos: " + e.getMessage() + "</h3>");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet de inicio de sesión conectado a MySQL con métodos GET y POST";
    }
}
