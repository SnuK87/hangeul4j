package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;

public enum MedialVowel {

    ㅏ(0), ㅐ(1), ㅑ(2), ㅒ(3), ㅓ(4), ㅔ(5), ㅕ(6), ㅖ(7), ㅗ(8), ㅘ(9), ㅙ(10), ㅚ(11), ㅛ(12), ㅜ(13), ㅝ(14), ㅞ(15), ㅟ(16), ㅠ(
	    17), ㅡ(18), ㅢ(19), ㅣ(20);

    private static Map<Integer, MedialVowel> map = new HashMap<>();

    static {
	for (MedialVowel value : MedialVowel.values()) {
	    map.put(value.getJamo(), value);
	}
    }

    private final int jamo;

    private MedialVowel(int value) {
	this.jamo = value;
    }

    public int getJamo() {
	return jamo;
    }

    public static MedialVowel ofJamo(int jamo) {
	checkArgument(jamo >= 0 && jamo <= 20);
	return map.get(jamo);
    }

    public String getUnicode() {
	String retVal = "";
	int input = jamo + 1;
	String hex = Integer.toHexString(input);

	if (input <= 15) {
	    retVal = "116" + hex;
	} else {
	    retVal = "117" + hex.charAt(1);
	}

	return retVal;
    }
}
