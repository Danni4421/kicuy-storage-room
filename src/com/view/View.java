package com.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.config.Config;

public class View {

  private static Scanner sc = new Scanner(System.in);

  public static void getAllData() {
    System.out.print("\n  ::::: DATA BARANG :::::\n");

    // call getAllData from config
    System.out.println(Config.getAllData());
  }

  public static void getDetailData() {
    System.out.print("\n ::::: DETAIL BARANG :::::\n"
        + Config.getAllData() + "\nMasukkan Pilihan : ");

    int getPilihan = sc.nextInt();
    System.out.println(Config.getDetailData(getPilihan));
  }

  public static void searchData() {
    System.out.println("\n::::: Search Data :::::");
    System.out.print("\nCari berdasarkan : \n" +
        "1. Nama\n" + "2. Harga\n3. Umum\nMasukkan Pilihan : ");

    int getPilihan = sc.nextInt();
    sc.nextLine();

    try {

      System.out.print("Masukkan Keyword : ");
      String getInputKeyword = sc.nextLine();

      System.out.println("Hasil Pencarian : \n");
      System.out.println(Config.searchData(getPilihan, getInputKeyword));

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public static void insertData() {
    System.out.println("::::: MENAMBAH DATA :::::");

    boolean condition = false;

    do {
      try {

        System.out.print("Masukkan Nama Barang : ");
        String namaBarang = sc.nextLine();
        System.out.print("Masukkan Deskripsi Barang : ");
        String deskripsiBarang = sc.nextLine();
        System.out.print("Masukkan Harga Barang : ");
        int hargaBarang = sc.nextInt();

        condition = Config.insertData(namaBarang, deskripsiBarang, hargaBarang);

      } catch (InputMismatchException e) {
        // TODO: handle exception
        System.out.println("Harap masukkan input yang benar");
        condition = false;
      }
    } while (!condition);

  }

  public static void updateData() {

    getAllData();
    System.out.print("Pilih Barang : ");
    int getData = sc.nextInt();

    System.out.print("\n::::: UPDATE DATA :::::\n" +
        "1. Ubah Nama Barang\n" +
        "2. Ubah Harga Barang\n" +
        "3. Ubah Deskripsi Barang\n" +
        "4. Ubah Semua\n" +
        "Masukkan Pilihan : ");

    String newNamaBarang = null, newDeskripsiBarang = null;
    int newHargaBarang = 0;

    int getPilihan = sc.nextInt();
    sc.nextLine();

    switch (getPilihan) {
      case 1:
        System.out.print("Masukkan Nama Baru Barang : ");
        newNamaBarang = sc.nextLine();
        break;
      case 2:
        System.out.print("Masukkan Deskripsi Baru Barang : ");
        newDeskripsiBarang = sc.nextLine();
        break;
      case 3:
        System.out.print("Masukkan Harga Baru Barang : ");
        newHargaBarang = sc.nextInt();
        break;
      case 4:
        System.out.print("Masukkan Nama Baru Barang : ");
        newNamaBarang = sc.nextLine();
        System.out.print("Masukkan Deskripsi Baru Barang : ");
        newDeskripsiBarang = sc.nextLine();
        System.out.print("Masukkan Harga Baru Barang : ");
        newHargaBarang = sc.nextInt();
        break;
    }

    try {

      Config.updateData(getData, getPilihan, newNamaBarang, newDeskripsiBarang, newHargaBarang);

    } catch (Exception e) {
      // TODO: handle exception
      System.out.println("Mohon masukkan input yang benar!");
    }

  }

  public static void setToBin() {
    getAllData();
    System.out.print("Masukkan Pilihan : ");
    int getPilihan = sc.nextInt();
    if (Config.moveDataToRecycleBin(getPilihan)) {
      System.out.println("Data berhasil dihapus sementara");
    } else {
      System.out.println("Data gagal dihapus!");
    }
  }
}
