package model.organization.unit;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;

public class UnitAdapter extends Adapter{

	private static UnitAdapter instance = null;
	

	public static UnitAdapter getInstance() {
		if(instance == null) {
			instance = new UnitAdapter();
		}
		return instance;
	}

	public static void setInstanceToNull() {
		UnitAdapter.instance = null;
	}
	
	public List<UnitModel> findAll(){
		//TODO
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from UnitModel");
			List<UnitModel> unitsList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return unitsList;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public UnitModel getUnit(Long unitId) {
		Transaction t = session.beginTransaction();
		UnitModel unit = (UnitModel)session.get(UnitModel.class, unitId);
		return unit;
	}
	

}
