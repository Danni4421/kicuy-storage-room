import java.util.Scanner;
import com.view.View;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("  ::::: MENU :::::\n"
                    + "1. Lihat Data Barang\n"
                    + "2. Detail Data Barang\n"
                    + "3. Cari Data Barang\n"
                    + "4. Tambah Data Barang\n"
                    + "5. Edit Data Barang\n"
                    + "0. Keluar\n"
                    + "Masukkan Perintah : ");

            int getPilihan = sc.nextInt();

            if (getPilihan == 0) {
                break;
            }

            switch (getPilihan) {
                case 1:
                    View.getAllData();
                    break;
                case 2:
                    View.getDetailData();
                    break;
                case 3:
                    View.searchData();
                    break;
                case 4:
                    View.insertData();
                    break;
                case 5:
                    View.updateData();
                    break;
            }
        }

        sc.close();

    }
}
