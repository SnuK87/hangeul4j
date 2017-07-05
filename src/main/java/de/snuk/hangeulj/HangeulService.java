package de.snuk.hangeulj;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import de.snuk.hangeulj.model.HangeulWord;
import de.snuk.hangeulj.model.Result;

public class HangeulService {

	private static Function<Result, Result> rule1 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkLinking(r.getOutput());
		r.setOutput(output);

		if (!r.isRule1()) {
			r.setRule1(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule2 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkNeutralization(r.getOutput());
		r.setOutput(output);

		if (!r.isRule2()) {
			r.setRule2(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule3 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkSimplificationOfFinalDoubleConsonant(r.getOutput());
		r.setOutput(output);

		if (!r.isRule3()) {
			r.setRule3(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule4 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkNasalization(r.getOutput());
		r.setOutput(output);

		if (!r.isRule4()) {
			r.setRule4(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule5 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkFortis(r.getOutput());
		r.setOutput(output);

		if (!r.isRule5()) {
			r.setRule5(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule6 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkAspiration(r.getOutput());
		r.setOutput(output);

		if (!r.isRule6()) {
			r.setRule6(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule7 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkPalatalization(r.getOutput());
		r.setOutput(output);

		if (!r.isRule7()) {
			r.setRule7(!input.equals(output));
		}

		return r;
	};

	private static Function<Result, Result> rule8 = r -> {
		HangeulWord input = r.getOutput();
		HangeulWord output = PronounciationRules.checkHOmission(r.getOutput());
		r.setOutput(output);

		if (!r.isRule8()) {
			r.setRule8(!input.equals(output));
		}

		return r;
	};

	public static void main(String[] args) {
		List<Function<Result, Result>> rules = Arrays.asList(rule8, rule7, rule1, rule6, rule3, rule5, rule4, rule2);

		List<HangeulWord> words1 = Arrays.asList("꽃이", "옷을", "먹어요", "밥이", "부엌에", "닫아요", "문어", "마음에", "살아요").stream()
				.map(HangeulWord::ofString).collect(Collectors.toList());

		List<HangeulWord> words2 = Arrays.asList("국", "부엌", "밖", "곧", "다섯", "갔다", "빚", "빛", "끝", "하읗", "밥", "숲")
				.stream().map(HangeulWord::ofString).collect(Collectors.toList());

		List<HangeulWord> words3 = Arrays
				.asList("넋", "앉다", "외곬", "핥다", "값", "몫", "많고", "많다", "많지", "싫고", "싫다", "싫지", "많소", "많네", "뚫소", "뚫네",
						"삶", "굶다", "젊다", "읊다", "읊지", "읊고")
				.stream().map(HangeulWord::ofString).collect(Collectors.toList());

		// TODO: 뚫네

		List<HangeulWord> words4 = Arrays
				.asList("앞마당", "믿는다", "한국말", "입는", "있는", "학년", "심리", "겅류장", "등록금", "염려", "국립", "대학로").stream()
				.map(HangeulWord::ofString).collect(Collectors.toList());

		List<HangeulWord> words5 = Arrays.asList("학교", "받다", "꽃밭", "국수", "국자", "책상").stream().map(HangeulWord::ofString)
				.collect(Collectors.toList());

		List<HangeulWord> words6 = Arrays.asList("국화", "맏형", "입학", "앉히다", "놓고", "놓다", "놓지", "많다").stream()
				.map(HangeulWord::ofString).collect(Collectors.toList());

		List<HangeulWord> words7 = Arrays.asList("굳이", "맏이", "해돋이", "같이", "끝이", "굳히다").stream()
				.map(HangeulWord::ofString).collect(Collectors.toList());

		List<HangeulWord> words8 = Arrays.asList("좋아요", "좋은", "촣을", "많아요", "많은", "많을").stream()
				.map(HangeulWord::ofString).collect(Collectors.toList());

		// Result r = new Result(words3.get(1));
		// r.setOutput(words3.get(1));
		// r = rule3.apply(r);
		// System.out.println(r);
		// r = rule5.apply(r);
		// System.out.println(r);

		for (HangeulWord hangeulWord : words3) {
			Result res = new Result(hangeulWord);
			res.setOutput(hangeulWord);

			for (Function<Result, Result> rule : rules) {
				res = rule.apply(res);
			}

			for (Function<Result, Result> rule : rules) {
				res = rule.apply(res);
			}

			System.out.println(res);
		}

	}

}
