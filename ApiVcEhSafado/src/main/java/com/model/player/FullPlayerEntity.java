package com.model.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.model.images.HeadShotImage;
import com.model.images.SpecialImage;


@Entity
@Table(name="tb_fullplayer")
public class FullPlayerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	//private String commonName;
	@Column(name="firstName")
	private String firstName;
	//private String headshotImgUrl;
	@Column(name="lastName")
	private String lastName;
	//private League league;
	//private Nation nation;
	//private Club club;
	//private HeadShotImage headshot;
	//private SpecialImage specialImages;
	@Column(name="position")
    private String position;
	@Column(name="playStyle")
    private String playStyle;
	@Column(name="playStyleId")
    private String playStyleId;
	@Column(name="height")
    private Integer height;
	@Column(name="weight")
    private Integer weight;
	@Column(name="birthdate")
    private String birthdate;
	@Column(name="age")
    private Integer age;
	@Column(name="acceleration")
    private Integer acceleration;
	@Column(name="aggression")
    private Integer aggression;
	@Column(name="agility")
    private Integer agility;
	@Column(name="balance")
    private Integer balance;
	@Column(name="ballcontrol")
    private Integer ballcontrol;
	@Column(name="foot")
    private String foot;
	@Column(name="skillMoves")
    private Integer skillMoves;
	@Column(name="crossing")
    private Integer crossing;
	@Column(name="curve")
    private Integer curve;
	@Column(name="dribbling")
    private Integer dribbling;
	@Column(name="finishing")
    private Integer finishing;
	@Column(name="freekickaccuracy")
    private Integer freekickaccuracy;
	@Column(name="gkdiving")
    private Integer gkdiving;
	@Column(name="gkhandling")
    private Integer gkhandling;
	@Column(name="gkkicking")
    private Integer gkkicking;
	@Column(name="gkpositioning")
    private Integer gkpositioning;
	@Column(name="gkreflexes")
    private Integer gkreflexes;
	@Column(name="headingaccuracy")
    private Integer headingaccuracy;
	@Column(name="interceptions")
    private Integer interceptions;
	@Column(name="jumping")
    private Integer jumping;
	@Column(name="longpassing")
    private Integer longpassing;
	@Column(name="longshots")
    private Integer longshots;
	@Column(name="marking")
    private Integer marking;
	@Column(name="penalties")
    private Integer penalties;
	@Column(name="positioning")
    private Integer positioning;
    @Column(name="potential")
    private Integer potential;
    @Column(name="reactions")
    private Integer reactions;
    @Column(name="shortpassing")
    private Integer shortpassing;
    @Column(name="shotpower")
    private Integer shotpower;
    @Column(name="slidingtackle")
    private Integer slidingtackle;
    @Column(name="sprintspeed")
    private Integer sprintspeed;
    @Column(name="standingtackle")
    private Integer standingtackle;
    @Column(name="stamina")
    private Integer stamina;
    @Column(name="strength")
    private Integer strength;
    @Column(name="vision")
    private Integer vision;
    @Column(name="volleys")
    private Integer volleys;
    @Column(name="weakFoot")
    private Integer weakFoot;
    //private String[] traits;
    //private String[] specialities;
    @Column(name="atkWorkRate")
    private String atkWorkRate;
    @Column(name="defWorkRate")
    private String defWorkRate;
    @Column(name="playerType")
    private String playerType;
    //private Attributes[] attributes;
    @Column(name="name")
    private String name;
    @Column(name="quality")
    private String quality;
    @Column(name="color")
    private String color;
    @Column(name="isGK")
    private Boolean isGK;
    @Column(name="positionFull")
    private String positionFull;
    @Column(name="isSpecialType")
    private Boolean isSpecialType;
    @Column(name="contracts")
    private String contracts;
    @Column(name="fitness")
    private String fitness;
    @Column(name="rawAttributeChemistryBonus")
    private String rawAttributeChemistryBonus;
    @Column(name="isLoan")
    private String isLoan;
    @Column(name="squadPosition")
    private String squadPosition;
    @Column(name="itemType")
    private String itemType;
    @Column(name="discardValue")
    private String discardValue;
    //private String id;
    @Column(name="modelName")
    private String modelName;
    @Column(name="baseId")
    private Integer baseId;
    @Column(name="rating")
    private Integer rating;
	
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPlayStyle() {
		return playStyle;
	}
	public void setPlayStyle(String playStyle) {
		this.playStyle = playStyle;
	}
	public String getPlayStyleId() {
		return playStyleId;
	}
	public void setPlayStyleId(String playStyleId) {
		this.playStyleId = playStyleId;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Integer acceleration) {
		this.acceleration = acceleration;
	}
	public Integer getAggression() {
		return aggression;
	}
	public void setAggression(Integer aggression) {
		this.aggression = aggression;
	}
	public Integer getAgility() {
		return agility;
	}
	public void setAgility(Integer agility) {
		this.agility = agility;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getBallcontrol() {
		return ballcontrol;
	}
	public void setBallcontrol(Integer ballcontrol) {
		this.ballcontrol = ballcontrol;
	}
	public String getFoot() {
		return foot;
	}
	public void setFoot(String foot) {
		this.foot = foot;
	}
	public Integer getSkillMoves() {
		return skillMoves;
	}
	public void setSkillMoves(Integer skillMoves) {
		this.skillMoves = skillMoves;
	}
	public Integer getCrossing() {
		return crossing;
	}
	public void setCrossing(Integer crossing) {
		this.crossing = crossing;
	}
	public Integer getCurve() {
		return curve;
	}
	public void setCurve(Integer curve) {
		this.curve = curve;
	}
	public Integer getDribbling() {
		return dribbling;
	}
	public void setDribbling(Integer dribbling) {
		this.dribbling = dribbling;
	}
	public Integer getFinishing() {
		return finishing;
	}
	public void setFinishing(Integer finishing) {
		this.finishing = finishing;
	}
	public Integer getFreekickaccuracy() {
		return freekickaccuracy;
	}
	public void setFreekickaccuracy(Integer freekickaccuracy) {
		this.freekickaccuracy = freekickaccuracy;
	}
	public Integer getGkdiving() {
		return gkdiving;
	}
	public void setGkdiving(Integer gkdiving) {
		this.gkdiving = gkdiving;
	}
	public Integer getGkhandling() {
		return gkhandling;
	}
	public void setGkhandling(Integer gkhandling) {
		this.gkhandling = gkhandling;
	}
	public Integer getGkkicking() {
		return gkkicking;
	}
	public void setGkkicking(Integer gkkicking) {
		this.gkkicking = gkkicking;
	}
	public Integer getGkpositioning() {
		return gkpositioning;
	}
	public void setGkpositioning(Integer gkpositioning) {
		this.gkpositioning = gkpositioning;
	}
	public Integer getGkreflexes() {
		return gkreflexes;
	}
	public void setGkreflexes(Integer gkreflexes) {
		this.gkreflexes = gkreflexes;
	}
	public Integer getHeadingaccuracy() {
		return headingaccuracy;
	}
	public void setHeadingaccuracy(Integer headingaccuracy) {
		this.headingaccuracy = headingaccuracy;
	}
	public Integer getInterceptions() {
		return interceptions;
	}
	public void setInterceptions(Integer interceptions) {
		this.interceptions = interceptions;
	}
	public Integer getJumping() {
		return jumping;
	}
	public void setJumping(Integer jumping) {
		this.jumping = jumping;
	}
	public Integer getLongpassing() {
		return longpassing;
	}
	public void setLongpassing(Integer longpassing) {
		this.longpassing = longpassing;
	}
	public Integer getLongshots() {
		return longshots;
	}
	public void setLongshots(Integer longshots) {
		this.longshots = longshots;
	}
	public Integer getMarking() {
		return marking;
	}
	public void setMarking(Integer marking) {
		this.marking = marking;
	}
	public Integer getPenalties() {
		return penalties;
	}
	public void setPenalties(Integer penalties) {
		this.penalties = penalties;
	}
	public Integer getPositioning() {
		return positioning;
	}
	public void setPositioning(Integer positioning) {
		this.positioning = positioning;
	}
	public Integer getPotential() {
		return potential;
	}
	public void setPotential(Integer potential) {
		this.potential = potential;
	}
	public Integer getReactions() {
		return reactions;
	}
	public void setReactions(Integer reactions) {
		this.reactions = reactions;
	}
	public Integer getShortpassing() {
		return shortpassing;
	}
	public void setShortpassing(Integer shortpassing) {
		this.shortpassing = shortpassing;
	}
	public Integer getShotpower() {
		return shotpower;
	}
	public void setShotpower(Integer shotpower) {
		this.shotpower = shotpower;
	}
	public Integer getSlidingtackle() {
		return slidingtackle;
	}
	public void setSlidingtackle(Integer slidingtackle) {
		this.slidingtackle = slidingtackle;
	}
	public Integer getSprintspeed() {
		return sprintspeed;
	}
	public void setSprintspeed(Integer sprintspeed) {
		this.sprintspeed = sprintspeed;
	}
	public Integer getStandingtackle() {
		return standingtackle;
	}
	public void setStandingtackle(Integer standingtackle) {
		this.standingtackle = standingtackle;
	}
	public Integer getStamina() {
		return stamina;
	}
	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}
	public Integer getStrength() {
		return strength;
	}
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	public Integer getVision() {
		return vision;
	}
	public void setVision(Integer vision) {
		this.vision = vision;
	}
	public Integer getVolleys() {
		return volleys;
	}
	public void setVolleys(Integer volleys) {
		this.volleys = volleys;
	}
	public Integer getWeakFoot() {
		return weakFoot;
	}
	public void setWeakFoot(Integer weakFoot) {
		this.weakFoot = weakFoot;
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
	public String getPlayerType() {
		return playerType;
	}
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Boolean getIsGK() {
		return isGK;
	}
	public void setIsGK(Boolean isGK) {
		this.isGK = isGK;
	}
	public String getPositionFull() {
		return positionFull;
	}
	public void setPositionFull(String positionFull) {
		this.positionFull = positionFull;
	}
	public Boolean getIsSpecialType() {
		return isSpecialType;
	}
	public void setIsSpecialType(Boolean isSpecialType) {
		this.isSpecialType = isSpecialType;
	}
	public String getContracts() {
		return contracts;
	}
	public void setContracts(String contracts) {
		this.contracts = contracts;
	}
	public String getFitness() {
		return fitness;
	}
	public void setFitness(String fitness) {
		this.fitness = fitness;
	}
	public String getRawAttributeChemistryBonus() {
		return rawAttributeChemistryBonus;
	}
	public void setRawAttributeChemistryBonus(String rawAttributeChemistryBonus) {
		this.rawAttributeChemistryBonus = rawAttributeChemistryBonus;
	}
	public String getIsLoan() {
		return isLoan;
	}
	public void setIsLoan(String isLoan) {
		this.isLoan = isLoan;
	}
	public String getSquadPosition() {
		return squadPosition;
	}
	public void setSquadPosition(String squadPosition) {
		this.squadPosition = squadPosition;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getDiscardValue() {
		return discardValue;
	}
	public void setDiscardValue(String discardValue) {
		this.discardValue = discardValue;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
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
    
    
    

}
