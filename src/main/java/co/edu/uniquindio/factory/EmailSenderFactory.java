package co.edu.uniquindio.factory;

import co.edu.uniquindio.infraestructura.appconfig.ConfigProperties;
import co.edu.uniquindio.infraestructura.email.JakartaEmailSender;

public class EmailSenderFactory {
    private EmailSenderFactory() {}

    public static JakartaEmailSender crearJakartaEmailSender() {
        try {
            return new JakartaEmailSender(
                ConfigProperties.getInstancia().getProperty("mail.username"),
                ConfigProperties.getInstancia().getProperty("mail.password")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
