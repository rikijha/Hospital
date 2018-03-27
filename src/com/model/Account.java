
package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;


public class Account {
    //step 1
    String user="root";
    String pass="";
    String url="jdbc:mysql://localhost:3306/hospital";
    String driver="com.mysql.jdbc.Driver";
    Connection con;
    //step 2
    void dbConnect() throws ClassNotFoundException, SQLException{
    Class.forName(driver);//loaded driver
 con =    DriverManager.getConnection(url, user, pass);//created connection
 
    }
    void dbClose() throws SQLException{
    con.close();
    }
    public void insertPatientInfo(String name,String contact,String age,String doc_name,String date,String time) throws ClassNotFoundException, SQLException{
      dbConnect();
      String query="insert into patient(name,contact,age,dname,date,time) values(?,?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, contact);
        pst.setString(3, age);
        pst.setString(4, doc_name);
        pst.setString(5, date);
        pst.setString(6, time);
            
        pst.executeUpdate();
      dbClose();
    }
    public  ResultSet fetchPatientInfo(String d) throws ClassNotFoundException, SQLException{
    dbConnect();
    String query="select * from patient where date=?";
        PreparedStatement pst=con.prepareStatement(query);
        
        pst.setString(1, d);
        ResultSet rst=  pst.executeQuery();
        return rst;
        
    }
    public ResultSet fetchPatientSearch(String name,String date) throws ClassNotFoundException, SQLException{
    dbConnect();
    String query="select * from patient where name=?";
        PreparedStatement pst=con.prepareStatement(query);
        
       // pst.setString(1, date);
        pst.setString(1, name);
        ResultSet rst=  pst.executeQuery();
        return rst;
    }
    public void deletePatientInfo(String name) throws ClassNotFoundException, SQLException{
       dbConnect();
       String query ="delete from patient where name=?";
       PreparedStatement pst=con.prepareStatement(query);
       pst.setString(1, name);
       pst.executeUpdate();
    }
}
