import javax.swing.JOptionPane;

public class BiodataMahasiswa {
    public static void main(String[] args) {
        // Biodata mahasiswa
        String nama = "Nama: Dandi Rizki Ramadan";
        String nim = "NIM: 231011400677";
        String jurusan = "Jurusan: Teknik Informatika";
        String alamat = "Alamat: Banten, Indonesia";
        String email = "Email: dandirizkiramadan@gmail.com";

        // Menggabungkan biodata menjadi satu string dengan format tabel menggunakan padding, titik dua sejajar
        String biodata = String.format("%-8s: %s\n", "Nama", "Dandi Rizki Ramadan") +
                         String.format("%-8s: %s\n", "NIM", "231011400677") +
                         String.format("%-8s: %s\n", "Jurusan", "Teknik Informatika") +
                         String.format("%-8s: %s\n", "Alamat", "Banten, Indonesia") +
                         String.format("%-8s: %s", "Email", "dandirizkiramadan@gmail.com");

        // Menampilkan biodata menggunakan JOptionPane dengan font monospace
        JOptionPane.showMessageDialog(null, "<html><pre style='font-family: monospace; font-size: 12pt;'>" + biodata + "</pre></html>", "Biodata Mahasiswa", JOptionPane.INFORMATION_MESSAGE);
    }
}