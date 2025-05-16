package co.edu.uniquindio.aplicacion.detalleproducto;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

/**
 * crea un detalle de producto
 */
public class CrearDetalleProductoUseCase {
    private DetalleProductoRepository repositorio;

    public CrearDetalleProductoUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * crea un detalle de producto
     * @param detalleProducto informacion del detalle de producto
     * @return detalle de producto creado
     */
    public DetalleProducto execute(DetalleProducto detalleProducto) {
        return repositorio.guardar(detalleProducto);
    }
}
