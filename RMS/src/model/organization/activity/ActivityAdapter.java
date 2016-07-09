package model.organization.activity;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;

public class ActivityAdapter extends Adapter {

	private static ActivityAdapter instance = null;

	public static ActivityAdapter getInstance() {
		if (instance == null) {
			instance = new ActivityAdapter();
		}
		return instance;
	}

	public int addActivity(ActivityModel activityModel) {
		// TODO {exception haye transaction va adapter e tekrari joda shavad}
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(activityModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<ActivityModel> findAll() {
		// TODO
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from ActivityModel");
			List<ActivityModel> activitiesList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return activitiesList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateActivity(ActivityModel activityModel) {
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.update(activityModel);
			t.commit();// transaction is committed
			System.out.println("updated");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void remove(ActivityModel activityModel) {
		// TODO
	}

}
