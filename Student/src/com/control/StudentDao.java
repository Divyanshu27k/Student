/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Divyanshu Kumar
 */
public class StudentDao {
     private final String JDBC_URL = "jdbc:mysql://localhost:3306/studentDb";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "divyanshu27k";

    public List<Student> search(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentDb,root,divyanshu27k");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setMobile(rs.getString("mobile"));
                student.setGender(rs.getString("gender"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public List<Student> viewData() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student();
               student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setMobile(rs.getString("mobile"));
                student.setGender(rs.getString("gender"));
                student.setAddress(rs.getString("address"));
                students.add(student);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean insertData(Student student) {
        
                
        int rowsAffected = 0;
        
        
        String sql = "INSERT INTO students (id,name, age, mobile, gender, address) VALUES (?, ?, ?, ?, ?,?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getMobile());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getAddress());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
          
    }

    public int updateData(Student student) {
        int rowsAffected =5;
        String sql = "UPDATE students SET name = ?, age = ?, mobile = ?, gender = ?, address = ? WHERE id = ?";
           
        try ( Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getMobile());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getAddress());
             pstmt.setInt(6, student.getId());

            rowsAffected= pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    //        return rowsAffected;
        }
         return rowsAffected;
    }

    public int deleteData(int id) {
        int rowsAffected =0;
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
          

            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return rowsAffected;
    }



}