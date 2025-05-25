package co.edu.uniquindio.aplicacion.exceptions;

import java.util.Collections;
import java.util.List;

public class ValidacionException extends Exception {
    private List<String> errors;

    public ValidacionException(List<String> errors) {
        this.errors = Collections.unmodifiableList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
