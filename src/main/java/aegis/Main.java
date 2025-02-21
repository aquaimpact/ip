package aegis;

import java.io.IOException;

import aegis.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Aegis using FXML.
 */
public class Main extends Application {

    private Aegis aegis = new Aegis();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Aegis.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAegis(aegis); // inject the Aegis instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
