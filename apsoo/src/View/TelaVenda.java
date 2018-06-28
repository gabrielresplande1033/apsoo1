/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ConexaoBD;
import Model.ModelItemProduto;
import Model.ModelProduto;
import Model.ModelTabela;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author gabriel
 */
public class TelaVenda extends javax.swing.JFrame {

    ConexaoBD conex = new ConexaoBD();
    ArrayList dados;
    ArrayList<ModelItemProduto> itens;
    
    public TelaVenda() {
        initComponents();
        preencherCliente();
        preencherProduto();
        preencherPagamento();
        jLabelTotal.setText("0");
        dados = new ArrayList();
        itens = new ArrayList<ModelItemProduto>();
    }
    
    public void preencherCliente(){
        conex.conexao();
        conex.executaSql("SELECT nome_cliente FROM cliente ORDER BY nome_cliente");
        try {
            conex.rs.first();
            jComboCliente.removeAllItems();
            
            do{
                jComboCliente.addItem(conex.rs.getString("nome_cliente"));
            }while(conex.rs.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conex.desconecta();
    }
    
    public void preencherProduto(){
        conex.conexao();
        conex.executaSql("SELECT nome_produto FROM produto ORDER BY nome_produto");
        try {
            conex.rs.first();
            jComboProduto.removeAllItems();
            
            do{
                jComboProduto.addItem(conex.rs.getString("nome_produto"));
            }while(conex.rs.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conex.desconecta();
    }
    
    public void preencherPagamento(){
        conex.conexao();
        conex.executaSql("SELECT nome_forma_pagamento FROM formapagamento ORDER BY nome_forma_pagamento");
        try {
            conex.rs.first();
            jComboFormaPagamento.removeAllItems();
            
            do{
                jComboFormaPagamento.addItem(conex.rs.getString("nome_forma_pagamento"));
            }while(conex.rs.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conex.desconecta();
    
    }
    
    public String retornarCPFCliente(){
        String cpf = "";
        conex.conexao();
        conex.executaSql("SELECT cpf_cliente FROM cliente WHERE nome_cliente LIKE'%" + jComboCliente.getSelectedItem().toString()+ "%'");
        conex.desconecta();
        try {
            conex.rs.first();
            cpf = conex.rs.getString("cpf_cliente");
            return cpf;
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cpf;
    }
    
      public int retornarCodBarrasProduto(){
        int cod = 0;
        conex.conexao();
        conex.executaSql("SELECT cod_barras FROM produto WHERE nome_produto LIKE'%" + jComboProduto.getSelectedItem().toString()+ "%'");
        conex.desconecta();
        try {
            conex.rs.first();
            cod = conex.rs.getInt("cod_barras");
            return cod;
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cod;
    }
      
       public int retornaIdPagamento(){
        int idPagamento = 0;
        conex.conexao();
        conex.executaSql("SELECT id_forma_pagamento FROM formapagamento WHERE nome_forma_pagamento LIKE'%" + jComboFormaPagamento.getSelectedItem().toString()+ "%'");
        conex.desconecta();
        try {
            conex.rs.first();
            idPagamento = conex.rs.getInt("id_forma_pagamento");
            return idPagamento;
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idPagamento;
    }  
      
      
    
      public int retornarValorProduto(){
        int valor = 0;
        conex.conexao();
        conex.executaSql("SELECT valor_unitario_produto FROM produto WHERE nome_produto LIKE'%" + jComboProduto.getSelectedItem().toString()+ "%'");
        conex.desconecta();
        try {
            conex.rs.first();
            valor = conex.rs.getInt("valor_unitario_produto");
            return valor;
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
      
      }
      
      public void inserirItem(String sql, ArrayList dados){
          String [] colunas = new String[]{"Cod de Barras","Nome Produto","Valor Unitario","Categoria"};
          conex.conexao();
          conex.executaSql(sql);
          
          try{
              conex.rs.first();
              dados.add(new Object[]{conex.rs.getInt("cod_barras"), conex.rs.getString("nome_produto"), conex.rs.getInt("valor_unitario_produto"), conex.rs.getString("categoria_produto")});
              ModelItemProduto item = new ModelItemProduto(conex.rs.getInt("cod_barras"));
              itens.add(item);
          }catch(SQLException ex){
              JOptionPane.showMessageDialog(rootPane, "Erro ao preencher ArrayList"+ex);
          }
          
          Model.ModelTabela modelo = new ModelTabela(dados, colunas);
          jTableProdutos.setModel(modelo);
          jTableProdutos.getColumnModel().getColumn(0).setPreferredWidth(120);
          jTableProdutos.getColumnModel().getColumn(0).setResizable(false);
          jTableProdutos.getColumnModel().getColumn(1).setPreferredWidth(150);
          jTableProdutos.getColumnModel().getColumn(1).setResizable(false);
          jTableProdutos.getColumnModel().getColumn(2).setPreferredWidth(110);
          jTableProdutos.getColumnModel().getColumn(2).setResizable(false);
          jTableProdutos.getColumnModel().getColumn(3).setPreferredWidth(100);
          jTableProdutos.getColumnModel().getColumn(3).setResizable(false);
          jTableProdutos.getTableHeader().setReorderingAllowed(false);
          jTableProdutos.setAutoResizeMode(jTableProdutos.AUTO_RESIZE_OFF);
          jTableProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          
          conex.desconecta();
      }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboCliente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboProduto = new javax.swing.JComboBox<>();
        jButtonSalvarVenda = new javax.swing.JButton();
        jComboFormaPagamento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonAdicionarProduto = new javax.swing.JButton();
        jLabelTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDataVenda = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jComboCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente");

        jLabel2.setText("Produto");

        jComboProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonSalvarVenda.setText("Salvar Venda");
        jButtonSalvarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarVendaActionPerformed(evt);
            }
        });

        jComboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Forma de Pagamento");

        jLabel4.setText("Valor Total = ");

        jButtonAdicionarProduto.setText("Adicionar Produto");
        jButtonAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarProdutoActionPerformed(evt);
            }
        });

        jLabelTotal.setText("0");

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(jTableProdutos);

        jLabel5.setText("Data da Venda");

        jTextFieldDataVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalvarVenda)
                        .addGap(26, 26, 26))))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 69, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jComboCliente, 0, 314, Short.MAX_VALUE)
                            .addComponent(jComboProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jButtonAdicionarProduto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdicionarProduto))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(63, 63, 63)
                .addComponent(jComboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvarVenda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboClienteActionPerformed
          
    }//GEN-LAST:event_jComboClienteActionPerformed

    private void jButtonSalvarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarVendaActionPerformed
        conex.conexao();
        try { 
            PreparedStatement pst = conex.con.prepareStatement("INSERT INTO venda_produto(cliente_cpf, forma_pagamento_id, data_venda, valor_total)" + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, retornarCPFCliente());
            pst.setInt(2, retornaIdPagamento());
            pst.setString(3, jTextFieldDataVenda.getText());
            pst.setInt(4, Integer.parseInt(jLabelTotal.getText()));
            pst.execute();
            
            ResultSet rs=pst.getGeneratedKeys();
            int id = 0;
           
            if(rs.next()){
               id = rs.getInt(1);
            }
            
            insereItensVenda(id);
            JOptionPane.showMessageDialog(null, "Venda inserida com sucesso!");
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
    }//GEN-LAST:event_jButtonSalvarVendaActionPerformed

    public void insereItensVenda(int idVenda){
      for (ModelItemProduto item: itens){
          inserirItensVenda(idVenda, item.getCod_barras());
      }
    }
    
    public void inserirItensVenda(int idVenda, int codBarras){
        conex.conexao();
        
        try { 
            PreparedStatement pst = conex.con.prepareStatement("INSERT INTO itens_venda(id_venda, cod_barras)" + "VALUES(?,?)");
            pst.setInt(1, idVenda);
            pst.setInt(2, codBarras);
            pst.execute();
       
        } catch (SQLException ex) {
            Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
    }                                       
        
   
    
    private void jButtonAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarProdutoActionPerformed
          int valor = Integer.parseInt(jLabelTotal.getText());
          valor += retornarValorProduto();
          jLabelTotal.setText(Integer.toString(valor));
          inserirItem("SELECT * FROM produto WHERE nome_produto LIKE'%" + jComboProduto.getSelectedItem().toString()+ "%'", this.dados);      
    }//GEN-LAST:event_jButtonAdicionarProdutoActionPerformed

    private void jTextFieldDataVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataVendaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionarProduto;
    private javax.swing.JButton jButtonSalvarVenda;
    private javax.swing.JComboBox<String> jComboCliente;
    private javax.swing.JComboBox<String> jComboFormaPagamento;
    private javax.swing.JComboBox<String> jComboProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JTextField jTextFieldDataVenda;
    // End of variables declaration//GEN-END:variables
}
