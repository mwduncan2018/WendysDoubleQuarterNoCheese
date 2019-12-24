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

	private Long numberFeaturesPassed;
	private Long numberFeaturesFailed;

	private Long numberScenariosPassed;
	private Long numberScenariosFailed;
	
	public Long getNumberScenariosPassed() {
		return numberScenariosPassed;
	}

	public void setNumberScenariosPassed(Long numberScenariosPassed) {
		this.numberScenariosPassed = numberScenariosPassed;
	}

	public Long getNumberScenariosFailed() {
		return numberScenariosFailed;
	}

	public void setNumberScenariosFailed(Long numberScenariosFailed) {
		this.numberScenariosFailed = numberScenariosFailed;
	}

	public String getTitle() {
		return title;
	}

	public Long getNumberFeaturesPassed() {
		return numberFeaturesPassed;
	}

	public void setNumberFeaturesPassed(Long numberFeaturesPassed) {
		this.numberFeaturesPassed = numberFeaturesPassed;
	}

	public Long getNumberFeaturesFailed() {
		return numberFeaturesFailed;
	}

	public void setNumberFeaturesFailed(Long numberFeaturesFailed) {
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
