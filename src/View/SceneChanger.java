package View;

import Models.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class SceneChanger {

    /**
     * This method accepts the title of the new scene,
     * the .fxml file name for the view, and the ActionEvent
     * that triggered the change
     */
    public void changeScenes(ActionEvent event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        // Get the stage from the event that was passed in
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method will change scenes and preload he next scene with
     * a Contact object
     */
    public void changeScenes(ActionEvent event, String viewName, String title, Contact contact, ControllerInterface controllerInterface) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        // access the controller class and preload the contact data
        controllerInterface = loader.getController();
        controllerInterface.preloadData(contact);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
