package co.edu.uniquindio.presentacion;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarroComprasController {

    @FXML
    private TextField busquedaTF;

    @FXML
    private TableColumn<CarroCompras, Integer> idCol;

    @FXML
    private TableColumn<CarroCompras, String> codigoCol;

    @FXML
    private TableColumn<CarroCompras, Integer> ciudadanoCol;

    @FXML
    private TableColumn<CarroCompras, LocalDate> fechaCol;

    @FXML
    private TableColumn<CarroCompras, LocalTime> horaCol;

    @FXML
    private TableColumn<CarroCompras, String> observacionesCol;

    @FXML
    private TableColumn<CarroCompras, String> subtotalCol;

    @FXML
    private TableColumn<CarroCompras, String> totalCol;

    @FXML
    private TableView<CarroCompras> tablaCarrosCompras;

    private CarroComprasService service;

    public CarroComprasController(
        CarroComprasService service
    ) {
        this.service = service;
    }

    @FXML
    void initialize() {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        ciudadanoCol.setCellValueFactory(cc -> new ReadOnlyIntegerWrapper(cc.getValue().getDueño().getId()).asObject());
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));
        subtotalCol.setCellValueFactory(cc -> new ReadOnlyStringWrapper(formato.format(cc.getValue().calcularSubtotal())));
        totalCol.setCellValueFactory(cc -> new ReadOnlyStringWrapper(formato.format(cc.getValue().calcularTotal())));
        observacionesCol.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        actualizarTabla();
    }

    @FXML
    void IrACrearCarroCompra(ActionEvent event) throws IOException {
        App.setRoot("carro_compra_crear");
    }

    @FXML
    void buscarCarroCompra(ActionEvent event) {
        if (busquedaTF.getText().isBlank()) {
            actualizarTabla();
        } else {
            List<CarroCompras> resultado = service.hallarPorCiudadano(
                Integer.parseInt(busquedaTF.getText().trim())
            );

            if (resultado == null) {
                tablaCarrosCompras.setItems(FXCollections.emptyObservableList());
            } else {
                tablaCarrosCompras.setItems(FXCollections.observableList(resultado));
            }
        }
    }

    @FXML
    void regresar(ActionEvent event) throws Exception {
        App.setRoot("main");
    }

    private void actualizarTabla() {
        tablaCarrosCompras.setItems(
            FXCollections.observableArrayList(service.hallarTodos())
        );
    }
}