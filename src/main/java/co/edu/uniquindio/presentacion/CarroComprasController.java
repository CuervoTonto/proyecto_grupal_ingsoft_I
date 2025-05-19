package co.edu.uniquindio.presentacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.aplicacion.ciudadano.CiudadanoService;
import co.edu.uniquindio.dominio.carro.CarroCompraEstado;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.factory.ServiceFactory;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarroComprasController {
    private CarroComprasService service;
    private CiudadanoService ciudadanoService;

    @FXML
    private TableColumn<CarroCompras, Integer> ciudadanoCol;

    @FXML
    private TextField ciudadanoTF;

    @FXML
    private TableColumn<CarroCompras, String> codigoCol;

    @FXML
    private TextField codigoTF;

    @FXML
    private TableColumn<CarroCompras, String> detalleCol;

    @FXML
    private TextArea detalleTF;

    @FXML
    private TableColumn<CarroCompras, LocalDate> fechaCol;

    @FXML
    private DatePicker fechaDF;

    @FXML
    private TableColumn<CarroCompras, LocalTime> horaCol;

    @FXML
    private TextField horaTF;

    @FXML
    private TableColumn<CarroCompras, Integer> idCol;

    @FXML
    private TableColumn<CarroCompras, Float> impuestoCol;

    @FXML
    private TextField impuestoTF;

    @FXML
    private TableColumn<CarroCompras, String> observacionesCol;

    @FXML
    private TextArea observacionesTF;

    @FXML
    private TableColumn<CarroCompras, Float> subtotalCol;

    @FXML
    private TextField subtotalTF;

    @FXML
    private TableColumn<CarroCompras, Float> totalCol;

    @FXML
    private TextField totalTF;

    @FXML
    private TableColumn<CarroCompras, CarroCompraEstado> estadoCol;

    @FXML
    private TableView<CarroCompras> tablaCarrosCompras;

    public CarroComprasController(CarroComprasService service, CiudadanoService ciudadanoService) {
        this.service = service;
        this.ciudadanoService = ciudadanoService;
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));
        impuestoCol.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        ciudadanoCol.setCellValueFactory(cc -> new SimpleIntegerProperty(cc.getValue().getId()).asObject());
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));

        actualizarTabla();
    }

    @FXML
    void crearCarrito(ActionEvent event) {
        CarroCompras carrito = new CarroCompras();
        carrito.setCodigo(codigoTF.getText().trim());
        carrito.setFecha(fechaDF.getValue());
        carrito.setHora(LocalTime.parse(horaTF.getText().trim()));
        carrito.setImpuesto(Float.parseFloat(impuestoTF.getText().trim()));
        carrito.setSubtotal(Integer.parseInt(subtotalTF.getText().trim()));
        carrito.setTotal(Integer.parseInt(totalTF.getText().trim()));
        carrito.setDueño(ciudadanoService.hallarPorId(
            Integer.parseInt(ciudadanoTF.getText().trim())
        ).get());
        carrito.setEstado(CarroCompraEstado.ACTIVO);

        service.craer(carrito);
        actualizarTabla();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        codigoTF.clear();
        fechaDF.setValue(null);
        horaTF.clear();
        impuestoTF.clear();
        subtotalTF.clear();
        totalTF.clear();
        ciudadanoTF.clear();
    }

    @FXML
    void regresar(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    @FXML
    void validarCarroCompras(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource("views/carro_compra_validar.fxml")
        );

        CarroCompraValidacionController controller =  new CarroCompraValidacionController(service, ServiceFactory.crearDetalleProductoService());
        controller.establecerCarroCompra(tablaCarrosCompras.getSelectionModel().getSelectedItem().getId());
        loader.setController(controller);

        App.setRoot((Parent) loader.load());
    }

    void actualizarTabla() {
        tablaCarrosCompras.setItems(
            FXCollections.observableArrayList(service.hallarTodos())
        );
    }
}