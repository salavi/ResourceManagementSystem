package model.project;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.DBInitializatorSingleton;

public class TechnologyAdapter {
	
	private Session session;
	private static TechnologyAdapter instance = null;

	private TechnologyAdapter(){
		DBInitializatorSingleton dbInit = DBInitializatorSingleton.getInstance();
		this.setSession(dbInit.getSession());
	}

	public static TechnologyAdapter getInstance() {
		if(instance == null) {
			instance = new TechnologyAdapter();
		}
		return instance;
	}
	
	public int addTechnology(TechnologyModel userAcModel){
		//TODO {exception haye transaction va username e tekrari joda shavad}
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(userAcModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;

		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
