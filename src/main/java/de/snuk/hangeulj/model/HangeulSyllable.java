package de.snuk.hangeulj.model;

import de.snuk.hangeulj.HangeulUtil;

public class HangeulSyllable {

    private HangeulChar initial;
    private HangeulChar medial;
    private HangeulChar finall;

    public HangeulSyllable(HangeulChar initial, HangeulChar medial) {
	this.initial = initial;
	this.medial = medial;
    }

    public HangeulSyllable(HangeulChar initial, HangeulChar medial, HangeulChar finall) {
	this.initial = initial;
	this.medial = medial;
	this.finall = finall;
    }

    public static HangeulSyllable ofString(String input) {
	// string lenght = 1
	String normalize = HangeulUtil.normalize(input.charAt(0));

	if (normalize.length() == 2) {
	    return new HangeulSyllable(HangeulChar.of(normalize.charAt(0)), HangeulChar.of(normalize.charAt(1)));
	}

	return new HangeulSyllable(HangeulChar.of(normalize.charAt(0)), HangeulChar.of(normalize.charAt(1)),
		HangeulChar.of(normalize.charAt(2)));
    }

    public HangeulChar getInitial() {
	return initial;
    }

    public HangeulChar getMedial() {
	return medial;
    }

    public HangeulChar getFinall() {
	return finall;
    }

    public void replaceInitial(HangeulChar newInitial) {
	// TODO check:
	initial = newInitial;
    }

    public void replaceFinal(HangeulChar newFinal) {
	// TODO check:
	finall = newFinal;
    }

    public char toChar() {
	return HangeulUtil.composeSyllable(initial.getJamo(), medial.getJamo(), finall != null ? finall.getJamo() : 0);
    }

    @Override
    public String toString() {
	return "HangeulSyllable [initial=" + initial + ", medial=" + medial + ", finall=" + finall + "]";
    }
}
