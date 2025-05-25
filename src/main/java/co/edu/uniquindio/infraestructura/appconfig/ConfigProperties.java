package co.edu.uniquindio.infraestructura.appconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * configuration from config.properties
 */
public class ConfigProperties {
    private static ConfigProperties instancia;

    private Properties props;

    private ConfigProperties() {
        props = new Properties();

        try {
            InputStream input = ConfigProperties.class
                .getClassLoader()
                .getResourceAsStream("config.properties")
            ;

            System.out.println("Archivo encontrado: " + (input != null));

            if (input == null) {
                throw new IOException();
            }

            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al cargar el archivo de configuracion [config.properties]");
        }
    }

    public static ConfigProperties getInstancia() {
        if (instancia == null) {
            synchronized (ConfigProperties.class) {
                if (instancia == null) {
                    instancia = new ConfigProperties();
                }
            }
        }

        return instancia;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
