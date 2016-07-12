package model.organization.module;

import org.hibernate.Transaction;

import model.Adapter;
import model.organization.project.ProjectAdapter;

public class ModuleAdapter extends Adapter{
	private static ModuleAdapter instance = null;

	public static ModuleAdapter getInstance() {
		if (instance == null) {
			instance = new ModuleAdapter();
		}
		return instance;
	}

	public int addModule(ModuleModel moduleModel) {
		// TODO Auto-generated method stub
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(moduleModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
