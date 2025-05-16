package co.edu.uniquindio.aplicacion.carro;

import java.util.Optional;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

/**
 * busca un carro de compras dado su id
 */
public class HallarCarroComprasUseCase {
    private CarroComprasRepository repositorio;

    public HallarCarroComprasUseCase(CarroComprasRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * busca un carrito de compras
     * @param id id del carro de compras
     * @return el carro de compras coincidente con el id
     */
    public Optional<CarroCompras> execute(Integer id) {
        return repositorio.hallar(id);
    }
}
