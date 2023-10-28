/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studikasus;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
/**
 *
 * @author mac
 */
public class TugasFormTable extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    public TugasFormTable() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            // Menambahkan WindowListener
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                    null, "Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Menutup aplikasi jika pengguna memilih "Ya"
                }
            }
        });

        // Label dan kotak teks untuk input Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 40, 150, 10);
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Label dan kotak teks untuk input Nomor Telepon
        JLabel labelNomorTelepon = new JLabel("Nomor Telepon:");
        labelNomorTelepon.setBounds(15, 100, 150, 10);
        JTextField textFieldNomorTelepon = new JTextField();
        textFieldNomorTelepon.setBounds(15, 120, 350, 30);
        //Label kelamin
        JLabel labelKelamin = new JLabel("Jenis Kelamin:");
        labelKelamin.setBounds(15, 160, 150, 10);

        // RadioButton untuk memilih jenis kelamin
          JRadioButton radioButton_1 = new JRadioButton("Laki-laki", true);
        radioButton_1.setBounds(15, 170, 200, 30);
         JRadioButton radioButton_2 = new JRadioButton("Perempuan");
        radioButton_2.setBounds(15, 190, 200, 30);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton_1);
        bg.add(radioButton_2);

        // Label dan TextArea untuk Alamat
        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 220, 150, 10);
        JTextArea textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 240, 350, 80);

        // Tombol untuk menyimpan data
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 330, 100, 40);

        // Tabel untuk menampilkan data
        String[] columnNames = {"Nama", "Jenis Kelamin", "Nomor Telepon", "Alamat"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 380, 450, 150);

        // Mendengarkan klik tombol "Simpan"           
        buttonSimpan.addActionListener(e -> {
            int confirm = JOptionPane.showOptionDialog(
                this, "Anda yakin ingin menyimpan data?", "Konfirmasi Simpan", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == JOptionPane.YES_OPTION) {
                String nama = textFieldNama.getText();
                String nomorTelepon = textFieldNomorTelepon.getText();
                String jenisKelamin = radioButton_1.isSelected() ? "Laki-Laki" : "Perempuan";
                String alamat = textAreaAlamat.getText();
            
            
            if (nama.isEmpty() || nomorTelepon.isEmpty() || alamat.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ada field yang belum diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                tableModel.addRow(new Object[] {nama, jenisKelamin, nomorTelepon, alamat});
                textFieldNama.setText("");
                textFieldNomorTelepon.setText("");
                radioButton_1.setSelected(false);
                radioButton_2.setSelected(false);
                textAreaAlamat.setText("");
            }
           }
        });

        // Menambahkan komponen-komponen ke dalam frame
        this.add(buttonSimpan);
        this.add(textFieldNama);
        this.add(textFieldNomorTelepon);
        this.add(radioButton_1);
        this.add(radioButton_2);
        this.add(labelNama);
        this.add(labelNomorTelepon);
        this.add(labelKelamin);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(scrollPane);

        // Mengatur ukuran frame
        this.setSize(500, 600);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               TugasFormTable h = new TugasFormTable();
                h.setVisible(true);
            }
        });
    }
}




