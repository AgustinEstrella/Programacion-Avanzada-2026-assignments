package com.mycompany.solidplataformaeduvirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstudianteBd {
    private ConexionBd conexionBd;

    public EstudianteBd() {
        this.conexionBd = new ConexionBd();
    }

    public void guardar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (nombre, email) VALUES (?, ?)";

        try (Connection conn = conexionBd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getEmail());
            pstmt.executeUpdate();
            System.out.println("Estudiante guardado exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al guardar el estudiante: " + e.getMessage());
        }
    }

    public void listar() {
        String sql = "SELECT * FROM estudiantes";

        try (Connection conn = conexionBd.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Lista de Estudiantes ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                System.out.println("ID: " + id + " | Nombre: " + nombre + " | Email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes: " + e.getMessage());
        }
    }
}
