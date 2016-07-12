package model.organization.project;

import org.hibernate.Transaction;

import model.Adapter;

public class ProcessAdapter extends Adapter{
	private static ProcessAdapter instance = null;

	public static ProcessAdapter getInstance() {
		if (instance == null) {
			instance = new ProcessAdapter();
		}
		return instance;
	}
	
	public int addProcess(ProcessModel processModel) {
		// TODO {exception haye transaction va project e tekrari joda shavad}
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(processModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
