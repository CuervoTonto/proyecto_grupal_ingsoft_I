package co.edu.uniquindio.aplicacion.carro;

import java.util.Optional;

import co.edu.uniquindio.dominio.carro.CarroCompraEstado;
import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

/**
 * valida el estado del carro de compras
 */
public class ValidarCarroComprasUseCase {
    private CarroComprasRepository repositorio;

    public ValidarCarroComprasUseCase(CarroComprasRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * cambia a "completado" el estado del carrito de compras
     * @param id id del carrito de compras a validar
     * @return {@code true} si se valido correctamente {@code false} en otro caso
     */
    public Boolean execute(Integer id) {
        Optional<CarroCompras> carro = repositorio.hallar(id);

        if (carro.isEmpty()) {
            return false;
        }

        carro.get().setEstado(CarroCompraEstado.COMPLETADO);
        repositorio.guardar(carro.get());

        return true;
    }
}
