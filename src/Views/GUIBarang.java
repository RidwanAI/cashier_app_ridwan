/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;
import Connectivity.Controllers;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GUIBarang extends javax.swing.JFrame {
    private String selectedId = "";
    private boolean isEditing = false;
    
    private final Controllers brg = new Controllers();
    
    public GUIBarang() {
        initComponents();
        brg.LoadData();
        tablelist.setModel(brg.showData());
        setupTableRenderers();
        setupTableListener();
        simpan.setText("SIMPAN");
    }
    
    @Override
    public void dispose() {
        super.dispose();
        if (brg != null) {
            brg.closeConnection();
        }
    }
    
    private void setupTableRenderers() {
        DefaultTableCellRenderer editRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(255, 255, 0)); // Warna kuning
                c.setForeground(Color.BLACK);
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };

        DefaultTableCellRenderer deleteRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.RED);
                c.setForeground(Color.WHITE);
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };

        tablelist.getColumnModel().getColumn(5).setCellRenderer(editRenderer);
        tablelist.getColumnModel().getColumn(6).setCellRenderer(deleteRenderer);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablelist.getTableHeader().setDefaultRenderer(headerRenderer);

        tablelist.getColumnModel().getColumn(5).setHeaderValue("Edit");
        tablelist.getColumnModel().getColumn(6).setHeaderValue("Hapus");
    }
    
    private void setupTableListener() {
        tablelist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = tablelist.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / tablelist.getRowHeight();

                if (row < tablelist.getRowCount() && row >= 0 && 
                    column < tablelist.getColumnCount() && column >= 0) {

                    String id = tablelist.getValueAt(row, 0).toString();
                    String nama = tablelist.getValueAt(row, 1).toString();
                    String harga = tablelist.getValueAt(row, 2).toString();
                    String stok = tablelist.getValueAt(row, 3).toString();
                    String jenis = tablelist.getValueAt(row, 4).toString();

                    if (column == 5) {
                        selectedId = id;
                        idbrg.setText(id);
                        idbrg.setEnabled(false);
                        namabrg.setText(nama);
                        hargabrg.setText(harga);
                        stokbrg.setText(stok);
                        jenisbrg.setSelectedItem(jenis);
                        isEditing = true;
                        simpan.setText("UPDATE");
                        simpan.setBackground(new Color(255, 255, 0));
                    } 
                    else if (column == 6) {
                        int confirm = JOptionPane.showConfirmDialog(null, 
                            "Apakah anda yakin ingin menghapus data ini?", 
                            "Konfirmasi Hapus", 
                            JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            brg.DeleteData(id);
                            tablelist.setModel(brg.showData());
                            setupTableRenderers();
                            clearForm();
                        }
                    }
                }
            }
        });
    }
    
    private void clearForm() {
        idbrg.setText("");
        idbrg.setEnabled(true);
        namabrg.setText("");
        hargabrg.setText("");
        stokbrg.setText("");
        jenisbrg.setSelectedIndex(0);
        isEditing = false;
        selectedId = "";
        simpan.setText("SIMPAN");
        simpan.setBackground(new Color(102, 255, 102));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idbrg = new javax.swing.JTextField();
        namabrg = new javax.swing.JTextField();
        hargabrg = new javax.swing.JTextField();
        jenisbrg = new javax.swing.JComboBox<String>();
        simpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablelist = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        stokbrg = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        printReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("DATA BARANG");

        jLabel2.setText("ID Barang");

        jLabel3.setText("Nama Barang");

        jLabel4.setText("Harga Barang");

        idbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idbrgActionPerformed(evt);
            }
        });

        namabrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabrgActionPerformed(evt);
            }
        });

        hargabrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargabrgActionPerformed(evt);
            }
        });

        jenisbrg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Makanan", "Minuman" }));
        jenisbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisbrgActionPerformed(evt);
            }
        });

        simpan.setBackground(new java.awt.Color(102, 255, 102));
        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        tablelist.setAutoCreateRowSorter(true);
        tablelist.setModel(brg.showData());
        jScrollPane1.setViewportView(tablelist);
        tablelist.getAccessibleContext().setAccessibleParent(simpan);

        jLabel5.setText("Stok Barang");

        jLabel6.setText("Jenis Barang");

        back.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        back.setText("<");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        printReport.setBackground(new java.awt.Color(102, 255, 102));
        printReport.setText("Cetak Report");
        printReport.setPreferredSize(new java.awt.Dimension(107, 33));
        printReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jenisbrg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hargabrg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(namabrg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(idbrg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(stokbrg, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(92, 92, 92)
                        .addComponent(printReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(printReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namabrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(hargabrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(stokbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jenisbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        String id = idbrg.getText();
        String nama = namabrg.getText();
        int harga = Integer.parseInt(hargabrg.getText());
        int stok = Integer.parseInt(stokbrg.getText());
        String jenis = jenisbrg.getSelectedItem().toString();
        ArrayList<String> emptyFields = new ArrayList<>();

        if (idbrg.getText().trim().isEmpty()) {
            emptyFields.add("ID Barang");
        }
        if (namabrg.getText().trim().isEmpty()) {
            emptyFields.add("Nama Barang");
        }
        if (hargabrg.getText().trim().isEmpty()) {
            emptyFields.add("Harga Barang");
        }
        if (stokbrg.getText().trim().isEmpty()) {
            emptyFields.add("Stok Barang");
        }

        if (!emptyFields.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Field ");

            for (int i = 0; i < emptyFields.size(); i++) {
                if (i == emptyFields.size() - 1 && emptyFields.size() > 1) {
                    errorMessage.append(" dan");
                }
                if (i > 0) {
                    errorMessage.append(i == emptyFields.size() - 1 ? " " : ", ");
                }
                errorMessage.append(emptyFields.get(i));
            }
            errorMessage.append(" Belum diisi");

            JOptionPane.showMessageDialog(
                null, 
                errorMessage.toString(), 
                "Validasi Error", 
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }
        
        if (isEditing) {
            // Pastikan ID yang sedang diedit adalah ID yang sama
            if (id.equals(selectedId)) {
                brg.UpdateData(id, nama, harga, stok, jenis);
                isEditing = false;
                simpan.setText("SIMPAN");
                tablelist.setModel(brg.showData());
                setupTableRenderers();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(
                    null, 
                    "Error: ID barang tidak sesuai!", 
                    "Validasi Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
            return; // Langsung keluar, jangan lanjut ke blok try
        }

        try {
            // Cek apakah ID sudah ada
            boolean idExists = brg.isBarangIDExists(id);

            // Cek apakah nama barang sudah ada dengan ID yang berbeda
            String existingIdByName = brg.getBarangIDByName(nama);

            // Kondisi 1: Nama barang sudah ada dengan ID yang berbeda
            if (existingIdByName != null && !id.equals(existingIdByName)) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Error: Nama barang sudah ada dengan ID berbeda!\n" +
                    "Nama barang '" + nama + "' sudah terdaftar dengan ID: " + existingIdByName,
                    "Duplikasi Data", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Kondisi 2: ID sudah ada, tapi nama barang berbeda
            if (idExists) {
                String namaBarangExisting = brg.getBarangNameByID(id);
                if (!nama.equals(namaBarangExisting)) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error: ID barang sudah ada dengan nama barang berbeda!\n" +
                        "ID '" + id + "' sudah terdaftar dengan nama: " + namaBarangExisting,
                        "Duplikasi Data", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }

            // Jika lolos validasi, lakukan update atau insert
            if (idExists) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Barang ini sudah ada dalam database, apakah anda ingin menimpanya?",
                        "Duplikasi Data",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    brg.OnlyDelSpec(id);
                    brg.OnlyInsSpec(id, nama, harga, stok, jenis);
                    tablelist.setModel(brg.showData());
                    setupTableRenderers();
                    clearForm();
                }           
            } else {
                brg.InsertData(id, nama, harga, stok, jenis);
                tablelist.setModel(brg.showData());
                setupTableRenderers();
                clearForm();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Harga atau Stok harus berupa angka!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }        
    }//GEN-LAST:event_simpanActionPerformed

    private void idbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idbrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idbrgActionPerformed

    private void jenisbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisbrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisbrgActionPerformed

    private void namabrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namabrgActionPerformed

    private void hargabrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargabrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargabrgActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.dispose();
        new GUIDashboard().setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void printReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportActionPerformed
        this.dispose();
        new GUILaporanBarang("barang").setVisible(true);
    }//GEN-LAST:event_printReportActionPerformed

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
            java.util.logging.Logger.getLogger(GUIBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField hargabrg;
    private javax.swing.JTextField idbrg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jenisbrg;
    private javax.swing.JTextField namabrg;
    private javax.swing.JButton printReport;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField stokbrg;
    public javax.swing.JTable tablelist;
    // End of variables declaration//GEN-END:variables
}
