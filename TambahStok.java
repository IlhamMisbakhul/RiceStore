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

public class TambahStok
{
    public static void main (String[]args){
        Tambahbrg();
    }
    static void Tambahbrg(){
        JFrame frame = new JFrame();
        JLabel ubah = new JLabel("Tambah Stok");
        JLabel input = new JLabel("Stok Tambahan");
        JLabel kodebrg = new JLabel("Kode Barang");
        JTextField txttambah = new JTextField();
        JTextField txtkodebrg = new JTextField();
        JButton update = new JButton("Tambah Stok");
        JButton kembali = new JButton("Kembali Ke Menu Utama");
        
        frame.setTitle("UBAH NAMA");
        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        
        frame.add(ubah);
        frame.add(kodebrg);
        frame.add(txtkodebrg);
        frame.add(input);
        frame.add(txttambah);
        frame.add(update);
        frame.add(kembali);
        
        ubah.setBounds(40,20,210,20);
        kodebrg.setBounds(20,70,130,20);
        txtkodebrg.setBounds(130,70,130,20);
        input.setBounds(20,100,130,20);
        txttambah.setBounds(130,100,130,20);
        update.setBounds(50,280,180,20);
        kembali.setBounds(50,310,180,20);
        
        
                     
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    int tambah = Integer.parseInt(txttambah.getText());
                    Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                    koneksi.createStatement().executeUpdate("update Barang set stok = stok + "+tambah+" where kodebrg = '"+txtkodebrg.getText()+"';");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                }catch(Exception ex){
                    ex.printStackTrace();
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
