package com.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	private Integer id;
	@Column(name="nome")	
	private String nome;
	@Column(name="sobrenome")
	private String sobrenome;
	@Column(name="email")
	private String email;
	@Column(name="login")
	private String login;
	@Column(name="senha")
	private String senha;
	@Column(name="keyauth")
	private String keyAuth;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getKeyAuth() {
		return keyAuth;
	}
	public void setKeyAuth(String keyAuth) {
		this.keyAuth = keyAuth;
	}

	
	
}
