package model.organization.resource;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import model.Adapter;
import model.authentication.UserAccountModel;

public class ResourceAdapter extends Adapter{
	private static ResourceAdapter instance = null;

	public static ResourceAdapter getInstance() {
		if (instance == null) {
			instance = new ResourceAdapter();
		}
		return instance;
	}

	public void addResource(ResourceModel resourceModel) {
		Transaction t = session.beginTransaction();
		session.persist(resourceModel);// persisting the object
		t.commit();// transaction is committed
		System.out.println("saved");
	}	

	public ResourceModel getResource(Long resourceId){
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("FROM ResourceModel resourceModel WHERE resourceModel.id = :un");
			query.setParameter("un", resourceId);
			List<ResourceModel> resourceModel = query.list();
			t.commit();// transaction is committed
			return resourceModel.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<HumanResourceModel> findAll() {
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from HumanResourceModel");
			List<HumanResourceModel> resources = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return resources;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public List<FinancialResourceModel> findAllFinancialResources() {
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from FinancialResourceModel");
			List<FinancialResourceModel> resources = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return resources;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public List<HumanResourceModel> findAllHumanResources() {
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from HumanResourceModel");
			List<HumanResourceModel> resources = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return resources;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public List<InformationalResourceModel> findAllInformationalResources() {
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from InformationalResourceModel");
			List<InformationalResourceModel> resources = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return resources;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public List<PhysicalResourceModel> findAllPhysicalResources() {
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from PhysicalResourceModel");
			List<PhysicalResourceModel> resources = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return resources;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public List<String> findUnitsOfResources(String type){
		try{
			List<String> result = new ArrayList<>();

			// creating transaction object
			Transaction t = session.beginTransaction();
			String sql = ("SELECT UnitID, Specialty FROM Unit "
					+ "RIGHT JOIN (SELECT DISTINCT currentUnit FROM Resource "
					+ "RIGHT JOIN " + type + "Resource "
					+ "On " + type + "Resource." + type + "ResourceID = Resource.ID "
					+ "LEFT JOIN Unit "
					+ "ON Unit.ID=Resource.currentUnit) AS currUnit "
					+ "ON Unit.ID=currUnit.currentUnit;");
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Object> resources = query.list();
			for (Object unitInformation: resources){
				result.add(unitInformation.toString());
			}
			t.commit();// transaction is committed
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<String> findUnitCountsOfResources(String type){
		try{
			List<String> result = new ArrayList<>();

			// creating transaction object
			Transaction t = session.beginTransaction();
			String sql = ("SELECT count(currentUnit) "
					+ "FROM Resource "
					+ "RIGHT JOIN " + type + "Resource "
					+ "ON " + type + "Resource." + type + "ResourceID=Resource.ID "
					+ "GROUP by currentUnit;");
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Object> counts = query.list();
			for (Object unitCounts: counts){
				result.add(unitCounts.toString());
			}
			t.commit();// transaction is committed
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
