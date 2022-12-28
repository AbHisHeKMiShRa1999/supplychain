package com.example.supplychainbyabhishek;

import java.sql.*;



public class DATABASECONNECTOR {
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/supplychainbyabhi";
    private static final String username = "root";
    private static final String password = "Abhishek";

    public Statement getStatement() {
        Statement statement = null;
        Connection conn;
        try {
            conn = DriverManager.getConnection(databaseUrl, username, password);
            statement = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;


    }

    public ResultSet getQueryTable(String query) {
        Statement statement = getStatement();
        try {
            return statement.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdateQuery(String query) {
        Statement statement = getStatement();
        try {
            return statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DATABASECONNECTOR databaseconnector = new DATABASECONNECTOR();
        ResultSet rs = databaseconnector.getQueryTable("SELECT email,firstname FROM COUSTOMER");
        try {
            while (rs.next()) {
                System.out.println(rs.getString("email") + " " + rs.getString("firstname"));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



