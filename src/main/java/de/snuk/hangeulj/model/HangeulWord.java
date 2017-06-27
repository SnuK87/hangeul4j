package de.snuk.hangeulj.model;

import java.util.LinkedList;
import java.util.List;

public class HangeulWord {

    private List<HangeulSyllable> syllables;

    private HangeulWord(String input) {
	syllables = new LinkedList<>();

	input.codePoints().mapToObj(i -> {
	    char c = (char) i;
	    return String.valueOf(c);
	}).forEach(s -> {
	    syllables.add(HangeulSyllable.ofString(s));
	});
    }

    private HangeulWord(HangeulSyllable... syllables) {
	this.syllables = new LinkedList<>();

	for (HangeulSyllable hangeulSyllable : syllables) {
	    this.syllables.add(hangeulSyllable);
	}
    }

    public static HangeulWord of(String input) {
	return new HangeulWord(input);
    }

    public static HangeulWord ofSyllables(HangeulSyllable... syllables) {
	return new HangeulWord(syllables);
    }

    public List<HangeulSyllable> getSyllables() {
	return syllables;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	syllables.stream().forEach(s -> sb.append(s.toChar()));
	return sb.toString();
    }
}
