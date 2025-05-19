package co.edu.uniquindio.infraestructura.email;

import java.util.Properties;

import co.edu.uniquindio.dominio.mail.Email;
import co.edu.uniquindio.dominio.mail.EmailSender;
import co.edu.uniquindio.infraestructura.appconfig.ConfigProperties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class JakartaEmailSender implements EmailSender {
    private Session session;

    public JakartaEmailSender(String from, String password) throws Exception {
        ConfigProperties config = ConfigProperties.getInstancia();

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", config.getProperty("mail.transport.protocol"));
        props.setProperty("mail.smtp.host", config.getProperty("mail.smtp.host"));
        props.setProperty("mail.smtp.port", config.getProperty("mail.smtp.port"));
        props.setProperty("mail.smtp.auth", config.getProperty("mail.smtp.auth"));
        props.setProperty("mail.smtp.starttls.enable", config.getProperty("mail.smtp.starttls.enable"));
        props.setProperty("mail.smtp.ssl.trust", config.getProperty("mail.smtp.ssl.trust"));
        
        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    @Override
    public void send(Email email) throws Exception {
        Message message = new MimeMessage(session);
        // message.setFrom(new InternetAddress(email.getEmisor()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getDestinatario()));
        message.setSubject(email.getAsunto());

        message.setContent(email.getContenido(), "text/html");

        Transport.send(message);
    }
}
