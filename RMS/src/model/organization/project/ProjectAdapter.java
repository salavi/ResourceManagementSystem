package model.organization.project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;
import model.organization.unit.UnitModel;

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

	public ProjectModel getProject(Long projectId) {
		Transaction t = session.beginTransaction();
		ProjectModel project = (ProjectModel) session.get(ProjectModel.class, projectId);
		return project;
	}

	public List<Long> findSimilarProjects(int minNumOfHumans, int maxNumOfHumans, int minNumOfModules,
			int maxNumOfModules, String[] technologies) {

//		String query1 = "SELECT project.ID FROM Project AS project RIGHT JOIN Technology as technology ON Project.ID = Technology.pid "
//				+ "WHERE Project.NumOfInvolvedHumans > :minNumHumans AND Project.NumOfInvolvedHumans < :maxNumHumans "
//				+ "AND Project.NumOfModules > :minNumModules AND Project.NumOfModules < :maxNumModules "
//				+ "AND Technology.Name IN (:technologyNames)";
		String query1 = "SELECT RUHResources.ResourceID From "
				+ "(SELECT t1.ID as pid, t1.Name, ResourceUsageHistory.ID as ruhid FROM "
				+ "(SELECT project.ID, Project.Name FROM Project AS project RIGHT JOIN Technology as technology ON Project.ID = Technology.pid "
				+ "WHERE Project.NumOfInvolvedHumans > :minNumHumans AND Project.NumOfInvolvedHumans < :maxNumHumans "
				+ "AND Project.NumOfModules > :minNumModules AND Project.NumOfModules < :maxNumModules "
				+ "AND Technology.Name IN " + parseTechnologies(technologies) + ")"
						+ " as t1 LEFT JOIN ResourceUsageHistory ON t1.ID = ResourceUsageHistory.project)"
						+ " as t2 LEFT JOIN RUHResources on t2.ruhid = RUHResources.ruhID";

		Transaction t = session.beginTransaction();
		Query query = session.createSQLQuery(query1);
		query.setParameter("minNumHumans", minNumOfHumans);
		query.setParameter("maxNumHumans", maxNumOfHumans);
		query.setParameter("minNumModules", minNumOfModules);
		query.setParameter("maxNumModules", maxNumOfModules);
		List<Long> projectsList = query.list();
		// result.addAll(projectsList);
		t.commit();// transaction is committed
		// }
		System.out.println("retrieved");
		System.out.println(projectsList);
		return projectsList;
	}

	private String parseTechnologies(String[] technologies) {
		// TODO Auto-generated method stub
		String result = "(";
		for (int i = 0; i < technologies.length; i++) {
			if (i == technologies.length - 1) {
				result = result + "'" + technologies[i] + "') ";
				break;
			}
				result = result + "'" + technologies[i] + "', ";
		}
		return result;
	}

}
