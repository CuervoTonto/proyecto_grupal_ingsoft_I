package co.edu.uniquindio.aplicacion.producto;

import java.util.List;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

public class HallarTodosProductosUseCase {
    private ProductoRepository repositorio;

    public HallarTodosProductosUseCase(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Producto> execute() {
        return repositorio.hallarTodos();
    }
}
