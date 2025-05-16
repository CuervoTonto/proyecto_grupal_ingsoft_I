package co.edu.uniquindio.infraestructura.carro.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import co.edu.uniquindio.dominio.carro.CarroCompras;
import co.edu.uniquindio.dominio.carro.CarroComprasRepository;

public class InMemoryCarritoComprasRepository implements CarroComprasRepository {
    public static final InMemoryCarritoComprasRepository INSTANCE = new InMemoryCarritoComprasRepository();

    private Map<Integer, CarroCompras> datos;

    private Integer ultimoId;

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
}
