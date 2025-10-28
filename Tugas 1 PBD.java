import java.util.Scanner;

//Class menu
class Menu {
	String namaMenu;
	double harga;
	String kategori;
	
	Menu(String namaMenu, double harga, String kategori) {
		this.namaMenu = namaMenu;
		this.harga = harga;
		this.kategori = kategori;
	}
	
	// getter menu
	public String toString() {
		return namaMenu + "-Rp" + harga;
	}
}

public class Main {
	// Data menu
	private static Menu makanan1 = new Menu("Steak", 48000, "Makanan");
	private static Menu makanan2 = new Menu("Spaghetti", 30000, "Makanan");
	private static Menu makanan3 = new Menu("Burger", 45000, "Makanan");
	private static Menu makanan4 = new Menu("French Fries", 25000, "Makanan");
	
	private static Menu minuman1 = new Menu("Latte", 30000, "Minuman");
	private static Menu minuman2 = new Menu("Cappucino", 35000, "Minuman");
	private static Menu minuman3 = new Menu("Americano", 25000, "Minuman");
	private static Menu minuman4 = new Menu("Matcha Latte", 45000, "Minuman");
	
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
	
	// Menampilkan menu
	System.out.println("============== DAFTAR MENU ===============" );
	System.out.println("Makanan:");
	System.out.println("1. " + makanan1.namaMenu + "\t\tRp. " + makanan1.harga);
	System.out.println("2. " + makanan2.namaMenu + "\tRp. " + makanan2.harga);
	System.out.println("3. " + makanan3.namaMenu + "\tRp. " + makanan3.harga);
	System.out.println("4. " + makanan4.namaMenu + "\tRp. " + makanan4.harga);
	System.out.println("Minuman: ");
	System.out.println("1. " + minuman1.namaMenu + "\t\tRp. " + minuman1.harga);
	System.out.println("2. " + minuman2.namaMenu + "\tRp. " + minuman2.harga);
	System.out.println("3. " + minuman3.namaMenu + "\tRp. " + minuman3.harga);
	System.out.println("4. " + minuman4.namaMenu + "\tRp. " + minuman4.harga);

	System.out.println("==========================================");
	System.out.println("Masukan pesanan (format : Nama Menu = Jumlah)");
	System.out.println("Maksimal 4 pesanan, kosongkan bila tidak memesan");
	
	// Input pesanan
	System.out.println("Masukan pesanan 1: ");
	String pesanan1 = input.nextLine();
	System.out.println("Masukan pesanan 2: ");
	String pesanan2 = input.nextLine();
	System.out.println("Masukan pesanan 3: ");
	String pesanan3 = input.nextLine();
	System.out.println("Masukan pesanan 4: ");
	String pesanan4 = input.nextLine();

	String[] namaMenu = new String[4];
	int[] jumlah = new int[4];
	double[] hargaSatuan = new double[4];
	double[] totalPerItem = new double[4];
	
	if(!pesanan1.isEmpty()) {
		String[] data = pesanan1.split("=");
		namaMenu[0] = data[0].trim();
		jumlah[0] = Integer.parseInt(data[1].trim());
		hargaSatuan[0] = cariHarga(namaMenu[0]);
		totalPerItem[0] = hargaSatuan[0] * jumlah[0];
	}
	if(!pesanan2.isEmpty()) {
		String[] data = pesanan2.split("=");
		namaMenu[1] = data[0].trim();
		jumlah[1] = Integer.parseInt(data[1].trim());
		hargaSatuan[1] = cariHarga(namaMenu[1]);
		totalPerItem[1] = hargaSatuan[1] * jumlah[1];
	}
	if(!pesanan3.isEmpty()) {
		String[] data = pesanan3.split("=");
		namaMenu[2] = data[0].trim();
		jumlah[2] = Integer.parseInt(data[1].trim());
		hargaSatuan[2] = cariHarga(namaMenu[2]);
		totalPerItem[2] = hargaSatuan[2] * jumlah[2];
	}
	if(!pesanan4.isEmpty()) {
		String[] data = pesanan4.split("=");
		namaMenu[3] = data[0].trim();
		jumlah[3] = Integer.parseInt(data[1].trim());
		hargaSatuan[3] = cariHarga(namaMenu[3]);
		totalPerItem[3] = hargaSatuan[3] * jumlah[3];
	}
	
	// Hitung total
	double subTotal = totalPerItem[0] +  totalPerItem[1] + totalPerItem[2] +  totalPerItem[3];
	double pajak = subTotal * 0.10;
	double biayaPelayanan = subTotal + 20000;
	double diskon = subTotal > 100000 ? subTotal * 0.10 : 0;
	double totalBayar = subTotal + pajak + biayaPelayanan - diskon;
	
	// Penawaran minuman gratis
	boolean dapatGratis = subTotal > 50000;
	String minumanGratis = "";
	
	if (dapatGratis) {
		System.out.println("\nSELAMAT!! ANDA MENDAPAT MINUMAN GRATIS!!");
		System.out.println("Pilih salah satu minuman berikut:");
		System.out.println("1. "+ minuman1.namaMenu);
		System.out.println("2. "+ minuman2.namaMenu);
		System.out.println("3. "+ minuman3.namaMenu);
		System.out.println("4. "+ minuman4.namaMenu);
		
		System.out.println("Masukan nama minuman gratis: ");
		minumanGratis = input.nextLine();
	}
	
	// Cetak Struk
	System.out.println("\n================= STRUK PEMBAYARAN ==================");
	System.out.printf("%-15s %-10s %-15s %-10s\n", "Menu", "Jumlah", "Harga/Item", "Total");
	System.out.println("-----------------------------------------------------");
	
	if (namaMenu[0] != null) 
		System.out.printf("%-15s %-10d Rp%-13.0f Rp%.0f\n", namaMenu[0], jumlah[0], hargaSatuan[0], totalPerItem[0]);
	if (namaMenu[1] != null) 
		System.out.printf("%-15s %-10d Rp%-13.0f Rp%.0f\n", namaMenu[1], jumlah[1], hargaSatuan[1], totalPerItem[1]);
	if (namaMenu[2] != null) 
		System.out.printf("%-15s %-10d Rp%-13.0f Rp%.0f\n", namaMenu[2], jumlah[2], hargaSatuan[2], totalPerItem[2]);
	if (namaMenu[3] != null) 
		System.out.printf("%-15s %-10d Rp%-13.0f Rp%.0f\n", namaMenu[3], jumlah[3], hargaSatuan[3], totalPerItem[3]);
	
	if (!minumanGratis.isEmpty()) {
	    double hargaGratis = cariHarga(minumanGratis); // Cari harga
	    if (hargaGratis > 0) {
	        System.out.printf("%-15s %-10d Rp%-13.0f Rp%.0f\n", minumanGratis + " (GRATIS)", 1, hargaGratis, 0.0);
	    } 
	}
	
	System.out.println("-----------------------------------------------------");
    System.out.printf("Subtotal\t\t: Rp%.0f\n", subTotal);
    System.out.printf("Pajak (10%%)\t: Rp%.0f\n", pajak);
    System.out.printf("Biaya Pelayanan\t: Rp%.0f\n", biayaPelayanan);
    System.out.printf("Diskon\t\t: Rp%.0f\n", diskon);
    System.out.printf("Total Pembayaran\t: Rp%.0f\n", totalBayar);
	
	System.out.println("====================================================");
	System.out.println("Terima kasih!! Silahkan datang kembali.");
	
	input.close();
	}
	
	// Fungsi tambahan
	
	static double cariHarga(String nama) {
		if (nama.equalsIgnoreCase("Steak")) return 48000;
		else if (nama.equalsIgnoreCase("Spaghetti")) return 30000;
		else if (nama.equalsIgnoreCase("Burger")) return 45000;
		else if (nama.equalsIgnoreCase("French Fries")) return 25000;
		else if (nama.equalsIgnoreCase("Latte")) return 30000;
		else if (nama.equalsIgnoreCase("Cappucino")) return 35000;
		else if (nama.equalsIgnoreCase("Americano")) return 25000;
		else if (nama.equalsIgnoreCase("Matcha Latte")) return 45000;
		else return 0;
	}

}