package Controllers;

import Models.Contact;
import Models.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {

    @FXML private TextField searchBar;
    @FXML private Button searchButton;
    @FXML private Button editContact;
    @FXML private Button createNewContact;
    // configure the table using TableView<object>
    @FXML private TableView<Contact> tableView;
    @FXML private TableColumn<Contact, Integer> idColumn;
    @FXML private TableColumn<Contact, String> firstNameColumn;
    @FXML private TableColumn<Contact, String> lastNameColumn;
    @FXML private TableColumn<Contact, String> addressColumn;
    @FXML private TableColumn<Contact, String> phoneColumn;

    /**
     * when method called, change
     * Scene to ProfileView
     */
    public void createNewContactButtonPushed(ActionEvent event) throws IOException {
        Parent formViewParent = FXMLLoader.load(getClass().getResource("/View/profileView.fxml"));
        Scene formViewScene = new Scene(formViewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(formViewScene);
        window.show();
    }

    /**
     * This initializes the controller class and instantiates the columns as variables
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("ID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));

        /**
         * this method connects to DB, selects all contacts, and returns them
         */
        try {
            tableView.getItems().addAll(DbConnect.getContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an ObservableList of People objects
     */
//    public ObservableList<Contact> testPeople() {
//        ObservableList<Contact> contacts = FXCollections.observableArrayList();
//        contacts.add(new Contact(1, "Nathaniel", "Fisher", "945 Dominion", "555-5555"));
//
//        return contacts;
//    }




}




