package Models;

import java.sql.*;
import java.util.ArrayList;

public class DbConnect {
    private static String user = "root";
    private static String password = "";

    public static ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsdb?useSSL=false",
                    user, password);

            //2. create a Statement object
            statement = conn.createStatement();

            //3.  create the sql query
            resultSet = statement.executeQuery("SELECT * FROM contacts");

            //4. loop over the results, create MobilePhone objects
            //   and add it to the ArrayList
            while (resultSet.next())
            {
                Contact newContact = new Contact(
                        //resultSet.getInt("contact_id"),
                        resultSet.getImage(),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getDate("birthday"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                    );
                contacts.add(newContact);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return contacts;
    }

//    public static void insertContactIntoDB(Contact newContact) throws SQLException {
//        Connection conn=null;
//        PreparedStatement ps = null;
//
//        try {
//            // 1. connect to database
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonesDb?useSSL=false", user, password);
//
//            // 2. create a sql statement
//            String sql = "INSERT INTO PHONES (make, model, os, screenSize, memory, frontCamRes, rearCamRes)\n" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
//
//            // 3. create the PReparedStatement
//            ps = conn.prepareStatement(sql);
//
//            // 4. bind the paramenters
//            ps.setString(1, newPhone.getMake());
//            ps.setString(2, newPhone.getModel());
//            ps.setString(3, newPhone.getOs());
//            ps.setDouble(4, newPhone.getScreenSize());
//            ps.setDouble(5, newPhone.getMemory());
//            ps.setDouble(6, newPhone.getFrontCameraRes());
//            ps.setDouble(7, newPhone.getRearCameraRes());
//
//            // 5. execute the INSERT statement
//            ps.executeUpdate();
//        }
//        catch (SQLException e){
//            System.err.println(e);
//        }
//        finally {
//            if (conn != null)
//                conn.close();
//            if (ps!=null)
//                ps.close();
//        }
//    }
}
