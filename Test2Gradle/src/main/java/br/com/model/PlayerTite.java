package br.com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class PlayerTite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "position")
	private String position;
	@Column(name = "name")
	private String name;
	@Column(name = "baseId")
	private Integer baseId;
	@Column(name = "rating")
	private Integer rating;
	@Column(name = "idLeague")
	private int idLeague; // LeagueTite.getOriginalId
	@Column(name = "originalId")
	private String originalId;
	@Column(name = "hasBid", columnDefinition = "boolean default false", nullable = false)
	private boolean hasBid;
	@Column(name = "clubName")
	private String clubName;	
	@Column(name = "height")
	private int height;	
	@Column(name = "weight")
	private int weight;	
	@Column(name = "age")
	private int age;	
	@Column(name = "foot")
	private String foot;	
	@Column(name = "atkWorkRate")
	private String atkWorkRate;	
	@Column(name = "defWorkRate")
	private String defWorkRate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	@JsonBackReference(value="team-ref")
	private TeamTite team;	
	
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="player-ref")
	private Set<PlayerAttributeAssociation> attributes;

	public PlayerTite() {
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

	public Set<PlayerAttributeAssociation> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<PlayerAttributeAssociation> attributes) {
		this.attributes = attributes;
	}

	public TeamTite getTeam() {
		return team;
	}

	public void setTeam(TeamTite team) {
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

	public void setIdLeague(int idLeague) {
		this.idLeague = idLeague;
	}	
}
