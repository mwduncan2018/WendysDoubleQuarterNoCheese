package com.duncan.wordreportbdd.cucumberjsonpojo;

public class Step {

	private Result result;
	private String line;
	private String name;
	private Match match;
	private String keyword;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "ClassPojo [result = " + result + ", line = " + line + ", name = " + name + ", match = " + match
				+ ", keyword = " + keyword + "]";
	}
}
