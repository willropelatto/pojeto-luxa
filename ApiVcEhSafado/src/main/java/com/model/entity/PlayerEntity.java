package com.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_player")
public class PlayerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;	
	@Column(name="position")
	private String position;
	@Column(name="name")
    private String name;
	@Column(name="baseId")
    private Integer baseId;
	@Column(name="rating")
    private Integer rating;
	@Column(name="idLeague")
	private Integer idLeague;
	@Column(name="originalId")
	private String originalId;
	@Column(name = "hasBid", columnDefinition = "boolean default false", nullable = false)
	private boolean hasBid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBaseId() {
		return baseId;
	}
	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getIdLeague() {
		return idLeague;
	}
	public void setIdLeague(Integer idLeague) {
		this.idLeague = idLeague;
	}
	
	public String getOriginalId() {
		return originalId;
	}
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	public boolean isHasBid() {
		return hasBid;
	}
	public void setHasBid(boolean hasBid) {
		this.hasBid = hasBid;
	}
	
	
	
	
	/*
 CREATE TABLE `tb_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(45) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `baseid` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `idleague` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

  */
}
