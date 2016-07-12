package logic.organization.project;

import model.organization.project.ProjectModel;

class RetrievedProject {
	private String visibleName;
	private Long projectId;
	private ProjectModel projectModel;

	public RetrievedProject(String visibleName, Long projectId, ProjectModel projectModel) {
		this.setVisibleName(visibleName);
		this.setProjectId(projectId);

	}

	public String getVisibleName() {
		return visibleName;
	}

	public void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}
	
	
}
