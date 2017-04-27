package com.ctrl.game;

import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.model.user.UserDAO;
import com.model.user.UserEntity;
import com.view.user.User;


@Path("/user")
public class UserController {

	
	private final UserDAO userDAO = new UserDAO();
	
	
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra um novo usuário
	 * */
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
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
	
	/**
	 * Essse método altera um usuario já cadastrado
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
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
	
	/**
	 * Esse método lista todos usuarios cadastrados na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todosUsuarios")
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
	
	/**
	 * Esse método busca um usuario cadastrado pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getUsuario/{codigo}")
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
 
	/**
	 * Excluindo um usuario pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String Excluir(@PathParam("codigo") Integer codigo){
 
		try {
 
			userDAO.Delete(codigo);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}	
	
}
