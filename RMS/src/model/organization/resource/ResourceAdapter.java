package model.organization.resource;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import model.Adapter;

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
		Transaction t = session.beginTransaction();
		ResourceModel module = (ResourceModel)session.get(ResourceModel.class, resourceId);
		return module;
		
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
	
	public List<Object> findExistingResourcesInUnits(String type){
		try{
			List<Object> result = new ArrayList<>();

			// creating transaction object
			Transaction t = session.beginTransaction();
			String sql = ("SELECT UnitID, Specialty, COUNT(currentUnit) FROM Unit"
					+ " RIGHT JOIN (SELECT currentUnit FROM Resource"
					+ " RIGHT JOIN " + type + "Resource"
					+ " On " + type + "Resource." + type + "ResourceID = Resource.ID"
					+ " LEFT JOIN Unit"
					+ " ON Unit.ID=Resource.currentUnit) AS currUnit"
					+ " ON Unit.ID=currUnit.currentUnit"
					+ " GROUP BY currentUnit;");
			SQLQuery query = session.createSQLQuery(sql);
			result = query.list();
			t.commit();// transaction is committed
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
