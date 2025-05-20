package co.edu.uniquindio.presentacion;

import java.util.List;

import co.edu.uniquindio.App;
import co.edu.uniquindio.aplicacion.ciudadano.CiudadanoService;
import co.edu.uniquindio.aplicacion.ciudadano.CrearCiudadanoValidador;
import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoEstado;
import co.edu.uniquindio.dominio.ciudadano.CrearCiudadanoData;
import co.edu.uniquindio.utilities.AlertUtility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CiudadanoController {
    private CiudadanoService service;

    @FXML
    private TextField apellidoTF;

    @FXML
    private TextField cedulaTF;

    @FXML
    private TableColumn<Ciudadano, String> columnaApellido;

    @FXML
    private TableColumn<Ciudadano, String> columnaEmail;

    @FXML
    private TableColumn<Ciudadano, CiudadanoEstado> columnaEstado;

    @FXML
    private TableColumn<Ciudadano, Integer> columnaId;

    @FXML
    private TableColumn<Ciudadano, String> columnaNombre;

    @FXML
    private TableColumn<Ciudadano, String> columnaTelefono;

    @FXML
    private TableColumn<Ciudadano, String> columnaCedula;

    @FXML
    private Button crearCiudadanoBtn;

    @FXML
    private TextField emailTF;

    @FXML
    private Button limpiarCamposBtn;

    @FXML
    private TextField nombreTF;

    @FXML
    private Button regresarBtn;

    @FXML
    private TableView<Ciudadano> tablaCiudadanos;

    @FXML
    private TextField telefonoTF;

    public CiudadanoController(CiudadanoService service) {
        this.service = service;
    }

    @FXML
    void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        actualizarTabla();
    }

    @FXML
    void crearUsuario(ActionEvent event) {
        CrearCiudadanoData datos = new CrearCiudadanoData(
            cedulaTF.getText(),
            nombreTF.getText(),
            apellidoTF.getText(),
            emailTF.getText(),
            telefonoTF.getText()
        );

        CrearCiudadanoValidador validador = new CrearCiudadanoValidador();
        List<String> errores = validador.validar(datos);

        if (! errores.isEmpty()) {
            AlertUtility.error(
                "Error(es) de validacion",
                String.join("\n", errores.stream().map(err -> "* " + err).toList())
            ).showAndWait();
            return;
        }

        // Ciudadano ciudadano = new Ciudadano();
        // ciudadano.setCedula(cedulaTF.getText().trim());
        // ciudadano.setNombre(nombreTF.getText().trim());
        // ciudadano.setApellido(apellidoTF.getText().trim());
        // ciudadano.setEmail(emailTF.getText().trim());
        // ciudadano.setTelefono(telefonoTF.getText().trim());
        // ciudadano.setEstado(CiudadanoEstado.ACTIVO);

        service.crear(datos);
        actualizarTabla();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        cedulaTF.clear();
        nombreTF.clear();
        apellidoTF.clear();
        emailTF.clear();
        telefonoTF.clear();
    }

    @FXML
    void regresar(ActionEvent event) throws Exception {
        App.setRoot("main");
    }

    private void actualizarTabla() {
        tablaCiudadanos.setItems(
            FXCollections.observableArrayList(service.hallarTodos())
        );
    }
}