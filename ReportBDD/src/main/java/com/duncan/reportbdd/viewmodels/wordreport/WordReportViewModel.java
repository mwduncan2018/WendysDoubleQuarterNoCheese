package com.duncan.reportbdd.viewmodels.wordreport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordReportViewModel {

	private String title;
	private String department;
	private String date;
	private String introParagraph;
	
	private List<FeatureViewModel> features = new ArrayList<FeatureViewModel>();
	private Integer numberFeaturesPassed;
	private Integer numberFeaturesFailed;

	public String getTitle() {
		return title;
	}

	public Integer getNumberFeaturesPassed() {
		return numberFeaturesPassed;
	}

	public void setNumberFeaturesPassed(Integer numberFeaturesPassed) {
		this.numberFeaturesPassed = numberFeaturesPassed;
	}

	public Integer getNumberFeaturesFailed() {
		return numberFeaturesFailed;
	}

	public void setNumberFeaturesFailed(Integer numberFeaturesFailed) {
		this.numberFeaturesFailed = numberFeaturesFailed;
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
