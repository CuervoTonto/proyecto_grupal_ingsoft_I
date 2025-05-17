package co.edu.uniquindio.aplicacion.carro;

import java.util.List;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

/**
 * obtiene los carros de compras basados en el ciudadano
 */
public class HallarCarroComprasPorCiudadanoUseCase {
    private CarroComprasRepository repositorio;

    public HallarCarroComprasPorCiudadanoUseCase(CarroComprasRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * obtiene los carros de compra que pertenecen a un ciudadano
     * @param ciudadanoId id del ciudadano
     * @return carros de compra coincidentes o {@code null} en caso contrario
     */
    public List<CarroCompras> execute(Integer ciudadanoId) {
        return repositorio.hallarCarrosComprasPorCliente(ciudadanoId);
    }
}
