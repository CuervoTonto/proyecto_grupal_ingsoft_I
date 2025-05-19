package co.edu.uniquindio.presentacion;

import java.io.IOException;

import co.edu.uniquindio.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    void IrACarrosCompras(ActionEvent event) throws IOException {
        App.setRoot("carro_compra");
    }

    @FXML
    void IrACiudadanos(ActionEvent event) throws Exception {
        App.setRoot("ciudadano");
    }

    @FXML
    void IrADetallesProductos(ActionEvent event) {

    }

    @FXML
    void IrAProductos(ActionEvent event) throws Exception {
        App.setRoot("producto");
    }
}
