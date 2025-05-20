package co.edu.uniquindio.presentacion;

import java.text.NumberFormat;
import java.util.Locale;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.utilities.AlertUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EditarCarroComprasController {

    @FXML
    private TextField cantidadField;

    @FXML
    private TextField impuestoField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField precioField;

    @FXML
    private TextField subtotalField;

    @FXML
    private TextField totalField;

    @FXML
    private TextField stockField;

    private DetalleProducto detalleProducto;

    public EditarCarroComprasController(
        DetalleProducto detalleProducto
    ) {
        this.detalleProducto = detalleProducto;
    }

    @FXML
    void initialize() {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        nombreField.setText(detalleProducto.getProducto().getNombre());
        precioField.setText(formato.format(detalleProducto.getProducto().getPrecio()));
        stockField.setText(detalleProducto.getProducto().getStock().toString());
        cantidadField.setText(detalleProducto.getCantidad().toString());
        impuestoField.setText(detalleProducto.getImpuesto().toString());
    }

    @FXML
    void actualizarDetalleProducto(ActionEvent event) {
        if (! cantidadField.getText().matches("[0-9]+")) {
            AlertUtility.error("Error", "Debes dar una cantidad de productos valida").showAndWait();
            return;
        }

        if (! impuestoField.getText().matches("[0-9]+(\\.[0-9]+)?")) {
            AlertUtility.error("Error", "El impuesto debe ser un decimal valido").showAndWait();
            return;
        }

        Integer cantidad = Integer.parseInt(cantidadField.getText().trim());

        if (cantidad > detalleProducto.getProducto().getStock()) {
            AlertUtility.error("Error", "La cantidad establecida supera el stock de productos").showAndWait();
            return;
        }

        detalleProducto.setCantidad(cantidad);
        detalleProducto.setImpuesto(Float.parseFloat(impuestoField.getText().trim()));
        
        cerrarVentana();
    }

    @FXML
    void calcularPrecios(KeyEvent event) {
        subtotalField.setText("");
        totalField.setText("");

        if (! cantidadField.getText().matches("[0-9]+")) {
            return;
        }

        if (! impuestoField.getText().matches("[0-9]+(\\.[0-9]+)?")) {
            return;
        }

        Float impuesto = Float.parseFloat(impuestoField.getText()) / 100;
        Integer cantidad = Integer.parseInt(cantidadField.getText());
        Float subtotal = (detalleProducto.getProducto().getPrecio() / (1 + impuesto) * cantidad);
        Float total = detalleProducto.getProducto().getPrecio() * cantidad;

        subtotalField.setText(subtotal.toString());
        totalField.setText(((Integer) total.intValue()).toString());
    }

    @FXML
    void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }
}