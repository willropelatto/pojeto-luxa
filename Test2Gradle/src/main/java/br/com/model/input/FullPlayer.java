package br.com.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullPlayer {

	@Override
	public String toString() {
		return "Jogador: " + name + " tipo: " + playerType + " cor: " + color;
	}
	
	private String commonName;
	private String firstName;
	private String headshotImgUrl;
	private String lastName;
	private League league;
	private Nation nation;
	private Club club;
	private HeadShotImage headshot;
	private SpecialImage specialImages;
    private String position;
    private String playStyle;
    private String playStyleId;
    private int height;
    private int weight;
    private String birthdate;
    private int age;
    private int acceleration;
    private int aggression;
    private int agility;
    private int balance;
    private int ballcontrol;
    private String foot;
    private int skillMoves;
    private int crossing;
    private int curve;
    private int dribbling;
    private int finishing;
    private int freekickaccuracy;
    private int gkdiving;
    private int gkhandling;
    private int gkkicking;
    private int gkpositioning;
    private int gkreflexes;
    private int headingaccuracy;
    private int interceptions;
    private int jumping;
    private int longpassing;
    private int longshots;
    private int marking;
    private int penalties;
    private int positioning;
    private int potential;
    private int reactions;
    private int shortpassing;
    private int shotpower;
    private int slidingtackle;
    private int sprintspeed;
    private int standingtackle;
    private int stamina;
    private int strength;
    private int vision;
    private int volleys;
    private int weakFoot;
    private String[] traits;
    private String[] specialities;
    private String atkWorkRate;
    private String defWorkRate;
    private String playerType;
    private Attributes[] attributes; 
    private String name;
    private String quality;
    private String color;
    private Boolean isGK;
    private String positionFull;
    private Boolean isSpecialType;
    private String contracts;
    private String fitness;
    private String rawAttributeChemistryBonus;
    private String isLoan;
    private String squadPosition;
    private String itemType;
    private String discardValue;
    private String id;
    private String modelName;
    private int baseId;
    private int rating;
    
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getHeadshotImgUrl() {
		return headshotImgUrl;
	}
	public void setHeadshotImgUrl(String headshotImgUrl) {
		this.headshotImgUrl = headshotImgUrl;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}
	public Nation getNation() {
		return nation;
	}
	public void setNation(Nation nation) {
		this.nation = nation;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public HeadShotImage getHeadshot() {
		return headshot;
	}
	public void setHeadshot(HeadShotImage headshot) {
		this.headshot = headshot;
	}
	public SpecialImage getSpecialImages() {
		return specialImages;
	}
	public void setSpecialImages(SpecialImage specialImages) {
		this.specialImages = specialImages;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}
	public int getAggression() {
		return aggression;
	}
	public void setAggression(int aggression) {
		this.aggression = aggression;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBallcontrol() {
		return ballcontrol;
	}
	public void setBallcontrol(int ballcontrol) {
		this.ballcontrol = ballcontrol;
	}
	public String getFoot() {
		return foot;
	}
	public void setFoot(String foot) {
		this.foot = foot;
	}
	public int getSkillMoves() {
		return skillMoves;
	}
	public void setSkillMoves(int skillMoves) {
		this.skillMoves = skillMoves;
	}
	public int getCrossing() {
		return crossing;
	}
	public void setCrossing(int crossing) {
		this.crossing = crossing;
	}
	public int getCurve() {
		return curve;
	}
	public void setCurve(int curve) {
		this.curve = curve;
	}
	public int getDribbling() {
		return dribbling;
	}
	public void setDribbling(int dribbling) {
		this.dribbling = dribbling;
	}
	public int getFinishing() {
		return finishing;
	}
	public void setFinishing(int finishing) {
		this.finishing = finishing;
	}
	public int getFreekickaccuracy() {
		return freekickaccuracy;
	}
	public void setFreekickaccuracy(int freekickaccuracy) {
		this.freekickaccuracy = freekickaccuracy;
	}
	public int getGkdiving() {
		return gkdiving;
	}
	public void setGkdiving(int gkdiving) {
		this.gkdiving = gkdiving;
	}
	public int getGkhandling() {
		return gkhandling;
	}
	public void setGkhandling(int gkhandling) {
		this.gkhandling = gkhandling;
	}
	public int getGkkicking() {
		return gkkicking;
	}
	public void setGkkicking(int gkkicking) {
		this.gkkicking = gkkicking;
	}
	public int getGkpositioning() {
		return gkpositioning;
	}
	public void setGkpositioning(int gkpositioning) {
		this.gkpositioning = gkpositioning;
	}
	public int getGkreflexes() {
		return gkreflexes;
	}
	public void setGkreflexes(int gkreflexes) {
		this.gkreflexes = gkreflexes;
	}
	public int getHeadingaccuracy() {
		return headingaccuracy;
	}
	public void setHeadingaccuracy(int headingaccuracy) {
		this.headingaccuracy = headingaccuracy;
	}
	public int getInterceptions() {
		return interceptions;
	}
	public void setInterceptions(int interceptions) {
		this.interceptions = interceptions;
	}
	public int getJumping() {
		return jumping;
	}
	public void setJumping(int jumping) {
		this.jumping = jumping;
	}
	public int getLongpassing() {
		return longpassing;
	}
	public void setLongpassing(int longpassing) {
		this.longpassing = longpassing;
	}
	public int getLongshots() {
		return longshots;
	}
	public void setLongshots(int longshots) {
		this.longshots = longshots;
	}
	public int getMarking() {
		return marking;
	}
	public void setMarking(int marking) {
		this.marking = marking;
	}
	public int getPenalties() {
		return penalties;
	}
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	public int getPositioning() {
		return positioning;
	}
	public void setPositioning(int positioning) {
		this.positioning = positioning;
	}
	public int getPotential() {
		return potential;
	}
	public void setPotential(int potential) {
		this.potential = potential;
	}
	public int getReactions() {
		return reactions;
	}
	public void setReactions(int reactions) {
		this.reactions = reactions;
	}
	public int getShortpassing() {
		return shortpassing;
	}
	public void setShortpassing(int shortpassing) {
		this.shortpassing = shortpassing;
	}
	public int getShotpower() {
		return shotpower;
	}
	public void setShotpower(int shotpower) {
		this.shotpower = shotpower;
	}
	public int getSlidingtackle() {
		return slidingtackle;
	}
	public void setSlidingtackle(int slidingtackle) {
		this.slidingtackle = slidingtackle;
	}
	public int getSprintspeed() {
		return sprintspeed;
	}
	public void setSprintspeed(int sprintspeed) {
		this.sprintspeed = sprintspeed;
	}
	public int getStandingtackle() {
		return standingtackle;
	}
	public void setStandingtackle(int standingtackle) {
		this.standingtackle = standingtackle;
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getVision() {
		return vision;
	}
	public void setVision(int vision) {
		this.vision = vision;
	}
	public int getVolleys() {
		return volleys;
	}
	public void setVolleys(int volleys) {
		this.volleys = volleys;
	}
	public int getWeakFoot() {
		return weakFoot;
	}
	public void setWeakFoot(int weakFoot) {
		this.weakFoot = weakFoot;
	}
	public String[] getTraits() {
		return traits;
	}
	public void setTraits(String[] traits) {
		this.traits = traits;
	}
	public String[] getSpecialities() {
		return specialities;
	}
	public void setSpecialities(String[] specialities) {
		this.specialities = specialities;
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
	public Attributes[] getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes[] attributes) {
		this.attributes = attributes;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getBaseId() {
		return baseId;
	}
	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}	
    
    
}