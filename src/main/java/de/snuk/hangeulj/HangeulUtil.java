package de.snuk.hangeulj;

import static de.snuk.hangeulj.HangeulUtilContract.isValueBetween;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;

import de.snuk.hangeulj.model.HangeulChar;
import de.snuk.hangeulj.model.Types;

public class HangeulUtil {

    private static Map<String, String> finalToInitialMap;

    static {
	finalToInitialMap = new HashMap<>();
	finalToInitialMap.put("11a8", "1100");
	finalToInitialMap.put("11a9", "1101");
	finalToInitialMap.put("11ab", "1102");
	finalToInitialMap.put("11ae", "1103");
	finalToInitialMap.put("11af", "1105");
	finalToInitialMap.put("11b7", "1106");
	finalToInitialMap.put("11b8", "1107");
	finalToInitialMap.put("11ba", "1109");
	finalToInitialMap.put("11bb", "110a");
	finalToInitialMap.put("11bc", "110b");
	finalToInitialMap.put("11bd", "110c");
	finalToInitialMap.put("11be", "110e");
	finalToInitialMap.put("11bf", "110f");
	finalToInitialMap.put("11c0", "1110");
	finalToInitialMap.put("11b1", "1111");
	finalToInitialMap.put("11b2", "1112");
    }

    public static String normalize(char input) {

	if (input == '\u0000') {
	    throw new NullPointerException("input must never be null.");
	}

	return Normalizer.normalize(String.valueOf(input), Form.NFD);
    }

    public static int toJamo(char input) {
	String hexString = Integer.toHexString(input);
	int retVal = -1;
	int parseInt = Integer.parseInt(hexString.substring(3), 16);

	// initial 1100 bis 1112
	if (hexString.startsWith("111")) {
	    int start = 16;
	    retVal = start + parseInt;
	}

	if (hexString.startsWith("110")) {
	    int start = 0;
	    retVal = start + parseInt;
	}

	// medial 1161 bis 1175
	if (hexString.startsWith("116")) {
	    int start = 0;
	    retVal = start + parseInt - 1;
	}

	if (hexString.startsWith("117")) {
	    int start = 16;
	    retVal = start + parseInt - 1;
	}

	// final 11A8 bis 11C2
	if (hexString.startsWith("11a")) {
	    int start = 0;
	    retVal = start + parseInt - 7;
	}

	if (hexString.startsWith("11b")) {
	    int start = 16;
	    retVal = start + parseInt - 7;
	}

	if (hexString.startsWith("11c")) {
	    int start = 32;
	    retVal = start + parseInt - 7;
	}

	return retVal;
    }

    // jamo to unicode
    public static String toUnicode(int input, Types type) {

	String retVal = "";
	String hex;

	switch (type) {
	case INITIAL:
	    hex = Integer.toHexString(input);
	    if (input <= 15) {
		retVal = "110" + hex;
	    } else {
		retVal = "11" + hex;
	    }
	    break;
	case MEDIAL:
	    input = input + 1;
	    hex = Integer.toHexString(input);

	    if (input <= 15) {
		retVal = "116" + hex;
	    } else {
		retVal = "117" + hex.charAt(1);
	    }
	    break;
	case FINAL:
	    // TODO return val bei 0?
	    if (input > 0) {
		input = input + 7;
		hex = Integer.toHexString(input);
		if (input <= 15) {
		    retVal = "11A" + hex;
		} else if (input <= 31) {
		    retVal = "11B" + hex.charAt(1);
		} else {
		    retVal = "11C" + hex.charAt(1);
		}
	    }

	    break;
	default:
	    break;

	}

	return retVal;

	// String hexString = Integer.toHexString(input);
	// int retVal = -1;
	// int parseInt = Integer.parseInt(hexString.substring(3), 16);
	//
	// // initial 1100 bis 1112
	// if (hexString.startsWith("111")) {
	// int start = 16;
	// retVal = start + parseInt;
	// }
	//
	// if (hexString.startsWith("110")) {
	// int start = 0;
	// retVal = start + parseInt;
	// }
	//
	// // medial 1161 bis 1175
	// if (hexString.startsWith("116")) {
	// int start = 0;
	// retVal = start + parseInt - 1;
	// }
	//
	// if (hexString.startsWith("117")) {
	// int start = 16;
	// retVal = start + parseInt - 1;
	// }
	//
	// // final 11A8 bis 11C2
	// if (hexString.startsWith("11a")) {
	// int start = 0;
	// retVal = start + parseInt - 7;
	// }
	//
	// if (hexString.startsWith("11b")) {
	// int start = 16;
	// retVal = start + parseInt - 7;
	// }
	//
	// if (hexString.startsWith("11c")) {
	// int start = 32;
	// retVal = start + parseInt - 7;
	// }
	//
	// return retVal;
    }

    public static Types getType(char input) {
	String hexString = Integer.toHexString(input);

	// initial 1100 bis 1112
	if (hexString.startsWith("111")) {
	    return Types.INITIAL;
	}

	if (hexString.startsWith("110")) {
	    return Types.INITIAL;
	}

	// medial 1161 bis 1175
	if (hexString.startsWith("116")) {
	    return Types.MEDIAL;
	}

	if (hexString.startsWith("117")) {
	    return Types.MEDIAL;
	}

	// final 11A8 bis 11C2
	if (hexString.startsWith("11a")) {
	    return Types.FINAL;
	}

	if (hexString.startsWith("11b")) {
	    return Types.FINAL;
	}

	if (hexString.startsWith("11c")) {
	    return Types.FINAL;
	}

	return null;
    }

    /**
     * Composes a syllable of the given jamo values.
     *
     * @param jamoInitial
     * @param jamoMedial
     * @param jamoFinal
     * @return
     */
    public static char composeSyllable(int jamoInitial, int jamoMedial, int jamoFinal) {
	isValueBetween(jamoInitial, 0, 18, "jamoInitial");
	isValueBetween(jamoMedial, 0, 20, "jamoMedial");
	isValueBetween(jamoFinal, 0, 27, "jamoFinal");

	int composed = ((jamoInitial * 588) + (jamoMedial * 28) + jamoFinal) + 44032;

	return (char) composed;
    }

    /**
     * Converts Final consonant Char to Initial consonant hexString
     *
     * @param input
     * @return
     */
    public static char convertFinalToInitial(char input) {
	String string = finalToInitialMap.get(Integer.toHexString(input));

	int parseInt = 0;

	if (string == null) {
	    System.out.println("Unable to convert " + input + " to initial consonant");
	} else {
	    parseInt = Integer.parseInt(string, 16);
	}

	return (char) parseInt;
    }

    public static HangeulChar convertFinalToInitialString(HangeulChar input) {
	String unicode = input.getUnicode().toLowerCase();
	String string = finalToInitialMap.get(unicode);
	int parseInt = Integer.parseInt(string, 16);

	HangeulChar of = HangeulChar.of((char) parseInt);

	return of;
    }

    public static boolean doesNextSyllableStartsWithJamo(int jamo, String nextSyllable) {
	if (toJamo(nextSyllable.charAt(0)) == jamo) {
	    return true;
	}

	return false;
    }

    public static boolean doesNextSyllablesMedialIsJamo(int jamo, String nextSyllable) {
	if (toJamo(nextSyllable.charAt(1)) == jamo) {
	    return true;
	}

	return false;
    }
}
