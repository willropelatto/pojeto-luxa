package br.com.player.model;

public class PlayerFilter {

	private String name;
	private String position;
	private int rating;
	private double endValue;
	private double startValue;
	private	String league;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public double getEndValue() {
		return endValue;
	}
	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}
	public double getStartValue() {
		return startValue;
	}
	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}	
	
}
