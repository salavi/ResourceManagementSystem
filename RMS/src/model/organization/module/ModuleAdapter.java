package model.organization.module;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import model.Adapter;
import model.organization.project.ProjectAdapter;
import model.organization.unit.UnitModel;

public class ModuleAdapter extends Adapter{
	private static ModuleAdapter instance = null;

	public static ModuleAdapter getInstance() {
		if (instance == null) {
			instance = new ModuleAdapter();
		}
		return instance;
	}

	public int addModule(ModuleModel moduleModel) {
		// TODO Auto-generated method stub
		try {
			// creating transaction object
			Transaction t = session.beginTransaction();
			session.persist(moduleModel);// persisting the object
			t.commit();// transaction is committed
			System.out.println("successfully saved");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ModuleModel getModule(Long moduleId) {
		Transaction t = session.beginTransaction();
		ModuleModel module = (ModuleModel)session.get(ModuleModel.class, moduleId);
		return module;
		
	}

	public List<ModuleModel> findAll() {
		try{
			// creating transaction object
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from ModuleModel");
			List<ModuleModel> modulesList = query.list();
			t.commit();// transaction is committed
			System.out.println("retrieved");
			return modulesList;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
