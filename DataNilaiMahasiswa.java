import java.util.*;

class Mahasiswa {
    String nim;
    String nama;
    double nilai;

    Mahasiswa(String nim, String nama, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    String getGrade() {
        if (nilai >= 85) return "A";
        else if (nilai >= 70) return "B";
        else if (nilai >= 55) return "C";
        else if (nilai >= 40) return "D";
        else return "E";
    }
}

public class DataNilaiMahasiswa {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Mahasiswa> daftar = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== MENU DATA MAHASISWA ===");
            System.out.println("1. Tambah data mahasiswa");
            System.out.println("2. Tampilkan semua nilai");
            System.out.println("3. Tampilkan rata-rata nilai dalam kelas");
            System.out.println("4. Cari mahasiswa");
            System.out.println("5. Hapus mahasiswa");
            System.out.println("6. Rekap grade");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilihan = bacaInt();

            switch (pilihan) {
                case 1 -> tambahDataMahasiswa();
                case 2 -> tampilkanSemuaNilai();
                case 3 -> tampilkanRataRataNilai();
                case 4 -> cariMahasiswa();
                case 5 -> hapusMahasiswa();
                case 6 -> rekapGrade();
                case 0 -> System.out.println("Program selesai.");
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);
    }

    private static int bacaInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Masukkan angka yang benar: ");
            sc.next();
        }
        int nilai = sc.nextInt();
        sc.nextLine();
        return nilai;
    }

    private static double bacaDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Masukkan nilai yang benar: ");
            sc.next();
        }
        double nilai = sc.nextDouble();
        sc.nextLine();
        return nilai;
    }

    private static void tambahDataMahasiswa() {
        System.out.print("Masukkan NIM: ");
        String nim = sc.nextLine().trim();
        System.out.print("Masukkan Nama: ");
        String nama = sc.nextLine().trim();
        System.out.print("Masukkan Nilai: ");
        double nilai = bacaDouble();

        daftar.add(new Mahasiswa(nim, nama, nilai));
        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    private static void tampilkanSemuaNilai() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        System.out.println("\nDaftar Mahasiswa dan Nilai:");
        for (Mahasiswa m : daftar) {
            System.out.printf("%s - %s - Nilai: %.2f - Grade: %s%n", m.nim, m.nama, m.nilai, m.getGrade());
        }
    }

    private static void tampilkanRataRataNilai() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        double total = 0;
        for (Mahasiswa m : daftar) {
            total += m.nilai;
        }
        double rata = total / daftar.size();
        System.out.printf("Rata-rata nilai dalam kelas: %.2f%n", rata);
    }

    private static void cariMahasiswa() {
        System.out.print("Masukkan NIM yang dicari: ");
        String nimCari = sc.nextLine().trim();
        for (Mahasiswa m : daftar) {
            if (m.nim.equalsIgnoreCase(nimCari)) {
                System.out.printf("Ditemukan: %s - %s - Nilai: %.2f - Grade: %s%n", m.nim, m.nama, m.nilai, m.getGrade());
                return;
            }
        }
        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
    }

    private static void hapusMahasiswa() {
        System.out.print("Masukkan NIM yang akan dihapus: ");
        String nimHapus = sc.nextLine().trim();
        Iterator<Mahasiswa> iterator = daftar.iterator();
        while (iterator.hasNext()) {
            Mahasiswa m = iterator.next();
            if (m.nim.equalsIgnoreCase(nimHapus)) {
                iterator.remove();
                System.out.println("Data mahasiswa berhasil dihapus.");
                return;
            }
        }
        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
    }

    private static void rekapGrade() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        Map<String, Integer> rekap = new LinkedHashMap<>();
        for (Mahasiswa m : daftar) {
            rekap.put(m.getGrade(), rekap.getOrDefault(m.getGrade(), 0) + 1);
        }
        System.out.println("\nRekap Grade:");
        rekap.forEach((grade, jumlah) -> System.out.println("Grade " + grade + ": " + jumlah + " mahasiswa"));
    }
}
