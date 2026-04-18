import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DataMahasiswaP5 extends JFrame {
    private String nim;
    private String nama;
    private String kelas;
    private double nilai;

    private JTextField nimField;
    private JTextField namaField;
    private JTextField kelasField;
    private JTextField nilaiField;
    private DefaultTableModel tableModel;
    private JTable table;
    private final List<DataMahasiswaP5> daftarMahasiswa = new ArrayList<>();

    public DataMahasiswaP5() {
        initComponents();
    }

    public DataMahasiswaP5(String nim, String nama, String kelas, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    private void initComponents() {
        setTitle("Input Data Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form Data Mahasiswa"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nimLabel = new JLabel("NIM:");
        JLabel namaLabel = new JLabel("Nama:");
        JLabel kelasLabel = new JLabel("Kelas:");
        JLabel nilaiLabel = new JLabel("Nilai:");

        nimField = new JTextField(15);
        namaField = new JTextField(15);
        kelasField = new JTextField(15);
        nilaiField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nimLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nimField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(namaLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(kelasLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(kelasField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(nilaiLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nilaiField, gbc);

        JButton addButton = new JButton("Input");
        JButton deleteButton = new JButton("Hapus");
        JButton showButton = new JButton("Tampilkan Data");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buttonPanel, gbc);

        String[] kolom = {"NIM", "Nama", "Kelas", "Nilai"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Mahasiswa"));

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahMahasiswa();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusMahasiswa();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanData();
            }
        });
    }

    private void tambahMahasiswa() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String kelas = kelasField.getText().trim();
        String nilaiText = nilaiField.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || kelas.isEmpty() || nilaiText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double nilai;
        try {
            nilai = Double.parseDouble(nilaiText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DataMahasiswaP5 mahasiswa = new DataMahasiswaP5(nim, nama, kelas, nilai);
        daftarMahasiswa.add(mahasiswa);
        tableModel.addRow(new Object[]{nim, nama, kelas, nilai});
        clearInputs();
    }

    private void hapusMahasiswa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data mahasiswa yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        daftarMahasiswa.remove(selectedRow);
        tableModel.removeRow(selectedRow);
    }

    private void tampilkanData() {
        refreshTable();
        if (daftarMahasiswa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada data mahasiswa.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (DataMahasiswaP5 m : daftarMahasiswa) {
            tableModel.addRow(new Object[]{m.getNim(), m.getNama(), m.getKelas(), m.getNilai()});
        }
    }

    private void clearInputs() {
        nimField.setText("");
        namaField.setText("");
        kelasField.setText("");
        nilaiField.setText("");
        nimField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DataMahasiswaP5().setVisible(true);
            }
        });
    }
}