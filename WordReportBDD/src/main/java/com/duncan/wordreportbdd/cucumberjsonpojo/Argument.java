package com.duncan.wordreportbdd.cucumberjsonpojo;

public class Argument {
	
	private String val;
	private String offset;

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "ClassPojo [val = " + val + ", offset = " + offset + "]";
	}
}