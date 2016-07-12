package logic.organization.module;

import model.organization.module.ModuleAdapter;
import model.organization.module.ModuleModel;

public class Module {

	private String name;
	private String moduleId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public ModuleModel createModuleModel() {
		ModuleModel moduleModel = new ModuleModel();
		moduleModel.setModuleId(moduleId);
		moduleModel.setName(name);
		
		ModuleAdapter.getInstance().addModule(moduleModel);
		return moduleModel;
	}
}
