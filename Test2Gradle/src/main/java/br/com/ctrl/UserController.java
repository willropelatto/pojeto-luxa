package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.UserAuthToken;
import br.com.model.UserDetail;
import br.com.model.UserDetailRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDetailRepository userDao;	

	@CrossOrigin
	@GetMapping("/get/{code}")	
	public UserDetail GetUser(@PathVariable("code") Integer codigo) {
		return userDao.findOne(codigo);		
	}

	@CrossOrigin  
	@PostMapping("/register")
	public UserDetail Register(@RequestBody UserDetail user) {		
		return userDao.save(user);		
	}	

	@CrossOrigin
	@PostMapping("/update")
	public UserDetail Update(UserDetail user){			
		return userDao.save(user);		
	}

	@CrossOrigin    	
	@PostMapping("/login")	
	public UserDetail login(@RequestBody UserDetail user) {
		try	{
			UserDetail entity = userDao.findOneByLoginAllIgnoringCase(user.getLogin());

			if (entity != null) {
				
				if (entity.getSenha().equals(user.getSenha())) {
					String keyAuth = UserAuthToken.GerarToken(user.getLogin());
					entity.setKeyAuth(keyAuth);	
					entity = userDao.save(entity);
					entity.setSenha("");
					return entity;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}