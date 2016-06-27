package src.authentication;


import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {
	// creating configuration object
	static Session session;

	public static void initial() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the
		// creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}
	
	public static void close() {
		session.close();
	}
											// configuration file

	// creating session object

	public static void register(String firstName, String lastName) {

		// creating transaction object
		Transaction t = session.beginTransaction();

		Employee e1 = new Employee();
		e1.setFirstName(firstName);
		e1.setLastName(lastName);

		session.persist(e1);// persisting the object

		t.commit();// transaction is committed


		System.out.println("successfully saved");

	}
}
