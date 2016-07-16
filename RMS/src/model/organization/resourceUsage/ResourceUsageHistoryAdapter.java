package model.organization.resourceUsage;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
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
	
	public List<Object> getResourceUsageHistoryInfo(){
		try{
			List<Object> result = new ArrayList<>();
			
			// creating transaction object
			Transaction t = session.beginTransaction();
			String sql = ("SELECT RUHResources.ResourceID, Activity.StartDate, Activity.EndDate,"
					+ " Process.TypeOfProcess, Project.Name"
					+ " FROM RUHResources"
					+ " LEFT JOIN ResourceUsageHistory"
					+ " ON RUHResources.ruhID=ResourceUsageHistory.ID"
					+ " LEFT JOIN Activity"
					+ " ON ResourceUsageHistory.activity=Activity.ID"
					+ " LEFT JOIN Process"
					+ " ON Activity.pid=Process.ID"
					+ " LEFT JOIN Project"
					+ " ON Project.ID=ResourceUsageHistory.project");
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
	
	public List<Object> getDistinctResourcesUsedInRUHResources(String type){
		try{
			List<Object> result = new ArrayList<>();
			
			// creating transaction object
			Transaction t = session.beginTransaction();
			String firstPartOfSQL = ("SELECT DISTINCT RUHResources.ResourceID,");
			String columnSelect = "";
			switch(type){
			case "Human":
				columnSelect += " HumanResource.firstName, HumanResource.lastName";
				break;
			case "Information":
				columnSelect += " InformationResource.type";
				break;
			case "Financial":
				columnSelect += " FinancialResource.amount";
				break;
			case "Physical":
				columnSelect += " PhysicalResource.type";
				break;
			};
			String secondPartOfSQL = " FROM RUHResources"
					+ " LEFT JOIN Resource"
					+ " ON RUHResources.ResourceID=Resource.ID"
					+ " RIGHT JOIN " + type + "Resource"
					+ " ON Resource.ID=" + type + "Resource." + type + "ResourceID"
					+ " WHERE RUHResources.ResourceID IS NOT NULL";
			String sql = firstPartOfSQL + columnSelect + secondPartOfSQL;
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
