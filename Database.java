import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database
{
    public static void main(String[] visual){
        menuju();
    }
    
    static void menuju(){
        JFrame jd = new JFrame ("Tabel Pembeli");
        JTable tabel = new JTable();
        JScrollPane sp = new JScrollPane(tabel);
        
        Connection konek;
        Statement state;
        ResultSet hasil;
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("No. Pembelian");
        tbl.addColumn("Kode Barang");
        tbl.addColumn("Harga");
        tbl.addColumn("Jumlah Pembelian");
        
        tabel.setModel(tbl);
        try {
            Class.forName("org.sqlite.JDBC");
            konek = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db");
            String sql = "select * from Pembeli;";
            state = konek.createStatement();
            hasil = state.executeQuery(sql);
            while (hasil.next()){
                tbl.addRow(new Object[]{
                    hasil.getString("nobeli"),
                    hasil.getString("kodebrg"),
                    hasil.getString("harga"),
                    hasil.getString("jumlah_pembelian"),
                });
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,".404 Table Not Found");
        }
        jd.add(sp);
        jd.setLocation(300,200);
        jd.setSize(800,400);
        jd.setVisible(true);
    }
}
