package co.edu.uniquindio.presentacion;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.aplicacion.ciudadano.CiudadanoService;
import co.edu.uniquindio.aplicacion.detalleproducto.DetalleProductoService;
import co.edu.uniquindio.dominio.carro.CarroCompraEstado;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.utilities.AlertUtility;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CrearCarroComprasController {

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
    private TableColumn<DetalleProducto, Integer> detalleProductoCantidadCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoCodigoCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> detalleProductoIdCol;

    @FXML
    private TableColumn<DetalleProducto, Float> detalleProductoImpuestoCol;

    @FXML
    private TableColumn<DetalleProducto, String> detalleProductoNombreCol;

    @FXML
    private TableColumn<DetalleProducto, Float> detalleProductoPrecioCol;

    @FXML
    private TableColumn<DetalleProducto, Float> detalleProductoSubtotalCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> detalleProductoTotalCol;

    @FXML
    private TableView<DetalleProducto> tablaDetalleProducto;

    @FXML
    private TextField totalCompraField;

    private CarroComprasService service;

    private CiudadanoService ciudadanoService;

    private DetalleProductoService dpService;

    private List<DetalleProducto> productos;

    public CrearCarroComprasController(
        CarroComprasService service,
        CiudadanoService ciudadanoService,
        DetalleProductoService dpService
    ) {
        this.service = service;
        this.ciudadanoService = ciudadanoService;
        this.dpService = dpService;
        this.productos = new LinkedList<>();
    }

    @FXML
    void initialize() {
        carroFechaField.setValue(LocalDate.now());
        carroHoraField.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        detalleProductoCantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        detalleProductoCodigoCol.setCellValueFactory(dp -> new ReadOnlyStringWrapper(dp.getValue().getProducto().getCodigo()));
        detalleProductoIdCol.setCellValueFactory(dp -> new ReadOnlyIntegerWrapper(dp.getValue().getProducto().getId()).asObject());
        detalleProductoImpuestoCol.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        detalleProductoNombreCol.setCellValueFactory(dp -> new ReadOnlyStringWrapper(dp.getValue().getProducto().getNombre()));
        detalleProductoPrecioCol.setCellValueFactory(dp -> new ReadOnlyFloatWrapper(dp.getValue().getProducto().getPrecio()).asObject());
        detalleProductoSubtotalCol.setCellValueFactory(dp -> new ReadOnlyFloatWrapper(dp.getValue().calcularSubtotal()).asObject());
        detalleProductoTotalCol.setCellValueFactory(dp -> new ReadOnlyIntegerWrapper(dp.getValue().calcularTotal()).asObject());

        actualizarTablaProductos();
    }

    @FXML
    void buscarDetalleProducto(ActionEvent event) {
        String busqueda = detalleProductoBusquedaField.getText();

        tablaDetalleProducto.setItems(FXCollections.observableList(
            productos.stream()
                .filter(dp -> dp.getProducto().getNombre().matches(".*" + busqueda + ".*"))
                .toList()
        ));
    }

    @FXML
    void cancelar(ActionEvent event) throws Exception {
        App.setRoot("carro_compra");
    }

    @FXML
    void consultarCiudadano(ActionEvent event) {
        if (
            ciudadanoIdField.getText().isBlank()
            || ! ciudadanoIdField.getText().matches("[0-9]+")
        ) {
            AlertUtility.error(
                "Validacion",
                "El id del ciudanano no puede estar vacio y debe ser un numero"
            ).showAndWait();
        } else {
            Optional<Ciudadano> busqueda = ciudadanoService.hallarPorId(
                Integer.parseInt(ciudadanoIdField.getText().trim())
            );

            if (busqueda.isEmpty()) {
                AlertUtility.error("Validacion", "El ciudadano no existe").showAndWait();
                return;
            }

            Ciudadano ciudadano = busqueda.get();

            ciudadanoNombreFIeld.setText(ciudadano.getNombre());
            ciudadanoCedulaField.setText(ciudadano.getCedula());
            ciudadanoEmailField.setText(ciudadano.getEmail());
            ciudadanoApellidoField.setText(ciudadano.getApellido());
            ciudadanoTelefonoField.setText(ciudadano.getTelefono());
        }
    }

    @FXML
    void crearCarroCompra(ActionEvent event) throws Exception {
        // verificar ciudadano
        if (
            ciudadanoIdField.getText().isBlank()
            || ! ciudadanoIdField.getText().matches("[0-9]+")
        ) {
            AlertUtility.error(
                "Validacion",
                "El id del ciudanano no puede estar vacio y debe ser un numero"
            ).showAndWait();
            return;
        }
        
        Optional<Ciudadano> busqueda = ciudadanoService.hallarPorId(
            Integer.parseInt(ciudadanoIdField.getText().trim())
        );

        if (busqueda.isEmpty()) {
            AlertUtility.error("Validacion", "El ciudadano no existe").showAndWait();
            return;
        }

        // crear carro compra
        CarroCompras carro = new CarroCompras();
        carro.setCodigo(carroCodigoField.getText().trim());
        carro.setFecha(carroFechaField.getValue());
        carro.setHora(LocalTime.parse(carroHoraField.getText().trim()));
        carro.setEstado(CarroCompraEstado.ACTIVO);
        carro.setDueño(busqueda.get());

        service.craer(carro);

        // crear detalle productos
        for (DetalleProducto detalleProducto : productos) {
            detalleProducto.setCarroCompras(carro);
            dpService.crear(detalleProducto);
        }

        App.setRoot("carro_compra");
    }

    @FXML
    void crearDetalleProducto(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass()
                .getClassLoader()
                .getResource("views/detalle_producto_crear.fxml")
        );

        CrearDetalleProductoController controller = App.getControllerFromFactory(CrearDetalleProductoController.class);
        loader.setController(controller);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Agregar producto");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        DetalleProducto detalleProducto = controller.getDetalleProducto();

        if (detalleProducto != null) {
            productos.add(detalleProducto);
        }

        actualizarTablaProductos();
    }

    @FXML
    void eliminarDetalleProducto(ActionEvent event) {
        DetalleProducto seleccion = tablaDetalleProducto.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            AlertUtility.error("Error", "Seleccione un producto de la lista");
            return;
        }

        productos.remove(seleccion);
        actualizarTablaProductos();
    }

    @FXML
    void actualizarDetalleProducto(ActionEvent event) throws Exception {
        DetalleProducto seleccion = tablaDetalleProducto.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            AlertUtility.error("Error", "Seleccione un producto de la lista").showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(
            getClass()
                .getClassLoader()
                .getResource("views/detalle_producto_editar.fxml")
        );

        EditarCarroComprasController controller = new EditarCarroComprasController(seleccion);
        loader.setController(controller);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Editar producto del carrito");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        actualizarTablaProductos();
    }

    private void actualizarTablaProductos() {
        tablaDetalleProducto.setItems(FXCollections.observableList(productos));
        tablaDetalleProducto.refresh();
        calcularTotalCompra();
    }

    private void calcularTotalCompra() {
        Integer total = productos.stream().mapToInt(dp -> dp.calcularTotal()).sum();

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        totalCompraField.setText(formato.format(total));
    }
}