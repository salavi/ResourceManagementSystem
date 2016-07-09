package model.organization.project;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;

public class TechnologyAdapter extends Adapter {

	private static TechnologyAdapter instance = null;

	public static TechnologyAdapter getInstance() {
		if (instance == null) {
			instance = new TechnologyAdapter();
		}
		return instance;
	}

	public int addTechnology(TechnologyModel technologyModel) {
		// TODO {exception haye transaction va technology e tekrari joda shavad}
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(technologyModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<TechnologyModel> findAll() {
		// TODO
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from TechnologyModel");
			List<TechnologyModel> technologiesList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return technologiesList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateTechnology(TechnologyModel technologyModel) {
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.update(technologyModel);
			t.commit();// transaction is committed
			System.out.println("updated");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void remove(TechnologyModel technologyModel) {
		// TODO
	}
}
