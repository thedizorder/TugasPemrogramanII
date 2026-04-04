import java.awt.event.*;
import javax.swing.*;

public class PraktekGUI extends JFrame {

    JLabel lbl1, lbl2, lbl3;
    JTextField txt1, txt2, txtHasil;
    JButton btnTambah, btnHapus, btnExit;

    public PraktekGUI() {

        setTitle("Praktek GUI Pemrograman II");
        setSize(400,250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label
        lbl1 = new JLabel("Angka Pertama");
        lbl1.setBounds(30,30,120,25);
        add(lbl1);

        lbl2 = new JLabel("Angka Kedua");
        lbl2.setBounds(30,70,120,25);
        add(lbl2);

        lbl3 = new JLabel("Hasil");
        lbl3.setBounds(30,110,120,25);
        add(lbl3);

        // TextField
        txt1 = new JTextField();
        txt1.setBounds(150,30,150,25);
        add(txt1);

        txt2 = new JTextField();
        txt2.setBounds(150,70,150,25);
        add(txt2);

        txtHasil = new JTextField();
        txtHasil.setBounds(150,110,150,25);
        txtHasil.setEditable(false);
        add(txtHasil);

        // Button
        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(30,160,90,30);
        add(btnTambah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(140,160,90,30);
        add(btnHapus);

        btnExit = new JButton("Exit");
        btnExit.setBounds(250,160,90,30);
        add(btnExit);

        // Event Tambah
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(txt1.getText());
                int b = Integer.parseInt(txt2.getText());
                int hasil = a + b;
                txtHasil.setText(String.valueOf(hasil));
            }
        });

        // Event Hapus
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt1.setText("");
                txt2.setText("");
                txtHasil.setText("");
            }
        });

        // Event Exit
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PraktekGUI();
    }
}