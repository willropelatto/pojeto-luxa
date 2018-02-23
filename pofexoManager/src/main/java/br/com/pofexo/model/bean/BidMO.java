package br.com.pofexo.model.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import br.com.pofexo.model.misc.BidStatus;

@Entity
@Table(name = "Bids")
public class BidMO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double bidValue;
	private double originalValue;
	private int team;
	
	private BidStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBidValue() {
		return bidValue;
	}

	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}

	public double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(double originalValue) {
		this.originalValue = originalValue;
	}

	public BidStatus getStatus() {
		return status;
	}
	
	public void setStatus(BidStatus status) {
		this.status = status;
	}
	
	@Transient
	public double getNextValue() {
		return (this.originalValue * 0.05) + this.bidValue;
	}

	@Transient
	public void setNextValue(double nextValue) {		
		//do nothing
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

}
