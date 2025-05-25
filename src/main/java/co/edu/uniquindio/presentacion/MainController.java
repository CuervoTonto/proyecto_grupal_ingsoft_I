package co.edu.uniquindio.presentacion;

import java.io.IOException;

import co.edu.uniquindio.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    void cambiarVistaCarroCompras(MouseEvent event) throws IOException {
        App.setRoot("carro_compra");
    }

    @FXML
    void cambiarVistaCiudadano(MouseEvent event) throws IOException {
        App.setRoot("ciudadano");
    }

    @FXML
    void cambiarVistaProductos(MouseEvent event) throws IOException {
        App.setRoot("producto");
    }
}