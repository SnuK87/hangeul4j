package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkNotNull;

import de.snuk.hangeulj.HangeulUtil;

public class HangeulSyllable {

    private InitialConsonant initial;
    private MedialVowel medial;
    private FinalConsonant finall;

    private HangeulSyllable(InitialConsonant initial, MedialVowel medial, FinalConsonant finall) {
	this.initial = initial;
	this.medial = medial;
	this.finall = finall;
    }

    public static HangeulSyllable of(char input) {
	String normalize = HangeulUtil.normalize(input);

	int jamoInit = HangeulUtil.toJamo(normalize.charAt(0));
	int jamoMedial = HangeulUtil.toJamo(normalize.charAt(1));

	if (normalize.length() == 2) {
	    return new HangeulSyllable(InitialConsonant.ofJamo(jamoInit), MedialVowel.ofJamo(jamoMedial),
		    FinalConsonant.EMPTY);
	}

	int jamoFinal = HangeulUtil.toJamo(normalize.charAt(2));

	return new HangeulSyllable(InitialConsonant.ofJamo(jamoInit), MedialVowel.ofJamo(jamoMedial),
		FinalConsonant.ofJamo(jamoFinal));
    }

    public InitialConsonant getInitial() {
	return initial;
    }

    public MedialVowel getMedial() {
	return medial;
    }

    public FinalConsonant getFinall() {
	return finall;
    }

    public HangeulSyllable replaceInitial(InitialConsonant newInitial) {
	checkNotNull(newInitial, "Initial consosant can't be replaced by null.");
	// initial = newInitial;

	return new HangeulSyllable(newInitial, medial, finall);
    }

    public HangeulSyllable replaceFinal(FinalConsonant newFinal) {
	checkNotNull(newFinal, "Final consosant can't be replaced by null. Consider using FinalConsonant.EMPTY");
	// finall = newFinal;

	return new HangeulSyllable(initial, medial, newFinal);
    }

    public char toChar() {
	return HangeulUtil.composeSyllable(initial.getJamo(), medial.getJamo(), finall != null ? finall.getJamo() : 0);
    }

    @Override
    public String toString() {
	return "HangeulSyllable [initial=" + initial + ", medial=" + medial + ", finall=" + finall + "]";
    }

    // TODO equals / hashcode
}
