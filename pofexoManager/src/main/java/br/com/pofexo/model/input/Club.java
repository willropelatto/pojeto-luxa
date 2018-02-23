package br.com.pofexo.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Club {

	private ColoredImage imageUrls;
	private String abbrName;
	private int id;
	private Object imgUrl;
	private String name;
	
	public ColoredImage getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(ColoredImage imageUrls) {
		this.imageUrls = imageUrls;
	}
	public String getAbbrName() {
		return abbrName;
	}
	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(Object imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	

}
