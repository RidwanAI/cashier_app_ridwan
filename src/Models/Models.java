/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Models;

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
    private String username;
    private String password;
    
    public Models(String id, String nama, int harga, int stok, String jenis){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.jenis = jenis;
    }
    
    public Models(String username, String password) {
        this.username = username;
        this.password = password;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validateLogin(String inputUsername, String inputPassword) {
        return this.username != null && 
               this.username.equals(inputUsername) && 
               this.password != null && 
               this.password.equals(inputPassword);
    }
    
    public static class TransactionItem {
        private final String idBarang;
        private final String namaBarang;
        private final int jumlah;
        private final int hargaSatuan;
        private final int totalHarga;

        public TransactionItem(String idBarang, String namaBarang, int jumlah, int hargaSatuan) {
            this.idBarang = idBarang;
            this.namaBarang = namaBarang;
            this.jumlah = jumlah;
            this.hargaSatuan = hargaSatuan;
            this.totalHarga = jumlah * hargaSatuan;
        }

        // Getters
        public String getIdBarang() { return idBarang; }
        public String getNamaBarang() { return namaBarang; }
        public int getJumlah() { return jumlah; }
        public int getHargaSatuan() { return hargaSatuan; }
        public int getTotalHarga() { return totalHarga; }
    }
}
