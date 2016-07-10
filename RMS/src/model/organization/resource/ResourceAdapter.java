package model.organization.resource;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;

public class ResourceAdapter extends Adapter{

	public void addResource(ResourceModel resourceModel) {
		Transaction t = session.beginTransaction();
		session.persist(resourceModel);// persisting the object
		t.commit();// transaction is committed
		System.out.println("saved");
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
}
