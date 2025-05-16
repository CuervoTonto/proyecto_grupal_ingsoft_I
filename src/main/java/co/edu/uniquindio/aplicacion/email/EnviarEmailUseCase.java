package co.edu.uniquindio.aplicacion.email;

import co.edu.uniquindio.dominio.mail.Email;
import co.edu.uniquindio.dominio.mail.EmailSender;

public class EnviarEmailUseCase {
    private EmailSender emailSender;

    public EnviarEmailUseCase(EmailSender sender) {
        this.emailSender = sender;
    }

    public void execute(Email email) throws Exception {
        emailSender.send(email);
    }
}
