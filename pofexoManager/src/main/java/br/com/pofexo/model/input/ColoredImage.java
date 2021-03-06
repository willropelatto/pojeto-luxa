package br.com.pofexo.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColoredImage {

	private SizedImage dark;
	private SizedImage normal;

	public SizedImage getDark() {
		return dark;
	}
	public void setDark(SizedImage dark) {
		this.dark = dark;
	}
	public SizedImage getNormal() {
		return normal;
	}
	public void setNormal(SizedImage normal) {
		this.normal = normal;
	}

}
