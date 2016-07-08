package logic.organization.resource;

public class HumanResourceFactory extends ResourceFactory {

	@Override
	public Resource createResource() {
		HumanResource humanResource = new HumanResource();
		return humanResource;
	}

}
