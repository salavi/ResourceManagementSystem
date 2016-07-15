package logic.organization.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.organization.module.ModuleAdapter;
import model.organization.module.ModuleModel;

public class Module {

	private String name;
	private String moduleId;
	
	public Module(ModuleModel moduleModel) {
		this.name = moduleModel.getName();
		this.moduleId = moduleModel.getModuleId();
	}
	
	public Module() {
		// TODO Auto-generated constructor stub
	}

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

	public Map<String, Long> getAllModules() {
		ModuleAdapter moduleAdapter = ModuleAdapter.getInstance();
		List<ModuleModel> modules = moduleAdapter.findAll();
		Map<String, Long> convertedModules = new HashMap<>();
		for (ModuleModel module : modules) {
			convertedModules.put(module.getModuleId() + ": " + module.getName(), module.getId());
		}
		
		return convertedModules;
	}
}
