// Nama : Amir Muafiq
// NIM : 053967231

package Tugas2PBD;

import java.util.Scanner;
import java.util.ArrayList;

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
		return namaMenu + "(" + kategori + ") - Rp" + harga;
	}
}

public class Main {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Menu> daftarMenu = new ArrayList();
	
	public static void main (String[] args) {
		// Tambahkan menu awal
		daftarMenu.add(new Menu("Steak", 48000, "Makanan"));
		daftarMenu.add(new Menu("Spaghetti", 30000, "Makanan"));
		daftarMenu.add(new Menu("Burger",45000, "Makanan"));
		daftarMenu.add(new Menu("French Fries", 25000, "Makanan"));
		daftarMenu.add(new Menu("Latte", 30000, "Minuman"));
		daftarMenu.add(new Menu("Cappucino", 35000, "Minuman"));
		daftarMenu.add(new Menu("Americano", 25000, "Minuman"));
		daftarMenu.add(new Menu("Matcha Latte", 45000, "Minuman"));
		
		int pilihan;
		do {
			System.out.println("\n" + "=".repeat(10) + " Aplikasi Restoran " + "=".repeat(10));
			System.out.println("1. Menu Pelanggan");
			System.out.println("2. Menu Pemilik Restoran");
			System.out.println("3. Keluar");
			System.out.println("Pilih Menu: ");
			
			pilihan = getInt();
			
			switch (pilihan) {
			case 1 -> menuPelanggan();
			case 2 -> menuPemilik();
			case 3 -> System.out.println("Terima kasih telah menggunakan aplikasi restoran");
			default -> System.out.println("Pilihan tidak valid!");
			}
			
		} while (pilihan != 3);
	}
	
	// ==== Menu Pelanggan ====^
	static void menuPelanggan() {
		ArrayList<String> pesananNama = new ArrayList<>();
		ArrayList<Integer> pesananJumlah = new ArrayList<>();
		ArrayList<Double> pesananHarga = new ArrayList<>();
		
		System.out.println("\n" + "=".repeat(12) + " Menu Restoran " + "=".repeat(13));
		tampilkanMenu();
		
		System.out.println("\nKetik nama menu untuk memesan. atau 'selesai' untuk mengakhiri:");
		while (true) {
			System.out.print("Masukan nama menu: ");
			String nama = input.nextLine().trim();
			if (nama.equalsIgnoreCase("Selesai")) break;
			
			Menu menu = cariMenu(nama);
			if (menu == null) {
				System.out.println("Menu tidak ditemukan. Silahkan coba lagi!");
				continue;
			}
			System.out.print("Jumlah pesanan: ");
			int jumlah = getInt();
			
			pesananNama.add(menu.namaMenu);
			pesananJumlah.add(jumlah);
			pesananHarga.add(menu.harga);
		}
		if (pesananNama.isEmpty()) {
			System.out.println("Tidak ada Pesanan.");
			return;
		}
		
		//hitung total biaya
		double subTotal = 0;
		for (int i=0; i<pesananNama.size(); i++) {
			subTotal += pesananHarga.get(i) * pesananJumlah.get(i);
		}
		
		double pajak = subTotal * 0.10;
		double biayaPelayanan = 20000;
		double diskon = (subTotal > 100000)? (subTotal * 0.10) : 0;
		
		boolean dapatGratis = subTotal > 50000;
		String minumanGratis = "";
		
		if (dapatGratis) {
			System.out.println("\nSELAMAT ANDA MENDAPATKAN MINUMAN GRATIS!");
			tampilkanMinuman();
			System.out.print("Silahkan pilih minuman gratis: ");
			minumanGratis = input.nextLine().trim();
		}
		
		double totalBayar = subTotal + pajak + biayaPelayanan - diskon;
		
		cetakStruk(pesananNama, pesananJumlah, pesananHarga, subTotal, pajak, biayaPelayanan, diskon, totalBayar, minumanGratis);
		
		
	}
	static void cetakStruk(ArrayList<String> nama, ArrayList<Integer> jumlah, ArrayList<Double> harga, double subTotal, double pajak, double biayaPelayanan, double diskon, double total, String gratis) {
		System.out.println("\n" + "=".repeat(18) + " Struk Pembayaran " + "=".repeat(18));
		System.out.printf("%-20s  %-10s %-10s %-10s\n", "Menu", "Jumlah", "Harga", "Total");
		System.out.println("-".repeat(55));
		
		for (int i=0; i<nama.size(); i++) {
			double totalPerItem = harga.get(i) * jumlah.get(i);
			System.out.printf("%-20s %-10d Rp%-9.0f Rp%.0f\n", nama.get(i), jumlah.get(i), harga.get(i), totalPerItem);
		}
		
		if(!gratis.isEmpty()) {
			System.out.printf("%-20s %-10.0f Rp%-9.0f Rp%.0f\n", gratis + "(GRATIS)" , 1.0 , cariHarga(gratis), 0.0);
		}
		System.out.println("-".repeat(55));
		System.out.printf("Subtotal\t\t\t: Rp%.0f\n", subTotal);
		System.out.printf("Pajak (10%%)\t\t: Rp%.0f\n", pajak);
		System.out.printf("Biaya Pelayanan\t\t: Rp%.0f\n", biayaPelayanan);
		if (diskon > 0) System.out.printf("Diskon 10%%\t\t: -Rp%.0f\n", diskon);
		System.out.printf("Total Bayar\t\t: Rp%.0f\n", total);
		System.out.println("=".repeat(55));
		System.out.println("Terima kasih atas kunjungan anda!");
		}
	
	// ===== MENU PEMILIK =====
	static void menuPemilik() {
		int pilih;
			do {
				System.out.println("\n" + "=".repeat(9) + " MENU PEMILIK RESTORAN " + "=".repeat(9));
				
				System.out.println("1. Lihat Daftar menu");
				System.out.println("2. Tambah Menu");
				System.out.println("3. Ubah Harga menu");
				System.out.println("4. Hapus Menu");
				System.out.println("5. Kembali");
				System.out.print("Pilih: ");
				pilih = getInt();
				
				switch (pilih ) {
				case 1 -> tampilkanMenu();
				case 2 -> tambahMenu();
				case 3 -> ubahHargaMenu();
				case 4 -> hapusMenu();
				case 5 -> System.out.println("Kembali ke menu utama....");
				default -> System.out.println("Pilihan Tidak Valid");
				}
			} while (pilih != 5);
		}
		
		static void tampilkanMenu() {
			System.out.println("\n" + "=".repeat(40));
			System.out.println(" ".repeat(12) + " DAFTAR MENU "  );
			System.out.println("=".repeat(40));
			
			boolean adaMakanan = false;
			boolean adaMinuman = false;
			
			//Tampilkan kategori makanan dulu
			System.out.println("=== Makanan ===");
			for (int i=0; i<daftarMenu.size(); i++) {
				Menu menu = daftarMenu.get(i);
				if (menu.kategori.equalsIgnoreCase("Makanan")) {
					System.out.printf("%-3d %-20s RP%,.0f%n", i+1, menu.namaMenu, menu.harga);
					adaMakanan = true;
				}
			}
			if(!adaMakanan) System.out.println("(Belum ada makanan!");
			
			// Lalu tampilkan menu minuman
			System.out.println("=== Minuman ===");
			for (int i=0; i<daftarMenu.size(); i++) {
				Menu menu = daftarMenu.get(i);
				if (menu.kategori.equalsIgnoreCase("Minuman")) {
					System.out.printf("%-3d %-20s RP%,.0f%n", i+1, menu.namaMenu, menu.harga);
					adaMinuman = true;
				}
			}
			if(!adaMinuman) System.out.println("(Belum ada minuman!");
			System.out.println("=".repeat(40));
	
	}
		static void tampilkanMinuman() {
			System.out.println("\n" + "-".repeat(10) + " Pilihan Minuman " + "-".repeat(10));
			for (Menu m : daftarMenu) {
				if (m.kategori.equalsIgnoreCase("Minuman")) {
					System.out.println("- " + m.namaMenu);
				}
			}
		}
		
		static void tambahMenu() {
			System.out.print("Masukan nama menu baru: ");
			String nama = input.nextLine();
			System.out.print("Masukan harga: ");
			double harga = getDouble();
			System.out.print("Masukan kategori (Makanan/Minuman): ");
			String kategori = input.nextLine();
;			Menu menuBaru = new Menu(nama, harga, kategori);
			
			// Tentukan posisi penyisipan berdasarkan kategori
			int posisi = 0;
			if (kategori.equalsIgnoreCase("Makanan")) {
				for (int i=0; i<daftarMenu.size(); i++) {
					if(daftarMenu.get(i).kategori.equalsIgnoreCase("Makanan")) {
						posisi = i + 1;
					}
				}
				daftarMenu.add(posisi, menuBaru);
			}
			else if (kategori.equalsIgnoreCase("Minuman")) {
	            // cari posisi pertama minuman, sisipkan setelahnya
	            for (int i = 0; i < daftarMenu.size(); i++) {
	                if (daftarMenu.get(i).kategori.equalsIgnoreCase("Minuman")) {
	                    daftarMenu.add(i, menuBaru);
	                    System.out.println("Menu baru berhasil ditambahkan ke kategori Minuman!");
	                    return;
	                }
	            }
	            daftarMenu.add(menuBaru); 
			} else {
				daftarMenu.add(menuBaru);
			}
			System.out.println("Daftar menu berhasil ditambahkan ke kategori " + kategori + "!");
		}
		
		static void ubahHargaMenu() {
			tampilkanMenu();
			System.out.print("Masukan nomor yang ingin diubah: ");
			int index = getInt() -1;
			
			if (index < 0 || index >= daftarMenu.size()) {
				System.out.println("Nomor tidak valid!");
				return;
			}
			
			System.out.print("Yakin ubah harga " + daftarMenu.get(index).namaMenu + "? (Ya/Tidak): ");
			String konfirmasi = input.nextLine();
			if (konfirmasi.equalsIgnoreCase("ya")) {
				System.out.print("Masukan harga baru: ");
				double hargaBaru = getDouble();
				daftarMenu.get(index).harga = hargaBaru;
				System.out.println("Harga Berhasil diubah!");
			} else {
				System.out.println("Perubahan dibatalkan.");
			}
		}
		static void hapusMenu() {
			tampilkanMenu();
			System.out.print("Masukan nomor menu yang akan dihapus: ");
			int index = getInt() - 1;
			if (index < 0 || index >= daftarMenu.size()) {
				System.out.println("Nomor tidak valid!");
				return;
			}
			
			System.out.print("Yakin hapus " + daftarMenu.get(index).namaMenu + "? (Ya/Tidak)");
			String konfirmasi = input.nextLine();
			if (konfirmasi.equalsIgnoreCase("Ya")) {
				daftarMenu.remove(index);
				System.out.println("Menu berhasil dihapus!");
			} else {
				System.out.println("Penghapusan dibatalkan.");
			}
		}
		
		//==== Fungsi tambahan ====
		static Menu cariMenu(String nama) {
			for (Menu m : daftarMenu) {
				if (m.namaMenu.equalsIgnoreCase(nama)) return m;
			}
			return null;
		}
		
		static double cariHarga (String nama) {
			Menu m = cariMenu(nama);
			return (m!= null) ? m.harga : 0;
		}
		static int getInt() {
			while (true) {
				try {
					return Integer.parseInt(input.nextLine());
				} catch (Exception e) {
					System.out.print("Input tidak valid! Masukan angka: ");
				}
			}
		}
		
		static double getDouble() {
			while (true) {
				try {
					return Double.parseDouble(input.nextLine());
				} catch (Exception e) {
					System.out.print("Input tidak valid! Masukan angka: ");
				}
			}
		}
	}

