package model.organization.resource;

import org.hibernate.Transaction;

import model.Adapter;

public class ResourceAdapter extends Adapter{

	public void addResource(ResourceModel resourceModel) {
		Transaction t = session.beginTransaction();
		session.persist(resourceModel);// persisting the object
		t.commit();// transaction is committed
		System.out.println("saved");
	}	

}
