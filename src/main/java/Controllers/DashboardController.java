package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.User;
import util.SessionManager;

public class DashboardController {

    @FXML
    private Label WelcomeLabel;

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getCurrentUser();

        if (currentUser != null) {
            WelcomeLabel.setText("Mirë se vini, " + currentUser.getName() +
                    " (" + currentUser.getRoli() + ")");
        } else {
            WelcomeLabel.setText("Nuk jeni të kyçur.");
        }
    }
}
