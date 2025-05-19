package co.edu.uniquindio.aplicacion.carro;

import java.util.List;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

public class HallarCarroComprasTodosUseCase {
    private CarroComprasRepository repositorio;

    public HallarCarroComprasTodosUseCase(CarroComprasRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<CarroCompras> execute() {
        return repositorio.hallarTodos();
    }
}
