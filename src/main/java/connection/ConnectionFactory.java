/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Microsoft
 */
public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/atividadeav02?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            
            System.err.println("Falha na conexão com o banco de dados.");
            e.printStackTrace(); // Isso mostrará a causa exata do erro no console

            
            throw new RuntimeException("Erro na conexão com o banco de dados!", e);
        }
    }
    
}
