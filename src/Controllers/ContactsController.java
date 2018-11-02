package Controllers;

import Models.Contact;
import Models.DbConnect;
import View.SceneChanger;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {

    @FXML private TextField searchBar;
    @FXML private Button searchButton;
    @FXML private Button editContactButton;
    @FXML private Button createNewContact;
    // configure the table using TableView<object>
    @FXML private TableView<Contact> tableView;
    @FXML private TableColumn<Contact, Integer> idColumn;
    @FXML private TableColumn<Contact, String> firstNameColumn;
    @FXML private TableColumn<Contact, String> lastNameColumn;
    @FXML private TableColumn<Contact, String> addressColumn;
    @FXML private TableColumn<Contact, String> phoneColumn;

    /**
     * This initializes the controller class and instantiates the columns as variables
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // disable the edit button until a volunteer has been selected form the table
        editContactButton.setDisable(true);

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
     * This method changes scenes to the profileView,
     * where a user can create a new contact
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
     * If a user has been selected, enable edit button
     */
    public void contactSelected()
    {
        editContactButton.setDisable(false);
    }

    /**
     * If the edit button is pushed, send the selected user to the
     * ProfileView scene and preload it with data
     */
    public void editContactButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        Contact contact = this.tableView.getSelectionModel().getSelectedItem();
        ProfileController pc = new ProfileController();

        System.out.printf("The user image is in %s%n", contact.getProfileImage().getCanonicalPath());
        System.out.printf("The user fname is in %s%n", contact.getFirstName());
        System.out.printf("The user image is in %s%n", contact.getProfileImage());

        sc.changeScenes(event, "profileView.fxml", "Edit Contact", contact, pc);
    }

}




