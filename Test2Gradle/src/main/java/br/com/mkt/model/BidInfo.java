package br.com.mkt.model;

public class BidInfo {
	
	private BidStatus status;
	private BidTite bid;
	
	public BidInfo(BidTite bid) {
		this.setStatus(BidStatus.UNSET);
		this.bid = bid;
	}
	
	
	public BidTite getBid() {
		return bid;
	}
	
	public void setBid(BidTite bid) {
		this.bid = bid;
	}


	public BidStatus getStatus() {
		return status;
	}


	public void setStatus(BidStatus status) {
		this.status = status;
	}

}
