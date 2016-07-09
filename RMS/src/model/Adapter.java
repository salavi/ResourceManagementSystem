package model;

import org.hibernate.Session;

public abstract class Adapter {

	protected Session session;

	protected Adapter() {
		DBInitializatorSingleton dbInit = DBInitializatorSingleton.getInstance();
		this.setSession(dbInit.getSession());
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
