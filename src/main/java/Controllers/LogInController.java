package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import services.UserService;
import models.Dto.UserDto.LoginUserDto;
import services.SceneManager;
import utils.SceneLocator;

public class LogInController {

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private Button LoginBttn;

    @FXML
    private Label ErrorLable;

    @FXML
    private Label CreateUsr; // ✅ Labeli që klikojmë për të shkuar në regjistrim

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {
        LoginBttn.setOnAction(event -> handleLogin());

        // 🔗 Kalimi në faqen e regjistrimit kur klikohet "Create your account"
        CreateUsr.setOnMouseClicked(event -> {
            try {
                SceneManager.load("/view/RegisterView.fxml");
            } catch (Exception e) {
                ErrorLable.setText("Nuk mund të hapet faqja e regjistrimit.");
                e.printStackTrace();
            }
        });
    }

    private void handleLogin() {
        String email = Username.getText().trim();
        String password = Password.getText();

        ErrorLable.setText(""); // Pastro mesazhin para validimit

        if (email.isEmpty() || password.isEmpty()) {
            ErrorLable.setText("Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        try {
            LoginUserDto loginDto = new LoginUserDto(email, password);
            User user = userService.login(loginDto);

            // Përkohësisht:
            ErrorLable.setText("Login i suksesshëm! Roli: " + user.getRoli());

            // Roli: admin, komunal, qytetar → hap faqen përkatëse
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
