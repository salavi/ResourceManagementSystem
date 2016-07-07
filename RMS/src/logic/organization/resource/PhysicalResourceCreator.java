package logic.organization.resource;

public class PhysicalResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		PhysicalResource physicalResource = new PhysicalResource();
		return physicalResource;
	}

}
