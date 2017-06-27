package de.snuk.hangeulj;

public enum Initial {

    G(0), GG(1);

    private int jamo;

    private Initial(int jamo) {
	this.jamo = jamo;
    }

    public int getJamo() {
	return jamo;
    }
}
