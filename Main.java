package Tugas2PBD;

import java.util.ArrayList;
import java.util.Scanner;

// Class Menu
class Menu {
    String namaMenu;
    double harga;
    String kategori;

    Menu(String namaMenu, double harga, String kategori) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String toString() {
        return namaMenu + " (" + kategori + ") - Rp " + harga;
    }
}

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Menu> daftarMenu = new ArrayList<>();

    public static void main(String[] args) {
        // Tambahkan menu awal
        daftarMenu.add(new Menu("Steak", 48000, "Makanan"));
        daftarMenu.add(new Menu("Spaghetti", 30000, "Makanan"));
        daftarMenu.add(new Menu("Burger", 45000, "Makanan"));
        daftarMenu.add(new Menu("French Fries", 25000, "Makanan"));
        daftarMenu.add(new Menu("Latte", 30000, "Minuman"));
        daftarMenu.add(new Menu("Cappucino", 35000, "Minuman"));
        daftarMenu.add(new Menu("Americano", 25000, "Minuman"));
        daftarMenu.add(new Menu("Matcha Latte", 45000, "Minuman"));

        int pilihan;
        do {
            System.out.println("\n" + "=".repeat(10) + " APLIKASI RESTORAN " + "=".repeat(10));
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Menu Pemilik Restoran");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = getInt();

            switch (pilihan) {
                case 1 -> menuPelanggan();
                case 2 -> menuPemilik();
                case 3 -> System.out.println("Terima kasih telah menggunakan aplikasi restoran!");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 3);
    }

    // ============================= MENU PELANGGAN =============================
    static void menuPelanggan() {
        ArrayList<String> pesananNama = new ArrayList<>();
        ArrayList<Integer> pesananJumlah = new ArrayList<>();
        ArrayList<Double> pesananHarga = new ArrayList<>();

        System.out.println("\n"+"=".repeat(12) + " MENU RESTORAN " +"=".repeat(13));
        tampilkanMenu();

        System.out.println("\nKetik nama menu untuk memesan, atau 'selesai' untuk mengakhiri:");
        while (true) {
            System.out.print("Masukkan nama menu: ");
            String nama = input.nextLine().trim();
            if (nama.equalsIgnoreCase("selesai")) break;

            Menu menu = cariMenu(nama);
            if (menu == null) {
                System.out.println("Menu tidak ditemukan. Silakan coba lagi!");
                continue;
            }

            System.out.print("Jumlah pesanan: ");
            int jumlah = getInt();

            pesananNama.add(menu.namaMenu);
            pesananJumlah.add(jumlah);
            pesananHarga.add(menu.harga);
        }

        if (pesananNama.isEmpty()) {
            System.out.println("Tidak ada pesanan.");
            return;
        }

        // Hitung total biaya
        double subTotal = 0;
        for (int i = 0; i < pesananNama.size(); i++) {
            subTotal += pesananHarga.get(i) * pesananJumlah.get(i);
        }

        double pajak = subTotal * 0.10;
        double biayaPelayanan = 20000;
        double diskon = (subTotal > 100000) ? (subTotal * 0.10) : 0;

        boolean dapatGratis = subTotal > 50000;
        String minumanGratis = "";

        if (dapatGratis) {
            System.out.println("\nSELAMAT! ANDA MENDAPAT MINUMAN GRATIS!");
            tampilkanMinuman();
            System.out.print("Pilih satu minuman gratis: ");
            minumanGratis = input.nextLine().trim();
        }

        double totalBayar = subTotal + pajak + biayaPelayanan - diskon;

        cetakStruk(pesananNama, pesananJumlah, pesananHarga,
                   subTotal, pajak, biayaPelayanan, diskon, totalBayar, minumanGratis);
    }

    static void cetakStruk(ArrayList<String> nama, ArrayList<Integer> jumlah, ArrayList<Double> harga,
                           double subTotal, double pajak, double pelayanan, double diskon, double total, String gratis) {
        System.out.println("\n" + "=".repeat(18) +  "  STRUK PEMBAYARAN " + "=".repeat(18));
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Menu", "Jumlah", "Harga", "Total");
        System.out.println("-".repeat(55));

        for (int i = 0; i < nama.size(); i++) {
            double totalPerItem = harga.get(i) * jumlah.get(i);
            System.out.printf("%-20s %-10d Rp%-9.0f Rp%.0f\n", nama.get(i), jumlah.get(i), harga.get(i), totalPerItem);
        }

        if (!gratis.isEmpty()) {
            System.out.printf("%-20s %-10.0f Rp%-9.0f Rp%.0f\n", gratis + " (GRATIS)", 1.0, cariHarga(gratis), 0.0);
        }

        System.out.println("-".repeat(55));
        System.out.printf("Subtotal\t\t\t: Rp%.0f\n", subTotal);
        System.out.printf("Pajak (10%%)\t\t: Rp%.0f\n", pajak);
        System.out.printf("Biaya Pelayanan\t\t: Rp%.0f\n", pelayanan);
        if (diskon > 0) System.out.printf("Diskon 10%%\t\t: -Rp%.0f\n", diskon);
        System.out.printf("TOTAL BAYAR\t\t: Rp%.0f\n", total);
        System.out.println("=".repeat(55));
        System.out.println("Terima kasih atas kunjungan Anda!");
    }

    // ============================= MENU PEMILIK =============================
    static void menuPemilik() {
        int pilih;
        do {
            System.out.println("\n" + "=".repeat(9) + " MENU PEMILIK RESTORAN " + "=".repeat(9));
            System.out.println("1. Lihat Daftar Menu");
            System.out.println("2. Tambah Menu");
            System.out.println("3. Ubah Harga Menu");
            System.out.println("4. Hapus Menu");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            pilih = getInt();

            switch (pilih) {
                case 1 -> tampilkanMenu();
                case 2 -> tambahMenu();
                case 3 -> ubahHargaMenu();
                case 4 -> hapusMenu();
                case 5 -> System.out.println("Kembali ke menu utama...");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 5);
    }

    static void tampilkanMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println(" ".repeat(12) + "DAFTAR MENU KAFE");
        System.out.println("=".repeat(40));

        boolean adaMakanan = false;
        boolean adaMinuman = false;

        // Tampilkan kategori Makanan dulu
        System.out.println("\n=== MENU MAKANAN ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu menu = daftarMenu.get(i);
            if (menu.kategori.equalsIgnoreCase("Makanan")) {
                System.out.printf("%-3d %-20s Rp%,.0f%n", i + 1, menu.namaMenu, menu.harga);
                adaMakanan = true;
            }
        }
        if (!adaMakanan) System.out.println("(Belum ada makanan)");

        // Lalu tampilkan kategori Minuman
        System.out.println("\n=== MENU MINUMAN ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu menu = daftarMenu.get(i);
            if (menu.kategori.equalsIgnoreCase("Minuman")) {
                System.out.printf("%-3d %-20s Rp%,.0f%n", i + 1, menu.namaMenu, menu.harga);
                adaMinuman = true;
            }
        }
        if (!adaMinuman) System.out.println("(Belum ada minuman)");

        System.out.println("=".repeat(40));
    }


    static void tampilkanMinuman() {
        System.out.println("\n" + "-".repeat(10) + " PILIHAN MINUMAN " + "-".repeat(10));
        for (Menu m : daftarMenu) {
            if (m.kategori.equalsIgnoreCase("Minuman")) {
                System.out.println("- " + m.namaMenu);
            }
        }
    }

    static void tambahMenu() {
        System.out.print("Masukkan nama menu baru: ");
        String nama = input.nextLine();
        System.out.print("Masukkan harga: ");
        double harga = getDouble();
        System.out.print("Masukkan kategori (Makanan/Minuman): ");
        String kategori = input.nextLine();

        Menu menuBaru = new Menu(nama, harga, kategori);

        // Tentukan posisi penyisipan berdasarkan kategori
        int posisi = 0;

        if (kategori.equalsIgnoreCase("Makanan")) {
            // cari posisi terakhir makanan
            for (int i = 0; i < daftarMenu.size(); i++) {
                if (daftarMenu.get(i).kategori.equalsIgnoreCase("Makanan")) {
                    posisi = i + 1;
                }
            }
            daftarMenu.add(posisi, menuBaru);
        } else if (kategori.equalsIgnoreCase("Minuman")) {
            // cari posisi pertama minuman, sisipkan setelahnya
            for (int i = 0; i < daftarMenu.size(); i++) {
                if (daftarMenu.get(i).kategori.equalsIgnoreCase("Minuman")) {
                    daftarMenu.add(i, menuBaru);
                    System.out.println("Menu baru berhasil ditambahkan ke kategori Minuman!");
                    return;
                }
            }
            daftarMenu.add(menuBaru); // kalau belum ada minuman, tambahkan di akhir
        } else {
            daftarMenu.add(menuBaru);
        }

        System.out.println("Menu baru berhasil ditambahkan ke kategori " + kategori + "!");
    }


    static void ubahHargaMenu() {
        tampilkanMenu();
        System.out.print("Masukkan nomor menu yang akan diubah: ");
        int index = getInt() - 1;

        if (index < 0 || index >= daftarMenu.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.print("Yakin ubah harga " + daftarMenu.get(index).namaMenu + "? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            System.out.print("Masukkan harga baru: ");
            double hargaBaru = getDouble();
            daftarMenu.get(index).harga = hargaBaru;
            System.out.println("Harga berhasil diubah!");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    static void hapusMenu() {
        tampilkanMenu();
        System.out.print("Masukkan nomor menu yang akan dihapus: ");
        int index = getInt() - 1;

        if (index < 0 || index >= daftarMenu.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        System.out.print("Yakin hapus " + daftarMenu.get(index).namaMenu + "? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            daftarMenu.remove(index);
            System.out.println("Menu berhasil dihapus!");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    // ============================= FUNGSI TAMBAHAN =============================
    static Menu cariMenu(String nama) {
        for (Menu m : daftarMenu) {
            if (m.namaMenu.equalsIgnoreCase(nama)) return m;
        }
        return null;
    }

    static double cariHarga(String nama) {
        Menu m = cariMenu(nama);
        return (m != null) ? m.harga : 0;
    }

    static int getInt() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.print("Input tidak valid! Masukkan angka: ");
            }
        }
    }

    static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(input.nextLine());
            } catch (Exception e) {
                System.out.print("Input tidak valid! Masukkan angka: ");
            }
        }
    }
}