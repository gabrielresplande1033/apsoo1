/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gabriel
 */
public class ModelItemProduto {
    private int id;
    private int venda_id;
    private int cod_barras;
    
    public ModelItemProduto(){
        
    }
    
    public ModelItemProduto(int cod_barras){
        this.cod_barras = cod_barras;
    }
    
    public ModelItemProduto(int id, int venda_id, int cod_barras){
        this.id = id;
        this.venda_id = venda_id;
        this.cod_barras = cod_barras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    public int getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(int cod_barras) {
        this.cod_barras = cod_barras;
    }
    
}
