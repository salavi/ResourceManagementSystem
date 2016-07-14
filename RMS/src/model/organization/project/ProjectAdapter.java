package model.organization.project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

	public List<Object> getFinancialResourcesUsedInProjects(){
		try{
			List<Object> result = new ArrayList<>();
			
			// creating transaction object
			Transaction t = session.beginTransaction();
			String sql = ("SELECT project.Name, FinancialResource.amount FROM PROJECT RIGHT JOIN resourceusagehistory ON project.ID=resourceusagehistory.project "
					+ "RIGHT JOIN ruhresources ON resourceusagehistory.ID=ruhresources.ruhID "
					+ "LEFT JOIN resource ON ruhresources.ResourceID=resource.ID "
					+ "LEFT JOIN FinancialResource ON FinancialResource.FinancialResourceID=resource.ID");
			SQLQuery query = session.createSQLQuery(sql);
//			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			result = query.list();
			t.commit();// transaction is committed
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Object> getInformationalResourcesUsedInProjects(){
		try{
			List<Object> result = new ArrayList<>();
			
			// creating transaction object
			Transaction t = session.beginTransaction();
			String sql = ("SELECT project.Name, InformationResource.type FROM PROJECT RIGHT JOIN resourceusagehistory ON project.ID=resourceusagehistory.project"
					+ " RIGHT JOIN ruhresources ON resourceusagehistory.ID=ruhresources.ruhID"
					+ " LEFT JOIN resource ON ruhresources.ResourceID=resource.ID"
					+ " LEFT JOIN InformationResource ON InformationResource.InformationResourceID=resource.ID");
			SQLQuery query = session.createSQLQuery(sql);
//			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			result = query.list();
			t.commit();// transaction is committed
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
//	public List<String> getFinancialResourcesUsedInProjects(){
//		try{
//			List<String> result = new ArrayList<>();
//			
//			// creating transaction object
//			Transaction t = session.beginTransaction();
//			String sql = ("SELECT project.Name, FinancialResource.amount FROM PROJECT RIGHT JOIN resourceusagehistory ON project.ID=resourceusagehistory.project "
//					+ "RIGHT JOIN ruhresources ON resourceusagehistory.ID=ruhresources.ruhID "
//					+ "LEFT JOIN resource ON ruhresources.ResourceID=resource.ID "
//					+ "LEFT JOIN FinancialResource ON FinancialResource.FinancialResourceID=resource.ID");
//			SQLQuery query = session.createSQLQuery(sql);
//			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//			List<Object> projResources = query.list();
//			for (Object projectResource: projResources){
//				result.add(projectResource.toString());
//			}
//			t.commit();// transaction is committed
//			return result;
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
}
