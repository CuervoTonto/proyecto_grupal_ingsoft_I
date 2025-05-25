package co.edu.uniquindio.presentacion.validador;

import java.util.LinkedList;
import java.util.List;

import co.edu.uniquindio.aplicacion.exceptions.ValidacionException;
import co.edu.uniquindio.presentacion.formulario.CrearProductoForm;
import co.edu.uniquindio.utilities.CadenaValidacion;

public class CrearProductoFormValidador {
    public void validar(CrearProductoForm form) throws ValidacionException {
        List<String> errores = new LinkedList<>();

        validarCodigo(form, errores);
        validarNombre(form, errores);
        validarPrecio(form, errores);
        validarStock(form, errores);

        if (! errores.isEmpty()) {
            throw new ValidacionException(errores);
        }
    }

    private void validarCodigo(CrearProductoForm form, List<String> errores) {
        String err = new CadenaValidacion("codigo", form.codigo())
            .requerido()
            .getError();

        if (err != null) errores.add(err);
    }

    private void validarNombre(CrearProductoForm form, List<String> errores) {
        String err = new CadenaValidacion("nombre", form.nombre())
            .requerido()
            .getError();

        if (err != null) errores.add(err);
    }

    private void validarPrecio(CrearProductoForm form, List<String> errores) {
        String err = new CadenaValidacion("precio", form.precio())
            .requerido()
            .decimal()
            .getError();

        if (err != null) errores.add(err);
    }

    private void validarStock(CrearProductoForm form, List<String> errores) {
        String err = new CadenaValidacion("stock", form.stock())
            .requerido()
            .entero()
            .getError();

        if (err != null) errores.add(err);
    }
}