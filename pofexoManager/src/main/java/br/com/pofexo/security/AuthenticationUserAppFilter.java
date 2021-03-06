package br.com.pofexo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pofexo.model.bean.UserAppMO;
import br.com.pofexo.security.AuthConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationUserAppFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationUserAppFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {			
			UserAppMO creds = new ObjectMapper().readValue(req.getInputStream(), UserAppMO.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + AuthConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, AuthConstants.SECRET.getBytes()).compact();
		
		
		res.getWriter().write("{\"token\":\"" + token + "\"}");
				
				//"{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
		          // "{\"name\":\"Jackie\",\"country\":\"China\"}]}");
		res.addHeader(AuthConstants.HEADER_STRING, AuthConstants.TOKEN_PREFIX + token);
		
	}

}
