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

public class InputBrg
{
    static JFrame frame = new JFrame();
    
    static JLabel kodebrg = new JLabel("Kode");
    static JLabel namabrg = new JLabel("Nama Barang");
    static JLabel harga = new JLabel("Harga Per 50Kg");
    static JLabel stok = new JLabel("Stok Barang");;
        
    static JTextField txtkodebrg = new JTextField();
    static JTextField txtnamabrg = new JTextField();
    static JTextField txtharga = new JTextField();
    static JTextField txtstok = new JTextField();
        
    static JButton simpan = new JButton("Simpan");
    static JButton kembali = new JButton("Kembali Ke Menu");
    static JButton delete = new JButton("Hapus Salah Satu Data");
    
    static int count = 0;
    public static void main (String [] args){
        Barang();
    }
    static void Barang(){
        tampil();
        reset();
        Menu();
        Handler();
    }
    static void tampil(){
        try {
            Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
            ResultSet res = koneksi.createStatement().executeQuery("Select * From Barang_tb");
            while(res.next()){
                int a = res.getInt(1);
                txtkodebrg.setText(Integer.toString(a+1));
                txtkodebrg.setEnabled(false);
            }
        }catch(SQLException ex){
        }
    }
    static void reset (){
        txtnamabrg.setText("");
        txtharga.setText("");
        txtstok.setText("");
    }
    static void Menu(){
        frame.setTitle("DAFTAR NAMA BARANG");
        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        
        frame.add(kodebrg);
        frame.add(namabrg);
        frame.add(harga);
        frame.add(stok);
                
        frame.add(txtkodebrg);
        frame.add(txtnamabrg);
        frame.add(txtharga);
        frame.add(txtstok);
                
        frame.add(simpan);
        frame.add(kembali);
        frame.add(delete);

        kodebrg.setBounds(20,20,130,20);
        namabrg.setBounds(20,50,130,20);
        harga.setBounds(20,80,130,20);
        stok.setBounds(20,110,130,20);
                
        txtkodebrg.setBounds(130,20,130,20);
        txtnamabrg.setBounds(130,50,130,20);
        txtharga.setBounds(130,80,130,20);
        txtstok.setBounds(130,110,130,20);
            
        simpan.setBounds(50,240,180,20);
        kembali.setBounds(50,270,180,20);
        delete.setBounds(50,300,180,20);
    }
    static void Handler(){
        simpan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(txtkodebrg.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Kode BarangHarus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txtnamabrg.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Nama Barang Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txtharga.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Harga Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txtstok.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Jumlah InputBrg Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else{
                    try{
                        Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                        koneksi.createStatement().executeUpdate("Insert into Barang values"+"('"+txtkodebrg.getText()+"','"+txtnamabrg.getText()+"','"+txtharga.getText()+"','"+txtstok.getText()+"')");
                        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                    }catch(Exception ex){
                    }
                }
            }
        });
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Hapusbrg.Hapus();
        }
        });
        kembali.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MenuUtama.keAwal();
            }
        });
    }
}
