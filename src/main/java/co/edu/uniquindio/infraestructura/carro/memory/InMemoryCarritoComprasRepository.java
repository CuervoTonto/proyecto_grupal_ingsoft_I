package co.edu.uniquindio.infraestructura.carro.memory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

public class InMemoryCarritoComprasRepository implements CarroComprasRepository {
    public static InMemoryCarritoComprasRepository instancia;

    private Map<Integer, CarroCompras> datos;

    private Integer ultimoId;

    public static InMemoryCarritoComprasRepository getInstancia() {
        if (instancia == null) {
            synchronized (InMemoryCarritoComprasRepository.class) {
                if (instancia == null) instancia = new InMemoryCarritoComprasRepository();
            }
        }
        
        return instancia;
    }

    private InMemoryCarritoComprasRepository() {
        datos = new HashMap<>();
        ultimoId = 0;
    }

    @Override
    public CarroCompras guardar(CarroCompras carro) {
        if (carro.getId() == null) {
            carro.setId(++ultimoId);
        } else {
            CarroCompras almacenado = datos.get(carro.getId());

            if (almacenado == null) return null;
        }

        datos.put(carro.getId(), carro);

        return carro;
    }

    @Override
    public Boolean eliminar(Integer id) {
        return datos.remove(id) != null;
    }

    @Override
    public Optional<CarroCompras> hallar(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<CarroCompras> hallarCarrosComprasPorCliente(Integer ciudadanoId) {
        List<CarroCompras> resultado = datos.values().stream()
            .filter(carro -> carro.getDueño().getId() == ciudadanoId)
            .toList()
        ;

        if (resultado.isEmpty()) {
            return null;
        }

        return resultado;
    }

    @Override
    public List<CarroCompras> hallarTodos() {
        return new LinkedList<>(datos.values());
    }
}
