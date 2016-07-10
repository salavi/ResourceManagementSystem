package logic.organization.resource;

import model.organization.resource.FinancialResourceModel;
import model.organization.resource.ResourceModel;
import model.organization.unit.UnitAdapter;
import model.organization.unit.UnitModel;

public class FinancialResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		FinancialResource financialResource = new FinancialResource();
		return financialResource;
	}
	
	public ResourceModel createFinancialResource(Long unitId, String resourceId, double amount){
		FinancialResourceModel financialResourceModel = new FinancialResourceModel();
		financialResourceModel.setAmount(amount);
		financialResourceModel.setResourceId(resourceId);
		
		UnitModel unit = new UnitAdapter().getUnit(unitId);
		financialResourceModel.setCurrentUnit(unit);
		
		resourceAdapter.addResource(financialResourceModel);
		return financialResourceModel;
	}

}
