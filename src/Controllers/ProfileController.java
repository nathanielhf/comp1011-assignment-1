package Controllers;

import Models.Contact;
import Models.DbConnect;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML private ImageView imageView;
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

    private File userImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            userImage = new File("./src/images/default-image.jpg");
            BufferedImage bufferedImage = ImageIO.read(userImage);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);


        } catch (IOException ioe) {
            System.err.println((ioe.getMessage()));
        }
    }

    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Parent formViewParent = FXMLLoader.load(getClass().getResource("/View/contactsView.fxml"));
        Scene formViewScene = new Scene(formViewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(formViewScene);
        window.show();
    }

    public void saveContactButtonPushed(ActionEvent event) throws IOException, SQLException {

        Contact newContact = new Contact(
                                firstNameTextField.getText(),
                                lastNameTextField.getText(),
                                birthdayDatePicker.getValue(),
                                addressTextField.getText(),
                                phoneTextField.getText()
                            );

        DbConnect.insertContactIntoDB(newContact);

        Parent formViewParent = FXMLLoader.load(getClass().getResource("/View/contactsView.fxml"));
        Scene formViewScene = new Scene(formViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(formViewScene);
        window.show();
    }


    /**
     * When the chooseImage button is pushed, a FileChooser window
     * is launched to allow the user to brows for a new image file
     */
    public void chooseImageButtonPushed(ActionEvent event)
    {
        // get the Stage to open a new window (aka Stage in JavaFX)
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // instantiate a FileChooser Object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose new image");

        // Filter for .jpg and .png files
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("image File (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("image File (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);

        // set default directory to user's picture directory or
        // user directory if no user dir available
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";

        // create directory using File object
        File userDirectory = new File(userDirectoryString);

        // if you cannot navigate the pictures directory, go to the home directory
        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));

        fileChooser.setInitialDirectory(userDirectory);

        // create temporary file
        // open the file dialog window
        File tempImgFile = fileChooser.showOpenDialog(stage);

        if(tempImgFile != null)
        {
            userImage = tempImgFile;
            // update the ImageView with the new image
            if (userImage.isFile())
            {
                try
                {
                    BufferedImage bufferedImage = ImageIO.read(userImage);
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(img);
                }
                catch (IOException ioe)
                {
                    System.err.println(ioe.getMessage());
                }
            }
        }



    }
}