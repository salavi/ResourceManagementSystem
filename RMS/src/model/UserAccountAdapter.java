package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserAccountAdapter {
	private Session session;
	private static UserAccountAdapter instance = null;

	private UserAccountAdapter(){
		DBInitializatorSingleton dbInit = DBInitializatorSingleton.getInstance();
		this.setSession(dbInit.getSession());
	}

	public static UserAccountAdapter getInstance() {
		if(instance == null) {
			instance = new UserAccountAdapter();
		}
		return instance;
	}

	public int addUserAccount(UserAccountModel userAcModel){
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

	public List<UserAccountModel> findAll(){
		//TODO
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from UserAccountModel");
			List<UserAccountModel> usersList = query.list();
			t.commit();// transaction is committed
			System.out.println("successfully retrieved");
			return usersList;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void remove(UserAccountModel userAccount){
		//TODO
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
