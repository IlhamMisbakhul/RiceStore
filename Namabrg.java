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

public class Namabrg
{
    public static void main (String[]args){
        NamaBarang();
    }
    static void NamaBarang(){
        JFrame frame = new JFrame();
        JLabel ubah = new JLabel("Masukkan Nama Barang Baru");
        JLabel input = new JLabel("Nama Barang");
        JLabel kode = new JLabel("Masukkan Kode");
        JTextField txtnb = new JTextField();
        JTextField txtkode = new JTextField();
        JButton update = new JButton("Ubah Nama Barang");
        JButton kembali = new JButton("Kembali Ke Menu Utama");
        
        frame.setTitle("UBAH NAMA BARANG");
        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        
        frame.add(ubah);
        frame.add(kode);
        frame.add(txtkode);
        frame.add(input);
        frame.add(txtnb);
        frame.add(update);
        frame.add(kembali);
        
        ubah.setBounds(40,20,210,20);
        kode.setBounds(20,70,130,20);
        txtkode.setBounds(130,70,130,20);
        input.setBounds(20,100,130,20);
        txtnb.setBounds(130,100,130,20);
        update.setBounds(50,280,180,20);
        kembali.setBounds(50,310,180,20);
        
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                koneksi.createStatement().executeUpdate("update Barang set namabrg = '"+txtnb.getText()+"' where kodebrg = '"+txtkode.getText()+"';");
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                }catch(Exception ex){
                }
            }
            
        });
                
        kembali.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MenuUtama.keAwal();
            }
        });
    }
}
