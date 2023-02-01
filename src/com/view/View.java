package com.view;

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
}
