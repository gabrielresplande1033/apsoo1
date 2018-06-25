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
    
    public void EditarUsuario(modelUsuario mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("UPDATE usuario SET nome_usuario=?, email_usuario=?, login_usuario=?, cpf_usuario=? WHERE id_serial=?");
            pst.setString(1, mod.getNomeUsuario());
            pst.setString(2, mod.getEmailusuario());
            pst.setString(3, mod.getLoginUsuario());
            pst.setString(4, mod.getCpfUsuario());
            pst.setInt(5, mod.getIdUsuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao alterar os dados:/n"+ex);
        }
        
        conex.desconecta();
    }
    
    public void ExcluirUsuario(modelUsuario mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("DELETE FROM usuario WHERE id_serial=?");
            pst.setInt(1, mod.getIdUsuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao excluir os dados:/n"+ex);
        }
        
        conex.desconecta();
    }
    
    public modelUsuario buscarUsuario(modelUsuario mod){
        conex.conexao();
        conex.executaSql("SELECT * FROM usuario WHERE nome_usuario LIKE'%" + mod.getConsulta()+ "%'");
        
        try {
            conex.rs.first();
            mod.setIdUsuario(conex.rs.getInt("id_serial"));
            mod.setNomeUsuario(conex.rs.getString("nome_usuario"));
            mod.setEmailusuario(conex.rs.getString("email_usuario"));
            mod.setLoginUsuario(conex.rs.getString("login_usuario"));
            mod.setCpfUsuario(conex.rs.getString("cpf_usuario"));
            mod.setSenhaUsuario(conex.rs.getString("senha_usuario"));
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuario"+ex);
        }
        conex.desconecta();
        return mod;
    }
    
}
