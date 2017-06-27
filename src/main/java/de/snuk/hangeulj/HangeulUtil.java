package de.snuk.hangeulj;

import static com.google.common.base.Preconditions.checkArgument;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
	return Normalizer.normalize(String.valueOf(input), Form.NFD);
    }

    public static int toJamo(char input) {
	String hexString = Integer.toHexString(input);
	int decimal = Integer.parseInt(hexString, 16);
	int retVal = -1;

	// initial
	if (decimal >= 4352 && decimal <= 4370) {
	    retVal = decimal - 4352;
	}

	// medial
	if (decimal >= 4449 && decimal <= 4469) {
	    retVal = decimal - 4449;
	}

	// final
	if (decimal >= 4520 && decimal <= 4546) {
	    retVal = decimal - 4520 + 1;
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
    }

    public static Optional<Types> getType(char input) {
	String hexString = Integer.toHexString(input);
	int decimal = Integer.parseInt(hexString, 16);
	Optional<Types> retVal = Optional.empty();

	if (decimal >= 4352 && decimal <= 4370) {
	    retVal = Optional.of(Types.INITIAL);
	}

	if (decimal >= 4449 && decimal <= 4469) {
	    retVal = Optional.of(Types.MEDIAL);
	}

	if (decimal >= 4520 && decimal <= 4546) {
	    retVal = Optional.of(Types.FINAL);
	}

	return retVal;
    }

    public static void main(String[] args) {
	char a = 'ᄂ';
	char b = 'ᄦ';

	Optional<Types> type = getType(a);
	Optional<Types> type2 = getType(b);

	System.out.println(type.orElseThrow(() -> new NullPointerException()));
	System.out.println(type2.orElseThrow(() -> new NullPointerException()));

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
	checkArgument(jamoInitial >= 0 && jamoInitial <= 18, "Invalid Argument for jamoInitial");
	checkArgument(jamoMedial >= 0 && jamoMedial <= 20, "Invalid Argument for jamoMedial");
	checkArgument(jamoFinal >= 0 && jamoFinal <= 27, "Invalid Argument for jamoFinal");

	int composed = ((jamoInitial * 588) + (jamoMedial * 28) + jamoFinal) + 44032;

	return (char) composed;
    }

    /**
     * Converts Final consonant to Initial consonant
     *
     * @param input
     * @return
     */
    public static HangeulChar convertFinalToInitialString(HangeulChar input) {
	String unicode = input.getUnicode().toLowerCase();
	String string = finalToInitialMap.get(unicode);
	int parseInt = Integer.parseInt(string, 16);

	HangeulChar of = HangeulChar.of((char) parseInt);

	return of;
    }
}
