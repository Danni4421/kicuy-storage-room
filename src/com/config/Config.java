package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Config {

  // access modifier private
  private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static String DB_URL = "jdbc:mysql://localhost:3306/dbkicuyshop";
  private static String USERNAME = "root";
  private static String PASSWORD = "";

  private static Connection conn;
  private static Statement state;
  private static ResultSet rslt;

  private static void dbConnection() {
    try {

      // Connect Driver
      Class.forName(JDBC_DRIVER);

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

      Thread.sleep(100);

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return dataTemp;
  }
}
