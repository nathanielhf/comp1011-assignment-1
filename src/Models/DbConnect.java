package Models;

import java.io.File;
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

            //4. loop over the results, create Contact objects
            //   and add it to the ArrayList
            while (resultSet.next())
            {
                Contact newContact = new Contact(
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getDate("birthdate").toLocalDate(),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                    );
                newContact.setID(resultSet.getInt("contact_id"));
                //newContact.setProfileImage(new File(resultSet.getString("userImage")));
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

    public static void insertContactIntoDB(Contact newContact) throws SQLException {
        Connection conn=null;
        PreparedStatement ps = null;

        try {
            // 1. connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsdb?useSSL=false", user, password);

            // 2. create a sql statement
            String sql = "INSERT INTO contacts (userImage, fname, lname, birthdate, address, phone)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            // 3. create the PReparedStatement
            ps = conn.prepareStatement(sql);

            //Date birthdate = Date.valueOf(newContact.getLastName());

            // 4. bind the parameters
           ps.setString(1, newContact.getProfileImage().getName());
           ps.setString(2, newContact.getFirstName());
           ps.setString(3, newContact.getLastName());
           ps.setDate(4, Date.valueOf(newContact.getBirthday()));
           ps.setString(5, newContact.getAddress());
           ps.setString(6, newContact.getPhone());

            // 5. execute the INSERT statement
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.err.println("DbConnect.java line 87 says: " + e.getMessage());
        }
        finally {
            if (conn != null)
                conn.close();
            if (ps!=null)
                ps.close();
        }
    }

}
