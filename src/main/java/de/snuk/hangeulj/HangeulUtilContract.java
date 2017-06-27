package de.snuk.hangeulj;

public class HangeulUtilContract {

	private static final String minI = "1100";
	private static final String maxI = "1112";
	private static final String minM = "1161";
	private static final String maxM = "1175";
	private static final String minF = "11a8";
	private static final String maxF = "11c2";

	public static void isNotNull(final Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
	}

	public static void isValueBetween(final int value, final int min, final int max, final String paramName) {
		if (value < min || value > max) {
			throw new IllegalArgumentException(paramName + " should be between " + min + " and " + max);
		}
	}

	public static boolean validateToJamoInput(final String hexString) {
		if (minI.compareTo(hexString) <= 0 && maxI.compareTo(hexString) >= 0) {
			return true;
		}

		if (minM.compareTo(hexString) <= 0 && maxM.compareTo(hexString) >= 0) {
			return true;
		}

		if (minF.compareTo(hexString) <= 0 && maxF.compareTo(hexString) >= 0) {
			return true;
		}

		return false;
	}
}
