package logic.organization.resource;

import model.organization.resource.ResourceAdapter;

public abstract class ResourceFactory {
	protected ResourceAdapter resourceAdapter;
	
	public ResourceFactory(){
		 resourceAdapter = new ResourceAdapter();
	}
	
	public abstract Resource createResource();
	
	
}
