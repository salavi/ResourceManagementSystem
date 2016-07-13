package model.organization.resourceUsage;

import org.hibernate.Transaction;

import model.Adapter;

public class ResourceUsageHistoryAdapter extends Adapter{
	private static ResourceUsageHistoryAdapter instance = null;

	public static ResourceUsageHistoryAdapter getInstance() {
		if (instance == null) {
			instance = new ResourceUsageHistoryAdapter();
		}
		return instance;
	}

	public void addResourceUsageHistory(ResourceUsageHistoryModel ruhModel) {
		Transaction t = session.beginTransaction();
		session.persist(ruhModel);// persisting the object
		t.commit();// transaction is committed
		System.out.println("saved");
	}	
}
