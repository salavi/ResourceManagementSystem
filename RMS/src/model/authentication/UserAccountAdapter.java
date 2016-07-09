package model.authentication;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import model.Adapter;

public class UserAccountAdapter extends Adapter {
	private static UserAccountAdapter instance = null;

	public static UserAccountAdapter getInstance() {
		if (instance == null) {
			instance = new UserAccountAdapter();
		}
		return instance;
	}

	public int addUserAccount(UserAccountModel userAcModel) {
		// TODO {exception haye transaction va username e tekrari joda shavad}
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(userAcModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("saved");
			return 1;

		}
		catch(ConstraintViolationException e){
			System.out.println("Constraint Violation Exception");
			return -1;
		}
//		catch(Exception e){
//			e.printStackTrace();
//			return -1;
//		}
	}

	public List<UserAccountModel> findAll() {
		// TODO
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from UserAccountModel");
			List<UserAccountModel> usersList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return usersList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateFirstLastName(UserAccountModel userAccountModel) {
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.update(userAccountModel);
			t.commit();// transaction is committed
			System.out.println("updated");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int updatePassword(UserAccountModel userAccountModel){
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.update(userAccountModel);
			t.commit();// transaction is committed
			System.out.println("updated");
			return 1;
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	public void remove(UserAccountModel userAccount) {
		// TODO
	}
}
