package co.edu.uniquindio.utilities;

import co.edu.uniquindio.aplicacion.exceptions.ValidacionException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtility {
    private AlertUtility() {}

    public static Alert error(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert;
    }

    public static void validacion(ValidacionException e) {
        error(
            "Validacion fallida",
            String.join(
                "\n",
                e.getErrors().stream().map(er -> "* " + er).toList()
            )
        ).showAndWait();
    }
}
