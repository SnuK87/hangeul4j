package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class HangeulWord {

    private List<HangeulSyllable> syllables = new LinkedList<>();

    private HangeulWord(String input) {
	checkNotNull(input);

	input.codePoints().mapToObj(i -> {
	    return (char) i;
	}).map(HangeulSyllable::of).forEach(syllables::add);
    }

    private HangeulWord(HangeulSyllable syllable, HangeulSyllable... syllables) {
	this.syllables.add(syllable);

	for (HangeulSyllable hangeulSyllable : syllables) {
	    this.syllables.add(hangeulSyllable);
	}
    }

    private HangeulWord(List<HangeulSyllable> list) {
	syllables = list;
    }

    public static HangeulWord ofString(String input) {
	return new HangeulWord(input);
    }

    public static HangeulWord ofSyllables(HangeulSyllable syllable, HangeulSyllable... syllables) {
	checkNotNull(syllable);
	return new HangeulWord(syllable, syllables);
    }

    public static HangeulWord ofList(List<HangeulSyllable> list) {
	return new HangeulWord(list);
    }

    public List<HangeulSyllable> getSyllables() {
	return ImmutableList.copyOf(syllables);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	syllables.stream().forEach(s -> sb.append(s.toChar()));
	return sb.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((syllables == null) ? 0 : syllables.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	HangeulWord other = (HangeulWord) obj;
	if (syllables == null) {
	    if (other.syllables != null) {
		return false;
	    }
	} else if (!syllables.equals(other.syllables)) {
	    return false;
	}
	return true;
    }

    // TODO: equals / hashcode
}
