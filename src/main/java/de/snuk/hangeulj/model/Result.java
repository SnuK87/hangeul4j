package de.snuk.hangeulj.model;

public class Result {

	private HangeulWord input;
	private HangeulWord output;

	private boolean rule1 = false;
	private boolean rule2 = false;
	private boolean rule3 = false;
	private boolean rule4 = false;
	private boolean rule5 = false;
	private boolean rule6 = false;
	private boolean rule7 = false;
	private boolean rule8 = false;

	public Result(HangeulWord input) {
		this.input = input;
	}

	public HangeulWord getInput() {
		return input;
	}

	public void setInput(HangeulWord input) {
		this.input = input;
	}

	public HangeulWord getOutput() {
		return output;
	}

	public void setOutput(HangeulWord output) {
		this.output = output;
	}

	public boolean isRule1() {
		return rule1;
	}

	public void setRule1(boolean rule1) {
		this.rule1 = rule1;
	}

	@Override
	public String toString() {
		return "Result [input=" + input + ", output=" + output + ", rule1=" + rule1 + ", rule2=" + rule2 + ", rule3="
				+ rule3 + ", rule4=" + rule4 + ", rule5=" + rule5 + ", rule6=" + rule6 + ", rule7=" + rule7 + ", rule8="
				+ rule8 + "]";
	}

	public boolean isRule2() {
		return rule2;
	}

	public void setRule2(boolean rule2) {
		this.rule2 = rule2;
	}

	public boolean isRule3() {
		return rule3;
	}

	public void setRule3(boolean rule3) {
		this.rule3 = rule3;
	}

	public boolean isRule4() {
		return rule4;
	}

	public void setRule4(boolean rule4) {
		this.rule4 = rule4;
	}

	public boolean isRule5() {
		return rule5;
	}

	public void setRule5(boolean rule5) {
		this.rule5 = rule5;
	}

	public boolean isRule6() {
		return rule6;
	}

	public void setRule6(boolean rule6) {
		this.rule6 = rule6;
	}

	public boolean isRule7() {
		return rule7;
	}

	public void setRule7(boolean rule7) {
		this.rule7 = rule7;
	}

	public boolean isRule8() {
		return rule8;
	}

	public void setRule8(boolean rule8) {
		this.rule8 = rule8;
	}

}
