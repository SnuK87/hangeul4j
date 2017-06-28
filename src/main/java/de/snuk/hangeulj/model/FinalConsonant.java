package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;

public enum FinalConsonant {

    EMPTY(0), ㄱ(1), ㄲ(2), ㄳ(3), ㄴ(4), ㄵ(5), ㄶ(6), ㄷ(7), ㄹ(8), ㄺ(9), ㄻ(10), ㄼ(11), ㄽ(12), ㄾ(13), ㄿ(14), ㅀ(15), ㅁ(16), ㅂ(
	    17), ㅄ(18), ㅅ(19), ㅆ(20), ㅇ(21), ㅈ(22), ㅊ(23), ㅋ(24), ㅌ(25), ㅍ(26), ㅎ(27);

    private static Map<Integer, FinalConsonant> map = new HashMap<>();

    static {
	for (FinalConsonant value : FinalConsonant.values()) {
	    map.put(value.getJamo(), value);
	}
    }

    private final int jamo;

    private FinalConsonant(int jamo) {
	this.jamo = jamo;
    }

    public int getJamo() {
	return jamo;
    }

    public static FinalConsonant ofJamo(int jamo) {
	checkArgument(jamo >= 0 && jamo <= 27);
	return map.get(jamo);
    }

    public String getUnicode() {

	String unicode = "";

	// TODO was für empty returnen?
	if (jamo == 0) {
	    return unicode;
	}

	int input = jamo + 7;
	String hex = Integer.toHexString(input);
	if (input <= 15) {
	    unicode = "11A" + hex;
	} else if (input <= 31) {
	    unicode = "11B" + hex.charAt(1);
	} else {
	    unicode = "11C" + hex.charAt(1);
	}

	return unicode;
    }
}
