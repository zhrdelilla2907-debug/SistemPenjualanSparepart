import java.util.ArrayList;
import java.util.Scanner;

class Barang {
    String nama;
    double harga;
    String kategori;

    Barang(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class TokoSparepart {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Barang> daftarBarang = new ArrayList<>();

    public static void main(String[] args) {

        daftarBarang.add(new Barang("Oli Mesin", 50000, "Bahan Penunjang"));
        daftarBarang.add(new Barang("Oli Gardan", 35000, "Bahan Penunjang"));
        daftarBarang.add(new Barang("Kampas Rem", 75000, "Alat"));
        daftarBarang.add(new Barang("Busi NGK", 25000, "Alat"));
        daftarBarang.add(new Barang("Rantai Motor", 120000, "Alat"));

        int pilih;

        do {
            System.out.println("\n=== TOKO SPAREPART MOTOR ===");
            System.out.println("1. Pemesanan Barang");
            System.out.println("2. Kelola Barang");
            System.out.println("3. Keluar");
            System.out.print("Pilih Menu : ");
            pilih = input.nextInt();

            switch (pilih) {
                case 1:
                    pemesanan();
                    break;
                case 2:
                    kelolaBarang();
                    break;
                case 3:
                    System.out.println("Terima kasih");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilih != 3);
    }

    static void tampilBarang() {
        System.out.println("\n=== DAFTAR BARANG ===");

        for (int i = 0; i < daftarBarang.size(); i++) {
            Barang b = daftarBarang.get(i);

            System.out.println((i + 1) + ". "
                    + b.nama + " | Rp" + b.harga
                    + " | " + b.kategori);
        }
    }

    static void pemesanan() {

        ArrayList<String> namaPesanan = new ArrayList<>();
        ArrayList<Integer> jumlahPesanan = new ArrayList<>();
        ArrayList<Double> totalItem = new ArrayList<>();

        double subtotal = 0;

        while (true) {

            tampilBarang();

            System.out.print("\nMasukkan nomor barang (0 = selesai): ");
            int pilih = input.nextInt();

            if (pilih == 0) {
                break;
            }

            if (pilih < 1 || pilih > daftarBarang.size()) {
                System.out.println("Pilihan tidak tersedia!");
                continue;
            }

            Barang barang = daftarBarang.get(pilih - 1);

            System.out.print("Jumlah : ");
            int jumlah = input.nextInt();

            double total = barang.harga * jumlah;

            namaPesanan.add(barang.nama);
            jumlahPesanan.add(jumlah);
            totalItem.add(total);

            subtotal += total;
        }

        double pajak = subtotal * 0.10;
        double pelayanan = 20000;

        double totalAwal = subtotal + pajak + pelayanan;

        double diskon = 0;

        if (totalAwal > 500000) {
            diskon = totalAwal * 0.10;
        }

        double totalBayar = totalAwal - diskon;

        System.out.println("\n=================================");
        System.out.println("      STRUK PEMBAYARAN");
        System.out.println("=================================");

        for (int i = 0; i < namaPesanan.size(); i++) {
            System.out.println(namaPesanan.get(i));
            System.out.println(jumlahPesanan.get(i)
                    + " x = Rp" + totalItem.get(i));
        }

        System.out.println("---------------------------------");
        System.out.println("Subtotal      : Rp" + subtotal);
        System.out.println("Pajak 10%     : Rp" + pajak);
        System.out.println("Pelayanan     : Rp" + pelayanan);
        System.out.println("Diskon        : Rp" + diskon);
        System.out.println("---------------------------------");
        System.out.println("TOTAL BAYAR   : Rp" + totalBayar);
        System.out.println("=================================");
    }

    static void kelolaBarang() {

        int pilih;

        do {

            System.out.println("\n=== KELOLA BARANG ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Ubah Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Kembali");
            System.out.print("Pilih : ");
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1:
                    tambahBarang();
                    break;

                case 2:
                    ubahBarang();
                    break;

                case 3:
                    hapusBarang();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Pilihan salah!");
            }

        } while (pilih != 4);
    }

    static void tambahBarang() {

        System.out.print("Nama Barang : ");
        String nama = input.nextLine();

        System.out.print("Harga : ");
        double harga = input.nextDouble();
        input.nextLine();

        System.out.print("Kategori : ");
        String kategori = input.nextLine();

        daftarBarang.add(new Barang(nama, harga, kategori));

        System.out.println("Barang berhasil ditambahkan!");
    }

    static void ubahBarang() {

        tampilBarang();

        System.out.print("Pilih nomor barang : ");
        int no = input.nextInt();
        input.nextLine();

        if (no < 1 || no > daftarBarang.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.print("Yakin ingin mengubah? (Y/T) : ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("Y")) {

            System.out.print("Nama Baru : ");
            String nama = input.nextLine();

            System.out.print("Harga Baru : ");
            double harga = input.nextDouble();
            input.nextLine();

            System.out.print("Kategori Baru : ");
            String kategori = input.nextLine();

            daftarBarang.get(no - 1).nama = nama;
            daftarBarang.get(no - 1).harga = harga;
            daftarBarang.get(no - 1).kategori = kategori;

            System.out.println("Data berhasil diubah!");
        }
    }

    static void hapusBarang() {

        tampilBarang();

        System.out.print("Pilih nomor barang : ");
        int no = input.nextInt();
        input.nextLine();

        if (no < 1 || no > daftarBarang.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.print("Yakin ingin menghapus? (Y/T) : ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("Y")) {

            daftarBarang.remove(no - 1);

            System.out.println("Data berhasil dihapus!");
        }
    }
}