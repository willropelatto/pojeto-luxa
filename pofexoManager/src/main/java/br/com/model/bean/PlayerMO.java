package br.com.model.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.model.misc.BidInfoFactory;
import br.com.model.misc.PlayerStatus;

@Entity
@Table(name = "Players")
public class PlayerMO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String position;
	private String name;
	private Integer baseId;
	private Integer rating;
	private String originalId;
	private String clubName;
	private int height;
	private int weight;
	private int age;
	private String foot;
	private String atkWorkRate;
	private String defWorkRate;
	private String headshotImgUrl;
	private PlayerStatus status;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "bid_id")
	private BidMO bid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "league_id")
	@JsonBackReference(value = "leagueplayer-ref")
	private LeagueMO league;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	@JsonBackReference(value = "team-ref")
	private TeamMO team;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "player-ref")
	private Set<PlayerAttributeAssociationMO> attributes;

	public PlayerMO() {
		super();
		this.attributes = new HashSet<>();
	}

	public int getId() {
		return id;		
	}

	public void setId(int id) {
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

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public Set<PlayerAttributeAssociationMO> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<PlayerAttributeAssociationMO> attributes) {
		this.attributes = attributes;
	}

	public TeamMO getTeam() {
		return team;
	}

	public void setTeam(TeamMO team) {
		this.team = team;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}

	public String getAtkWorkRate() {
		return atkWorkRate;
	}

	public void setAtkWorkRate(String atkWorkRate) {
		this.atkWorkRate = atkWorkRate;
	}

	public String getDefWorkRate() {
		return defWorkRate;
	}

	public void setDefWorkRate(String defWorkRate) {
		this.defWorkRate = defWorkRate;
	}

	public String getHeadshotImgUrl() {
		return headshotImgUrl;
	}

	public void setHeadshotImgUrl(String headshotImgUrl) {
		this.headshotImgUrl = headshotImgUrl;
	}

	public LeagueMO getLeague() {
		return league;
	}

	public void setLeague(LeagueMO league) {
		this.league = league;
	}

	public BidMO getBid() {
		if (bid == null) 
			bid = BidInfoFactory.newBid(this);
		
		return bid;
	}

	public void setBid(BidMO bid) {
		this.bid = bid;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
}
