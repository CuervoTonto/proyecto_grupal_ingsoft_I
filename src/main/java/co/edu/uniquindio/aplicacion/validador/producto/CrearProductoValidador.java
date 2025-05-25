package co.edu.uniquindio.aplicacion.validador.producto;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.aplicacion.exceptions.ValidacionException;
import co.edu.uniquindio.dominio.producto.CrearProductoData;
import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

public class CrearProductoValidador {
    private ProductoRepository repositorio;

    public CrearProductoValidador(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void validate(CrearProductoData datos) throws ValidacionException {
        List<String> errors = new LinkedList<>();

        this.validarCodigo(datos, errors);

        if (! errors.isEmpty()) {
            throw new ValidacionException(errors);
        }
    }

    private void validarCodigo(CrearProductoData datos, List<String> errors) {
        Optional<Producto> producto = repositorio.hallarPorCodigo(datos.codigo());

        if (producto.isPresent()) {
            errors.add("Codigo de producto en uso");
        }
    }
}
