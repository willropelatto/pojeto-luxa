package br.com.model.misc;

import br.com.model.bean.BidMO;

public class BidInfo {

	private BidStatus status;
	private BidMO bid;

	public BidInfo(BidMO bid) {
		this.setStatus(BidStatus.UNSET);
		this.bid = bid;
	}

	public BidMO getBid() {
		return bid;
	}

	public void setBid(BidMO bid) {
		this.bid = bid;
	}

	public BidStatus getStatus() {
		return status;
	}

	public void setStatus(BidStatus status) {
		this.status = status;
	}

}
