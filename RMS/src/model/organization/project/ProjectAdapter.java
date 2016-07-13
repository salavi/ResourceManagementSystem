package model.organization.project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
		List<Long> result = new ArrayList<>();
		for (String technology : technologies) {
			String query1 = "select distinct projectModel.id from ProjectModel as projectModel, TechnologyModel as technologyModel "
					+ "WHERE projectModel.numOfInvolvedHumans < :maxNumHumans AND projectModel.numOfInvolvedHumans > :minNumHumans "
					+ "AND projectModel.numOfModules < :maxNumModules AND projectModel.numOfModules > :minNumModules "
					+ "AND technologyModel.name = :technologyName";

			Transaction t = session.beginTransaction();
			Query query = session.createQuery(query1);
			query.setParameter("minNumHumans", minNumOfHumans);
			query.setParameter("maxNumHumans", maxNumOfHumans);
			query.setParameter("minNumModules", minNumOfModules);
			query.setParameter("maxNumModules", maxNumOfModules);
			query.setParameter("technologyName", technology);
			List<Long> projectsList = query.list();
			result.addAll(projectsList);
			t.commit();// transaction is committed
		}
		System.out.println("retrieved");
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
