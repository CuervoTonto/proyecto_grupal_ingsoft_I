package co.edu.uniquindio.presentacion;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.detalleproducto.DetalleProductoService;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MostrarInformacionCarroComprasController {

    @FXML
    private TextField carroCodigoField;

    @FXML
    private DatePicker carroFechaField;

    @FXML
    private TextField carroHoraField;

    @FXML
    private TextField ciudadanoApellidoField;

    @FXML
    private TextField ciudadanoCedulaField;

    @FXML
    private TextField ciudadanoEmailField;

    @FXML
    private TextField ciudadanoIdField;

    @FXML
    private TextField ciudadanoNombreFIeld;

    @FXML
    private TextField ciudadanoTelefonoField;

    @FXML
    private TextField detalleProductoBusquedaField;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoCantidadCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoCodigoCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> detalleProductoIdCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoImpuestoCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoNombreCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoPrecioCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoSubtotalCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoTotalCol;

    @FXML
    private Text subtotalText;

    @FXML
    private TableView<DetalleProducto> tablaDetalleProducto;

    @FXML
    private Text totalText;

    private CarroCompras carroCompras;

    private List<DetalleProducto> productos;

    public MostrarInformacionCarroComprasController(
        CarroCompras carroCompras,
        DetalleProductoService dpService
    ) {
        this.carroCompras = carroCompras;
        this.productos = dpService.hallarPorCarroCompra(carroCompras.getId());
    }

    @FXML
    void initialize() {
        initDatosBasicos();
        initCiudadano();
        initTablaProductos();
    }

    @FXML
    void buscarDetalleProducto(ActionEvent event) {
        String nombre = detalleProductoBusquedaField.getText();

        tablaDetalleProducto.setItems(FXCollections.observableArrayList(
            productos.stream()
                .filter(p -> p.getProducto().getNombre().matches("(?i).*" + nombre + ".*"))
                .toList()
        ));

        tablaDetalleProducto.refresh();
    }

    @FXML
    void regresar() throws IOException {
        App.setRoot("carro_compra");
    }

    private void initDatosBasicos() {
        carroCodigoField.setText(carroCompras.getCodigo());
        carroFechaField.setValue(carroCompras.getFecha());
        carroHoraField.setText(carroCompras.getHora().format(
            DateTimeFormatter.ofPattern("H:m:s")
        ));
    }

    private void initCiudadano() {
        Ciudadano ciudadano = carroCompras.getDueño();

        ciudadanoIdField.setText(ciudadano.getId().toString());
        ciudadanoNombreFIeld.setText(ciudadano.getNombre());
        ciudadanoCedulaField.setText(ciudadano.getCedula());
        ciudadanoEmailField.setText(ciudadano.getEmail());
        ciudadanoApellidoField.setText(ciudadano.getApellido());
        ciudadanoTelefonoField.setText(ciudadano.getTelefono());
    }

    private void initTablaProductos() {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(
            new Locale("es", "CO")
        );

        detalleProductoIdCol.setCellValueFactory(dp ->
            new ReadOnlyIntegerWrapper(dp.getValue().getProducto().getId()).asObject()
        );
        detalleProductoCodigoCol.setCellValueFactory(dp ->
            new ReadOnlyStringWrapper(dp.getValue().getProducto().getCodigo())
        );
        detalleProductoNombreCol.setCellValueFactory(dp ->
            new ReadOnlyStringWrapper(dp.getValue().getProducto().getNombre())
        );
        detalleProductoCantidadCol.setCellValueFactory(
            new PropertyValueFactory<>("cantidad")
        );
        detalleProductoImpuestoCol.setCellValueFactory(dp -> 
            new ReadOnlyStringWrapper(NumberFormat.getPercentInstance().format(dp.getValue().getImpuesto() / 100))
        );
        detalleProductoPrecioCol.setCellValueFactory(dp ->
            new ReadOnlyStringWrapper(formatoMoneda.format(dp.getValue().getProducto().getPrecio()))
        );
        detalleProductoSubtotalCol.setCellValueFactory(dp ->
            new ReadOnlyStringWrapper(formatoMoneda.format(dp.getValue().calcularSubtotal()))
        );
        detalleProductoTotalCol.setCellValueFactory(dp ->
            new ReadOnlyStringWrapper(formatoMoneda.format(dp.getValue().calcularTotal()))
        );

        tablaDetalleProducto.setItems(FXCollections.observableArrayList(
            productos
        ));

        subtotalText.setText(formatoMoneda.format(carroCompras.calcularSubtotal()));
        totalText.setText(formatoMoneda.format(carroCompras.calcularTotal()));
    }
}