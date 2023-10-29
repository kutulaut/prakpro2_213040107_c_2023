/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studikasus;


import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;


public class TugasFormTable extends JFrame {
   
    public TugasFormTable() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                    null, "Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 40, 150, 10);
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelNomorTelepon = new JLabel("Nomor Telepon:");
        labelNomorTelepon.setBounds(15, 100, 150, 10);
        JTextField textFieldNomorTelepon = new JTextField();
        textFieldNomorTelepon.setBounds(15, 120, 350, 30);

        JLabel labelKelamin = new JLabel("Jenis Kelamin:");
        labelKelamin.setBounds(15, 160, 150, 10);

        JRadioButton radioButton_1 = new JRadioButton("Laki-laki", true);
        radioButton_1.setBounds(15, 180, 150, 30);
        JRadioButton radioButton_2 = new JRadioButton("Perempuan");
        radioButton_2.setBounds(15, 210, 150, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton_1);
        bg.add(radioButton_2);

        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 240, 150, 10);
        JTextArea textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 260, 350, 80);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 360, 100, 40);

        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(130, 360, 100, 40);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(245, 360, 100, 40);
        
        JButton buttonSimpanTxt = new JButton("Simpan TXT");
        buttonSimpanTxt.setBounds(360, 360, 100, 40);

        // Membuat tabel
        JTable table = new JTable();
        JScrollPane scrollabletable = new JScrollPane(table);
        scrollabletable.setBounds(15, 420, 450, 150);

        // Membuat model tabel yang khusus Anda
        FormInput formtable = new FormInput();
        table.setModel(formtable);

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
                    formtable.add(new ArrayList<>(Arrays.asList(nama, nomorTelepon, jenisKelamin, alamat)));
                    textFieldNama.setText("");
                    textFieldNomorTelepon.setText("");
                    radioButton_1.setSelected(false);
                    radioButton_2.setSelected(false);
                    textAreaAlamat.setText("");
                }
            }
        });

        buttonHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int dialogResult = JOptionPane.showConfirmDialog(TugasFormTable.this, "Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        formtable.removeRow(selectedRow);
                        clearInputFields();
                    }
                } else {
                    JOptionPane.showMessageDialog(TugasFormTable.this, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


       buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    ArrayList<String> rowData = formtable.getData().get(selectedRow);
                   textFieldNama.setText(rowData.get(0));
                   textFieldNomorTelepon.setText(rowData.get(1));
                    if (rowData.get(2).equals("Laki-Laki")) {
                        radioButton_1.setSelected(true);
                        radioButton_2.setSelected(false);
                    } else {
                         radioButton_1.setSelected(false);
                       radioButton_2.setSelected(true);
                    }
                    textAreaAlamat.setText(rowData.get(3));
                    formtable.remove(selectedRow);
                    JOptionPane.showMessageDialog(TugasFormTable.this, "Edit data!");
                } else {
                    JOptionPane.showMessageDialog(TugasFormTable.this, "Pilih baris yang akan diedit!");
             }
            }
        });
       
       buttonSimpanTxt.addActionListener(e -> {
            int confirm = JOptionPane.showOptionDialog(
                this, "Anda yakin ingin menyimpan data ke file TXT?", "Konfirmasi Simpan TXT", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == JOptionPane.YES_OPTION) {
                if (formtable.getRowCount() > 0) {
                    try {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Simpan Sebagai File TXT");
                        int userSelection = fileChooser.showSaveDialog(this);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = fileChooser.getSelectedFile();
                            FileWriter writer = new FileWriter(fileToSave + ".txt");
                            BufferedWriter bufferedWriter = new BufferedWriter(writer);

                            for (int i = 0; i < formtable.getRowCount(); i++) {
                                String nama = (String) formtable.getValueAt(i, 0);
                                String nomorTelepon = (String) formtable.getValueAt(i, 2);
                                String jenisKelamin = (String) formtable.getValueAt(i, 1);
                                String alamat = (String) formtable.getValueAt(i, 3);

                                // Menulis data ke dalam file teks
                                bufferedWriter.write("Nama: " + nama);
                                bufferedWriter.newLine();
                                bufferedWriter.write("Nomor Telepon: " + nomorTelepon);
                                bufferedWriter.newLine();
                                bufferedWriter.write("Jenis Kelamin: " + jenisKelamin);
                                bufferedWriter.newLine();
                                bufferedWriter.write("Alamat: " + alamat);
                                bufferedWriter.newLine();
                                bufferedWriter.newLine();
                            }

                            bufferedWriter.close();
                            JOptionPane.showMessageDialog(this, "Data telah disimpan ke dalam file TXT.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke dalam file TXT.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Tidak ada data yang akan disimpan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        this.add(buttonSimpan);
        this.add(buttonHapus);
        this.add(buttonEdit);
        this.add(buttonSimpanTxt);
        this.add(textFieldNama);
        this.add(textFieldNomorTelepon);
        this.add(radioButton_1);
        this.add(radioButton_2);
        this.add(labelNama);
        this.add(labelNomorTelepon);
        this.add(labelKelamin);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(scrollabletable);

        this.setSize(500, 600);
        this.setLayout(null);
    }

      private void clearInputFields() {
        JTextField textFieldNama = new JTextField();
        textFieldNama.setText("");
        JTextField textFieldNomorTelepon = new JTextField();
        textFieldNomorTelepon.setText("");
         JTextArea textAreaAlamat = new JTextArea();
        textAreaAlamat.setText("");
        JRadioButton radioButton_1 = new JRadioButton();
        radioButton_1.setSelected(false);
        JRadioButton radioButton_2 = new JRadioButton();
        radioButton_2.setSelected(false);
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


