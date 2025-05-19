package co.edu.uniquindio.aplicacion.email;

import co.edu.uniquindio.dominio.mail.Email;
import co.edu.uniquindio.dominio.mail.EmailSender;

public class EmailService {
    private EnviarEmailUseCase envairEmail;

    public EmailService(EmailSender emailSender) {
        this.envairEmail = new EnviarEmailUseCase(emailSender);
    }

    public void enviar(Email email) throws Exception {
        envairEmail.execute(email);
    }
}
