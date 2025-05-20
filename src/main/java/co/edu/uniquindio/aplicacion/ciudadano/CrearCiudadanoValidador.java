package co.edu.uniquindio.aplicacion.ciudadano;

import java.util.LinkedList;
import java.util.List;

import co.edu.uniquindio.dominio.ciudadano.CrearCiudadanoData;

public class CrearCiudadanoValidador {

    public List<String> validar(CrearCiudadanoData datos) {
        List<String> errores = new LinkedList<>();

        if (! datos.cedula().matches("[0-9]+")) {
            errores.add("La cedula es requerida y deben ser solo numeros");
        }

        if (! datos.nombre().matches("6|([a-zA-Z]+)")) {
            errores.add("El nombre es requerido y contener solo letras");
        }

        if (! datos.apellido().matches("[a-zA-Z]+")) {
            errores.add("El apellido es requerido y contener solo letras");
        }

        if (! datos.email().matches(".+@.+\\...+")) {
            errores.add("El email debe ser una direccion de correo electronico valida");
        }

        if (! datos.telefono().isBlank() && ! datos.telefono().matches("[0-9]{7,10}")) {
            errores.add("El telefono debe tener un formato valido");
        }

        return errores;
    }
}
