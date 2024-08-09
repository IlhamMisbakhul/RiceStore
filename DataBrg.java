import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBrg
{
    public static void main(String[] visual){
        DataBarang();
    }
    
    static void DataBarang(){
        JFrame jd = new JFrame ("Tabel Barang");
        JTable tabel = new JTable();
        JScrollPane sp = new JScrollPane(tabel);
        
        Connection konek;
        Statement state;
        ResultSet hasil, update;
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Kode Barang");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Harga");
        tbl.addColumn("Stok Tersisa");
        tbl.addColumn("Total Liter");
        
        tabel.setModel(tbl);
        try {
            Class.forName("org.sqlite.JDBC");
            konek = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db");
            state = konek.createStatement();
            
            hasil = state.executeQuery("Select kodebrg, namabrg, harga,stok, stok*50 as total from Barang");

            while (hasil.next()){
                tbl.addRow(new Object[]{
                    hasil.getString("kodebrg"),
                    hasil.getString("namabrg"),
                    hasil.getString("harga"),
                    hasil.getString("stok"),
                    hasil.getString("total"),
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
