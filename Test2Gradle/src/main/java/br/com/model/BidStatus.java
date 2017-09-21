package br.com.model;

public enum BidStatus {
	UNSET(0),
	APROVED(1),
	NO_MONEY(2),
	RESYNC(3),
	MARKET_CLOSE(4);	

    public int status;
    BidStatus(int status) {
        this.status = status;
    }

}
