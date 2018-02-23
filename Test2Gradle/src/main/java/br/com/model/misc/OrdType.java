package br.com.model.misc;

public enum OrdType {

	NAME(0),
	POSITION(1),
	RATING(2);
	
    public int type;
    OrdType(int type) {
        this.type = type;
    }
}
