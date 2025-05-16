package co.edu.uniquindio.aplicacion.producto;

import java.util.Optional;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

/**
 * busca un producto en base a su id
 */
public class HallarProductoUseCase {
    private ProductoRepository repositorio;

    public HallarProductoUseCase(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * busca un producto
     * @param id id del producto
     * @return el producto coincidente con el id
     */
    public Optional<Producto> execute(Integer id) {
        return repositorio.hallar(id);
    }
}
