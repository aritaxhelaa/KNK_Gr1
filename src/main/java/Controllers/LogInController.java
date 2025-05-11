package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import models.Dto.UserDto.LoginUserDto;
import services.SceneManager;
import services.UserService;
import utils.SceneLocator;

public class LogInController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Button LoginBttn;

    @FXML
    private Label ErrorLable;

    @FXML
    private Label CreateUsr;

    private final UserService userService = new UserService();

    /**
     * Metoda që lidhet me butonin "Log in"
     * e cila do të thirret nga SceneBuilder me `onAction="handleLogin"`
     */
    @FXML
    private void handleLogin() {
        String email = Username.getText().trim();
        String password = Password.getText();

        ErrorLable.setText("");

        if (email.isEmpty() || password.isEmpty()) {
            ErrorLable.setText("Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        try {
            LoginUserDto loginDto = new LoginUserDto(email, password);
            User user = userService.login(loginDto);

            ErrorLable.setText("Login i suksesshëm! Roli: " + user.getRoli());

            // Redirect sipas rolit
//            switch (user.getRoli()) {
//                case "admin" -> SceneManager.load(SceneLocator.ADMIN_DASHBOARD);
//                case "komunal" -> SceneManager.load(SceneLocator.KOMUNAL_DASHBOARD);
//                case "qytetar" -> SceneManager.load(SceneLocator.QYTETAR_DASHBOARD);
//                default -> ErrorLable.setText("Roli i panjohur.");
//            }

        } catch (Exception e) {
            ErrorLable.setText(e.getMessage());
        }
    }

    /**
     * Metoda që lidhet me klikimin në "Create your account"
     * e cila do të thirret nga SceneBuilder me `onMouseClicked="goToRegister"`
     */
    @FXML
    private void goToRegister() {
        try {
            SceneManager.load("/view/RegisterView.fxml");
        } catch (Exception e) {
            ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
            e.printStackTrace();
        }
    }

    // Metodë rezervë nëse do ta përdorësh manualisht diku
    private void openDashboard(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) Username.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            ErrorLable.setText("Nuk u hap pamja përkatëse.");
            e.printStackTrace();
        }
    }
}
