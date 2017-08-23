package com.model.in;

public class League {

    private String abbrName;
    private int id;
    private String imgUrl;
    private String name;
    
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof League)) {
	        return false;
	    }
	    final League other = (League) obj;
	    return this.getId() == other.getId();
	}

    
}
