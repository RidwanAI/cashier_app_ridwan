package Connectivity;
import Connectivity.Models.TransactionItem;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controllers {
    private final ArrayList<Models> ArrayData;
    private DefaultTableModel tabelModel;
    private final DBase conn;
    private final Connection connection;
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
            // Cek apakah data dengan ID tersebut ada
            if (!isBarangIDExists(id_brg)) {
                JOptionPane.showMessageDialog(null, "Data barang tidak ditemukan!");
                return;
            }

            String sql = "UPDATE barang SET nama_barang=?, harga_barang=?, stok_barang=?, jenis_barang=? WHERE id_barang=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nama_brg);
            ps.setInt(2, harga_brg);
            ps.setInt(3, stok_brg);
            ps.setString(4, jenis_brg);
            ps.setString(5, id_brg);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Update lokal ArrayData
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
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang diupdate!");
            }
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
    
    public void OnlyInsSpec(String id_brg, String nama_brg, int harga_brg, int stok_brg, String jenis_brg) {
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
        } catch (SQLException e) {
            
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    public void OnlyDelSpec(String id_brg) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM barang WHERE id_barang=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id_brg);
            ps.executeUpdate();
            
            ArrayData.removeIf(brg -> brg.getID().equals(id_brg));
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
    
    public boolean isBarangExists(String nama_brg) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM barang WHERE nama_barang = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nama_brg);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public boolean isBarangIDExists(String id_brg) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM barang WHERE id_barang = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id_brg);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public String getBarangIDByName(String nama_brg) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_barang FROM barang WHERE nama_barang = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, nama_brg);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("id_barang");
            }
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public String getBarangNameByID(String id_brg) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT nama_barang FROM barang WHERE id_barang = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id_brg);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("nama_barang");
            }
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public Models getBarangByIdOrName(String searchTerm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM barang WHERE id_barang = ? OR nama_barang LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, searchTerm);
            ps.setString(2, "%" + searchTerm + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id_barang");
                String nama = rs.getString("nama_barang");
                int harga = rs.getInt("harga_barang");
                int stok = rs.getInt("stok_barang");
                String jenis = rs.getString("jenis_barang");
                return new Models(id, nama, harga, stok, jenis);
            }
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
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
    
    public boolean reduceStock(String id_brg, int qty) {
        PreparedStatement ps = null;
        try {
            // First, check if sufficient stock is available
            Models barang = getBarangByIdOrName(id_brg);
            if (barang == null || barang.getStokBrg() < qty) {
                JOptionPane.showMessageDialog(null, "Stok tidak mencukupi!");
                return false;
            }

            // Update stock in database
            String sql = "UPDATE barang SET stok_barang = stok_barang - ? WHERE id_barang = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setString(2, id_brg);
            ps.executeUpdate();

            // Update local ArrayData
            for (Models brg : ArrayData) {
                if (brg.getID().equals(id_brg)) {
                    brg.setStokBrg(brg.getStokBrg() - qty);
                    break;
                }
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }
        }
    }
    
    public boolean saveTransaction(ArrayList<TransactionItem> items) {
        PreparedStatement psTrx = null;
        PreparedStatement psTrxDetail = null;
        ResultSet rs = null;
        try {
            // Begin transaction
            connection.setAutoCommit(false);

            // Generate transaction ID
            String trxId = generateTransactionId();

            // Insert transaction header
            String sqlTrx = "INSERT INTO transaksi (id_transaksi, total_harga, tanggal_transaksi) VALUES (?, ?, NOW())";
            psTrx = connection.prepareStatement(sqlTrx);
            int totalHarga = items.stream().mapToInt(item -> item.getTotalHarga()).sum();
            psTrx.setString(1, trxId);
            psTrx.setInt(2, totalHarga);
            psTrx.executeUpdate();

            // Insert transaction details
            String sqlTrxDetail = "INSERT INTO detail_transaksi (id_transaksi, id_barang, nama_barang, jumlah, harga_satuan, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
            psTrxDetail = connection.prepareStatement(sqlTrxDetail);

            for (TransactionItem item : items) {
                psTrxDetail.setString(1, trxId);
                psTrxDetail.setString(2, item.getIdBarang());
                psTrxDetail.setString(3, item.getNamaBarang());
                psTrxDetail.setInt(4, item.getJumlah());
                psTrxDetail.setInt(5, item.getHargaSatuan());
                psTrxDetail.setInt(6, item.getTotalHarga());
                psTrxDetail.executeUpdate();
            }

            // Commit transaction
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback in case of error
            } catch (SQLException rollbackEx) {
                System.out.println("Error rolling back transaction: " + rollbackEx.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Error saving transaction: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                if (psTrx != null) psTrx.close();
                if (psTrxDetail != null) psTrxDetail.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    private String generateTransactionId() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COALESCE(MAX(CAST(SUBSTRING(id_transaksi, 4) AS UNSIGNED)), 0) + 1 AS next_id FROM transaksi";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int nextId = rs.getInt("next_id");
                return String.format("TRX%05d", nextId);
            }
            return "TRX00001";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error generating transaction ID: " + e.getMessage());
            return "TRX00001";
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}