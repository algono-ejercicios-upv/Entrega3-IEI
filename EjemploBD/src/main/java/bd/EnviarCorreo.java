package bd;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class EnviarCorreo implements TaskListener {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private static final String 
		from = "jsanchez20042003@yahoo.es", // Cuenta de yahoo desde donde se envian los emails 
		pass = "ferfqjhspdjbbazi", // Clave de la aplicación
		host = "smtp.mail.yahoo.com";

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("Inicio de envio de correo");
		
		String destinatario = (String) delegateTask.getExecution().getVariable("IDEmail");
		String asunto = (String) delegateTask.getExecution().getVariable("IDAsunto");
		String cuerpo = (String) delegateTask.getExecution().getVariable("IDCuerpo");
		
		Properties props = initProperties(host, from, pass);
		
		Session session = Session.getInstance(props, new MyAuthenticator());
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println("Excepcion detectada: " + e);
			throw new RuntimeException(e);
		}
	}
	
	private static Properties initProperties(String host, String from, String pass) {
		Properties props = System.getProperties();
		
		String trueString = new Boolean(true).toString();
				
		props.put("mail.smtp.starttls.enable", trueString);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.ssl.enable", trueString);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", trueString);
		
		return props;
	}
	
	private class MyAuthenticator extends javax.mail.Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(from, pass);
		}
	}
}
