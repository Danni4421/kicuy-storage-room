package com.view;
import com.config.Config;

public class View {
  public static void getAllData() {
    System.out.print("\n  ::::: DATA BARANG :::::\n");

    // call getAllData from config
    System.out.println(Config.getAllData());
  }
}
