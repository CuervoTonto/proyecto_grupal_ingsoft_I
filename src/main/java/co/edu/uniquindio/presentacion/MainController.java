package co.edu.uniquindio.presentacion;

import co.edu.uniquindio.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    void IrACarrosCompras(ActionEvent event) {

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
