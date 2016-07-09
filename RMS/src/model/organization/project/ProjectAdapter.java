package model.organization.project;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;

public class ProjectAdapter extends Adapter {
	private static ProjectAdapter instance = null;

	public static ProjectAdapter getInstance() {
		if (instance == null) {
			instance = new ProjectAdapter();
		}
		return instance;
	}

	public int addProject(ProjectModel projectModel) {
		// TODO {exception haye transaction va project e tekrari joda shavad}
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(projectModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<ProjectModel> findAll() {
		// TODO
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from ProjectModel");
			List<ProjectModel> projectsList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return projectsList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int updateProject(ProjectModel projectModel) {
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.update(projectModel);
			t.commit();// transaction is committed
			System.out.println("updated");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void remove(ProjectModel projectModel) {
		// TODO
	}

}
