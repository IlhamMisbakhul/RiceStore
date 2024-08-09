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

public class Pembelian
{
    static JFrame frame = new JFrame();
    
    static JLabel nobeli = new JLabel("No. Pembelian");
    static JLabel kodebrg = new JLabel("Kode Barang");
    static JLabel harga = new JLabel("Harga Per Karung");
    static JLabel jumlah_pembelian = new JLabel("Jumlah Pembelian");
    static JLabel total_harga = new JLabel("Total Harga (Rp)");
        
    static JTextField txtnobeli = new JTextField();
    static JTextField txtkodebrg = new JTextField();
    static JTextField txtharga = new JTextField();
    static JTextField txtjumlah_pembelian = new JTextField();
    static JTextField txttotal_harga = new JTextField();
        
    static JButton hitung = new JButton("Harga Yang Harus Dibayar");
    static JButton simpan = new JButton("Simpan");
    static JButton kembali = new JButton("Kembali Ke Menu");
    static JButton delete = new JButton("Hapus Salah Satu Data");
    
    static int count = 0;
    public static void main (String [] args){
        menuju_Pembelian();
    }
    static void menuju_Pembelian(){
        tampil();
        reset();
        Menu();
        Handler();
    }
    static void tampil(){
        try {
            Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
            ResultSet res = koneksi.createStatement().executeQuery("Select * From Pembeli_tb");
            while(res.next()){
                int a = res.getInt(1);
                txtkodebrg.setText(Integer.toString(a+1));
                txtkodebrg.setEnabled(false);
            }
        }catch(SQLException ex){
        }
    }
    static void reset (){
        txtnobeli.setText("");
        txtkodebrg.setText("");
        txtharga.setText("");
        txtjumlah_pembelian.setText("");
        txttotal_harga.setText("");
    }
    static void Menu(){
        frame.setTitle("DAFTAR PEMBELI TOKO HARTONO ELEKTRONIK");
        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        txttotal_harga.setEnabled(false);
        
        frame.add(nobeli);
        frame.add(kodebrg);
        frame.add(harga);
        frame.add(jumlah_pembelian);
                
        frame.add(txtnobeli);
        frame.add(txtkodebrg);
        frame.add(txtharga);
        frame.add(txtjumlah_pembelian);
                
        frame.add(hitung);
        frame.add(simpan);
        frame.add(kembali);
        frame.add(delete);
        frame.add(total_harga);
        frame.add(txttotal_harga);
        
        nobeli.setBounds(20,20,130,20);
        kodebrg.setBounds(20,50,130,20);
        harga.setBounds(20,80,130,20);
        jumlah_pembelian.setBounds(20,110,130,20);
        total_harga.setBounds(20,140,130,20);
                
        txtnobeli.setBounds(130,20,130,20);
        txtkodebrg.setBounds(130,50,130,20);
        txtharga.setBounds(130,80,130,20);
        txtjumlah_pembelian.setBounds(130,110,130,20);
        txttotal_harga.setBounds(130,140,130,20);
            
        hitung.setBounds(20,200,170,20);
        simpan.setBounds(50,240,180,20);
        kembali.setBounds(50,270,180,20);
        delete.setBounds(50,300,180,20);
    }
    static void Handler(){
        simpan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(txtnobeli.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "No. Pembeli Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txtkodebrg.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Nama Pembeli Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txtharga.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Harga Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txtjumlah_pembelian.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Jumlah Pembelian Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                }else if(txttotal_harga.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Total Harga Harus Diisi!\n"+"Silahkan Isi Terlebih Dahulu");
                    txttotal_harga.requestFocus();
                    hitung.requestFocus();
                }else{
                    try{
                        Connection koneksi = DriverManager.getConnection ("jdbc:sqlite:/C:\\Users\\Lenovo\\Kelompok3SBD\\Kelompok3.db","root","");
                        koneksi.createStatement().executeUpdate("Insert into Pembeli values"+"('"+txtnobeli.getText()+"','"+txtkodebrg.getText()+"','"+txtharga.getText()+"','"+txtjumlah_pembelian.getText()+"')");
                        ResultSet update;
                        Statement state;
                        state = koneksi.createStatement();
                        update = state.executeQuery("UPDATE Barang SET stok = stok - (SELECT jumlah_pembelian FROM Pembeli WHERE KodeBrg = Barang.KodeBrg) WHERE KodeBrg IN (SELECT KodeBrg FROM Pembeli);");
                    }catch(Exception ex){
                    }
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                }
            }
        });
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Delete.hapusData();
        }
        });
        hitung.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int a = Integer.parseInt(txtharga.getText());
                int b = Integer.parseInt(txtjumlah_pembelian.getText());
                
                int hasil = a*b;
                String total = Integer.toString(hasil);
                txttotal_harga.setText(total);
            }
        });
        kembali.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MenuUtama.keAwal();
            }
        });
    }
}
