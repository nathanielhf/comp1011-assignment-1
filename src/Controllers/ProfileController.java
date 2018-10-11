package Controllers;

import Models.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML private ImageView profileImage;
    @FXML private Button chooseImageButton;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField phoneTextField;
    @FXML private DatePicker birthdayDatePicker;
    @FXML private TextField idField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML void cancelChanges(ActionEvent event) { }
    @FXML void chooseImage(ActionEvent event) { }
    @FXML void saveProfile(ActionEvent event) { }

    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Parent formViewParent = FXMLLoader.load(getClass().getResource("/View/contactsView.fxml"));
        Scene formViewScene = new Scene(formViewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(formViewScene);
        window.show();
    }

    public void saveContactButtonPushed(ActionEvent event) throws IOException {
        Contact newContact = new Contact(
                                //Integer.parseInt(idField.getText()),
                                profileImage.getImage(),
                                firstNameTextField.getText(),
                                lastNameTextField.getText(),
                                birthdayDatePicker.getValue(),
                                addressTextField.getText(),
                                phoneTextField.getText()
                            );

        Parent formViewParent = FXMLLoader.load(getClass().getResource("/View/contactsView.fxml"));
        Scene formViewScene = new Scene(formViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(formViewScene);
        window.show();
    }
}