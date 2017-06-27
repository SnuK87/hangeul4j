package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkArgument;

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

	checkArgument(jamo >= 0, "Unable to determine jamo Value for given char: " + normalizedSyllable);

	String unicode = Integer.toHexString(normalizedSyllable);
	Types type = HangeulUtil.getType(normalizedSyllable).orElseThrow(
		() -> new IllegalArgumentException("Unable to Determine type by given char: " + normalizedSyllable));
	;

	HangeulChar c = new HangeulChar(jamo, unicode, type);

	return c;
    }

    public static HangeulChar ofJamo(int jamo, Types type) {
	String unicode = HangeulUtil.toUnicode(jamo, type);
	HangeulChar c = new HangeulChar(jamo, unicode, type);

	return c;
    }

    public static HangeulChar ofUnicode(String unicode) throws IllegalArgumentException {
	int parseInt = Integer.parseInt(unicode, 16);

	int jamo = HangeulUtil.toJamo((char) parseInt);
	Types type = HangeulUtil.getType((char) parseInt).orElseThrow(
		() -> new IllegalArgumentException("Unable to Determine type by given unicode: " + unicode));

	HangeulChar c = new HangeulChar(jamo, unicode, type);

	return c;
    }

    @Override
    public String toString() {
	return "HangeulChar [jamo=" + jamo + ", unicode=" + unicode + ", type=" + type + "]";
    }
}
