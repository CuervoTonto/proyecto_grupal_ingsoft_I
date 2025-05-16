package co.edu.uniquindio.aplicacion.carro;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

/**
 * crea un carro de compras
 */
public class CrearCarroComprasUseCase {
    private CarroComprasRepository repositorio;

    public CrearCarroComprasUseCase(CarroComprasRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * crea un carro de compras
     * @param carroCompras informacion del carro de compras
     * @return carro de compras creado
     */
    public CarroCompras execute(CarroCompras carroCompras) {
        return this.repositorio.guardar(carroCompras);
    }
}
