package aegis.ui;

import aegis.Aegis;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class represents the primary user interface for the Aegis chatbot.
 * It manages user interactions, displays dialog boxes, and handles user input.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Aegis aegis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image aegisImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    /**
     * Initializes the UI components. Binds the scroll pane to the height of the dialog container,
     * ensuring automatic scrolling as new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Aegis instance */
    public void setAegis(Aegis d) {
        aegis = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Aegis's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String aegisText = aegis.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getAegisDialog(aegisText, aegisImage)
        );
        userInput.clear();
    }
}
