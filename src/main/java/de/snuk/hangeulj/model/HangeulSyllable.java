package de.snuk.hangeulj.model;

import static com.google.common.base.Preconditions.checkNotNull;

import de.snuk.hangeulj.HangeulUtil;

public class HangeulSyllable {

	private InitialConsonant initial;
	private MedialVowel medial;
	private FinalConsonant finall;

	private HangeulSyllable(InitialConsonant initial, MedialVowel medial, FinalConsonant finall) {
		this.initial = initial;
		this.medial = medial;
		this.finall = finall;
	}

	public static HangeulSyllable of(char input) {
		String normalize = HangeulUtil.normalize(input);

		int jamoInit = HangeulUtil.toJamo(normalize.charAt(0));
		int jamoMedial = HangeulUtil.toJamo(normalize.charAt(1));

		if (normalize.length() == 2) {
			return new HangeulSyllable(InitialConsonant.ofJamo(jamoInit), MedialVowel.ofJamo(jamoMedial),
					FinalConsonant.EMPTY);
		}

		int jamoFinal = HangeulUtil.toJamo(normalize.charAt(2));

		return new HangeulSyllable(InitialConsonant.ofJamo(jamoInit), MedialVowel.ofJamo(jamoMedial),
				FinalConsonant.ofJamo(jamoFinal));
	}

	public InitialConsonant getInitial() {
		return initial;
	}

	public MedialVowel getMedial() {
		return medial;
	}

	public FinalConsonant getFinal() {
		return finall;
	}

	public HangeulSyllable replaceInitial(InitialConsonant newInitial) {
		checkNotNull(newInitial, "Initial consosant can't be replaced by null.");
		return new HangeulSyllable(newInitial, medial, finall);
	}

	public HangeulSyllable replaceFinal(FinalConsonant newFinal) {
		checkNotNull(newFinal, "Final consosant can't be replaced by null. Consider using FinalConsonant.EMPTY");
		return new HangeulSyllable(initial, medial, newFinal);
	}

	public char toChar() {
		return HangeulUtil.composeSyllable(initial.getJamo(), medial.getJamo(), finall != null ? finall.getJamo() : 0);
	}

	@Override
	public String toString() {
		return "HangeulSyllable [initial=" + initial + ", medial=" + medial + ", finall=" + finall + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (finall == null ? 0 : finall.hashCode());
		result = prime * result + (initial == null ? 0 : initial.hashCode());
		result = prime * result + (medial == null ? 0 : medial.hashCode());
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
		HangeulSyllable other = (HangeulSyllable) obj;
		if (finall != other.finall) {
			return false;
		}
		if (initial != other.initial) {
			return false;
		}
		if (medial != other.medial) {
			return false;
		}
		return true;
	}
}
