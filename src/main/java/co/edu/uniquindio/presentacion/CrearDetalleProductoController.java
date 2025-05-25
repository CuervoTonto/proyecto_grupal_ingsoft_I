package co.edu.uniquindio.presentacion;

import co.edu.uniquindio.aplicacion.producto.ProductoService;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.utilities.AlertUtility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.*;

public class CrearDetalleProductoController {

    @FXML
    private TextField cantidadField;

    @FXML
    private TextField impuestoField;

    @FXML
    private ComboBox<Producto> productoCB;

    @FXML
    private TextField subtotalField;

    @FXML
    private TextField totalField;

    private DetalleProducto detalleProducto;

    private ProductoService productoService;

    public CrearDetalleProductoController(
        ProductoService productoService
    ) {
        this.productoService = productoService;
    }

    @FXML
    void initialize() {
        productoCB.setItems(FXCollections.observableList(
            productoService.hallarTodos()
        ));

        impuestoField.setText("0");
        cantidadField.setText("1");

        productoCB.setConverter(new StringConverter<Producto>() {
            @Override
            public Producto fromString(String string) {
                return null;
            }

            @Override
            public String toString(Producto object) {
                return object.getNombre() + " (" + object.getStock() + ") " + "[ $" + object.getPrecio() + "]";
            }
        });
    }

    @FXML
    void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    void crearDetalleProducto(ActionEvent event) {
        Producto seleccion = productoCB.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            AlertUtility.error("Error", "Desbes seleccionar un producto").showAndWait();
            return;
        }

        if (! cantidadField.getText().matches("[0-9]+")) {
            AlertUtility.error("Error", "Debes dar una cantidad de productos valida").showAndWait();
            return;
        }

        if (! impuestoField.getText().matches("[0-9]+(\\.[0-9]+)?")) {
            AlertUtility.error("Error", "El impuesto debe ser un decimal valido").showAndWait();
            return;
        }

        Integer cantidad = Integer.parseInt(cantidadField.getText().trim());

        if (cantidad > seleccion.getStock()) {
            AlertUtility.error("Error", "La cantidad establecida supera el stock de productos").showAndWait();
            return;
        }

        detalleProducto = new DetalleProducto();
        detalleProducto.setCantidad(cantidad);
        detalleProducto.setImpuesto(Float.parseFloat(impuestoField.getText().trim()));
        detalleProducto.setProducto(seleccion);

        cerrarVentana();
    }

    // @FXML
    // void calcularPrecios(KeyEvent event) {
    //     calcularPrecios();
    // }

    // @FXML
    // void calcularPrecios(ActionEvent event) {
    //     calcularPrecios();
    // }
    @FXML
    void ver(){

        Producto seleccion = productoCB.getSelectionModel().getSelectedItem();
        mostrarInformacionProducto(seleccion);

    }
    @FXML
    void calcularPrecios() {
        subtotalField.setText("");
        totalField.setText("");

        Producto seleccion = productoCB.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            return;
        }

        if (! cantidadField.getText().matches("[0-9]+")) {
            return;
        }

        if (! impuestoField.getText().matches("[0-9]+(\\.[0-9]+)?")) {
            return;
        }

        Float impuesto = Float.parseFloat(impuestoField.getText()) / 100;
        Integer cantidad = Integer.parseInt(cantidadField.getText());
        Float subtotal = (seleccion.getPrecio() / (1 + impuesto) * cantidad);
        Float total = seleccion.getPrecio() * cantidad;

        subtotalField.setText(subtotal.toString());
        totalField.setText(((Integer) total.intValue()).toString());
    }

    private void cerrarVentana() {
        Stage stage = (Stage) productoCB.getScene().getWindow();
        stage.close();
    }
    public static void mostrarInformacionProducto(Producto producto) {
        String mensaje = """
            ID: %d
            Código: %s
            Nombre: %s
            Categoría Principal: %s
            Categoría Secundaria: %s
            Descripción: %s
            Precio: %.2f
            Stock: %d
            Características: %s
            Estado: %s
            """.formatted(
                producto.getId(),
                producto.getCodigo(),
                producto.getNombre(),
                producto.getCategoriaPrincipal(),
                producto.getCategoriaSecundaria(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCaracteristicas(),
                producto.getEstado()
        );

        JOptionPane.showMessageDialog(null, mensaje, "Información del Producto", JOptionPane.INFORMATION_MESSAGE);
    }


    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }
}