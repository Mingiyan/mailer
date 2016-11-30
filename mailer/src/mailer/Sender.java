package mailer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by mdordzhiev on 24.08.2016.
 */
public class Sender{
    private String username;
    private String password;
    private Properties p;

    public Sender (String username,String password){
        this.username = username;
        this.password = password;

        p = new Properties();
        p.put("mail.smtp.host", "smtp.mail.ru");
       // p.put("mail.smtp.socketFactory.port", "465");
       // p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); это если SSL будет
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "25");
    }
    public void send(String subject, String textik, String fromEmail, String toEmail){
        Session session = Session.getDefaultInstance(p, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(textik);
            Transport.send(message);
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }

        }
}
