import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Mahasiswa {
    private String nama;
    private String nim;
    private String kelas;
    private double nilai1;
    private double nilai2;
    private double nilai3;
    private double rataRata;

    public Mahasiswa(String nama, String nim, String kelas, double nilai1, double nilai2, double nilai3) {
        this.nama = nama;
        this.nim = nim;
        this.kelas = kelas;
        this.nilai1 = nilai1;
        this.nilai2 = nilai2;
        this.nilai3 = nilai3;
        this.rataRata = (nilai1 + nilai2 + nilai3) / 3;
    }

    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public String getKelas() { return kelas; }
    public double getNilai1() { return nilai1; }
    public double getNilai2() { return nilai2; }
    public double getNilai3() { return nilai3; }
    public double getRataRata() { return rataRata; }

    @Override
    public String toString() {
        return nama + " (" + nim + ") - Nilai RataRata: " + String.format("%.2f", rataRata);
    }
}

public class DataNilaiMahasiswa extends JFrame {
    private ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private JTextField tfNama, tfNim, tfKelas, tfNilai1, tfNilai2, tfNilai3, tfCariNim;
    private DefaultListModel<String> listModel;
    private JList<String> listMahasiswa;
    private JButton btnTambah, btnCari, btnHapus, btnTampilkan;

    public DataNilaiMahasiswa() {
        setTitle("Data Nilai Mahasiswa");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // Alice blue background

        // Panel untuk input dengan GridBagLayout
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data Mahasiswa"));
        panelInput.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        tfNama = new JTextField(15);
        panelInput.add(tfNama, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelInput.add(new JLabel("NIM:"), gbc);
        gbc.gridx = 1;
        tfNim = new JTextField(15);
        panelInput.add(tfNim, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelInput.add(new JLabel("Kelas:"), gbc);
        gbc.gridx = 1;
        tfKelas = new JTextField(15);
        panelInput.add(tfKelas, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelInput.add(new JLabel("Nilai 1:"), gbc);
        gbc.gridx = 1;
        tfNilai1 = new JTextField(15);
        panelInput.add(tfNilai1, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelInput.add(new JLabel("Nilai 2:"), gbc);
        gbc.gridx = 1;
        tfNilai2 = new JTextField(15);
        panelInput.add(tfNilai2, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelInput.add(new JLabel("Nilai 3:"), gbc);
        gbc.gridx = 1;
        tfNilai3 = new JTextField(15);
        panelInput.add(tfNilai3, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panelInput.add(new JLabel("Cari/Hapus NIM:"), gbc);
        gbc.gridx = 1;
        tfCariNim = new JTextField(15);
        panelInput.add(tfCariNim, gbc);

        add(panelInput, BorderLayout.NORTH);

        // Panel untuk tombol
        JPanel panelButton = new JPanel(new FlowLayout());
        panelButton.setBackground(new Color(240, 248, 255));
        btnTambah = new JButton("Tambah Mahasiswa");
        btnTambah.setBackground(new Color(34, 139, 34));
        btnTambah.setForeground(Color.WHITE);
        btnCari = new JButton("Cari Mahasiswa");
        btnCari.setBackground(new Color(70, 130, 180));
        btnCari.setForeground(Color.WHITE);
        btnHapus = new JButton("Hapus Mahasiswa");
        btnHapus.setBackground(new Color(220, 20, 60));
        btnHapus.setForeground(Color.WHITE);
        btnTampilkan = new JButton("Tampilkan Semua");
        btnTampilkan.setBackground(new Color(255, 165, 0));
        btnTampilkan.setForeground(Color.WHITE);

        panelButton.add(btnTambah);
        panelButton.add(btnCari);
        panelButton.add(btnHapus);
        panelButton.add(btnTampilkan);

        add(panelButton, BorderLayout.CENTER);

        // List untuk menampilkan mahasiswa
        listModel = new DefaultListModel<>();
        listMahasiswa = new JList<>(listModel);
        listMahasiswa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMahasiswa.setBackground(new Color(255, 255, 255));
        JScrollPane scrollPane = new JScrollPane(listMahasiswa);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Mahasiswa"));
        add(scrollPane, BorderLayout.SOUTH);

        // Event listeners
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahMahasiswa();
            }
        });

        btnCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cariMahasiswa();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hapusMahasiswa();
            }
        });

        btnTampilkan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tampilkanSemua();
            }
        });
    }

    private void tambahMahasiswa() {
        try {
            String nama = tfNama.getText();
            String nim = tfNim.getText();
            String kelas = tfKelas.getText();
            double nilai1 = Double.parseDouble(tfNilai1.getText());
            double nilai2 = Double.parseDouble(tfNilai2.getText());
            double nilai3 = Double.parseDouble(tfNilai3.getText());

            Mahasiswa mhs = new Mahasiswa(nama, nim, kelas, nilai1, nilai2, nilai3);
            daftarMahasiswa.add(mhs);
            JOptionPane.showMessageDialog(this, "Mahasiswa ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            tfNama.setText("");
            tfNim.setText("");
            tfKelas.setText("");
            tfNilai1.setText("");
            tfNilai2.setText("");
            tfNilai3.setText("");

            // Update list
            updateList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Nilai harus angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cariMahasiswa() {
        String cariNim = tfCariNim.getText();
        boolean found = false;
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(cariNim)) {
                JOptionPane.showMessageDialog(this, "Ditemukan:\nNama: " + m.getNama() + "\nNIM: " + m.getNim() + "\nKelas: " + m.getKelas() + "\nNilai 1: " + m.getNilai1() + "\nNilai 2: " + m.getNilai2() + "\nNilai 3: " + m.getNilai3() + "\nNilai RataRata: " + String.format("%.2f", m.getRataRata()), "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak ditemukan.", "Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void hapusMahasiswa() {
        String hapusNim = tfCariNim.getText();
        boolean removed = false;
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).getNim().equals(hapusNim)) {
                daftarMahasiswa.remove(i);
                JOptionPane.showMessageDialog(this, "Mahasiswa dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                removed = true;
                break;
            }
        }
        if (!removed) {
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak ditemukan.", "Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
        }
        // Update list
        updateList();
    }

    private void tampilkanSemua() {
        updateList();
    }

    private void updateList() {
        listModel.clear();
        for (Mahasiswa m : daftarMahasiswa) {
            listModel.addElement(m.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DataNilaiMahasiswa().setVisible(true);
            }
        });
    }
}
