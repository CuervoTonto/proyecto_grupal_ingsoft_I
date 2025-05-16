package co.edu.uniquindio.dominio.mail;

public interface EmailSender {
    public void send(Email email) throws Exception;
}
