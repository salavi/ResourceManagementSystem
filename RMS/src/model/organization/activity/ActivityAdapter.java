package model.organization.activity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import model.Adapter;
import model.organization.unit.UnitModel;

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
	
	public List<Object> findAll(String typeOfProcess) {
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT activity.id, activity.Name FROM activity "
					+ "LEFT JOIN process on activity.pid = process.id "
					+ "LEFT JOIN module on activity.module = module.ID "
					+ "WHERE process.TypeOfProcess = '"+ typeOfProcess + "'");
			List<Object> activityList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return activityList;

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
	

	public List<ActivityModel> findActivitiesOfProject(Long projectId) {
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT * FROM activity JOIN project ON activity.pid = project.DevProcID WHERE project.ID =" + projectId).addEntity(ActivityModel.class);
			List<ActivityModel> activityList = query.list();
			 
			query = session.createSQLQuery("SELECT * FROM activity JOIN project ON activity.pid = project.MainProcID WHERE project.ID =" + projectId).addEntity(ActivityModel.class);
			activityList.addAll(query.list());
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return activityList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ActivityModel getActivity(Long activityId) {
		Transaction t = session.beginTransaction();
		ActivityModel unit = (ActivityModel)session.get(ActivityModel.class, activityId);
		return unit;
	}

}
