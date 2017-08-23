package com.model.out;

import java.util.List;

public class PlayerList {

	private int page;
	private int totalPages;
	private int count;
	private List<ResumedPlayer> players;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<ResumedPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<ResumedPlayer> players) {
		this.players = players;
	}

	
}
