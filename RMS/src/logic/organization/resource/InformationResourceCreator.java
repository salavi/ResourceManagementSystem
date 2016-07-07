package logic.organization.resource;

public class InformationResourceCreator extends ResourceFactory {

	@Override
	public Resource createResource() {
		InformationalResource informationResource = new InformationalResource();
		return informationResource;
	}

}
