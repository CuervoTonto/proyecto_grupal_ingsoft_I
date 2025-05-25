package co.edu.uniquindio.utilities;

import java.util.function.Function;

public class CadenaValidacion {
    private String error;
    private String campo;
    private String valor;

    public CadenaValidacion(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }

    public CadenaValidacion manual(Function<String, String> validador) {
        if (error != null) return this;

        String validacionMessage = validador.apply(valor);

        if (validacionMessage != null) error = validacionMessage;

        return this;
    }

    public CadenaValidacion requerido() {
        return manual((valor) -> {
            if (valor == null || valor.isBlank())
                return campo + " es requerido";
            return null;
        });
    }

    public CadenaValidacion decimal() {
        return manual((valor) -> {
            try {
                Double.parseDouble(valor);
                return null;
            } catch (NumberFormatException e) {
                return campo + " debe ser un numero decimal valido";
            }
        });
    }

    public CadenaValidacion entero() {
        return manual((valor) -> {
            try {
                Integer.parseInt(valor);
                return null;
            } catch (NumberFormatException e) {
                return campo + " debe ser un numero entero valido";
            }
        });
    }

    public String getError() {
        return error;
    }
}
