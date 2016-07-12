package model.organization.project;

import model.Adapter;

public class ProcessAdapter extends Adapter{
	private static ProcessAdapter instance = null;

	public static ProcessAdapter getInstance() {
		if (instance == null) {
			instance = new ProcessAdapter();
		}
		return instance;
	}
}
