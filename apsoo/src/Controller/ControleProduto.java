/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.PreparedStatement;
import Model.ModelProduto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ControleProduto {
    
    ConexaoBD conex = new ConexaoBD();
    ModelProduto mod = new ModelProduto();
    
    public void Salvar(ModelProduto mod){
        conex.conexao();   //insert into usuario(nome_usuario, email_usuario, login_usuario, cpf_usuario, senha_usuario) values(?,?,?,?,?)
        
        try {
            PreparedStatement pst = conex.con.prepareStatement("INSERT INTO produto(cod_barras, nome_produto, descricao_produto, fabricante_produto, valor_produto, categoria_produto)" + "VALUES(?,?,?,?,?,?)"); 
  
            pst.setInt(1, mod.getCodBarras());    
            pst.setString(2, mod.getNomeProduto());
            pst.setString(3, mod.getDescricao());
            pst.setString(4, mod.getFabricante());
            pst.setDouble(5, mod.getValorUnitario());
            pst.setString(6, mod.getCategoria());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } 
        
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados"+ex);
        }
        
        conex.desconecta();
    }
    
    
      public void EditarUsuario(ModelProduto mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("UPDATE produto SET cod_barras=?, nome_produto=?, descricao_produto=?, fabricante_produto=?, valor_produto=?, categoria_produto=? WHERE cod_barras=?");
            pst.setInt(1, mod.getCodBarras());
            pst.setString(2, mod.getNomeProduto());
            pst.setString(3, mod.getDescricao());
            pst.setString(4, mod.getFabricante());
            pst.setDouble(5, mod.getValorUnitario());
            pst.setString(6, mod.getCategoria());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao alterar os dados:/n"+ex);
        }
        
        conex.desconecta();
      }
     
     
      public void ExcluirUsuario(ModelProduto mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("DELETE FROM produto WHERE cod_barras=?");
            pst.setInt(1, mod.getCodBarras());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao excluir os dados:/n"+ex);
        }
        
        conex.desconecta();
      }
      
      public ModelProduto buscarUsuario(ModelProduto mod){
        conex.conexao();
        conex.executaSql("SELECT * FROM usuario WHERE nome_usuario LIKE'%" + mod.getConsulta()+ "%'");
        
        try {
            conex.rs.first();
            mod.setCodBarras(conex.rs.getInt("cod_barras"));
            mod.setNomeProduto(conex.rs.getString("nome_produto"));
            mod.setDescricao(conex.rs.getString("descricao_produto"));
            mod.setFabricante(conex.rs.getString("fabricante_produto"));
            mod.setValorUnitario(conex.rs.getDouble("valor_produto"));
            mod.setCategoria(conex.rs.getString("categoria_produto"));
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuario"+ex);
        }
        conex.desconecta();
        return mod;
    }
    
    
}
