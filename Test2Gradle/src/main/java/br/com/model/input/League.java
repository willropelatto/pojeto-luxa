package br.com.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.model.bean.LeagueMO;
@JsonIgnoreProperties(ignoreUnknown = true)
public class League {

    private String abbrName;
    private int id;
    private String imgUrl;
    private String name;
    
    @JsonIgnore
    private LeagueMO league;
    
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
	public LeagueMO getLeague() {
		return league;
	}
	public void setLeague(LeagueMO league) {
		this.league = league;
	}

    
}
