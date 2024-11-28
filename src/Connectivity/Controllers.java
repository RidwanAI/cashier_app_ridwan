package Connectivity;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Controllers {
    private ArrayList<Models> ArrayData;
    private DefaultTableModel tabelModel;
    private DBase conn;
    private Connection connection;
    private Models adminModel;
    
    public Controllers() {
        ArrayData = new ArrayList<Models>();
        conn = new DBase();
        connection = conn.getConnection();
    }
    
    public void closeConnection() {
        conn.closeConnection();
    }
    
    public boolean LoginUser(String username, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                // Create a Models instance for the admin
                adminModel = new Models(username, password);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public String getAdmin(String username) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String usrnm = null;
        try {
            String sql = "SELECT username FROM admin WHERE username = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                usrnm = rs.getString("username");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.out.println("error closing resources: " + e.getMessage());
            }
        }
        return usrnm;
    }
    
    public void InsertData(String id_brg, String nama_brg, int harga_brg, int stok_brg, String jenis_brg) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO barang (id_barang, nama_barang, harga_barang, stok_barang, jenis_barang) VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id_brg);
            ps.setString(2, nama_brg);
            ps.setInt(3, harga_brg);
            ps.setInt(4, stok_brg);
            ps.setString(5, jenis_brg);
            ps.executeUpdate();
            
            Models brg = new Models(id_brg, nama_brg, harga_brg, stok_brg, jenis_brg);
            ArrayData.add(brg);
            
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    public void UpdateData(String id_brg, String nama_brg, int harga_brg, int stok_brg, String jenis_brg) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE barang SET nama_barang=?, harga_barang=?, stok_barang=?, jenis_barang=? WHERE id_barang=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nama_brg);
            ps.setInt(2, harga_brg);
            ps.setInt(3, stok_brg);
            ps.setString(4, jenis_brg);
            ps.setString(5, id_brg);
            ps.executeUpdate();
            
            for(Models brg : ArrayData) {
                if(brg.getID().equals(id_brg)) {
                    brg.setNamaBrg(nama_brg);
                    brg.setHargaBrg(harga_brg);
                    brg.setStokBrg(stok_brg);
                    brg.setJenisBrg(jenis_brg);
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    public void DeleteData(String id_brg) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM barang WHERE id_barang=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id_brg);
            ps.executeUpdate();
            
            ArrayData.removeIf(brg -> brg.getID().equals(id_brg));
            
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    public void LoadData() {
        Statement st = null;
        ResultSet rs = null;
        try {
            ArrayData.clear();
            String sql = "SELECT * FROM barang";
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                String id = rs.getString("id_barang");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga_barang");
                int stok = rs.getInt("stok_barang");
                String jenis = rs.getString("jenis_barang");
                
                Models brg = new Models(id, nama, harga, stok, jenis);
                ArrayData.add(brg);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public DefaultTableModel showData(){
        String[] kolom = {"ID Barang", "Nama Barang", "Harga Barang", "Stok Barang", "Jenis Barang", "", ""};
        Object[][] objData = new Object[ArrayData.size()][6]; 
        int i = 0;

        for(Models n : ArrayData){
            Object[] arrData = {
                n.getID(), 
                n.getNamaBrg(), 
                n.getHargaBrg(), 
                n.getStokBrg(),
                n.isJenisBrg(),
                "Edit",
                "Hapus"
            };
            objData[i] = arrData;
            i++;
        }

        tabelModel = new DefaultTableModel(objData, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelModel.setColumnIdentifiers(new Object[]{"ID Barang", "Nama Barang", "Harga Barang", "Stok Barang", "Jenis Barang", "Edit", "Hapus"});

        return tabelModel;
    }
}