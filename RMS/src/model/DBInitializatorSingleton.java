package model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBInitializatorSingleton {
	// creating configuration object
	private Session session;
	private static DBInitializatorSingleton instance = null;
	
	public DBInitializatorSingleton() {
		// Exists only to defeat instantiation.
		Configuration cfg = new Configuration();
		cfg.configure("DBXML/hibernate.cfg.xml");
		// creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();
		setSession(factory.openSession());
	}
	
	public static DBInitializatorSingleton getInstance() {
		if(instance == null) {
			instance = new DBInitializatorSingleton();
		}
		return instance;
	}

	public void initial() {
//		Configuration cfg = new Configuration();
//		cfg.configure("DBXML/hibernate.cfg.xml");
//		// creating seession factory object
//		SessionFactory factory = cfg.buildSessionFactory();
//		setSession(factory.openSession());
	}

	public void close() {
		getSession().close();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}


