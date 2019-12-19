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

	public Integer getNumTestsPassed() {
		return features.stream().map(x -> x.getNumTestsPassed()).collect(Collectors.summingInt(Integer::intValue));
	}

	public Integer getNumTestsFailed() {
		return features.stream().map(x -> x.getNumTestsFailed()).collect(Collectors.summingInt(Integer::intValue));
	}

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
