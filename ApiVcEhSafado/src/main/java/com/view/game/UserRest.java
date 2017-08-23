package com.view.game;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
import com.ctrl.game.UserController;
import com.model.out.User;


@Path("/user")
public class UserRest {


	private final UserController ctrl = new UserController();


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
	public User Cadastrar(User user) {

		ctrl.Cadastrar(user);
		
		return user;
	}	

	/**
	 * Essse método altera um usuario já cadastrado
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String Alterar(User user){

		return ctrl.Alterar(user);
	}

	/**
	 * Esse método lista todos usuarios cadastrados na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todosUsuarios")
	public List<User> TodosUsuarios(){

		return ctrl.TodosUsuarios();
	}

	/**
	 * Esse método busca um usuario cadastrado pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getUsuario/{codigo}")
	public User GetUser(@PathParam("codigo") Integer codigo){

		return ctrl.GetUser(codigo);
	}

	/**
	 * Excluindo um usuario pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String Excluir(@PathParam("codigo") Integer codigo){

		return ctrl.Excluir(codigo);
	}	


	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/login")
	public User login(User user) {
		
		
		//user.setId(1);
		return ctrl.login(user);
		
		
		//return user;
	}	

}
