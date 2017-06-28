package de.snuk.hangeulj;

import static com.google.common.base.Preconditions.checkNotNull;
import static de.snuk.hangeulj.model.FinalConsonant.ㄱ;
import static de.snuk.hangeulj.model.FinalConsonant.ㄷ;
import static de.snuk.hangeulj.model.FinalConsonant.ㅂ;
import static de.snuk.hangeulj.model.FinalConsonant.ㅅ;
import static de.snuk.hangeulj.model.FinalConsonant.ㅈ;

import java.util.LinkedList;
import java.util.List;

import de.snuk.hangeulj.model.FinalConsonant;
import de.snuk.hangeulj.model.HangeulSyllable;
import de.snuk.hangeulj.model.HangeulWord;
import de.snuk.hangeulj.model.InitialConsonant;
import de.snuk.hangeulj.model.MedialVowel;

public final class PronounciationRules {

    private PronounciationRules() {
    }

    // Rule 1
    public static HangeulWord checkLinking(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {
	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (syllables.get(i).getFinall() != FinalConsonant.EMPTY) {
		HangeulSyllable nextSyllable = syllables.get(i + 1);

		if (nextSyllable.getInitial() == InitialConsonant.ㅇ) {
		    InitialConsonant newInit = HangeulUtil.convertFinalToInitial(currentSyllable.getFinall());
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.EMPTY));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(newInit));
		    i++;
		}
	    }
	}

	return HangeulWord.ofList(newSyllables);
    }

    // Rule 2
    public static HangeulWord checkNeutralization(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {
	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == FinalConsonant.EMPTY) {
		continue;
	    }

	    int jamo = currentSyllable.getFinall().getJamo();

	    if (jamo == 1 || jamo == 2 || jamo == 24) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(1)));
	    }

	    if (jamo == 7 || jamo == 19 || jamo == 20 || jamo == 22 || jamo == 23 || jamo == 25 || jamo == 27) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(7)));
	    }

	    if (jamo == 17 || jamo == 26) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(17)));
	    }

	}

	return HangeulWord.ofList(newSyllables);
    }

    // rule 3
    public static HangeulWord checkSimplificationOfFinalDoubleConsonant(HangeulWord input) {
	checkNotNull(input);
	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {

	    HangeulSyllable currentSyllable = syllables.get(i);
	    FinalConsonant finalConsonant = currentSyllable.getFinall();

	    if (finalConsonant == FinalConsonant.EMPTY) {
		continue;
	    }

	    if (finalConsonant == FinalConsonant.ㄳ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄱ));
	    }

	    if (finalConsonant == FinalConsonant.ㄵ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
	    }

	    if (finalConsonant == FinalConsonant.ㄽ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
	    }

	    if (finalConsonant == FinalConsonant.ㄾ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
	    }

	    if (finalConsonant == FinalConsonant.ㅄ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅂ));
	    }

	    // (2) - first is pronounced and the second transforms
	    if (finalConsonant == FinalConsonant.ㄶ) {
		HangeulSyllable nextSyllable = syllables.get(i + 1);
		InitialConsonant nextInitial = nextSyllable.getInitial();

		if (nextInitial == InitialConsonant.ㄱ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ㅋ));
		}

		if (nextInitial == InitialConsonant.ㄷ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ㅌ));
		}

		if (nextInitial == InitialConsonant.ㅈ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ㅊ));
		}

		if (nextInitial == InitialConsonant.ㄴ || nextInitial == InitialConsonant.ㅅ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
		}
	    }

	    if (finalConsonant == FinalConsonant.ㅀ) {
		HangeulSyllable nextSyllable = syllables.get(i + 1);
		InitialConsonant nextInitial = nextSyllable.getInitial();

		if (nextInitial == InitialConsonant.ㄱ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ㅋ));
		}

		if (nextInitial == InitialConsonant.ㄷ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ㅌ));
		}

		if (nextInitial == InitialConsonant.ㅈ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
		    newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ㅊ));
		}

		if (nextInitial == InitialConsonant.ㄴ || nextInitial == InitialConsonant.ㅅ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
		}
	    }

	    if (finalConsonant == FinalConsonant.ㅀ) {

	    }

	    // (3) - first not, second pronounced
	    if (finalConsonant == FinalConsonant.ㄻ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅁ));
	    }
	    if (finalConsonant == FinalConsonant.ㄿ) {
		newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅍ));
	    }

	    // (4) - either first or second (exception to the rules)
	    // TODO
	}

	return HangeulWord.ofList(newSyllables);
    }

    // rule4
    public static HangeulWord checkNasalization(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {

	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == FinalConsonant.EMPTY) {
		continue;
	    }

	    FinalConsonant currentFinal = currentSyllable.getFinall();
	    InitialConsonant nextInitial = syllables.get(i + 1).getInitial();

	    if (nextInitial == InitialConsonant.ㄴ || nextInitial == InitialConsonant.ㅁ
		    || nextInitial == InitialConsonant.ㅇ) {

		if (currentFinal == FinalConsonant.ㄱ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅇ));
		}

		if (currentFinal == FinalConsonant.ㄷ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
		}

		if (currentFinal == FinalConsonant.ㅂ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅁ));
		}
	    }
	}

	return HangeulWord.ofList(newSyllables);
    }

    // rule5
    public static HangeulWord checkFortis(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {

	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulSyllable currentSyllable = syllables.get(i);
	    FinalConsonant currentFinal = currentSyllable.getFinall();

	    // TODO: sind das alle obstruents?

	    if (currentFinal == ㄱ || currentFinal == ㄷ || currentFinal == ㅂ || currentFinal == ㅅ || currentFinal == ㅈ) {
		HangeulSyllable nextSyllable = syllables.get(i + 1);
		InitialConsonant nextInitial = nextSyllable.getInitial();

		if (nextInitial == InitialConsonant.ㄱ || nextInitial == InitialConsonant.ㄷ
			|| nextInitial == InitialConsonant.ㅂ || nextInitial == InitialConsonant.ㅅ
			|| nextInitial == InitialConsonant.ㅈ) {
		    newSyllables.set(i + 1,
			    nextSyllable.replaceInitial(InitialConsonant.ofJamo(nextInitial.getJamo() + 1)));
		}
	    }
	}

	return input;
    }

    // rule6
    public static HangeulWord checkAspiration(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {
	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getInitial().getJamo() == 18) {
		if (i == 0) {
		    continue;
		}
		HangeulSyllable previousSyllable = syllables.get(i - 1);

		int prevJamo = previousSyllable.getFinall().getJamo();

		if (prevJamo == 1) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.EMPTY));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(15)));
		}

		if (prevJamo == 7) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.EMPTY));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(16)));
		}

		if (prevJamo == 17) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.EMPTY));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(17)));
		}

		if (prevJamo == 22) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.EMPTY));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(14)));
		}

		if (prevJamo == 5) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.ofJamo(4)));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(14)));
		}

		if (prevJamo == 9) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.ofJamo(8)));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(15)));
		}

		if (prevJamo == 11) {
		    newSyllables.set(i - 1, previousSyllable.replaceFinal(FinalConsonant.ofJamo(8)));
		    newSyllables.set(i, currentSyllable.replaceInitial(InitialConsonant.ofJamo(17)));
		}
	    }

	    if (currentSyllable.getFinall() != FinalConsonant.EMPTY) {

		// ㅀ
		if (currentSyllable.getFinall().getJamo() == 15) {
		    HangeulSyllable nextSyllable = syllables.get(i + 1);
		    int nextJamo = nextSyllable.getInitial().getJamo();

		    if (nextJamo == 0) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(8)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(15)));
		    }

		    if (nextJamo == 3) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(8)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(16)));
		    }

		    if (nextJamo == 7) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(8)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(17)));
		    }

		    if (nextJamo == 12) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(8)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(14)));
		    }
		}

		// ㄶ
		if (currentSyllable.getFinall().getJamo() == 6) {
		    HangeulSyllable nextSyllable = syllables.get(i + 1);
		    int nextJamo = nextSyllable.getInitial().getJamo();

		    if (nextJamo == 0) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(4)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(15)));
		    }

		    if (nextJamo == 3) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(4)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(16)));
		    }

		    if (nextJamo == 7) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(4)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(17)));
		    }

		    if (nextJamo == 12) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ofJamo(4)));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(14)));
		    }
		}

		// ㅎ
		if (currentSyllable.getFinall().getJamo() == 27) {
		    HangeulSyllable nextSyllable = syllables.get(i + 1);
		    int nextJamo = nextSyllable.getInitial().getJamo();

		    if (nextJamo == 0) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.EMPTY));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(15)));
		    }

		    if (nextJamo == 3) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.EMPTY));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(16)));
		    }

		    if (nextJamo == 7) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.EMPTY));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(17)));
		    }

		    if (nextJamo == 12) {
			newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.EMPTY));
			newSyllables.set(i + 1, nextSyllable.replaceInitial(InitialConsonant.ofJamo(14)));
		    }
		}
	    }
	}

	return input;
    }

    // rule 7
    public static HangeulWord checkPalatalization(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllabes = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {
	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == FinalConsonant.EMPTY) {
		continue;
	    }

	    FinalConsonant finalConsonant = currentSyllable.getFinall();
	    InitialConsonant nextInitial = syllables.get(i + 1).getInitial();
	    MedialVowel nextMedial = syllables.get(i + 1).getMedial();

	    if (finalConsonant == FinalConsonant.ㄷ) {
		if (nextInitial == InitialConsonant.ㅇ && nextMedial == MedialVowel.ㅣ) {
		    newSyllabes.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅈ));
		}
	    }

	    if (finalConsonant == FinalConsonant.ㅌ) {
		if (nextInitial == InitialConsonant.ㅎ && nextMedial == MedialVowel.ㅣ) {
		    newSyllabes.set(i, currentSyllable.replaceFinal(FinalConsonant.ㅊ));
		}
	    }
	}

	return HangeulWord.ofList(newSyllabes);
    }

    // rule 8
    public static HangeulWord checkHOmission(HangeulWord input) {
	checkNotNull(input);

	List<HangeulSyllable> syllables = input.getSyllables();
	List<HangeulSyllable> newSyllables = new LinkedList<>(syllables);

	for (int i = 0; i < syllables.size(); i++) {
	    if (i == syllables.size() - 1) {
		break;
	    }

	    HangeulSyllable currentSyllable = syllables.get(i);

	    if (currentSyllable.getFinall() == FinalConsonant.EMPTY) {
		continue;
	    }

	    FinalConsonant finalConsonant = currentSyllable.getFinall();
	    InitialConsonant nextInitial = syllables.get(i + 1).getInitial();

	    if (finalConsonant == FinalConsonant.ㅎ) {
		if (nextInitial == InitialConsonant.ㅇ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.EMPTY));
		}
	    } else if (finalConsonant == FinalConsonant.ㄶ) {
		if (nextInitial == InitialConsonant.ㅇ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄴ));
		}
	    } else if (finalConsonant == FinalConsonant.ㅀ) {
		if (nextInitial == InitialConsonant.ㅇ) {
		    newSyllables.set(i, currentSyllable.replaceFinal(FinalConsonant.ㄹ));
		}
	    }
	}

	return HangeulWord.ofList(newSyllables);
    }

    public static void main(String[] args) {
	// // rule1
	// System.out.println("#### RULE 1 ####");
	// Arrays.asList("꽃이", "옷을", "먹어요", "밥이", "부엌에", "닫아요", "문어", "마음에",
	// "살아요").stream().map(HangeulWord::ofString)
	// .map(PronounciationRules::checkLinking).forEach(System.out::println);

	// // rule2
	// System.out.println("#### RULE 2 ####");
	// Arrays.asList("국", "부엌", "밖", "곧", "다섯", "갔다", "빚", "빛", "끝", "하읗",
	// "밥", "숲").stream()
	// .map(HangeulWord::ofString).map(PronounciationRules::checkNeutralization).forEach(System.out::println);

	// // rule 3
	// System.out.println("#### RULE 3 (1) ####");
	// // TODO: watch 외곬 !!
	// Arrays.asList("넋", "앉다", "외곬", "핥다", "값",
	// "몫").stream().map(HangeulWord::of)
	// .map(PronounciationRules::checkSimplificationOfFinalDoubleConsonant).forEach(System.out::println);

	// System.out.println("#### RULE 3 (2) ####");
	// Arrays.asList("많고", "많다", "많지", "싫고", "싫다",
	// "싫지").stream().map(HangeulWord::ofString)
	// .map(PronounciationRules::checkSimplificationOfFinalDoubleConsonant).forEach(System.out::println);

	// System.out.println("#### RULE 3 (3) ####");
	// Arrays.asList("삶", "굶다", "읊다", "읊지").stream().map(HangeulWord::of)
	// .map(PronounciationRules::checkSimplificationOfFinalDoubleConsonant).forEach(System.out::println);

	// // rule 4
	// System.out.println("#### RULE 4 ####");
	// // TODO: watch 앞마당 and 있는 !!
	// Arrays.asList("앞마당", "믿는다", "한국말", "입는", "있는",
	// "학년").stream().map(HangeulWord::ofString)
	// .map(PronounciationRules::checkNasalization).forEach(System.out::println);

	// // rule5
	// System.out.println("#### RULE 5 ####");
	// // TODO: watch 꽃밭 !!
	// Arrays.asList("학교", "받다", "꽃밭", "국수", "국자",
	// "책상").stream().map(HangeulWord::of)
	// .map(PronounciationRules::checkFortis).forEach(System.out::println);
	//
	// // rule6
	// System.out.println("#### RULE 6 ####");
	// List<String> asList6 = Arrays.asList("국화", "맏형", "입학", "앉히다", "놓구",
	// "놓다", "놓지", "많다");
	// asList6.stream().map(HangeulWord::of).map(PronounciationRules::checkAspiration).forEach(System.out::println);
	//
	// // rule7
	// System.out.println("#### RULE 7 ####");
	// // TODO: watch "굳히다"!!
	// List<String> asList7 = Arrays.asList("굳이", "맏이", "해돋이", "같이", "끝이",
	// "굳히다");
	// asList7.stream().map(HangeulWord::of).map(PronounciationRules::checkPalatalization)
	// .forEach(System.out::println);
	//
	// // rule8
	// System.out.println("#### RULE 8 ####");
	// List<String> asList8 = Arrays.asList("좋아요", "좋은", "촣을", "많아요", "많은",
	// "많을");
	// asList8.stream().map(HangeulWord::of).map(PronounciationRules::checkHOmission).forEach(System.out::println);
    }
}
