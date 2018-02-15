package br.com.model.misc;

public enum PlayerStatus {
	
	AVAIBLE(0),
	UNAVAIBLE(1),	
	ON_BID(2),
	CONTRACT(3);	

    public int status;
    PlayerStatus(int status) {
        this.status = status;
    }

}
