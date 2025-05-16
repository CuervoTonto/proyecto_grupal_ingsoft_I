package co.edu.uniquindio.aplicacion.detalleproducto;

import java.util.Optional;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

/**
 * busca el datalle de un producto en base a su id
 */
public class HallarDetalleProductoUseCase {
    private DetalleProductoRepository repositorio;

    public HallarDetalleProductoUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * busca el detalle de un producto
     * @param id id del detalle del producto
     * @return el detalle de producto coincidente con el id
     */
    public Optional<DetalleProducto> execute(Integer id) {
        return repositorio.hallar(id);
    }
}
