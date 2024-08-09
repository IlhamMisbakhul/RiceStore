import java.lang.*;
import java.sql.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MenuUtama
{
    static JFrame frm = new JFrame();
    static JLabel lbl = new JLabel("             MENU UTAMA             ");
    static JLabel lblbrg = new JLabel("DATA GUDANG");
    static JButton input = new JButton("Input Data Pembelian");
    static JButton brg = new JButton("Input Data Barang");
    static JButton tblbrg = new JButton("Lihat List Barang");
    static JButton tambahstok = new JButton("Tambahkan Stok");
    static JButton tabel = new JButton("Lihat Tabel");
    static JButton createbrg = new JButton("Buat Tabel Barang");
    static JButton deltab = new JButton("Hapus Tabel");
    static JButton creatab = new JButton("Tabel Baru");
    static JButton lapor = new JButton("Lihat Laporan Penjualan");
    static JButton keluar = new JButton("Close");
    
    public static void main(String [] args)throws Exception{
        keAwal();
    }
    static void keAwal(){
        JOptionPane.showMessageDialog(null, "SELAMAT DATANG DI TOKO BERAS BERKAH");
        frm.setTitle  ("MENU UTAMA");
        frm.setSize(400,400);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);
        frm.setVisible(true);
        frm.setLayout(null);
        
        frm.add(lbl);
        frm.add(lblbrg);
        frm.add(input);
        frm.add(tabel);
        frm.add(deltab);
        frm.add(creatab);
        frm.add(createbrg);
        frm.add(lapor);
        frm.add(keluar);
        frm.add(brg);
        frm.add(tblbrg);
        frm.add(tambahstok);
        lbl.setBounds(0,10,300,20);
        lblbrg.setBounds(220,10,300,20);
        input.setBounds(50,40,120,40);
        tabel.setBounds(50,90,120,40);
        deltab.setBounds(50,140,120,40);
        creatab.setBounds(50,190,120,40);
        brg.setBounds(220,40,120,40);
        tblbrg.setBounds(220,90,120,40);
        createbrg.setBounds(220,140,120,40);
        tambahstok.setBounds(220,190,120,40);
        lapor.setBounds(100,280,180,20);
        keluar.setBounds(100,310,180,20);
        
        input.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Pembelian.menuju_Pembelian();
            }
        });
        tabel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                           Database.menuju();
            }
        });
        tblbrg.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                           DataBrg.DataBarang();
            }
        });
        tambahstok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                           TambahStok.Tambahbrg();
            }
        });
        deltab.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                    ResultSet res = koneksi.createStatement().executeQuery("drop table Pembeli");
                }catch(SQLException ex){
                }
                JOptionPane.showMessageDialog(null, "Data Pembeli Berhasil Dihapus");
            }
        });
        creatab.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Connection konek = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                    ResultSet hasil = konek.createStatement().executeQuery("create table Pembeli (nobeli varchar primary key, kodebrg varchar, harga varchar, jumlah_pembelian Integer);");
                }catch(SQLException ex){
                }
                JOptionPane.showMessageDialog(null, "Tabel Berhasil Dibuat");
            }
        });
        createbrg.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    Connection konek = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                    ResultSet hasil = konek.createStatement().executeQuery("create table Barang (kodebrg varchar primary key, namabrg text, harga varchar, stok Integer, total integer);");
                }catch(SQLException ex){
                }
                JOptionPane.showMessageDialog(null, "Tabel Berhasil Dibuat");
            }
        });
        brg.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                           InputBrg.Barang();
            }
        });
        lapor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                            Laporan.Lapor();
            }
        });
        
        keluar.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 int jawab = JOptionPane.showOptionDialog(null, 
                        "Ingin Keluar?", 
                        "Keluar", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                 if(jawab == JOptionPane.YES_OPTION){
                 System.exit(0);
                 }
            }
        });
    }   
}