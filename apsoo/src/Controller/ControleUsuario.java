/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.PreparedStatement;
import Model.modelUsuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControleUsuario {  // Controle é responsavel por realizar os processos de controle de BD (Salvar, editar, excluir...)
    
    ConexaoBD conex = new ConexaoBD();
    modelUsuario mod = new modelUsuario();
    
    public void Salvar(modelUsuario mod){
        conex.conexao();   //insert into usuario(nome_usuario, email_usuario, login_usuario, cpf_usuario, senha_usuario) values(?,?,?,?,?)
        try {
            PreparedStatement pst = conex.con.prepareStatement("INSERT INTO usuario(nome_usuario, email_usuario, login_usuario, cpf_usuario, senha_usuario)" + "VALUES(?,?,?,?,?)"); 
  
            pst.setString(1, mod.getNomeUsuario());
            pst.setString(2, mod.getEmailusuario());
            pst.setString(3, mod.getLoginUsuario());
            pst.setString(4, mod.getCpfUsuario());
            pst.setString(5, mod.getSenhaUsuario());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados"+ex);
        }
        
        conex.desconecta();
    }
    
}
