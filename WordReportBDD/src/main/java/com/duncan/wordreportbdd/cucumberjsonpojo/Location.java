package com.duncan.wordreportbdd.cucumberjsonpojo;

public class Location {
	
	private String line;
	private String column;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "ClassPojo [line = " + line + ", column = " + column + "]";
	}
}
