package Models;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DbConnect {
    private static String user = "root";
    private static String password = "";

    /**
     * This method connects to the database, selects all contacts
     * and then populates the GUI with all contacts
     * @return
     * @throws SQLException
     */
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
                        resultSet.getString("fname"),
                        resultSet.getString("lname"),
                        resultSet.getDate("birthdate").toLocalDate(),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                    );
                newContact.setID(resultSet.getInt("contact_id"));
                newContact.setProfileImage(new File(resultSet.getString("userImage")));
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

    /**
     * This method receives a Contact object as an argument
     * connects to the database, and executes an SQL insert statement
     * @param newContact
     * @throws SQLException
     */
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

            Date birthdate = Date.valueOf(newContact.getBirthday());

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

    /**
     * This method will update an existing Contact in the database
     */
    public static void updateContactInDB(Contact updatedContact) throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1. Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contactsdb?useSSL=false", user, password);

            // 2. Create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE contacts SET userImage = ?, fname = ?, lname = ?, birthdate = ?, address = ?, phone = ? WHERE contact_id = ?;";

            // 3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);

            // 4. convert the birthday into a Date object
            Date birthdate = Date.valueOf(updatedContact.getBirthday());

            // 5. bind the parameters
            preparedStatement.setString(1, updatedContact.getProfileImage().getName());
            preparedStatement.setString(2, updatedContact.getFirstName());
            preparedStatement.setString(3, updatedContact.getLastName());
            preparedStatement.setDate(4, Date.valueOf(updatedContact.getBirthday()));
            preparedStatement.setString(5, updatedContact.getAddress());
            preparedStatement.setString(6, updatedContact.getPhone());
            preparedStatement.setInt(7, updatedContact.getID());

            // 6. execute the update command on the SQL server
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }



}
