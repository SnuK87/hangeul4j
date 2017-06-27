package de.snuk.hangeulj;

import static de.snuk.hangeulj.HangeulUtil.normalize;
import static de.snuk.hangeulj.HangeulUtil.toJamo;

import java.util.Arrays;
import java.util.List;

import de.snuk.hangeulj.model.HangeulChar;
import de.snuk.hangeulj.model.HangeulSyllable;
import de.snuk.hangeulj.model.HangeulWord;
import de.snuk.hangeulj.model.Types;

public class PronounciationRules {

    // Rule 1
    static HangeulWord checkLinking(HangeulWord input) {
	if (input == null) {
	    return null;
	}

	List<HangeulSyllable> syllables = input.getSyllables();

	for (int i = 0; i < syllables.size(); i++) {
	    if (syllables.get(i).getFinall() != null) {
		if (syllables.get(i + 1).getInitial().getJamo() == 11) {
		    HangeulChar newInit = HangeulUtil.convertFinalToInitialString(syllables.get(i).getFinall());
		    syllables.get(i).replaceFinal(null);
		    syllables.get(i + 1).replaceInitial(newInit);

		    i = i + 1;
		}
	    }
	}

	return input;
    }

    // Rule 2
    static HangeulWord checkNeutralization(HangeulWord input) {
	List<HangeulSyllable> syllables = input.getSyllables();
	for (int i = 0; i < syllables.size(); i++) {
	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == null) {
		continue;
	    }

	    int jamo = currentSyllable.getFinall().getJamo();

	    if (jamo == 1 || jamo == 2 || jamo == 24) {
		currentSyllable.replaceFinal(HangeulChar.ofJamo(1, Types.FINAL));
	    }

	    if (jamo == 7 || jamo == 19 || jamo == 20 || jamo == 22 || jamo == 23 || jamo == 25 || jamo == 27) {
		currentSyllable.replaceFinal(HangeulChar.ofJamo(7, Types.FINAL));
	    }

	    if (jamo == 17 || jamo == 26) {
		currentSyllable.replaceFinal(HangeulChar.ofJamo(17, Types.FINAL));
	    }

	}

	return input;
    }

    // Rule 3
    public static String checkSimplificationOfFinalDoubleConsonant(String input) {

	char[] charArray = input.toCharArray();
	StringBuffer output = new StringBuffer();

	for (int i = 0; i < charArray.length; i++) {
	    String normalize = normalize(charArray[i]);
	    String substring = normalize.substring(2);

	    char[] charArray2 = normalize.toCharArray();
	    int eins = toJamo(charArray2[0]);
	    int zwei = toJamo(charArray2[1]);
	    int drei;

	    switch (substring) {
	    case "ᆪ": // (1) - only first and second not
		drei = 1;
		break;
	    case "ᆬ": // (1) - only first and second not
		drei = 4;
		break;
	    case "ᆳ": // (1) - only first and second not
		drei = 8;
		break;
	    case "ᆴ": // (1) - only first and second not
		drei = 8;
		break;
	    case "ᆹ": // (1) - only first and second not
		drei = 17;
		break;
	    case "ᆭ": // (2) - first is pronounced and the second transforms
		drei = 4;
		// TODO
		break;
	    case "ᆶ": // (2) - first is pronounced and the second transforms
		drei = 8;
		String normalize2 = normalize(charArray[i + 1]);
		normalize.substring(0, 1);

		// TODO
		break;
	    case "ᆱ": // (3) - first not, second pronounced
		drei = 16;
		break;
	    case "ᆵ": // (3) - first not, second pronounced
		drei = 26;
	    default:
		toJamo(charArray[2]);
		break;
	    }

	    // (2) - first is pronounced and the second transforms

	    // (3) - first not, second pronounced
	    // (4) - either first or second (exception to the rules)

	    // if (substring.equals("ᆪ") || substring.equals("ᆬ") ||
	    // substring.equals("ᆳ") || substring.equals("ᆴ")
	    // || substring.equals("ᆹ")) {
	    //
	    // }

	}

	return null;
    }

    // rule5
    public static String checkFortis(String input) {
	// HangeulUtilContract.isNotNull(input);
	StringBuffer output = new StringBuffer();

	char[] syllables = input.toCharArray();

	for (char syllable : syllables) {

	}

	return output.toString();
    }

    // rule6
    public static HangeulWord checkAspiration(HangeulWord input) {
	List<HangeulSyllable> syllables = input.getSyllables();

	for (int i = 0; i < syllables.size(); i++) {
	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getInitial().getJamo() == 18) {
		// TODO double final consonants beachten

		HangeulSyllable previousSyllable = syllables.get(i - 1);

		int prevJamo = previousSyllable.getFinall().getJamo();

		if (prevJamo == 1) {
		    previousSyllable.replaceFinal(null);
		    currentSyllable.replaceInitial(HangeulChar.ofJamo(15, Types.INITIAL));
		}

		if (prevJamo == 7) {
		    previousSyllable.replaceFinal(null);
		    currentSyllable.replaceInitial(HangeulChar.ofJamo(16, Types.INITIAL));
		}

		if (prevJamo == 17) {
		    previousSyllable.replaceFinal(null);
		    currentSyllable.replaceInitial(HangeulChar.ofJamo(17, Types.INITIAL));
		}

		if (prevJamo == 22) {
		    previousSyllable.replaceFinal(null);
		    currentSyllable.replaceInitial(HangeulChar.ofJamo(14, Types.INITIAL));
		}
	    }

	    if (currentSyllable.getFinall() != null) {
		if (currentSyllable.getFinall().getJamo() == 27) {
		    HangeulSyllable nextSyllable = syllables.get(i + 1);
		    int nextJamo = nextSyllable.getInitial().getJamo();

		    if (nextJamo == 0) {
			currentSyllable.replaceFinal(null);
			nextSyllable.replaceInitial(HangeulChar.ofJamo(15, Types.INITIAL));
		    }

		    if (nextJamo == 3) {
			currentSyllable.replaceFinal(null);
			nextSyllable.replaceInitial(HangeulChar.ofJamo(16, Types.INITIAL));
		    }

		    if (nextJamo == 7) {
			currentSyllable.replaceFinal(null);
			nextSyllable.replaceInitial(HangeulChar.ofJamo(17, Types.INITIAL));
		    }

		    if (nextJamo == 12) {
			currentSyllable.replaceFinal(null);
			nextSyllable.replaceInitial(HangeulChar.ofJamo(14, Types.INITIAL));
		    }
		}
	    }
	}

	return input;
    }

    // rule 7
    public static HangeulWord checkPalatalization(HangeulWord input) {

	List<HangeulSyllable> syllables = input.getSyllables();

	for (int i = 0; i < syllables.size(); i++) {

	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == null) {
		continue;
	    }

	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulChar finalConsonant = currentSyllable.getFinall();
	    HangeulSyllable nextSyllable = syllables.get(i + 1);

	    if (finalConsonant.getJamo() == 7) {
		if (nextSyllable.getInitial().getJamo() == 11 && nextSyllable.getMedial().getJamo() == 20) {
		    currentSyllable.replaceFinal(HangeulChar.ofJamo(22, Types.FINAL));
		}
	    }

	    if (finalConsonant.getJamo() == 25) {
		if (nextSyllable.getInitial().getJamo() == 18 && nextSyllable.getMedial().getJamo() == 20) {
		    currentSyllable.replaceFinal(HangeulChar.ofJamo(23, Types.FINAL));
		}
	    }
	}

	return input;
    }

    // rule 8
    public static HangeulWord checkHOmission(HangeulWord input) {
	// TODO gilt es auch für lh? jamo=15

	List<HangeulSyllable> syllables = input.getSyllables();
	for (int i = 0; i < syllables.size(); i++) {
	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == null) {
		continue;
	    }

	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulChar finalConsonant = currentSyllable.getFinall();
	    HangeulSyllable nextSyllable = syllables.get(i + 1);

	    if (finalConsonant.getJamo() == 27) {
		if (nextSyllable.getInitial().getJamo() == 11) {
		    currentSyllable.replaceFinal(null);
		}
	    } else if (finalConsonant.getJamo() == 6) {
		if (nextSyllable.getInitial().getJamo() == 11) {
		    currentSyllable.replaceFinal(HangeulChar.ofJamo(4, Types.FINAL));
		}
	    }
	}

	return input;
    }

    public static void main(String[] args) {
	// rule1
	System.out.println("#### RULE 1 ####");
	List<String> asList = Arrays.asList("꽃이", "옷을", "먹어요", "밥이", "부엌에", "닫아요", "문어", "마음에", "살아요");
	asList.stream().map(HangeulWord::of).map(PronounciationRules::checkLinking).forEach(System.out::println);

	// rule2
	System.out.println("#### RULE 2 ####");
	List<String> asList2 = Arrays.asList("국", "부엌", "밖", "곧", "다섯", "갔다", "빚", "빛", "끝", "하읗", "밥", "숲");
	asList2.stream().map(HangeulWord::of).map(PronounciationRules::checkNeutralization)
		.forEach(System.out::println);

	// rule6
	System.out.println("#### RULE 6 ####");
	List<String> asList6 = Arrays.asList("국화", "맏형", "입학", "앉히다", "놓구", "놓다", "놓지", "많다");
	asList6.stream().map(HangeulWord::of).map(PronounciationRules::checkAspiration).forEach(System.out::println);

	// rule7
	System.out.println("#### RULE 7 ####");
	// TODO: watch "굳히다"!!
	List<String> asList7 = Arrays.asList("굳이", "맏이", "해돋이", "같이", "끝이", "굳히다");
	asList7.stream().map(HangeulWord::of).map(PronounciationRules::checkPalatalization)
		.forEach(System.out::println);

	// rule8
	System.out.println("#### RULE 8 ####");
	List<String> asList8 = Arrays.asList("좋아요", "좋은", "촣을", "많아요", "많은", "많을");
	asList8.stream().map(HangeulWord::of).map(PronounciationRules::checkHOmission).forEach(System.out::println);

    }
}
