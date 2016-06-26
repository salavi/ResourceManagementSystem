package model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserAccountAdapter {
	private Session session;

	public UserAccountAdapter(){
		DBInitializatorSingleton dbInit = new DBInitializatorSingleton();
		this.setSession(dbInit.getSession());
	}

	public void addUserAccount(String firstName, String lastName, String username, String password){
		// creating transaction object
		Transaction t = session.beginTransaction();
		UserAccountModel userAcModel = new UserAccountModel(firstName, lastName, username, password);
		session.persist(userAcModel);// persisting the object
		t.commit();// transaction is committed
		System.out.println("successfully saved");
	}

	//	public UserAccountModel[] findAll(){
	//		//TODO
	//	}

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
