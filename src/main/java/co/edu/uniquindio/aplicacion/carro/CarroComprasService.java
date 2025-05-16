package co.edu.uniquindio.aplicacion.carro;

import java.util.Optional;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

public class CarroComprasService {
    // private CarroComprasRepository repositorio;

    private CrearCarroComprasUseCase crearCarroCompras;
    private HallarCarroComprasUseCase hallarCarroCompras;
    private ValidarCarroComprasUseCase validarCarroCompras;

    public CarroComprasService(CarroComprasRepository repositorio) {
        // this.repositorio = repositorio;
        crearCarroCompras = new CrearCarroComprasUseCase(repositorio);
        hallarCarroCompras = new HallarCarroComprasUseCase(repositorio);
        validarCarroCompras = new ValidarCarroComprasUseCase(repositorio);
    }

    public CarroCompras craer(CarroCompras carroCompras) {
        return crearCarroCompras.execute(carroCompras);
    }

    public Optional<CarroCompras> hallarPorId(Integer id) {
        return hallarCarroCompras.execute(id);
    }

    public Boolean validar(Integer id) {
        return validarCarroCompras.execute(id);
    }
}
