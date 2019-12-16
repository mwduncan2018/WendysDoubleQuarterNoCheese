package com.duncan.wordreportbdd.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class WordReportViewModel {

	private String title;
	private String department;
	private String date;
	private String introParagraph;

	private List<FeatureViewModel> features = new ArrayList<FeatureViewModel>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIntroParagraph() {
		return introParagraph;
	}

	public void setIntroParagraph(String introParagraph) {
		this.introParagraph = introParagraph;
	}

	public List<FeatureViewModel> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureViewModel> features) {
		this.features = features;
	}

}
