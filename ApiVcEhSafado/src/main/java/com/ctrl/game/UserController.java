package com.ctrl.game;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import com.model.dao.UserDAO;
import com.model.entity.UserEntity;
import com.model.out.User;
 
public class UserController {

	
	private final UserDAO userDAO = new UserDAO();
	
	public String Cadastrar(User user){
 
		UserEntity entity = new UserEntity();
 
		try {
 
			entity.setNome(user.getNome());
			entity.setSobrenome(user.getSobrenome());
			entity.setEmail(user.getEmail());
			entity.setLogin(user.getLogin());
			entity.setSenha(user.getSenha());
			
			userDAO.Save(entity);
 
			return "Registro cadastrado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
 
	}	
	
	public String Alterar(User user){
 
		UserEntity entity = new UserEntity();
 
		try {
			entity.setNome(user.getNome());
			entity.setSobrenome(user.getSobrenome());
			entity.setEmail(user.getEmail());
			entity.setLogin(user.getLogin());
			entity.setSenha(user.getSenha());
 
			userDAO.Update(entity);
 
			return "Registro alterado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao alterar o registro " + e.getMessage();
 
		}
 
	}

	public List<User> TodosUsuarios(){
 
		List<User> users =  new ArrayList<User>();
 
		List<UserEntity> listaEntityUsers = userDAO.TodosUsuarios();
 
		for (UserEntity entity : listaEntityUsers) {
 
			users.add(new User(entity.getId(), 
					           entity.getNome(),
					           entity.getSobrenome(),
					           entity.getEmail(),
					           entity.getLogin(),
					           entity.getSenha()));
		}
 
		return users;
	}
	
	public User GetUser(@PathParam("codigo") Integer codigo){
 
		UserEntity entity = userDAO.getUser(codigo);
 
		if(entity != null)
			return new User(entity.getId(), 
			           entity.getNome(),
			           entity.getSobrenome(),
			           entity.getEmail(),
			           entity.getLogin(),
			           entity.getSenha());
 
		return null;
	}
 
	public String Excluir(@PathParam("codigo") Integer codigo){
 
		try {
 
			userDAO.Delete(codigo);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}	
	
	public User login(User user) { 
		try{
			UserEntity entity = userDAO.getUserByLogin(user.getLogin());
			
			if (entity != null) {
				if (entity.getSenha().equals(user.getSenha())) {
					String keyAuth;
					try {
						keyAuth = TokenAuth.GerarToken(user.getLogin());
					} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
						
						return null; //return "Não foi possivel gerar o token: "+e.getMessage();
					}
					try {
						entity.setKeyAuth(keyAuth);	
						userDAO.Update(entity);
					} catch (Exception e) {
			 
						return null; //return "Não foi possivel gravar Token de autenticação: " + e.getMessage();
			 		}		
					user.setId(entity.getId());
					user.setKeyAuth(keyAuth);
					user.setNome(entity.getNome());
					user.setSobrenome(entity.getSobrenome());
					user.setEmail(entity.getEmail());
					user.setSenha("");
					return user;
					//return keyAuth;
		 
				} else {
					return null; //return "Senha inválida.";
				}
				
			} else {
				return null; 
			}
		}
		catch (Exception e) {
			return null;
		}
		
			
		
	}	
	
}
