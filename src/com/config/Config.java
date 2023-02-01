package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;

public class Config {

  // access modifier private
  // private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static String DB_URL = "jdbc:mysql://localhost:3306/dbkicuyshop";
  private static String USERNAME = "root";
  private static String PASSWORD = "";

  private static Connection conn;
  private static Statement state;
  private static ResultSet rslt;

  private static void dbConnection() {
    try {

      // registrasi Driver
      // Class.forName(JDBC_DRIVER);
      // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      // Apabila tidak pakai registrasi sudah bisa tidak usah lagi

      // Connect Database
      conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public static String getAllData() {

    // koneksi ke database dengan method connection
    dbConnection();

    // penampun data sementara untuk menampilkan data
    String dataTemp = "Maaf, data tidak ditemukan!!";

    try {

      // inisialisasi obj statement yang diambil dari koneksi
      state = conn.createStatement();

      // Query get all data SELECT * FROM <tabel>
      String query = "SELECT idBarang, namaBarang FROM tbl_barang";

      // mengisi resultData dengan hasil query
      rslt = state.executeQuery(query);

      // menginisialisasi dataTemp menjadi null
      dataTemp = "";

      // menampilkan data
      while (rslt.next()) { // mean : apabila masih ada data maka next()

        // concat dataTemp
        dataTemp += rslt.getInt("idBarang") + ". " +
            rslt.getString("namaBarang") + "\n";

      }

      conn.close();
      state.close();

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return dataTemp;
  }

  public static String getDetailData(int pilihan) {
    dbConnection();

    String dataTemp = "Tidak dapat menampilkan detail data";

    try {

      // statement
      state = conn.createStatement();

      // query
      String query = "SELECT * FROM tbl_barang WHERE idBarang = " + pilihan;

      rslt = state.executeQuery(query);

      dataTemp = "";

      while (rslt.next()) {
        dataTemp += "\nID \t\t: " + rslt.getInt(1) +
            "\nNama \t\t: " + rslt.getString(2) +
            "\nDeskripsi \t: " + rslt.getString(3) +
            "\nHarga \t\t: Rp." + rslt.getInt(4) + "\n";
      }

      conn.close();
      state.close();

    } catch (

    Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return dataTemp;
  }

  public static String searchData(int pilihan, String searchValue) {
    dbConnection();

    String dataTemp = "";
    String query = "";

    switch (pilihan) {
      case 1:
        query = "SELECT * FROM tbl_barang WHERE namaBarang LIKE '%" + searchValue + "%'";
        break;
      case 2:
        query = "SELECT * FROM tbl_barang WHERE hargaBarang LIKE '%" + searchValue + "%'";
        break;
      case 3:
        query = "SELECT * FROM tbl_barang WHERE idBarang LIKE '%" + searchValue +
            "%' OR namaBarang LIKE '%" + searchValue + "%' OR deskripsiBarang LIKE '%" + searchValue +
            "%' OR hargaBarang LIKE '%" + searchValue + "%'";
        break;
    }

    try {

      state = conn.createStatement();

      rslt = state.executeQuery(query);

      while (rslt.next()) {
        dataTemp += "ID \t\t: " + rslt.getInt("idBarang") +
            "\nNama \t\t: " + rslt.getString("namaBarang") +
            "\nDeskripsi\t: " + rslt.getString("deskripsiBarang") +
            "\nHarga\t\t: " + rslt.getInt("hargaBarang");

        dataTemp += "\n=============================\n";
      }

      conn.close();
      state.close();

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return dataTemp;
  }

  public static boolean insertData(String namaBarang, String deskripsiBarang, int hargaBarang) {
    dbConnection();
    boolean result = false;

    try {

      state = conn.createStatement();

      String query = "INSERT INTO tbl_barang VALUES (" + null + ",'" + namaBarang +
          "', '" + deskripsiBarang + "', '" + hargaBarang + "', '" + 1 + "')";

      if (!state.execute(query)) {
        System.out.println("Data berhasil ditambahkan!!\n");
        result = true;
      } else {
        System.out.println("Data gagal ditambahkan!!\n");
      }

      state.close();
      conn.close();

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return result;
  }

  public static boolean updateData(int id, int pilihan, String namaBarang, String deskripsiBarang,
      int hargaBarang) {
    boolean result = false;

    // connection
    dbConnection();

    // perintah query
    String query = "UPDATE tbl_barang";

    switch (pilihan) {
      case 1:
        query += " SET `namaBarang` = '" + namaBarang + "' WHERE idBarang = " + id;
        break;
      case 2:
        query += " SET `deskripsiBarang` = '" + deskripsiBarang + "' WHERE idBarang = " + id;
        break;
      case 3:
        query += " SET `hargaBarang` = '" + hargaBarang + "' WHERE idBarang = " + id;
        break;
      case 4:
        query += " SET `namaBarang` = '" + namaBarang + "', `deskripsiBarang` = '" +
            deskripsiBarang + "', `hargaBarang` = '" + hargaBarang + "' WHERE idBarang = " + id;
    }

    try {

      state = conn.createStatement();

      if (state.execute(query) == false) {
        result = true;
        System.out.println("Berhasil merubah data");
      } else {
        System.out.println("Gagal merubah data");
      }

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return result;

  }
}
