/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.locadora.dal;

import java.sql.*;

/**
 *
 * @author guilh3rme
 */
public class ModuloConexao {

    // metodo que estabelece a conexão com o banco.
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // A variável abaixo chama o driver para conexão com o banco.
        String driver = "com.mysql.jdbc.Driver";
        // A variável abaixo armazena informações referente ao banco.
        String url = "jdbc:mysql://localhost:3306/videolocadora";
        String user = "root";
        String password = "";
        // Agora vamos estabelecer a conexão com o banco.
        try {
           Class.forName(driver);
           conexao = DriverManager.getConnection(url, user, password);
           return conexao;
           
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        
    }
}
