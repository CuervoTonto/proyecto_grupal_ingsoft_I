package co.edu.uniquindio.aplicacion.detalleproducto;

import java.util.List;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

public class HallarDetalleProductoPorCarroComprasUseCase {
    private DetalleProductoRepository repositorio;

    public HallarDetalleProductoPorCarroComprasUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<DetalleProducto> execute(Integer carroComprasID) {
        return repositorio.obtenerPorCarroCompras(carroComprasID);
    }
}
