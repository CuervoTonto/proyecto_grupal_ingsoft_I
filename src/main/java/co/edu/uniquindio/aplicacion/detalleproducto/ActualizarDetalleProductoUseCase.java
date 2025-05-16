package co.edu.uniquindio.aplicacion.detalleproducto;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

/**
 * actualiza la informacion de un detalle de producto
 */
public class ActualizarDetalleProductoUseCase {
    private DetalleProductoRepository repositorio;

    public ActualizarDetalleProductoUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * actualiza el detalle de un producto
     * @param detalleProducto nueva informacion del detalle (debe incluir id del detalle a actualizar)
     * @return detalle actualizado
     */
    public DetalleProducto execute(DetalleProducto detalleProducto) {
        return repositorio.guardar(detalleProducto);
    }
}
