package co.edu.uniquindio.presentacion;

import java.util.Optional;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.aplicacion.detalleproducto.DetalleProductoService;
import co.edu.uniquindio.aplicacion.producto.ProductoService;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.producto.Producto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetalleProductoController {
    
    private DetalleProductoService service;

    private ProductoService productoService;

    private CarroComprasService carroComprasService;

    @FXML
    private TableColumn<DetalleProducto, Integer> idCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> cantidadCol;

    @FXML
    private TableColumn<DetalleProducto, String> conceptoCol;

    @FXML
    private TableColumn<DetalleProducto, Float> impuestoCol;

    @FXML
    private TableColumn<DetalleProducto, Float> subtotalCol;

    @FXML
    private TableColumn<DetalleProducto, Integer> montoCol;

    @FXML
    private TableView<DetalleProducto> tablaDetalleProducto;

    @FXML
    private TextField cantidadTF;

    @FXML
    private TextField carroCompraTF;

    @FXML
    private TextField conceptoTF;

    @FXML
    private DatePicker fechaEntregaTF;

    @FXML
    private DatePicker fechaPrestamoTF;

    @FXML
    private TextField idTF;

    @FXML
    private TextField impuestoTF;

    @FXML
    private TextField montoTF;

    @FXML
    private TextField productoTF;

    @FXML
    private TextField subtotalTF;

    @FXML
    private TextField buscarCarroComprasTF;

    public DetalleProductoController(
        DetalleProductoService service,
        ProductoService productoService,
        CarroComprasService carroComprasService
    ) {
        this.service = service;
        this.productoService = productoService;
        this.carroComprasService = carroComprasService;
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        conceptoCol.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        impuestoCol.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        montoCol.setCellValueFactory(new PropertyValueFactory<>("monto"));

        actualizarTabla();
    }

    @FXML
    void actualizarDetalleProducto(ActionEvent event) {
        DetalleProducto detalleProducto = construirDetalleProducto();

        if (detalleProducto.getId() == null) return;

        service.actualizar(detalleProducto);
        actualizarTabla();
    }

    @FXML
    void consultarDetalleProducto(ActionEvent event) {
        limpiarCampos(event);
        DetalleProducto seleccion = tablaDetalleProducto.getSelectionModel().getSelectedItem();

        if (seleccion == null) return;

        idTF.setText(seleccion.getId().toString());
        conceptoTF.setText(seleccion.getConcepto());
        cantidadTF.setText(seleccion.getCantidad().toString());
        impuestoTF.setText(seleccion.getImpuesto().toString());
        subtotalTF.setText(seleccion.getSubtotal().toString());
        montoTF.setText(seleccion.getMonto().toString());
        fechaEntregaTF.setValue(seleccion.getFechaEntrega());
        fechaPrestamoTF.setValue(seleccion.getFechaPrestamo());
        carroCompraTF.setText(seleccion.getCarroCompras().getId().toString());
        productoTF.setText(seleccion.getProducto().getId().toString());
    }

    @FXML
    void crearDetalleProducto(ActionEvent event) {
        DetalleProducto detalleProducto = construirDetalleProducto();
        detalleProducto.setId(null);
        service.crear(detalleProducto);
        actualizarTabla();
    }

    @FXML
    void eliminarDetalleProducto(ActionEvent event) {
        DetalleProducto seleccion = tablaDetalleProducto.getSelectionModel().getSelectedItem();

        if (seleccion == null) return;

        service.eliminar(seleccion.getId());
        actualizarTabla();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        idTF.clear();
        conceptoTF.clear();
        cantidadTF.clear();
        impuestoTF.clear();
        subtotalTF.clear();
        montoTF.clear();
        fechaEntregaTF.setValue(null);
        fechaPrestamoTF.setValue(null);
        carroCompraTF.clear();
        productoTF.clear();
    }

    @FXML
    void regresar(ActionEvent event) throws Exception {
        App.setRoot("main");
    }

    @FXML
    void buscarDetalleProducto() {
        if (buscarCarroComprasTF.getText().isEmpty()) {
            actualizarTabla();
        } else {
            Optional<CarroCompras> carrito = carroComprasService.hallarPorId(
                Integer.parseInt(buscarCarroComprasTF.getText())
            );

            if (carrito.isEmpty()) {
                tablaDetalleProducto.setItems(FXCollections.emptyObservableList());
            } else {
                tablaDetalleProducto.setItems(FXCollections.observableList(
                    service.hallarPorCarroCompra(carrito.get().getId())
                ));
            }
        }
    }

    @FXML
    void eliminarDetalleProductos() {
        if (buscarCarroComprasTF.getText().isEmpty()) return;

        service.elimnarPorCarroCompras(
            Integer.parseInt(buscarCarroComprasTF.getText())
        );

        actualizarTabla();
    }

    private void actualizarTabla() {
        buscarCarroComprasTF.clear();

        tablaDetalleProducto.setItems(
            FXCollections.observableArrayList(service.hallarTodos())
        );
    }

    private DetalleProducto construirDetalleProducto() {
        DetalleProducto detalleProducto = new DetalleProducto();
        detalleProducto.setConcepto(conceptoTF.getText().trim());
        detalleProducto.setCantidad(Integer.parseInt(cantidadTF.getText().trim()));
        detalleProducto.setImpuesto(Float.parseFloat(impuestoTF.getText().trim()));
        detalleProducto.setSubtotal(Float.parseFloat(subtotalTF.getText().trim()));
        detalleProducto.setMonto(Integer.parseInt(montoTF.getText().trim()));
        detalleProducto.setFechaEntrega(fechaEntregaTF.getValue());
        detalleProducto.setFechaPrestamo(fechaPrestamoTF.getValue());

        if (! idTF.getText().trim().isEmpty()) {
            detalleProducto.setId(Integer.parseInt(idTF.getText().trim()));
        }

        Optional<Producto> producto = productoService.hallarPorId(
            Integer.parseInt(productoTF.getText().trim())
        );

        Optional<CarroCompras> carrito = carroComprasService.hallarPorId(
            Integer.parseInt(carroCompraTF.getText().trim())
        );

        detalleProducto.setProducto(producto.get());
        detalleProducto.setCarroCompras(carrito.get());

        return detalleProducto;
    }
}