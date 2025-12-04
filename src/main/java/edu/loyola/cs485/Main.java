package edu.loyola.cs485;

import edu.loyola.cs485.view.MainFrame;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    static String Port = "3306"; //default MySQL port
    static String Database = "music_db"; // database/schema name
    static String Username = "cs485dev"; //read this from a local file
    static String Password = "csforever"; //Also read this from a file

    static String url = ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame(); // Instantiates the Window
        frame.setVisible(true); // Activates it (and turn it visible)

    }

    public static void secureInsertClient() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        String sql = "INSERT INTO client(name_client,email) VALUES (?,?);";
        // We use question marks for parameters on our SQL

        PreparedStatement pst = con.prepareStatement(sql); //PreparedStatement are SECURE
        pst.setString(1, "CS485 Student"); // Parameter 1 = CS495 Student
        pst.setString(2,"student@loyola.edu"); // Parameter 2 = student@loyola.edu
        pst.executeUpdate(); // executeUpdate() is for INSERT, DELETE, UPDATE

        con.close();
    }

    @Deprecated
    public static void unsecureInsertArtist() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        // We write our SQL inside a String
        String sql = "INSERT INTO artist(name_artist) VALUES ('Elton John');";
        Statement st = con.createStatement(); //Creates an UNSECURE statement
        st.executeUpdate(sql); //executeQuery() are used in SELECTs

        con.close();
    }

    @Deprecated
    public static void unsecureSelectClient() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        String sql = "SELECT * FROM client ORDER BY name_client";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()) { //next() moves to the next row of results

            //Now we can fetch each attribute on the row
            int id = rs.getInt("id_client");
            String name = rs.getString("name_client");
            String email = rs.getString("email");
            Date dob = rs.getDate("dob");

            System.out.printf("%d:%s - %s - %s \n", id, name, email, dob);
        }
        con.close();
    }
}