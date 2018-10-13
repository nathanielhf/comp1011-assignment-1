package Models;

import java.sql.SQLException;

public class DbTester {

    public static void main(String[] args)
    {
        try {
            System.out.println(DbConnect.getContacts());

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}