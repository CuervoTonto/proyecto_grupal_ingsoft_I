package co.edu.uniquindio.presentacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.carro.CarroComprasService;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
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
    private TableColumn<CarroCompras, Integer> ciudadanoCol;

    @FXML
    private TableColumn<CarroCompras, LocalDate> fechaCol;

    @FXML
    private TableColumn<CarroCompras, LocalTime> horaCol;

    @FXML
    private TableColumn<CarroCompras, String> observacionesCol;

    @FXML
    private TableColumn<CarroCompras, Float> subtotalCol;

    @FXML
    private TableColumn<CarroCompras, Integer> totalCol;

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
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ciudadanoCol.setCellValueFactory(cc -> new ReadOnlyIntegerWrapper(cc.getValue().getDueño().getId()).asObject());
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));
        subtotalCol.setCellValueFactory(cc -> new ReadOnlyFloatWrapper(cc.getValue().calcularSubtotal()).asObject());
        totalCol.setCellValueFactory(cc -> new ReadOnlyIntegerWrapper(cc.getValue().calcularTotal()).asObject());
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
                actualizarTabla();
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