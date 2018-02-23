package br.com.pofexo.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {

	private String name;
	private int value;
	private int[] chemistryBonus;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int[] getChemistryBonus() {
		return chemistryBonus;
	}
	public void setChemistryBonus(int[] chemistryBonus) {
		this.chemistryBonus = chemistryBonus;
	}
}
