package de.snuk.hangeulj;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;

import de.snuk.hangeulj.model.FinalConsonant;
import de.snuk.hangeulj.model.InitialConsonant;

public class HangeulUtil {

    private static Map<FinalConsonant, InitialConsonant> finalToInitialMap;

    static {
	finalToInitialMap = new HashMap<>();
	finalToInitialMap.put(FinalConsonant.ㄱ, InitialConsonant.ㄱ);
	finalToInitialMap.put(FinalConsonant.ㄲ, InitialConsonant.ㄲ);
	finalToInitialMap.put(FinalConsonant.ㄴ, InitialConsonant.ㄴ);
	finalToInitialMap.put(FinalConsonant.ㄷ, InitialConsonant.ㄷ);
	finalToInitialMap.put(FinalConsonant.ㄹ, InitialConsonant.ㄹ);
	finalToInitialMap.put(FinalConsonant.ㅁ, InitialConsonant.ㅁ);
	finalToInitialMap.put(FinalConsonant.ㅂ, InitialConsonant.ㅂ);
	finalToInitialMap.put(FinalConsonant.ㅅ, InitialConsonant.ㅅ);
	finalToInitialMap.put(FinalConsonant.ㅆ, InitialConsonant.ㅆ);
	finalToInitialMap.put(FinalConsonant.ㅇ, InitialConsonant.ㅇ);
	finalToInitialMap.put(FinalConsonant.ㅈ, InitialConsonant.ㅈ);
	finalToInitialMap.put(FinalConsonant.ㅊ, InitialConsonant.ㅊ);
	finalToInitialMap.put(FinalConsonant.ㅋ, InitialConsonant.ㅋ);
	finalToInitialMap.put(FinalConsonant.ㅌ, InitialConsonant.ㅌ);
	finalToInitialMap.put(FinalConsonant.ㅍ, InitialConsonant.ㅍ);
	finalToInitialMap.put(FinalConsonant.ㅎ, InitialConsonant.ㅎ);
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

    public static InitialConsonant convertFinalToInitial(FinalConsonant input) {
	InitialConsonant initialConsonant = finalToInitialMap.get(input);
	checkState(initialConsonant != null, "Unable to Convert FinalConsonant: " + input);

	return initialConsonant;
    }
}
