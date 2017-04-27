package com.model.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.model.team.TeamPlayerEntity;

@Entity
@Table(name="tb_team")
public class TeamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="teamid")
	private Integer id;
	@Column(name="nome")
	private String nome;
	
	@OneToMany(mappedBy = "teamEntity", cascade = CascadeType.ALL)
	private Set<TeamPlayer> teamPlayer;	
}
