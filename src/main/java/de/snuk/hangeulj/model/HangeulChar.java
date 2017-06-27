package de.snuk.hangeulj.model;

import de.snuk.hangeulj.HangeulUtil;

public class HangeulChar {

    private int jamo;
    private String unicode;
    private Types type;

    private HangeulChar(int jamo, String unicode, Types type) {
	this.jamo = jamo;
	this.unicode = unicode;
	this.type = type;
    }

    public int getJamo() {
	return jamo;
    }

    public String getUnicode() {
	return unicode;
    }

    public Types getType() {
	return type;
    }

    public static HangeulChar of(char normalizedSyllable) {
	int jamo = HangeulUtil.toJamo(normalizedSyllable);
	String unicode = Integer.toHexString(normalizedSyllable);
	Types type = HangeulUtil.getType(normalizedSyllable);

	HangeulChar c = new HangeulChar(jamo, unicode, type);

	return c;
    }

    public static HangeulChar ofJamo(int jamo, Types type) {
	String unicode = HangeulUtil.toUnicode(jamo, type);
	HangeulChar c = new HangeulChar(jamo, unicode, type);

	return c;
    }

    public static HangeulChar ofUnicode(String unicode) {
	int parseInt = Integer.parseInt(unicode, 16);

	int jamo = HangeulUtil.toJamo((char) parseInt);
	Types type = HangeulUtil.getType((char) parseInt);

	HangeulChar c = new HangeulChar(jamo, unicode, type);

	return c;
    }

    @Override
    public String toString() {
	return "HangeulChar [jamo=" + jamo + ", unicode=" + unicode + ", type=" + type + "]";
    }
}
