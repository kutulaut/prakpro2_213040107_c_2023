/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studikasus;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
public class FormInput extends AbstractTableModel {
    // Array yang menyimpan nama-nama kolom
    private String[] columnNames = {"Nama", "Nomor", "Jenis Kelamin", "Alamat"};
    // ArrayList yang menyimpan data sebagai ArrayList<String>
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    // Mengimplementasikan metode dari kelas AbstractTableModel
    // untuk mengambil jumlah baris dalam tabel (jumlah item dalam data)
    public int getRowCount() {
        return data.size();
    }

    // Mengimplementasikan metode dari kelas AbstractTableModel
    // untuk mengambil jumlah kolom dalam tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    // Mengimplementasikan metode dari kelas AbstractTableModel
    // untuk mengambil nama kolom berdasarkan indeks kolom
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mengimplementasikan metode dari kelas AbstractTableModel
    // untuk mengambil nilai sel dalam tabel berdasarkan indeks baris dan kolom
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<String> rowData = data.get(rowIndex);
        return rowData.get(columnIndex);
    }

    // Mengimplementasikan metode dari kelas AbstractTableModel
    // untuk menentukan apakah sel dalam tabel dapat diedit
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Metode khusus untuk menambahkan data ke dalam tabel
    public void add(ArrayList<String> value) {
        // Menambahkan nilai ke dalam ArrayList data
        data.add(value);
        // Memberitahu tabel bahwa baris baru telah ditambahkan
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
  
    public List<String> getRowData(int rowIndex) {
        return data.get(rowIndex);
    }


    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

     public ArrayList<ArrayList<String>> getData() {
        return data;
    }

     public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }


 
    
}
