import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DataMahasiswa {

    // Kelas Mahasiswa
    static class Mahasiswa {
        private String nama;
        private String ttl;
        private String alamat;
        private String pekerjaan;

        public Mahasiswa(String nama, String ttl, String alamat, String pekerjaan) {
            this.nama = nama;
            this.ttl = ttl;
            this.alamat = alamat;
            this.pekerjaan = pekerjaan;
        }

        public String getNama() { return nama; }
        public String getTtl() { return ttl; }
        public String getAlamat() { return alamat; }
        public String getPekerjaan() { return pekerjaan; }
    }

    public static void main(String[] args) {
        // Data mahasiswa dummy
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        mahasiswaList.add(new Mahasiswa("Ahmad", "Jakarta, 01-01-2000", "Jl. Sudirman No.1", "Mahasiswa"));
        mahasiswaList.add(new Mahasiswa("Budi", "Bandung, 15-05-1999", "Jl. Asia Afrika No.2", "Pegawai"));
        mahasiswaList.add(new Mahasiswa("Citra", "Surabaya, 20-10-2001", "Jl. Malioboro No.3", "Wiraswasta"));

        // Membuat GUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Data Mahasiswa");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Model tabel
            String[] columnNames = {"Nama", "TTL", "Alamat", "Pekerjaan"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Menambahkan data ke tabel
            for (Mahasiswa m : mahasiswaList) {
                Object[] row = {m.getNama(), m.getTtl(), m.getAlamat(), m.getPekerjaan()};
                model.addRow(row);
            }

            // Tabel
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            // Panel input
            JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
            inputPanel.setBorder(BorderFactory.createTitledBorder("Tambah Mahasiswa"));

            JTextField namaField = new JTextField();
            JTextField ttlField = new JTextField();
            JTextField alamatField = new JTextField();
            JTextField pekerjaanField = new JTextField();
            JButton tambahButton = new JButton("Tambah");

            inputPanel.add(new JLabel("Nama:"));
            inputPanel.add(namaField);
            inputPanel.add(new JLabel("TTL:"));
            inputPanel.add(ttlField);
            inputPanel.add(new JLabel("Alamat:"));
            inputPanel.add(alamatField);
            inputPanel.add(new JLabel("Pekerjaan:"));
            inputPanel.add(pekerjaanField);
            inputPanel.add(new JLabel(""));
            inputPanel.add(tambahButton);

            // Action listener untuk tombol tambah
            tambahButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nama = namaField.getText().trim();
                    String ttl = ttlField.getText().trim();
                    String alamat = alamatField.getText().trim();
                    String pekerjaan = pekerjaanField.getText().trim();

                    if (!nama.isEmpty() && !ttl.isEmpty() && !alamat.isEmpty() && !pekerjaan.isEmpty()) {
                        Mahasiswa newMahasiswa = new Mahasiswa(nama, ttl, alamat, pekerjaan);
                        mahasiswaList.add(newMahasiswa);
                        model.addRow(new Object[]{nama, ttl, alamat, pekerjaan});

                        // Clear fields
                        namaField.setText("");
                        ttlField.setText("");
                        alamatField.setText("");
                        pekerjaanField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            frame.setLayout(new BorderLayout());
            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
