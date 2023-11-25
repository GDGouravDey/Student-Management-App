package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDAO {
    public static boolean insertStudentToDB(Student st) {
        boolean f = false;
        // JDBC Code
        try {
            Connection con = CP.createC();
            String q = "insert into students(sname,sphone,scity) values(?,?,?)";
            // PreparedStatement
            PreparedStatement ps = con.prepareStatement(q);
            // Set Value of Parameters
            ps.setString(1, st.getStudentName());
            ps.setString(2, st.getStudentPhone());
            ps.setString(3, st.getStudentCity());

            // Execute
            ps.executeUpdate();
            f = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean insertStudentToDB(int userId, Student st) {
        boolean f = false;
        // JDBC Code
        try {
            Connection con = CP.createC();
            String q = "insert into students(sid,sname,sphone,scity) values(?,?,?,?)";
            // PreparedStatement
            PreparedStatement ps = con.prepareStatement(q);
            // Set Value of Parameters
            ps.setInt(1, userId);
            ps.setString(2, st.getStudentName());
            ps.setString(3, st.getStudentPhone());
            ps.setString(4, st.getStudentCity());

            // Execute
            ps.executeUpdate();
            f = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static int deleteStudent(int userId) {
        int rowsAffected = -1;
        // JDBC Code
        try {
            Connection con = CP.createC();
            String q = "delete from students where sid=?";
            // PreparedStatement
            PreparedStatement ps = con.prepareStatement(q);
            // Set Value of Parameters
            ps.setInt(1, userId);

            // Execute
            rowsAffected = ps.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public static boolean showAllStudents() {
        boolean f = false;
        // JDBC Code
        try {
            int k = 0;
            Connection con = CP.createC();
            String q = "select * from students;";
            Statement st = con.createStatement();

            ResultSet set = st.executeQuery(q);

            while(set.next()) {
                k++;
                int id = set.getInt(1);
                String name = set.getString(2);
                String phone = set.getString(3);
                String city = set.getString("scity");

                System.out.println("ID : "+id);
                System.out.println("Name : "+name);
                System.out.println("Phone : "+phone);
                System.out.println("City : "+city);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            }
            f = true;
            if(k == 0)
                System.out.println("No Students Found.");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean checkStudentExistence(int userId) {
        boolean studentExists = false;
        try {
            Connection con = CP.createC();
            String q = "SELECT COUNT(*) FROM students WHERE sid=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                studentExists = count > 0; // If count is greater than 0, student exists
                if(studentExists)
                    return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return studentExists;
    }
}
