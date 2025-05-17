package co.edu.uniquindio.aplicacion.detalleproducto;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

/**
 * elimina los detalles de productos de un carro de compras
 */
public class EliminarDetalleProductoPorCarroComprasUseCase {
    private DetalleProductoRepository repositorio;

    public EliminarDetalleProductoPorCarroComprasUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Boolean execute(Integer carroComprasId) {
        repositorio.obtenerPorCarroCompras(carroComprasId).forEach((dp) -> {
            repositorio.eliminar(dp.getId());
        });

        return true;
    }
}
