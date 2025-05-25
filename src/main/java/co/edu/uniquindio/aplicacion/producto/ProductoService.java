package co.edu.uniquindio.aplicacion.producto;

import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.aplicacion.exceptions.ValidacionException;
import co.edu.uniquindio.dominio.producto.CrearProductoData;
import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

public class ProductoService {
    // private ProductoRepository repositorio;

    private CrearProductoUseCase crearProducto;
    private HallarProductoUseCase hallarProducto;
    private HallarProductosPorNombreUseCase hallarProductosPorNombre;
    private HallarTodosProductosUseCase hallarTodosProductos;

    public ProductoService(ProductoRepository repositorio) {
        // this.repositorio = repositorio;
        crearProducto = new CrearProductoUseCase(repositorio);
        hallarProducto = new HallarProductoUseCase(repositorio);
        hallarProductosPorNombre = new HallarProductosPorNombreUseCase(repositorio);
        hallarTodosProductos = new HallarTodosProductosUseCase(repositorio);
    }

    public Producto crear(CrearProductoData producto) throws ValidacionException {
        return crearProducto.execute(producto);
    }

    public Optional<Producto> hallarPorId(Integer id) {
        return hallarProducto.execute(id);
    }

    public List<Producto> hallarPorNombre(String nombre) {
        return hallarProductosPorNombre.execute(nombre);
    }

    public List<Producto> hallarTodos() {
        return hallarTodosProductos.execute();
    }
}
