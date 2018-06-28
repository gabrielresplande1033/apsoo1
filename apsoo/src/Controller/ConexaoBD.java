/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConexaoBD {
    
    public Statement stm; // realizar consulta
    public ResultSet rs; // armazenar resultado consulta
    private String driver = "org.postgresql.Driver"; // identificar servico
    private String caminho = "jdbc:postgresql://localhost:5432/bancobrinquedo"; // setar local BD
    private String usuario = "postgres"; // usuario BD
    private String senha = "teste1212"; // senha BD
    public Connection con; // realizar conexao
    
    public void conexao(){
    
        System.setProperty("jdbc.Drivers", driver);
        try {
            con = DriverManager.getConnection(caminho, usuario, senha);
                    // JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!");
                    } catch (SQLException ex) {
                          JOptionPane.showMessageDialog(null, "Problema ao se conectar com o banco de dados:\n"+ex);
                      }
    }
    
    public void executaSql(String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao executar consulta SQL:\n"+ex);
        }
        
    }
    
    public void desconecta(){
        try {
            con.close();
             // JOptionPane.showMessageDialog(null, "Banco Desconectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao fechar conexão com o banco de dados:\n"+ex);
        }
    }
    
    
}
