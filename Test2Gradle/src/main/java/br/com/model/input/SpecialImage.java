package br.com.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialImage {

	private String largeTOTWImgUrl;
	private String medTOTWImgUrl;

	public String getLargeTOTWImgUrl() {
		return largeTOTWImgUrl;
	}
	public void setLargeTOTWImgUrl(String largeTOTWImgUrl) {
		this.largeTOTWImgUrl = largeTOTWImgUrl;
	}
	public String getMedTOTWImgUrl() {
		return medTOTWImgUrl;
	}
	public void setMedTOTWImgUrl(String medTOTWImgUrl) {
		this.medTOTWImgUrl = medTOTWImgUrl;
	}

}
