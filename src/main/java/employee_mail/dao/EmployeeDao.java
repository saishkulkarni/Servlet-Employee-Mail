package employee_mail.dao;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import employee_mail.dto.Employee;

public class EmployeeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void save(Employee employee) {
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
	}

	public void sendEmail(String email) {
		String to = email;
		String from = "Email here";
		String host = "smtp.gmail.com";
		final String username = "Email here";
		final String password = "Generated App Password here";
		String subject = "Triwits";
		String messageText = "Your Resume is Stored into our Database";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(messageText);
			Transport.send(message);
		} catch (Exception ex) {
		}
	}

	public List<Employee> fetchAll() {
		return manager.createQuery("select x from Employee x").getResultList();
	}
	
	public Employee find(int id)
	{
		return manager.find(Employee.class, id);
	}

	public void remove(Employee employee)
	{
		transaction.begin();
		manager.remove(employee);
		transaction.commit();
	}
}
