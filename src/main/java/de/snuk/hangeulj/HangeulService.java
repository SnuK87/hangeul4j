package de.snuk.hangeulj;

import de.snuk.hangeulj.model.HangeulWord;

public class HangeulService {

    public static Result check(HangeulWord input) {
	Result result = new Result(input);

	HangeulWord word1 = PronounciationRules.checkLinking(input);
	result.setRule1(input.equals(word1) ? false : true);

	HangeulWord word2 = PronounciationRules.checkNeutralization(word1);
	result.setRule2(word1.equals(word2) ? false : true);

	HangeulWord word3 = PronounciationRules.checkSimplificationOfFinalDoubleConsonant(word2);
	result.setRule3(word2.equals(word3) ? false : true);

	HangeulWord word4 = PronounciationRules.checkNasalization(word3);
	result.setRule4(word3.equals(word4) ? false : true);

	HangeulWord word5 = PronounciationRules.checkFortis(word4);
	result.setRule5(word4.equals(word5) ? false : true);

	HangeulWord word6 = PronounciationRules.checkAspiration(word5);
	result.setRule6(word5.equals(word6) ? false : true);

	HangeulWord word7 = PronounciationRules.checkPalatalization(word6);
	result.setRule7(word6.equals(word7) ? false : true);

	HangeulWord word8 = PronounciationRules.checkPalatalization(word7);
	result.setRule8(word7.equals(word8) ? false : true);

	result.setOutput(word8);

	return result;
    }

    public static Result checkReverse(HangeulWord input) {
	Result result = new Result(input);

	HangeulWord word1 = PronounciationRules.checkHOmission(input);
	result.setRule1(input.equals(word1) ? false : true);

	HangeulWord word2 = PronounciationRules.checkPalatalization(word1);
	result.setRule2(word1.equals(word2) ? false : true);

	HangeulWord word3 = PronounciationRules.checkAspiration(word2);
	result.setRule3(word2.equals(word3) ? false : true);

	HangeulWord word4 = PronounciationRules.checkFortis(word3);
	result.setRule4(word3.equals(word4) ? false : true);

	HangeulWord word5 = PronounciationRules.checkNasalization(word4);
	result.setRule5(word4.equals(word5) ? false : true);

	HangeulWord word6 = PronounciationRules.checkSimplificationOfFinalDoubleConsonant(word5);
	result.setRule6(word5.equals(word6) ? false : true);

	HangeulWord word7 = PronounciationRules.checkLinking(word6);
	result.setRule7(word6.equals(word7) ? false : true);

	HangeulWord word8 = PronounciationRules.checkNeutralization(word7);
	result.setRule8(word7.equals(word8) ? false : true);

	result.setOutput(word8);

	return result;
    }

    public static void main(String[] args) {
	// String in = "먹어요";
	// String in = "많소";
	// Result check = check(HangeulWord.ofString(in));
	// System.out.println(check);

	// Arrays.asList("꽃이", "옷을", "먹어요", "밥이", "부엌에", "닫아요", "문어", "마음에",
	// "살아요").stream().map(HangeulWord::ofString)
	// .map(HangeulService::check).forEach(System.out::println);

	// Arrays.asList("국", "부엌", "밖", "곧", "다섯", "갔다", "빚", "빛", "끝", "하읗",
	// "밥", "숲").stream()
	// .map(HangeulWord::ofString).map(HangeulService::check).forEach(System.out::println);

	// 7
	// // TODO: watch "굳히다"!!
	// Arrays.asList("굳이", "맏이", "해돋이", "같이", "끝이",
	// "굳히다").stream().map(HangeulWord::ofString)
	// .map(HangeulService::check).forEach(System.out::println);
	//
	// System.out.println("########");
	//
	// Arrays.asList("굳이", "맏이", "해돋이", "같이", "끝이",
	// "굳히다").stream().map(HangeulWord::ofString)
	// .map(HangeulService::checkReverse).forEachOrdered(System.out::println);

	// HangeulWord test =
	// PronounciationRules.checkPalatalization(HangeulWord.ofString("굳이"));
	// System.out.println(test);
	// test = PronounciationRules.checkLinking(test);
	// System.out.println(test);

	System.out.println(check(HangeulWord.ofString("굳이")));
	System.out.println(checkReverse(HangeulWord.ofString("굳이")));
    }

}

class Result {

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