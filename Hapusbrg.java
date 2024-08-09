import java.lang.*;
import java.sql.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Hapusbrg
{
     
    public static void main(String[] args){
        Hapus();
    }
    
    static void Hapus(){
        JFrame frame = new JFrame();
        JLabel hapus = new JLabel("Masukkan Kode Yang Ingin Dihapus");
        JLabel input = new JLabel("Masukkan Kode");
        JLabel update = new JLabel("Atau Hanya Ingin Update Data Yang Ada?");
        JTextField txthapus = new JTextField();
        JButton delete = new JButton("Hapus Data");
        JButton kembali = new JButton("Kembali Ke Menu Utama");
        JButton nama = new JButton("Update Nama Barang");
        JButton barang = new JButton("Update Barang");
        JButton harga = new JButton("Update Harga");
        JButton jumlah = new JButton("Update Jumlah Beli");
                        
        frame.setTitle("PENGHAPUSAN DATA");
        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        
        frame.add(hapus);
        frame.add(input);
        frame.add(txthapus);
        frame.add(update);
        frame.add(nama);
        frame.add(barang);
        frame.add(harga);
        frame.add(jumlah);
        frame.add(delete);
        frame.add(kembali);
                
        hapus.setBounds(40,20,210,20);
        input.setBounds(20,70,130,20);
        txthapus.setBounds(130,70,130,20);
        update.setBounds(20,110,230,20);
        nama.setBounds(50,140,180,20);
        harga.setBounds(50,170,180,20);
        delete.setBounds(50,280,180,20);
        kembali.setBounds(50,310,180,20);
        
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                koneksi.createStatement().executeUpdate("Delete from Barang where kodebrg = '"+txthapus.getText()+"';");
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                }catch(Exception ex){
                }
            }
            
        });
                
        kembali.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MenuUtama.keAwal();
            }
        });
        
        nama.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Namabrg.NamaBarang();
            }
        });
        
        barang.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Barang.ubahBarang();
            }
        });
        
        harga.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                HargaBrg.ubahHarga();
            }
        });
        
        jumlah.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Jumlah.ubahJumlah();
            }
        });
    }
}
