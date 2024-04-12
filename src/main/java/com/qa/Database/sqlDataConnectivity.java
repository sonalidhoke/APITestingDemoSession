package com.qa.Database;

import java.sql.*;

public class sqlDataConnectivity {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Loaded");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium", "root", "Di8vyanka@1234");

        System.out.println("connected to mysql");
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("select * from seleniumuser");
        while (rs.next()) {

            String firstname = rs.getString("Firstname");
            System.out.println(" First database record is created  " + firstname);

            String emailaddress = rs.getString("email");
            System.out.println("Second database record is created  " + emailaddress);


        }
    }
}
