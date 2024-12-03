/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class ConFig {
           public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
            con = DriverManager.getConnection("jdbc:sqlite:grades.db"); // Establish connection     
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }
    
    public void addStudent(String sql, Object... values) {
    try (Connection conn = this.connectDB(); // Use the connectDB method
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Loop through the values and set them in the prepared statement dynamically
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
            } else if (values[i] instanceof Double) {
                pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
            } else if (values[i] instanceof Float) {
                pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
            } else if (values[i] instanceof Long) {
                pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
            } else if (values[i] instanceof Boolean) {
                pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
            } else if (values[i] instanceof java.util.Date) {
                pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
            } else if (values[i] instanceof java.sql.Date) {
                pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
            } else if (values[i] instanceof java.sql.Timestamp) {
                pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
            } else {
                pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
            }
        }

        pstmt.executeUpdate();
        System.out.println("Record added successfully!");
    } catch (SQLException e) {
        System.out.println("Error adding record: " + e.getMessage());
    }
}
    
   
         public void viewStudent(String sqlQuery, String[] columnHeaders, String[] columnNames) {
        
        if (columnHeaders.length != columnNames.length) {
            System.out.println("Error: Mismatch between column headers and column names.");
            return;
        }

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

           
            StringBuilder headerLine = new StringBuilder();
            headerLine.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n| ");
            for (String header : columnHeaders) {
                headerLine.append(String.format("%-20s | ", header)); 
            }
            headerLine.append("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println(headerLine.toString());

           
            while (rs.next()) {
                StringBuilder row = new StringBuilder("| ");
                for (String colName : columnNames) {
                    String value = rs.getString(colName);
                    row.append(String.format("%-20s | ", value != null ? value : "")); 
                }
                System.out.println(row.toString());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
    }
     public void deleteStudent(String sql, Object... values) {
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Loop through the values and set them in the prepared statement dynamically
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
            } else {
                pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
            }
        }

        pstmt.executeUpdate();
        System.out.println("Record deleted successfully!");
    } catch (SQLException e) {
        System.out.println("Error deleting record: " + e.getMessage());
    }
}
         
     
     public void updateStudent(String sql, Object... values) {
        try (Connection conn = this.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop through the values and set them in the prepared statement dynamically
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
                } else {
                    pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }
     public double getSingleValues(String sql, Object... params){
         double result = 0.0;
         
         try(Connection conn = this.connectDB();
             PreparedStatement pst  = conn.prepareStatement(sql)){
             
             
             for(int i = 0; i < params.length; i++){
                 pst.setObject(i + 1, params[i]);
             }
             ResultSet rs = pst.executeQuery();
             if(rs.next()){
                 result  = rs.getDouble(1);
             }
         }catch(SQLException e ){
             System.out.println("Erorr retrieving single values: "+e.getMessage());
         }
         return  result;
     }
     
      public String getSingleVal(String sql, Object... params){
         String result = null;
         
         try(Connection conn = this.connectDB();
             PreparedStatement pst  = conn.prepareStatement(sql)){
             
             
             for(int i = 0; i < params.length; i++){
                 pst.setObject(i + 1, params[i]);
             }
             ResultSet rs = pst.executeQuery();
             if(rs.next()){
                 result  = rs.getString("Program_Head");
             }
         }catch(SQLException e ){
             System.out.println("Erorr retrieving single values: "+e.getMessage());
         }
         return  result;
     }
      public String getSingleVal1(String sql, Object... params){
         String result = null;
         
         try(Connection conn = this.connectDB();
             PreparedStatement pst  = conn.prepareStatement(sql)){
             
             
             for(int i = 0; i < params.length; i++){
                 pst.setObject(i + 1, params[i]);
             }
             ResultSet rs = pst.executeQuery();
             if(rs.next()){
                 result  = rs.getString("Year_and_Section");
             }
         }catch(SQLException e ){
             System.out.println("Erorr retrieving single values: "+e.getMessage());
         }
         return  result;
     }
       public void viewStudents(String query, String[] header, String[] columns, int applicantId) {
    try (Connection conn = this.connectDB();  // Use the configuration to get the connection
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the applicantId parameter for filtering
        stmt.setInt(1, applicantId);
        
        ResultSet rs = stmt.executeQuery();

        // Print header
        for (String h : header) {
            System.out.print(h + "\t");
        }
        System.out.println();
        
        // Print a separator line after the header for better readability
        System.out.println("------------------------------------------------------------");

        // Print rows
        while (rs.next()) {
            for (String col : columns) {
                System.out.print(rs.getString(col) + "          \t");
            }
            System.out.println();
            
            // Print a separator line between rows
            System.out.println("------------------------------------------------------------");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     
}
