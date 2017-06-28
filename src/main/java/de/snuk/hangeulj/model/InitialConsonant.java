package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;

public enum InitialConsonant {

    ㄱ(0), ㄲ(1), ㄴ(2), ㄷ(3), ㄸ(4), ㄹ(5), ㅁ(6), ㅂ(7), ㅃ(8), ㅅ(9), ㅆ(10), ㅇ(11), ㅈ(12), ㅉ(13), ㅊ(14), ㅋ(15), ㅌ(16), ㅍ(
	    17), ㅎ(18);

    private static Map<Integer, InitialConsonant> map = new HashMap<>();

    static {
	for (InitialConsonant value : InitialConsonant.values()) {
	    map.put(value.getJamo(), value);
	}
    }

    private final int jamo;

    private InitialConsonant(int jamo) {
	this.jamo = jamo;
    }

    public int getJamo() {
	return jamo;
    }

    public static InitialConsonant ofJamo(int jamo) {
	checkArgument(jamo >= 0 && jamo <= 18);
	return map.get(jamo);
    }

    public String getUnicode() {
	String unicode = "";
	String hex = Integer.toHexString(jamo);

	if (jamo <= 15) {
	    unicode = "110" + hex;
	} else {
	    unicode = "11" + hex;
	}

	return unicode;
    }
}