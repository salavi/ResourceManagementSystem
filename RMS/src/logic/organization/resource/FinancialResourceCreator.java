package logic.organization.resource;

public class FinancialResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		FinancialResource financialResource = new FinancialResource();
		return financialResource;
	}

}
