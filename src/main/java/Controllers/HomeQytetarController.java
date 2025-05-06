package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeQytetarController {

    @FXML
    private Label lblWelcome;

    @FXML
    private Button btnKerkoAdrese;

    @FXML
    private Button btnShfaqHarte;

    @FXML
    private Button btnDil;

    @FXML
    private void initialize() {
        // Tekst mirëseardhjeje mund të vendoset dinamikisht më vonë
        lblWelcome.setText("Mirë se vini, qytetar!");
    }

    @FXML
    private void kerkoAdrese() {
        System.out.println("Klikohet: Kërko Adresë");
        // TODO: Ngarko KërkoAdresë.fxml
    }

    @FXML
    private void shfaqHarte() {
        System.out.println("Klikohet: Shfaq në Hartë");
        // TODO: Ngarko AdresaMapView.fxml
    }

    @FXML
    private void dil() {
        System.out.println("Klikohet: Dil");
        // TODO: Kthehu në faqen e login
    }
}

