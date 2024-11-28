/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Connectivity;

/**
 *
 * @author user
 */
public class Models {
    private String id;
    private String nama;
    private int harga;
    private int stok;
    private String jenis;
    
    public Models(String id, String nama, int harga, int stok, String jenis){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.jenis = jenis;
    }
    
    public String getID(){
        return id;
    }
    public String getNamaBrg(){
        return nama;
    }
    public void setNamaBrg(String nama){
        this.nama = nama;
    }
    public int getHargaBrg(){
        return harga;
    }
    public void setHargaBrg(int harga){
        this.harga = harga;
    }
    public int getStokBrg() {
        return stok;
    }
    public void setStokBrg(int stok) {
        this.stok = stok;
    }
    public String isJenisBrg(){
        return jenis;
    }
    public void setJenisBrg(String jenis){
        this.jenis = jenis;
    }
}
